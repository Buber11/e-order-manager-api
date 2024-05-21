package com.example.eordermanagerapi.Client;

import com.example.eordermanagerapi.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * Represents a client entity.
 */
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    /** The unique identifier for the client. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private long clientId;

    /** The user associated with the client. */
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id_user")
    private User user;

    /** The sign-up date of the client. */
    @Column(name = "sing_up_date")
    private Date signUpDate;
}