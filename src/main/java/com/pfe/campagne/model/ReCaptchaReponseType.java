package com.pfe.campagne.model;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReCaptchaReponseType {

    private  Boolean success;
    private String challenge_ts;
    private  String hostname;

    public Boolean isSuccess(){
        return success;
    }


}
