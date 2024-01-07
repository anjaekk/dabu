package b172.challenging.member.dto.response;

import b172.challenging.member.domain.Member;
import b172.challenging.member.domain.OauthProvider;
import b172.challenging.member.domain.Role;
import b172.challenging.member.domain.Sex;
import lombok.*;

@Getter
@RequiredArgsConstructor
public class MemberProfileResponseDto {

    private final Long id;

    private final OauthProvider oauthProvider;

    private final String nickName;

    private final Role role;

    private final Long birthYear;

    private final Sex sex;

    public static MemberProfileResponseDto of(Member member) {
        return new MemberProfileResponseDto(
                member.getId(),
                member.getOauthProvider(),
                member.getNickname(),
                member.getRole(),
                member.getBirthYear(),
                member.getSex()
        );
    }
}
