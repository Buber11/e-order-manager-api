package com.example.eordermanagerapi.Address;

import jakarta.persistence.*;
import lombok.*;

import javax.naming.ldap.PagedResultsControl;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addressId;
    private String city;
    private String country;
    private String street;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "post_code")
    private String postCode;

}
