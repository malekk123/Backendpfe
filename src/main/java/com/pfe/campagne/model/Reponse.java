package com.pfe.campagne.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "reponse")
public class Reponse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idreponse;

    private String reponse;

    private Boolean estcorrecte;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Question question;

}
