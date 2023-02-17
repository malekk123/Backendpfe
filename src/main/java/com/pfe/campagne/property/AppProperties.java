package com.pfe.campagne.property;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//@ConfigurationProperties(prefix = "app")
@Getter
@Setter
@Component
public class AppProperties {
    private final Auth auth = new Auth();
    private final OAuth2 oauth2 = new OAuth2();
    private final Client client = new Client();

    @Getter
    @Setter
    public static class Auth{
        private String tokenSecret;
        private long tokenExpirationMsec;
    }
    @Getter
    public static final class OAuth2{
        private List<String> authorisedRedirectUris = new ArrayList<>();

        public OAuth2 authorisedRedirectUris (List<String> authorisedRedirectUris){
            this.authorisedRedirectUris =authorisedRedirectUris;
            return this;
        }
    }

    public static class Client{
        private String baseUrl;
    }


}
