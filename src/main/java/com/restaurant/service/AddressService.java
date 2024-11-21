package com.restaurant.service;

import com.restaurant.model.Address;
import com.restaurant.repository.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AddressService extends BaseService<Address, Integer> {

    @Autowired
    public AddressService(AddressRepo addressRepo) {
        super(addressRepo);
    }
}
