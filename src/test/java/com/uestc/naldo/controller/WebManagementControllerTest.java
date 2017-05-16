package com.uestc.naldo.controller;

import com.uestc.naldo.domain.Admin;
import com.uestc.naldo.service.AdminService;
import com.uestc.naldo.service.impl.AdminServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.junit.Assert.*;

/**
 * Created by Naldo on 2017/5/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class WebManagementControllerTest {


    @Autowired
    private AdminService adminService;


    @Test
    public void index() throws Exception {

        System.out.println("Add: "+adminService.add(new Admin(null,"admin", "123", "admin", "1234567890@example.com")));


    }

}