package com.example.eordermanagerapi.order;

import com.example.eordermanagerapi.Author.DTO.AuthorDTOView;
import com.example.eordermanagerapi.order.DTO.OrderDtoView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    //@Query("select o FROM Order o WHERE o.clientId")
    List<Order> findAllByClientId(long id);
}
