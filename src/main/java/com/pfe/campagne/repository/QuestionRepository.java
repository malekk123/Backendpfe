package com.pfe.campagne.repository;

import com.pfe.campagne.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Set<Question> findByQuiz(Quiz quiz);

  // Set <Question> findByExamen (Examen examen);
    @Query(value = "select * from question where entreprise_entrpriseid=?1 and quiz_q_id=?2",nativeQuery = true)
    public Set<Question> findEntreQuiz(Long entreprise,Long qid);

    @Query(value = "select * from question where quiz_q_id=?1 and bibad=true",nativeQuery = true)
    public Set<Question> findquesofbibadmbyquiz(Long qid);

     @Query(value = "select * from question where examen_id_exam=?1",nativeQuery = true)
     public List<Question> findAllByexam(Long idExam);

}
