package com.example.eordermanagerapi.order;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface OrderNeo4jRepository extends Neo4jRepository<OrderNeo4j,Long> {
}
