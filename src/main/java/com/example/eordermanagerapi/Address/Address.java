package com.example.eordermanagerapi.Address;

import com.example.eordermanagerapi.user.User;
import jakarta.persistence.*;
import lombok.*;

import javax.naming.ldap.PagedResultsControl;
import java.util.List;

import java.util.List;

/**
 * Represents an address entity.
 */
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

    /** The city of the address. */
    private String city;

    /** The country of the address. */
    private String country;

    /** The street of the address. */
    private String street;

    /** The phone number associated with the address. */
    @Column(name = "phone_number")
    private String phoneNumber;

    /** The postal code of the address. */
    @Column(name = "post_code")
    private String postCode;

    /** The list of users associated with this address. */
    @ManyToMany(mappedBy = "addresses")
    private List<User> users;
}
