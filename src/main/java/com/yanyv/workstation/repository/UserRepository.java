package com.yanyv.workstation.repository;

import com.yanyv.workstation.entity.User;

public abstract class UserRepository extends DomainRepositoryAbs<User, Long> {
    public abstract User queryByEmail(String email);
}
