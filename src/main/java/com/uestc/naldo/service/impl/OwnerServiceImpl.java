package com.uestc.naldo.service.impl;

import com.uestc.naldo.dao.OwnerMapper;
import com.uestc.naldo.domain.Owner;
import com.uestc.naldo.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService{

    @Autowired
    OwnerMapper ownerMapper;

    @Override
    public int add(Owner owner) {

        return this.ownerMapper.add(owner);
    }

    @Override
    public int deleteById(Long id) {

        return this.ownerMapper.deleteById(id);
    }

    @Override
    public int update(Owner owner) {

        return this.ownerMapper.update(owner);
    }

    @Override
    public Owner queryOwnerById(Long id) {
        return this.ownerMapper.queryOwnerById(id);
    }

    @Override
    public List<Owner> queryOwnerListByName(String name) {

        return this.ownerMapper.queryOwnerListByName(name);
    }

    @Override
    public List<Owner> queryOwnerListAll() {

        return this.ownerMapper.queryOwnerListAll();
    }
}
