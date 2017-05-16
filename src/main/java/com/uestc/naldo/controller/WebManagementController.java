package com.uestc.naldo.controller;

import com.uestc.naldo.domain.Admin;
import com.uestc.naldo.domain.Trainer;
import com.uestc.naldo.service.AdminService;


import com.uestc.naldo.service.TrainerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/web")
public class WebManagementController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private TrainerService trainerService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("/index");

        int result = adminService.deleteById(13L);

        logger.debug("result = "+result);

        return modelAndView;
    }



}
