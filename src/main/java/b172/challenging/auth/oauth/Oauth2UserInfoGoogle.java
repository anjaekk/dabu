package b172.challenging.auth.oauth;

import java.util.Map;

public class Oauth2UserInfoGoogle extends Oauth2UserInfo {

    private final String GOOGLE_ID_NAME = "sub";

    public Oauth2UserInfoGoogle(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return (String) attributes.get(GOOGLE_ID_NAME);
    }
}
