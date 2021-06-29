package com.yanyv.workstation.service;

import com.yanyv.workstation.entity.User;
import com.yanyv.workstation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public Long save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User get(Long id) {
        return userRepository.get(id);
    }

    @Override
    public User load(Long id) {
        return userRepository.load(id);
    }

    @Override
    public User queryByEmail(String email) {
        return userRepository.queryByEmail(email);
    }
}
