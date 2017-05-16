package com.uestc.naldo.service;

import com.uestc.naldo.domain.Feedback;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Naldo on 2017/5/15.
 */
@Component
public interface FeedbackService {

    int add(Feedback feedback);

    int deleteById(Long id);

    int update(Feedback feedback);



    List<Feedback> queryFeedbackListAll();


}
