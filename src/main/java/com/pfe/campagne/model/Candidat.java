package com.pfe.campagne.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class Candidat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCandidat;

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    @Column
    private String nom;

    @Column
    private String prenom;

    @Column(unique = true)
    private String token;

    @Column
    @CreationTimestamp
    private Timestamp timestamp;

      @Column(updatable = false)
      @Basic(optional = false)
      private LocalDateTime expiredAt;

      @Transient
    private boolean isDone=false;

    @OneToOne
    private Candidatexam candidatexam;
}
