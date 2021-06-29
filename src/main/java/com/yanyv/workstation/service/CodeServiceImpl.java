package com.yanyv.workstation.service;

import com.yanyv.workstation.entity.Code;
import com.yanyv.workstation.entity.User;
import com.yanyv.workstation.repository.CodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeServiceImpl implements CodeService {
    @Autowired
    private CodeRepository codeRepository;

    @Override
    public Long save(Code code) {
        return codeRepository.save(code);
    }

    @Override
    public Code get(Long id) {
        return codeRepository.get(id);
    }

    @Override
    public Code queryByEmail(String email) {
        return codeRepository.queryByEmail(email);
    }

}
