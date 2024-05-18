package com.example.eordermanagerapi.order;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.time.OffsetDateTime;

@Node("Order")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderNeo4j {
    @Id
    @GeneratedValue
    private Long id;

    @CreationTimestamp
    private OffsetDateTime purchaseDate;
    private Long clientId;
    private Long orderId;
    private Double price;
    private String status;

}
