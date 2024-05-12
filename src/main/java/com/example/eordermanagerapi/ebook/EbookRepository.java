package com.example.eordermanagerapi.ebook;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface EbookRepository extends JpaRepository<Ebook, Long> {
    @Query("SELECT e FROM Ebook e ORDER BY e.rating DESC")
    List<Ebook> getTopEbooks(Pageable pageable);

    @Query("SELECT e FROM Ebook e ORDER BY e.title ASC")
    List<Ebook> getEbooksAlphabeticalOrder();


}
