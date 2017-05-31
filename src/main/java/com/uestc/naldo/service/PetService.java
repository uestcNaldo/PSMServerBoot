package com.uestc.naldo.service;

import com.uestc.naldo.domain.Pet;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Naldo on 2017/5/15.
 */
@Component
public interface PetService {

    int add(Pet pet);

    int deleteById(Long id);

    int update(Pet pet);


    Pet queryPetById(Long id);

    List<Pet> queryPetListAll();

    List<Pet> queryPetListByName(String name);

    List<Pet> queryPetListByOid(Long oid);

}
