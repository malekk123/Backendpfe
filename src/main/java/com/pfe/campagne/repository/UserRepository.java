package com.pfe.campagne.repository;

import com.pfe.campagne.model.Entreprise;
import com.pfe.campagne.model.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface UserRepository extends JpaRepository<User,Integer> {

    public User findByUsername(String username);
   /* @Modifying
    @Transactional
    @Query(value="delete from user_role where user_id=?1 delete from users where id=?1 ",nativeQuery = true)
    public void supprimer(Integer id);
*/

     @Query(value = "SELECT * FROM users user join user_role ur on user.id=ur.user_id join roles r on r.role_id= ur.role_role_id where r.role_name='NORMAL';",nativeQuery = true)
    public List<User> findAllByrolenormal();

     public User findByResetPasswordToken(String token);

    @Query(value = "SELECT * FROM users user join user_role ur on user.id=ur.user_id join roles r on r.role_id= ur.role_role_id where r.role_name='CANDIDAT';",nativeQuery = true)
    public List<User> findAllByrolecandidat();

    @Query("select u FROM  User u")
    public List<User> getAllUser();

 //   public List<User> findByEntreprise (Entreprise entreprise);
  //   public  User findByEntreprise(Entreprise entreprise);
    @Query(value = "SELECT * FROM users where entreprise_entrpriseid=?1",nativeQuery = true)
    public List<User> findUserByEntreprise(Long entrpriseid);

}

