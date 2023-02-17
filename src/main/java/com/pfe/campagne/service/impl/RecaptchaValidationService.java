package com.pfe.campagne.service.impl;

import com.pfe.campagne.model.ReCaptchaReponseType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class RecaptchaValidationService {

    private static final String GOOGLE_RECAPCHA_ENDPOINT =
            "https://www.google.com/recaptcha/api/siteverify";

    private final String RECATCHA_SECRET = "6LczPqkiAAAAAPZdDYUK3xVNoZ8wlXlxIpFp5Waw";

    public Boolean validateCaptcha(String captchaResponse){
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String,String> requestMap = new LinkedMultiValueMap<>();
        requestMap.add("response",captchaResponse);

        ReCaptchaReponseType apiResponse = restTemplate.postForObject(GOOGLE_RECAPCHA_ENDPOINT,requestMap, ReCaptchaReponseType.class);

        if(apiResponse == null){
            return false;
        }

        return Boolean.TRUE.equals(apiResponse.isSuccess());
    }
}
