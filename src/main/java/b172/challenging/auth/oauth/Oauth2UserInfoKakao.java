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

}

