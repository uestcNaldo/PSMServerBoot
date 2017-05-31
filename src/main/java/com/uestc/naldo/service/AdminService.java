package com.uestc.naldo.service;

import com.uestc.naldo.domain.Admin;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface AdminService {

    Admin login(String username, String password);

    int add(Admin admin);

    int deleteById(Long id);

    int update(Admin admin);

    Admin queryAdminById(Long id);

    List<Admin> queryAdminListAll();

}
