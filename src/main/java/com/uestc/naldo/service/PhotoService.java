package com.uestc.naldo.service;

import com.uestc.naldo.domain.Photo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PhotoService {

    int add(Photo photo);

    int deleteById(Long id);

    Photo queryPhotoById(Long id);

    List<Photo> queryPhotoListAll();

}
