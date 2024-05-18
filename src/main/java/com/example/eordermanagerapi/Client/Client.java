package com.example.eordermanagerapi.Client;

import com.example.eordermanagerapi.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private long clientId;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id_user")
    private User user;
    @Column(name = "sing_up_date")
    private Date signUpDate;

}
