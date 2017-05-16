package com.uestc.naldo.service.impl;

import com.uestc.naldo.dao.AdminMapper;
import com.uestc.naldo.domain.Admin;
import com.uestc.naldo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by Naldo on 2017/5/15.
 */
@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public int add(Admin admin) {
        return this.adminMapper.add(admin);
    }

    @Override
    public int deleteById(Long id) {
        return this.adminMapper.deleteById(id);
    }

    @Override
    public int update(Admin admin) {
        return this.adminMapper.update(admin);
    }

    @Override
    public Admin queryAdminById(Long id) {

        return this.adminMapper.queryById(id);

    }

    @Override
    public List<Admin> queryAdminListAll() {
        return this.adminMapper.queryAdminListAll();
    }


}
