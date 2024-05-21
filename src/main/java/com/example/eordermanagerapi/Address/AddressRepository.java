package com.example.eordermanagerapi.Address;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for performing CRUD operations on Address entities.
 */
public interface AddressRepository extends JpaRepository<Address, Long> {
}
