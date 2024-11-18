package com.restaurant.service;

import com.restaurant.model.Address;
import com.restaurant.repository.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AddressService {
    private final AddressRepo addressRepo;

    @Autowired

    public AddressService(AddressRepo addressRepo) {
        this.addressRepo = addressRepo;
    }

    public Address save(Address address) {
        return addressRepo.save(address);
    }

    public List<Address> saveAll(Iterable<Address> entities) {
        return addressRepo.saveAll(entities);
    }

    @Transactional(readOnly = true)
    public Address findById(int id) {
        return addressRepo.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public boolean existsById(int id) {
        return addressRepo.existsById(id);
    }

    @Transactional(readOnly = true)
    public List<Address> findAll() {
        return addressRepo.findAll();
    }

    @Transactional(readOnly = true)
    public List<Address> findAllById(Iterable<Integer> ids) {
        return addressRepo.findAllById(ids);
    }

    @Transactional(readOnly = true)
    public long count() {
        return addressRepo.count();
    }

    public void deleteById(int id) {
        addressRepo.deleteById(id);
    }

    public void delete(Address address) {
        addressRepo.delete(address);
    }

    public void deleteAllById(Iterable<? extends Integer> ids) {
        addressRepo.deleteAllById(ids);
    }

    public void deleteAll(Iterable<Address> addresses) {
        addressRepo.deleteAll(addresses);
    }
}
