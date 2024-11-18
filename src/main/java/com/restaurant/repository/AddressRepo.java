package com.restaurant.repository;

import com.restaurant.model.Address;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends ListCrudRepository<Address, Integer> {
}
