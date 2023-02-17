package com.pfe.campagne.service.impl;

import com.pfe.campagne.helper.UserFoundException;
import com.pfe.campagne.helper.UserNotFoundException;
import com.pfe.campagne.model.Candidatexam;
import com.pfe.campagne.model.TokenConfirmation;
import com.pfe.campagne.model.User;
import com.pfe.campagne.model.UserRole;
import com.pfe.campagne.repository.RoleRepository;
import com.pfe.campagne.repository.TokenConfirmationRepository;
import com.pfe.campagne.repository.UserRepository;
import com.pfe.campagne.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenConfirmationRepository tokenConfirmationRepository;
    @Autowired
    private RoleRepository roleRepository;

    //creating user
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {


        User local = this.userRepository.findByUsername(user.getUsername());
        if (local != null) {
            System.out.println("User is already there !!");
            throw new UserFoundException();
        } else {
            //user create
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);
            local = this.userRepository.save(user);

        }

        return local;
    }

    @Override
    public  Boolean  confirmToken(String confirmationToken){
        TokenConfirmation token = tokenConfirmationRepository.findByConfirmationToken(confirmationToken);
         if(token != null){
             User user =userRepository.findByUsername(token.getUser().getUsername());
             user.setEnabled(true);
             userRepository.save(user);
             System.out.println("Le lien est vérifié ");
             return true;
         }
         else{
             System.out.println("Le lien est invalide or broken !");
             return false;
         }
    }

    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }


    //getting user by username

    @Override
    public Optional<User> getUserId(Integer id) {
        return this.userRepository.findById(id);
    }


    @Override
    public void deleteUser(Integer id) {
       User  user = userRepository.findById(id).get();
        UserRole userRole=new UserRole();
          userRole.setUser(user);
             if (userRole.getUser().getId() == user.getId() ) {
                userRole.setUser(null);
                userRole.setRole(null);
                userRole.setUserRoleId(null);
            }

        this.userRepository.deleteById(id);

    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public List<User> findAllByrolenormal() {
        return userRepository.findAllByrolenormal();
    }

    @Override
    public User update(User user) {
      /*User  user1 = new User();
      user1.setId(user.getId());
       user1.setFirstName(user.getFirstName());
       user1.setProfile(user.getProfile());
       user1.setEntreprise(user.getEntreprise());
       user1.setAdresse(user.getAdresse());
       user1.setLastName(user.getLastName());
       user1.setEmail(user.getEmail());
       user1.setUserRoles(user.getUserRoles());
       user1.setEnabled(user.isEnabled());
       user1.setPassword(user.getPassword());
       return user1;
    */
    return this.userRepository.save(user);
    }

    @Override
    public void updateResetPasswordToken(String token, String email) throws UserNotFoundException {
     User user = userRepository.findByUsername(email);
     if(user != null){
         user.setResetPasswordToken(token);
         userRepository.save(user);

     }else{
         throw  new UserNotFoundException("Nous pouvons pas trouver l'utilisateur avec cette email"+ email);

     }
    }

    @Override
    public User getUserByResetPasswordToken(String token) {
        return userRepository.findByResetPasswordToken(token);
    }

    @Override
    public void updatePassword(User user, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        user.setResetPasswordToken(null);
        userRepository.save(user);
    }

    @Override
    public List<User> findAllUsercandidat() {
        return this.userRepository.findAllByrolecandidat();
    }

    @Override
    public void addRoletoUser(String username, String roleName) {

    }

    @Override
    public List<User> findUserByEntreprise(Long entrpriseid) {
        return this.userRepository.findUserByEntreprise(entrpriseid);
    }

   /* @Override
    public void addRoletoUser(String username, String roleName) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByRoleName(roleName);
        UserRole userRole
        if (user.getUserRoles() == role.getUserRoles())
    }
*/
}
