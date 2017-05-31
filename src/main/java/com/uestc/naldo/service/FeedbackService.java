package com.uestc.naldo.service;

import com.uestc.naldo.domain.Feedback;
import com.uestc.naldo.domain.FeedbackItem;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

/**
 * Created by Naldo on 2017/5/15.
 */
@Component
public interface FeedbackService {

    int add(Feedback feedback);

    int deleteById(Long id);

    int update(Feedback feedback);

    Feedback queryFeedbackById(Long id);

    List<Feedback> queryFeedbackListAll();

    List<FeedbackItem> queryFeedbackItemListAll();

    List<FeedbackItem> queryFeedbackItemListByNameAndDate(String name, Date date);

}
