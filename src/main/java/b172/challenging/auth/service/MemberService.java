package b172.challenging.auth.service;

import b172.challenging.auth.domain.Member;
import b172.challenging.auth.dto.response.MemberProfileResponseDto;
import b172.challenging.auth.repository.MemberRepository;
import b172.challenging.common.exception.CustomRuntimeException;
import b172.challenging.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
}
