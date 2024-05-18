package com.example.eordermanagerapi.Client;

import com.example.eordermanagerapi.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.CustomLog;
import lombok.Data;

@Entity
@Builder
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private long clientId;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id_user")
    private User user;
    @Column(name = "sing_up_date")
    private Data signUpDate;

}
