package com.pfe.campagne.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
@Data
@NoArgsConstructor
@Entity
@Table(name = "confirmationToken")
public class TokenConfirmation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long tokenid;

    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;



    public TokenConfirmation(User user) {
        this.user=user;
        createDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }


}
