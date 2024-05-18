package com.example.eordermanagerapi.Author;

import com.example.eordermanagerapi.ebook.Ebook;
import com.example.eordermanagerapi.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Author {

    @Id
    @GeneratedValue
    @Column(name = "id_author")
    private long authorId;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id_user")
    private User user;

    @Column(name = "signup_date")
    private Date signUpDate;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "author_ebook",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "ebook_id")
    )
    private List<Ebook> ebooks;


}
