package com.example.eordermanagerapi.Author.DTO;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
/**
 * Represents a DTO (Data Transfer Object) for author information related to an ebook.
 * This class is used to transfer author data between layers of the application.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTOForEbook implements Serializable {

    /** The unique identifier for the author. */
    private long id;

    /** The sign-up date of the author. */
    private Date signUpDate;

    /** The name of the author. */
    private String name;

    /** The surname of the author. */
    private String surname;

    /** The email of the author. */
    private String email;
}
