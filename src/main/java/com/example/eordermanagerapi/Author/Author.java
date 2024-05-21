package com.example.eordermanagerapi.Author;

import com.example.eordermanagerapi.ebook.Ebook;
import com.example.eordermanagerapi.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * Represents an author entity.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Author {

    /** The unique identifier for the author. */
    @Id
    @GeneratedValue
    @Column(name = "id_author")
    private long authorId;

    /** The user associated with the author. */
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id_user")
    private User user;

    /** The sign-up date of the author. */
    @Column(name = "signup_date")
    private Date signUpDate;

    /** The list of ebooks authored by this author. */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "author_ebook",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "ebook_id")
    )
    private List<Ebook> ebooks;
}