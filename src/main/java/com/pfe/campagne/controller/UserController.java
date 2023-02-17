package com.pfe.campagne.controller;

import com.pfe.campagne.helper.UserFoundException;
import com.pfe.campagne.model.*;
import com.pfe.campagne.repository.TokenConfirmationRepository;
import com.pfe.campagne.service.EntrepriseService;
import com.pfe.campagne.service.SendMailService;
import com.pfe.campagne.service.UserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EntrepriseService entrepriseService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SendMailService service;

    @Autowired
    private TokenConfirmationRepository confirmationRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    //creating user with entreprise
    @PostMapping("/{nom}/")
    public User createUser(@RequestBody User user,@PathVariable("nom")String nom) throws Exception {

        Entreprise entreprise = new Entreprise();
        entreprise.setNoment(nom);

        this.entrepriseService.addEntreprise(entreprise);

        user.setEntreprise(entreprise);

        user.setProfile("default.png");
        //encoding password with bcryptpasswordencoder

        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

        Set<UserRole> roles = new HashSet<>();

        Role role = new Role();
        role.setRoleId(45);
        role.setRoleName("NORMAL");

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        roles.add(userRole);

        return this.userService.createUser(user, roles);

    }


   @RequestMapping("/confirm/{id}")
    public Boolean confirmationToken(@PathVariable ("id") Integer id) throws Exception{

        service.sendConfirmationToken(id);
        return true;
    }



    @RequestMapping("/confirmAccount/{confirmationToken}")
    public Boolean confirmUserAccount(@PathVariable ("confirmationToken") String confirmationToken){
      return    this.userService.confirmToken(confirmationToken);
    }

    @PostMapping("/")
     public User createUserwithoutEntreprise(@RequestBody User user) throws Exception{
        user.setProfile("default.png");
        //encoding password with bcryptpasswordencoder

        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

        Set<UserRole> roles = new HashSet<>();

        Role role = new Role();
        role.setRoleId(45);
        role.setRoleName("NORMAL");

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        roles.add(userRole);

        return this.userService.createUser(user, roles);

    }

    @RequestMapping("/testdto")
    public void confirmUserAccount(@Valid @RequestBody TestDTO test){

        System.out.println("test "+test);
    }

    @PostMapping("/forgotPassword/{username}")
    public Boolean processForgotPassword(@PathVariable("username")String username) throws Exception{
        String token = RandomString.make(30);

            this.userService.updateResetPasswordToken(token,username);
            String resetPasswordLink = "http://localhost:4200/reset_password?token="+token;
            service.sendEmailLinkReset(username,resetPasswordLink);
            return true;

        /*}catch(UserNotFoundException() ex){

            return "utilisateur avec cet email non trouvé";
        }catch (UnsupportedEncodingException| MessagingException e){
            return "Erreur dans l'envoie d'email";
        }*/
    }
    @PostMapping("/resetPassword/{password}/{token}")
    public Boolean processResetPassword(@PathVariable("token") String token,@PathVariable("password")String password){
        User user =userService.getUserByResetPasswordToken(token);
        if (user ==null){
            return false;
        }else {
            userService.updatePassword(user,password);
            return true;
        }
    }
    @PostMapping("/candidat")
    public User CreateUserCandidat(@RequestBody User user) throws Exception{
        user.setProfile("default.png");
      //encrypting et setting password
        user.setPassword(this.bCryptPasswordEncoder.encode("smtppou21candit"));
        Set<UserRole> roles = new HashSet<>();
        Role role = new Role();
        role.setRoleId(80);
        role.setRoleName("CANDIDAT");

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        roles.add(userRole);



        return this.userService.createUser(user,roles);

    }

   /* @PostMapping("/signup/")
    public User createforsignup(@RequestBody User user)throws Exception {


        user.setProfile("default.png");
        //encoding password with bcryptpasswordencoder

        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

        Set<UserRole> roles = new HashSet<>();

        Role role = new Role();
        role.setRoleId(45);
        role.setRoleName("NORMAL");

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        roles.add(userRole);


        TokenConfirmation tokenConfirmation = new TokenConfirmation();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("confirmer inscription");
        mailMessage.setFrom("mayssa.karmous1234@gmail.com");
        mailMessage.setText("Pour confirmer votre compte, Veuillez cliquer ici :"
        +"http://localhost:4200/confirm-account?token"+tokenConfirmation.getConfirmationToken());
        javaMailSender.send(mailMessage);


        return this.userService.createUser(user, roles);


    }*/


    @PutMapping("/")
    public ResponseEntity<User> update(@RequestBody User user) {
        return ResponseEntity.ok(this.userService.update(user));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable("userId")Integer userId){
        return ResponseEntity.ok(this.userService.getUserId(userId));
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username) {
        return this.userService.getUser(username);
    }

    //delete the user by id
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Integer id) {
        this.userService.deleteUser(id);
    }

    //list utilisateurs


    @GetMapping("/users")
    public List<User>findAllUsers(){
        return userService.findAllUsers();
    }

    @GetMapping("/getlist")
    public List<User>findAllByrolenormal(){
        return  userService.findAllByrolenormal();
    }


    @GetMapping("/allcandidat")
    public List<User>findAllcandidat(){
        return this.userService.findAllUsercandidat();
    }


    @GetMapping("/entreprise/{entrpriseid}")
    public List<User>findUserByEntreprise(@PathVariable("entrpriseid")Long entrpriseid){
        return this.userService.findUserByEntreprise(entrpriseid);
    }

    //update api


    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserFoundException ex) {
        return ResponseEntity.ok(ex.getMessage());
    }



}
