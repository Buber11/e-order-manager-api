package com.example.eordermanagerapi.Author.DTO;

import com.example.eordermanagerapi.ebook.DTO.EbookDTOForAuthor;
import com.example.eordermanagerapi.ebook.DTO.EbookDTOView;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Represents a DTO (Data Transfer Object) for author information used for viewing.
 * This class is used to transfer author data between layers of the application for viewing purposes.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class AuthorDTOView implements Serializable {

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

    /** The list of ebooks authored by the author. */
    private List<EbookDTOForAuthor> ebooks;
}
