package com.restaurant.service;

import com.restaurant.model.Summary;
import com.restaurant.repository.SummaryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SummaryService extends BaseService<Summary, Integer, SummaryRepo> {

    @Autowired
    public SummaryService(SummaryRepo summaryRepo) {
        super(summaryRepo);
    }
}
