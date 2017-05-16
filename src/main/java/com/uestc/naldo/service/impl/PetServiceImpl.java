package com.uestc.naldo.service.impl;

import com.uestc.naldo.dao.PetMapper;
import com.uestc.naldo.domain.Pet;
import com.uestc.naldo.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService{

    @Autowired
    PetMapper petMapper;

    @Override
    public int add(Pet pet) {

        return this.petMapper.add(pet);
    }

    @Override
    public int deleteById(Long id) {

        return this.petMapper.deleteById(id);
    }

    @Override
    public int update(Pet pet) {
        return this.petMapper.update(pet);

    }

    @Override
    public Pet queryPetById(Long id) {

        return this.petMapper.queryPetById(id);
    }

    @Override
    public List<Pet> queryPetListAll() {

        return this.petMapper.queryPetListAll();
    }

    @Override
    public List<Pet> queryPetListByName(String name) {

        return this.petMapper.queryPetListByName(name);

    }
}
