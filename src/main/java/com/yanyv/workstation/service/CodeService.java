package com.yanyv.workstation.service;

import com.yanyv.workstation.entity.Code;
import com.yanyv.workstation.entity.User;

public interface CodeService {
    Long save(Code code);
    Code get(Long id);
    Code queryByEmail(String email);
}
