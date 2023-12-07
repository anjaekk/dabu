package b172.challenging.auth.oauth;

import b172.challenging.auth.domain.Member;
import b172.challenging.auth.domain.OauthProvider;
import b172.challenging.auth.service.MemberNicknameService;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OauthAttributes {

    private String nameAttributeKey;
    private Oauth2UserInfo oauth2UserInfo;

    private final MemberNicknameService memberService;

    @Builder
    public OauthAttributes(String nameAttributeKey, Oauth2UserInfo oauth2UserInfo, MemberNicknameService memberService) {
        this.nameAttributeKey = nameAttributeKey;
        this.oauth2UserInfo = oauth2UserInfo;
        this.memberService = memberService;
    }

    public static OauthAttributes of(OauthProvider oauthProvider,
                                     String userNameAttributeName, Map<String, Object> attributes) {
        if (oauthProvider == OauthProvider.KAKAO) {
            return ofKakao(userNameAttributeName, attributes);
        }
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OauthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        return OauthAttributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .oauth2UserInfo(new Oauth2UserInfoKakao(attributes))
                .build();
    }

    private static OauthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OauthAttributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .oauth2UserInfo(new Oauth2UserInfoGoogle(attributes))
                .build();
    }

    public Member toEntity(OauthProvider oauthProvider, Oauth2UserInfo oauth2UserInfo, MemberNicknameService memberService) {
        String randomNickname = memberService.getRandomNickname();
        return Member.builder()
                .oauthProvider(oauthProvider)
                .oauthId(oauth2UserInfo.getId())
                .nickname(randomNickname)
                .build();
    }
}
