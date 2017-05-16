package com.uestc.naldo.service.impl;


import com.uestc.naldo.dao.CourseMapper;
import com.uestc.naldo.domain.Course;
import com.uestc.naldo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    CourseMapper courseMapper;

    @Override
    public int add(Course course) {

        return this.courseMapper.add(course);
    }

    @Override
    public int deleteById(Long id) {

        return this.courseMapper.deleteById(id);
    }

    @Override
    public int update(Course course) {

        return this.courseMapper.update(course);
    }

    @Override
    public List<Course> queryCourseListByName(String name) {

        return this.courseMapper.queryCourseListByName(name);
    }

    @Override
    public List<Course> queryCourseListAll() {

        return this.courseMapper.queryCourseListAll();
    }
}
