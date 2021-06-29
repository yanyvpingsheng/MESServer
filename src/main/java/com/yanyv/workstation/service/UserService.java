package com.yanyv.workstation.service;

import com.yanyv.workstation.entity.User;

public interface UserService {
    Long save(User user);
    User get(Long id);
    User load(Long id);
    User queryByEmail(String email);
}
