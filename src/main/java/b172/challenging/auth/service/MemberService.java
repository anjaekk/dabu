package b172.challenging.auth.service;

import b172.challenging.auth.domain.Member;
import b172.challenging.auth.dto.request.MemberProfileUpdateRequestDto;
import b172.challenging.auth.dto.response.MemberProfileResponseDto;
import b172.challenging.auth.repository.MemberRepository;
import b172.challenging.common.exception.CustomRuntimeException;
import b172.challenging.common.exception.ErrorCode;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberNicknameService memberNicknameService;


    public Member updateMemberProfile(Long memberId, MemberProfileUpdateRequestDto memberProfileUpdateRequestDto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() ->  new CustomRuntimeException(ErrorCode.NOT_FOUND_MEMBER));
        if (member.isNicknameChanged(memberProfileUpdateRequestDto.getNickname())) {
            memberNicknameService.isNicknameExists(memberProfileUpdateRequestDto.getNickname());
            member.setNickname(memberProfileUpdateRequestDto.getNickname());
            memberRepository.save(member);
        }
        return member;
    }
}
