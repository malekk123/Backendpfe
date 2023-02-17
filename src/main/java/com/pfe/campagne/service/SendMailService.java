package com.pfe.campagne.service;

import com.pfe.campagne.model.Mail;
import com.pfe.campagne.model.User;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

public interface SendMailService {
    String sendMail(Mail mail);

    public void sendMAilangular(String frommail, String toMail,String subject, String body);
    public String sendMail();
    void sendMailWithAttachments(Mail mail,String attachement) throws MessagingException;

    public void sendEmailLinkReset(String email,String link) throws MessagingException, UnsupportedEncodingException;

    public void sendConfirmationToken (Integer id)throws MessagingException, UnsupportedEncodingException;

    }
