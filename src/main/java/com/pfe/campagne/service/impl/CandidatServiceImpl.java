package com.pfe.campagne.service.impl;

import com.pfe.campagne.helper.UserFoundException;
import com.pfe.campagne.model.Candidat;
import com.pfe.campagne.model.Candidatexam;
import com.pfe.campagne.repository.CandidatRepository;
import com.pfe.campagne.repository.CandidatexamRepository;
import com.pfe.campagne.service.CandidatService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CandidatServiceImpl implements CandidatService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private CandidatRepository candidatRepository;
    @Autowired
    private CandidatexamRepository candidatexamRepository;

    @Override
    public Candidat CreateNewCandidat(Candidat candidat) throws Exception {
        Candidat candidat1 =this.candidatRepository.findByEmail(candidat.getEmail());

        if (candidat1 != null){
            System.out.println("Cette email existe déjà veuillez saisir un autre");
            throw new UserFoundException();
        }
        String token=RandomString.make(50);
        if( candidat.getToken() == null) {
            candidat.setToken(token);
            candidat.setExpiredAt(LocalDateTime.now().plusDays(3));
        }
        return this.candidatRepository.save(candidat) ;
    }

    @Override
    public Candidat findBytoken(String token) {
        return this.candidatRepository.findByToken(token);
    }

    @Override
    public void envoiMail(Candidat candidat)throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("contact@gmail.com","Support administratif");
        helper.setTo(candidat.getEmail());

        String subject ="Evaluation technique";

        String link = "http://localhost:4200/evaluation-technique?token="+candidat.getToken();
        String content = "<p>Bonjour "+candidat.getNom()+" "+ candidat.getPrenom() + ",</p>"+
                "<p>Votre candidature a retenu notre attention</p>"+
                "<p>Dans le cadre de notre processus de recrutement,nous avons le plaisir de vous inviter à passer une évaluation technique"+
                "Vous ne pouvez pas dépasser  <strong>" + candidat.getExpiredAt() + " </strong> </p>"+
                "<p>Mais vous êtes libre de choisir le moment le plus apprioprié pour vous pour passer ce test.</p>"
                +"<p>Quand vous serez prêt(e),cliquez sur le lien ci-dessous il vous dirigera vers  votre propre session.</p>\n"
                +"<p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Evaluation technique</a></p></blockquote>\\"
                +"<br>"+

                "<p>Bonne chance!</p>"+
                "<p>Cordialement,</p>"
                ;
        helper.setSubject(subject);
        helper.setText(content,true);
        javaMailSender.send(message);
    }

    @Override
    public Boolean confirmToken(String token) {
        Candidat candidat=candidatRepository.findByToken(token);

        if(candidat.getToken() != null){
            if(candidat.getExpiredAt().compareTo(LocalDateTime.now())>0){
                 return true;
            }
            return false;
        }

        return false;
    }

    @Override
    public Candidat findCandidatByid(Integer idCandidat){
        return this.candidatRepository.findByIdCandidat(idCandidat);
    }

    @Override
    public Candidat findCandidatByEmail(String email) {
        return this.candidatRepository.findByEmail(email);
    }

    @Override
    public Candidat supprimeToken(String token) {
        Candidat candidat =this.candidatRepository.findByToken(token);

            candidat.setToken(null);
         /*   candidat.setExpiredAt(null);
            candidat.setTimestamp(null);*/
            return  this.candidatRepository.save(candidat);


    }

    @Override
    public Candidat findCandidatBycandexam(Long idcandidat) {

        Candidatexam candidatexam = this.candidatexamRepository.findByIdcandidat(idcandidat);

        return this.candidatRepository.findByCandidatexam(candidatexam);
    }

    @Override
    public List<Candidat> findAllCandidatbycadex(List<Candidatexam> candidatexamList) {
        List<Candidat>candidats=new ArrayList<>();
        for(Candidatexam cd:candidatexamList){
            Candidatexam candidatexam=this.candidatexamRepository.findByIdcandidat(cd.getIdcandidat());
          candidats.add(this.candidatRepository.findByCandidatexam(candidatexam));
        }

        return candidats;
    }

    @Override
    public Candidat generateToken(Candidat candidat) {

        String token=RandomString.make(50);

            candidat.setTimestamp(Timestamp.from(Instant.now()));
            candidat.setToken(token);
            candidat.setExpiredAt(LocalDateTime.now().plusDays(3));

       return  this.candidatRepository.save(candidat) ;

    }

    @Override
    public void delete(Candidat candidat) {
        this.candidatRepository.delete(candidat);
    }




}
