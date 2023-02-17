package com.pfe.campagne.repository;

import com.pfe.campagne.model.Candidatexam;
import com.pfe.campagne.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CandidatexamRepository extends JpaRepository<Candidatexam,Long> {

    @Query(value="select * from candidatexam where examen_id_exam=?1 and candidat_idCandidat=?2;",nativeQuery = true)
    public Candidatexam getListofcandidatexambyexanduser(Long idExam,Integer idCandidat);

    @Query(value="select * from candidatexam where examen_id_exam=?1",nativeQuery = true)
    public List<Candidatexam> getcandidatExam(Long idExam);


    @Query(value="SELECT * FROM question q join candidatexam can join candidat dd where  can.candidat_id_candidat = dd.id_candidat and  can.candidat_id_candidat=?1 and "+
            "+can.examen_id_exam = q.examen_id_exam and can.examen_id_exam=?2;",nativeQuery = true)
    public List<Question> getQuestionofcandidattest( Long id,Long idExam);

    @Query(value="select * from candidat cc join candidatexam cd where cd.candidat_id_candidat=cc.id_candidat and cc.token=?1",nativeQuery = true)
    public Candidatexam getcandidatbytoken(String token);

    public Candidatexam findByIdcandidat(Long idcandidat);

}
