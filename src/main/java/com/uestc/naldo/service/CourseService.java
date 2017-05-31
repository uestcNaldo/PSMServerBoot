package com.uestc.naldo.service;

import com.uestc.naldo.domain.Course;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Naldo on 2017/5/15.
 */
@Component
public interface CourseService {

    int add(Course course);

    int deleteById(Long id);

    int update(Course course);

    List<Course> queryCourseListByName(String name);

    List<Course> queryCourseListAll();

    Course queryCourseById(Long id);

}
