package com.pfe.campagne.service.impl;

import com.pfe.campagne.model.Mail;
import com.pfe.campagne.model.TokenConfirmation;
import com.pfe.campagne.model.User;
import com.pfe.campagne.repository.TokenConfirmationRepository;
import com.pfe.campagne.repository.UserRepository;
import com.pfe.campagne.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.UnsupportedEncodingException;


@Service
public class SendMailServiceImpl implements SendMailService {
    //private final JavaMailSender javaMailSender;

/*    public SendMailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }*/
   @Autowired
  private JavaMailSender javaMailSender;
   @Autowired
   private TokenConfirmationRepository confirmationRepository;

   @Autowired
   private UserRepository userRepository;

   @Override
    public void sendMailWithAttachments(Mail mail,String attachement) throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo(mail.getMailTo());

        helper.setSubject(mail.getMailSubject());

        helper.setText(mail.getMailContent(), true);

        FileSystemResource fileSystemResource =
                new FileSystemResource(new File(attachement));
        helper.addAttachment(fileSystemResource.getFilename(),fileSystemResource);
        //helper.addAttachment(mail.getAttachments(),);


        javaMailSender.send(msg);


        System.out.println("Mail with attachement");
    }

    @Override
    @Async
    public String sendMail(Mail mail) {

        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setFrom(mail.getMailFrom());

        msg.setTo(mail.getMailTo());

        msg.setSubject(mail.getMailSubject());

        msg.setText(mail.getMailContent());


        javaMailSender.send(msg);
        return "Mail send successfully";
    }

    @Override
    public void sendMAilangular(String frommail,String toMail, String subject, String body) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(frommail);//"mayssa.karmous1234@gmail.com");
        message.setTo(toMail);
        message.setSubject(subject);
        message.setText(body);

    }

    @Override
    public String sendMail() {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("mayssa.karmous1234@gmail.com");
        message.setTo("kartv7916@gmail.com");
        message.setSubject("Evaluation technique");
        message.setText("Bonjour Candidat,\n" +
                "Votre candidature a retenu notre attention\n"
        +"Dans le cadre de notre processus de recrutement,nous avons le plaisir de vous inviter à passer une évaluation technique."
        +"Vous pouvez choisir le moment le plus apprioprié pour vous pour passer ce test(le lien dûre 3 jours aprés cette durée vous n'aurez plus le droit de passer l'examen)." +
                "Quand vous serez prêt(e),cliquez sur le lien ci-dessous à la page d'accueil de votre session.\n"
        +"Bonne chance!"+"Cordialement,\n");
        javaMailSender.send(message);

        return  "Mail send successfully";

    }
    public void sendEmailLinkReset(String email,String link) throws MessagingException, UnsupportedEncodingException{
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("contact@gmail.com","Support administrateur");
        helper.setTo(email);

        String subject ="Réinitialisation du mot de passe";

        String content = "<p>Bonjour</p>"
           + "<p>Vous avez demandé de réinitialiser votre mot de passe .</p>"
           +"<p>Cliquez sur le lien ci-dessous pour changer votre mot de passe:</p>"
           +"<p> <a  class=\"btn btn-primary\" href=\"" + link + "\">Changer mot de passe</p>"
           +"<br>"
           +"<p>Ignorer cet email si vous vous souvenez de votre mot de passe précédent  </p>";

        helper.setSubject(subject);

        helper.setText(content,true);

        javaMailSender.send(message);

    }

    @Override
    @Async
    public void sendConfirmationToken(Integer id) throws MessagingException, UnsupportedEncodingException{

        User user =userRepository.findById(id).get();
        TokenConfirmation tokenConfirmation = new TokenConfirmation(user);

        confirmationRepository.save(tokenConfirmation);

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        String link = "http://localhost:4200/confirm-account?token="+tokenConfirmation.getConfirmationToken();

         String Content ="<p>Vous avez demandé de confirmer votre compte </p>"
                 +"<p>Veuillez cliquer sur ce lien </p>"+
                 "<p><a href=\""+link+"\"> lien de Confirmation du compte</p>";

        helper.setTo(user.getUsername());
        helper.setSubject("Confirmer votre compte ");
        helper.setFrom("mayssa.karmous1234@gmail.com","Support Administratif");
        helper.setText(Content,true);
        javaMailSender.send(message);
       // mailMessage.setSubject("Confirmer inscription");
        //mailMessage.setFrom("mayssa.karmous1234@gmail.com");
       // mailMessage.setText(Content,);
       // javaMailSender.send(mailMessage);


    }

    @Async
    public void sendEmail(SimpleMailMessage email){
        javaMailSender.send(email);
    }
}

