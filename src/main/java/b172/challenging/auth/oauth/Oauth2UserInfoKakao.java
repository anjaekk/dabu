package b172.challenging.auth.oauth;

import b172.challenging.auth.domain.Member;

import java.util.Map;

public class Oauth2UserInfoKakao extends Oauth2UserInfo{
    public Oauth2UserInfoKakao(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return String.valueOf(attributes.get("id"));
    }

    @Override
    public String getNickname() {
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) account.get("profile");

        if (profile == null) {
            return null;
        }

        return (String) profile.get("nickname");
    }
}

