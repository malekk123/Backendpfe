package com.pfe.campagne.model;

import lombok.*;

import javax.persistence.Column;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mail {
    private String mailFrom;
    private String mailTo;
    private String mailCc;
    private String mailBcc;
    private String mailSubject;
    @Column(length = 5000)
    private String mailContent;
    private String contenType;
    private List<Object> attachments;



}
