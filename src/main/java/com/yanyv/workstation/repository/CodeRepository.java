package com.yanyv.workstation.repository;

import com.yanyv.workstation.entity.Code;

public abstract class CodeRepository extends DomainRepositoryAbs<Code, Long> {
    public abstract Code queryByEmail(String email);
}
