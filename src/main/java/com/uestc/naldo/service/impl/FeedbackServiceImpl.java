package com.uestc.naldo.service.impl;

import com.uestc.naldo.dao.FeedbackMapper;
import com.uestc.naldo.domain.Feedback;
import com.uestc.naldo.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Naldo on 2017/5/15.
 */
@Service
public class FeedbackServiceImpl implements FeedbackService{

    @Autowired
    FeedbackMapper feedbackMapper;

    @Override
    public int add(Feedback feedback) {

        return feedbackMapper.add(feedback);
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }

    @Override
    public int update(Feedback feedback) {
        return 0;
    }

    @Override
    public List<Feedback> queryFeedbackListAll() {
        return feedbackMapper.queryAttendanceListAll();
    }
}
