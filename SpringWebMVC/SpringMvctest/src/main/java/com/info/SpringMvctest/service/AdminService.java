package com.info.SpringMvctest.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.info.SpringMvctest.entity.Admin;
import com.info.SpringMvctest.repo.AdminRepository;

@Service
@Transactional
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }
    
    public Admin login(String username, String password) {
        return adminRepository.findByUsernameAndPassword(username, password);
    }
}
