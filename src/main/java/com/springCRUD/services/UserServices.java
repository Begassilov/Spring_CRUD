package com.springCRUD.services;

import java.util.List;


import com.springCRUD.model.User;
import com.springCRUD.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserServices {

    @Autowired
    private UserRepository repository;

    public List<User> listAllUser(){
        return repository.findAll();
    }

    public void save(User user) {
        repository.save(user);
    }

    public User get(Long id) {
        return repository.findById(id).get();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}

