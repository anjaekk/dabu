package b172.challenging.gathering.service;

import b172.challenging.common.exception.CustomRuntimeException;
import b172.challenging.common.exception.Exceptions;
import b172.challenging.gathering.domain.Gathering;
import b172.challenging.gathering.domain.GatheringMember;
import b172.challenging.gathering.domain.GatheringSavingCertification;
import b172.challenging.gathering.domain.GatheringSavingLog;
import b172.challenging.gathering.dto.response.GatheringSavingLogCertificateResponseDto;
import b172.challenging.gathering.dto.response.GatheringSavingLogResponseDto;
import b172.challenging.gathering.dto.request.GatheringSavingLogRequestDto;
import b172.challenging.gathering.repository.GatheringMemberRepository;
import b172.challenging.gathering.repository.GatheringRepository;
import b172.challenging.gathering.repository.GatheringSavingCertificationRepository;
import b172.challenging.gathering.repository.GatheringSavingLogRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import b172.challenging.wallet.domain.Wallet;
import b172.challenging.wallet.repository.WalletRepository;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class GatheringSavingLogService {
    private final GatheringSavingCertificationRepository gatheringSavingCertificationRepository;
    private final GatheringSavingLogRepository gatheringSavingLogRepository;
    private final GatheringRepository gatheringRepository;
    private final AmazonS3Client amazonS3Client;

    private final WalletRepository walletRepository;
    private final GatheringMemberRepository gatheringMemberRepository;

    @Value("${cdn.path.base}")
    private String cdnPathBase;

    @Value("${cdn.url}")
    private String cdnUrl;

    @Value("${cloud.aws.s3.bucket}")
    private String s3Bucket;

    public GatheringSavingLogResponseDto findGatheringSavingLog(Long savingLogId){
        Gathering gathering = gatheringRepository.findById(savingLogId)
                .orElseThrow(() -> new CustomRuntimeException(Exceptions.NOT_FOUND_GATHERING));
        List<GatheringSavingLog> gatheringSavingLogs = gatheringSavingLogRepository
                        .findAllByGatheringMember_Gathering(gathering);

        if(gatheringSavingLogs.isEmpty()){
            throw new CustomRuntimeException(Exceptions.NOT_FOUND_GATHERING_SAVING_LOG);
        }

        List<GatheringMember> gatheringMembers =
                gatheringSavingLogs.stream()
                        .map(GatheringSavingLog::getGatheringMember)
                        .toList();

        return GatheringSavingLogResponseDto.builder()
                .gatheringMemberList(gatheringMembers)
                .build();
    }

    public GatheringSavingLogCertificateResponseDto saveGatheringSavingLog(Long memberId, Long gatheringMemberId, GatheringSavingLogRequestDto gatheringSavingLogRequestDto) {

        String imgUrl = fileUpload(gatheringSavingLogRequestDto.file(), gatheringSavingLogRequestDto.imgUrl());

        GatheringMember gm = gatheringMemberRepository.findById(gatheringMemberId)
                .orElseThrow(() -> new CustomRuntimeException(Exceptions.NOT_FOUND_GATHERING_MEMBER));

        GatheringSavingLog gatheringSavingLog = gatheringSavingLogRepository.save(
                GatheringSavingLog.builder()
                        .gatheringMember(gm)
                        .amount(gatheringSavingLogRequestDto.amount())
                        .build()
        );

        gatheringSavingCertificationRepository.save(
                GatheringSavingCertification.builder()
                        .gatheringSavingLog(gatheringSavingLog)
                        .imageUrl(imgUrl)
                        .build()
        );

        Wallet wallet = walletRepository.findByMemberId(memberId)
                .orElseThrow(() -> new CustomRuntimeException(Exceptions.NOT_FOUND_WALLET));
        wallet.savePoint(gatheringSavingLogRequestDto.amount());
        walletRepository.save(wallet);

        return GatheringSavingLogCertificateResponseDto.builder()
                .amount(gatheringSavingLogRequestDto.amount())
                .imgUrl(imgUrl)
                .build();
    }

    @Transactional
    public GatheringSavingLogCertificateResponseDto updateGatheringSavingLog(Long memberId, Long savingLogId, GatheringSavingLogRequestDto gatheringSavingLogRequestDto) {

        String imgUrl = fileUpload(gatheringSavingLogRequestDto.file(), gatheringSavingLogRequestDto.imgUrl());

        GatheringSavingLog gatheringSavingLog = gatheringSavingLogRepository.findById(savingLogId).orElseThrow(() -> new CustomRuntimeException(Exceptions.NOT_FOUND_MEMBER));

        gatheringSavingLog.setAmount(gatheringSavingLogRequestDto.amount());

        Wallet wallet = walletRepository.findByMemberId(memberId)
                .orElseThrow(() -> new CustomRuntimeException(Exceptions.NOT_FOUND_WALLET));
        wallet.savePoint(gatheringSavingLogRequestDto.amount() - gatheringSavingLog.getAmount() + gatheringSavingLogRequestDto.amount());

        GatheringSavingCertification gatheringSavingCertification =
                gatheringSavingCertificationRepository
                        .findByGatheringSavingLog(gatheringSavingLog)
                        .orElseThrow(() -> new CustomRuntimeException(Exceptions.NOT_FOUND_MEMBER));
        gatheringSavingCertification.setImageUrl(imgUrl);

        return GatheringSavingLogCertificateResponseDto.builder()
                .amount(gatheringSavingLogRequestDto.amount())
                .imgUrl(imgUrl)
                .build();
    }

    private String fileUpload(MultipartFile file, String imgUrl){
        if(file.isEmpty()){
            if(imgUrl.isBlank()){
                throw new CustomRuntimeException(Exceptions.INVALID_REQUEST);
            } else {
                return imgUrl;
            }
        }

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());
        metadata.setExpirationTime(DateTime.now().plusDays(1).toDate());

        String fileName = UUID.randomUUID().toString();

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = currentDateTime.format(formatter);

        try {
            amazonS3Client.putObject(new PutObjectRequest(s3Bucket + cdnPathBase + "/" + formattedDate, fileName, file.getInputStream(), metadata)
                    .withCannedAcl(CannedAccessControlList.Private)
            );
            // https://
            return cdnUrl + cdnPathBase + "/" + formattedDate + "/" + fileName;
        } catch (IOException ie){
            ie.printStackTrace();
            throw new CustomRuntimeException(Exceptions.NOT_UPLOAD_IMAGE);
        }
    }
}
