package com.uestc.naldo.service;

import com.uestc.naldo.domain.Owner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Naldo on 2017/5/15.
 */
@Component
public interface OwnerService {

    int add(Owner owner);

    int deleteById(Long id);

    int update(Owner owner);

    Owner queryOwnerById(Long id);

    List<Owner> queryOwnerListByName(String name);

    List<Owner> queryOwnerListAll();

}
