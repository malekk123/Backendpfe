package com.pfe.campagne.controller;

import com.pfe.campagne.model.Mail;
import com.pfe.campagne.service.SendMailService;
import com.pfe.campagne.service.impl.SendMailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private SendMailServiceImpl service;

    @RequestMapping(value = "/sendmail",method = RequestMethod.POST)
    public void sendEmailSimple(@RequestBody Mail mail) {
        System.out.println(mail);
        this.service.sendMail(mail);

    }
    @GetMapping("/simpleEmail")
    public String sendEmail(){
        return this.service.sendMail();
    }

}
