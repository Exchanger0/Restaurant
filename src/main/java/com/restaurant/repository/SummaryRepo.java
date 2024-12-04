package com.restaurant.repository;

import com.restaurant.model.Summary;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SummaryRepo extends ListCrudRepository<Summary, Integer> {
}
