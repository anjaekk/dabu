package b172.challenging.auth.oauth;

import b172.challenging.auth.domain.Member;

import java.util.Map;

public class Oauth2UserInfoKakao extends Oauth2UserInfo{

    private final String KAKAO_ID_NAME = "id";

    public Oauth2UserInfoKakao(Map<String, Object> attributes) {
        super(attributes);
    }


    @Override
    public String getId() {
        return String.valueOf(attributes.get(KAKAO_ID_NAME));
    }
}

