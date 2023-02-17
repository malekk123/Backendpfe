package com.pfe.campagne.service;

import com.pfe.campagne.helper.UserNotFoundException;
import com.pfe.campagne.model.User;
import com.pfe.campagne.model.UserRole;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
@Service
public interface UserService {

    //creating user
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;

    //get user by username
    public User getUser(String username);

    public  Boolean  confirmToken(String confirmationToken);

    //get user by id
    public Optional<User> getUserId(Integer id);

    //delete user by id
    public void deleteUser(Integer userId);

     //find all users
    public List<User>findAllUsers();

    //find all user by role

//    public  List<User>findAllUsersByRole();

   // @Query("select u FROM User u join UserRole ur on u.id= ur.userRoleId join Role r on ur.userRoleId=r.roleId where r.roleName='NORMAL'")
    public List<User> findAllByrolenormal();

    public User update(User user);

    public void updateResetPasswordToken(String token,String email)throws UserNotFoundException;

    public User getUserByResetPasswordToken(String token);

    public void updatePassword(User user,String newPassword);

    public List<User> findAllUsercandidat();

    public void addRoletoUser(String username,String roleName);

    public List<User> findUserByEntreprise(Long entrpriseid);
}
