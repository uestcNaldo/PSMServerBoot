package com.uestc.naldo.service.impl;

import com.uestc.naldo.dao.PhotoMapper;
import com.uestc.naldo.domain.Photo;
import com.uestc.naldo.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoMapper photoMapper;

    @Override
    public int add(Photo photo) {
        return this.photoMapper.add(photo);
    }

    @Override
    public int deleteById(Long id) {
        return this.photoMapper.deleteById(id);
    }

    @Override
    public Photo queryPhotoById(Long id) {
        return this.photoMapper.queryPhotoById(id);
    }

    @Override
    public List<Photo> queryPhotoListAll() {
        return this.photoMapper.queryPhotoListAll();
    }
}
