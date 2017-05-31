package com.uestc.naldo.controller;


import com.alibaba.fastjson.JSON;
import com.uestc.naldo.domain.*;
import com.uestc.naldo.json.LoginResult;
import com.uestc.naldo.json.OperationResult;
import com.uestc.naldo.json.PetListResult;
import com.uestc.naldo.service.*;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.util.List;


@Controller
@RequestMapping("/app")
public class AppController {

    @Autowired
    private OwnerService ownerService;
    @Autowired
    private TrainerService trainerService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private PetService petService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private WorkplanService workplanService;
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private AttendanceService attendanceService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String MESSAGE_REGISTER_SUCCESS = "注册成功";
    private final String MESSAGE_REGISTER_FAILURE = "注册失败";

    @ResponseBody
    @RequestMapping(value = "/getattendancelist", method = RequestMethod.POST)
    public List<Attendance> getTrainerAttendanceList(@Param("trainerId") String trainerId){

        List<Attendance> attendanceList = attendanceService.queryAttendanceListByTid(Long.valueOf(trainerId));

        return attendanceList;

    }

    @ResponseBody
    @RequestMapping(value = "/updateattendance", method = RequestMethod.POST)
    public OperationResult updateAttendance(@Param("trainerId") String trainerId, @Param("status") String status, @Param("date") String date){
        Attendance attendance = new Attendance();
        attendance.setTid(Long.valueOf(trainerId));
        attendance.setStatus(status);
        attendance.setDate(Date.valueOf(date));
        OperationResult updateAttendanceResult = new OperationResult();
        updateAttendanceResult.setOpt("updateattendance");
        int result = attendanceService.add(attendance);
        if (result == 1 ){
            updateAttendanceResult.setCode(1);
            updateAttendanceResult.setMessage("考勤记录更新成功");
        }
        if (result == 0 ){
            updateAttendanceResult.setCode(0);
            updateAttendanceResult.setMessage("考勤记录更新失败");
        }
        return updateAttendanceResult;
    }

    @ResponseBody
    @RequestMapping(value = "/sendfeedback", method = RequestMethod.POST)
    public OperationResult sendFeedback(@Param("title") String title, @Param("content") String content, @Param("ownerId") String ownerId, @Param("date") String date){
        Feedback feedback = new Feedback();
        feedback.setTitle(title);
        feedback.setContent(content);
        feedback.setOid(Long.valueOf(ownerId));
        feedback.setDate(Date.valueOf(date));
        OperationResult sendFeedbackResult = new OperationResult();
        sendFeedbackResult.setOpt("sendfeedback");
        int result = feedbackService.add(feedback);
        if (result == 1){
            sendFeedbackResult.setCode(1);
            sendFeedbackResult.setMessage("反馈发布成功");
        }
        if(result == 0){
            sendFeedbackResult.setCode(0);
            sendFeedbackResult.setMessage("反馈发布失败，请重试");
        }
        return sendFeedbackResult;

    }

    @ResponseBody
    @RequestMapping(value = "/sendnotifi", method = RequestMethod.POST)
    public OperationResult sendNotifi(@Param("title") String title, @Param("content") String content, @Param("adminId") String adminId, @Param("date") String date){
        Workplan workplan = new Workplan();
        workplan.setTitle(title);
        workplan.setContent(content);
        workplan.setDate(Date.valueOf(date));
        workplan.setAid(Long.valueOf(adminId));
        OperationResult sendNotifiResult = new OperationResult();
        sendNotifiResult.setOpt("sendnotifi");
        int result = workplanService.add(workplan);
        if (result == 1){
            sendNotifiResult.setCode(1);
            sendNotifiResult.setMessage("通知发布成功");
        }
        if (result == 0){
            sendNotifiResult.setCode(0);
            sendNotifiResult.setMessage("通知发布失败，请重试");
        }
        return sendNotifiResult;

    }

    @ResponseBody
    @RequestMapping(value = "/notifigetadmin", method = RequestMethod.POST)
    public Admin notifiGetAdmin(@Param("adminId") String adminId){
        Long aid = Long.valueOf(adminId);
        return adminService.queryAdminById(aid);


    }

    @ResponseBody
    @RequestMapping(value = "/trainergetnotifilist", method = RequestMethod.GET)
    public List<Workplan> getWorkplanListAll(){
        List<Workplan> workplanListAll = workplanService.queryWorkplanListAll();
        return workplanListAll;
    }

    @ResponseBody
    @RequestMapping(value = "/updatepethr", method = RequestMethod.POST)
    public OperationResult updatePetHR(@Param("petId") String petId, @Param("petHR") String petHR){
        OperationResult updatePetHRResult = new OperationResult();
        updatePetHRResult.setOpt("updatepethr");
        Pet pet = new Pet();
        pet.setId(Long.valueOf(petId));
        pet.setHrStatus(petHR);
        int result = petService.update(pet);

        if (result == 1 ){
            updatePetHRResult.setCode(1);
            updatePetHRResult.setMessage("健康信息更新成功");
        }
        if (result ==0 ){
            updatePetHRResult.setCode(0);
            updatePetHRResult.setMessage("更新失败，请重试");
        }

        return updatePetHRResult;
    }

    @ResponseBody
    @RequestMapping(value = "/gettrainerlistall", method = RequestMethod.GET)
    public List<Trainer> getTrainerListAll(){

        List<Trainer> trainerListAll = trainerService.queryTrainerListAll();
        return trainerListAll;

    }

    @ResponseBody
    @RequestMapping(value = "/getcourselistall", method = RequestMethod.GET)
    public List<Course> getCourseListAll(){

        List<Course> courseListAll = courseService.queryCourseListAll();
        return courseListAll;

    }

    @ResponseBody
    @RequestMapping(value = "/getpetlistall", method = RequestMethod.GET)
    public PetListResult getPetListAll(){

        List<Pet> petList = petService.queryPetListAll();
        PetListResult petListResult = new PetListResult();
        petListResult.setOpt("getpetlistall");
        if (petList != null ){
            petListResult.setCode(1);
            petListResult.setPetList(petList);

        }
        if (petList == null){
            petListResult.setCode(0);
            petListResult.setPetList(null);
        }

        return petListResult;

    }

    @Transactional
    @ResponseBody
    @RequestMapping(value = "/ownergetpetlist", method = RequestMethod.POST)
    public PetListResult ownerGetPetList(@Param("ownerId") String ownerId){

        List<Pet> petList = petService.queryPetListByOid(Long.valueOf(ownerId));
        PetListResult petListResult = new PetListResult();
        petListResult.setOpt("ownergetpetlist");
        if (petList.size()!=0 ){
            petListResult.setCode(1);
            petListResult.setPetList(petList);

        }
        if (petList.size()==0){
            petListResult.setCode(0);
            petListResult.setPetList(null);
        }

        return petListResult;

    }

    @ResponseBody
    @RequestMapping(value = "/petgetcourse", method = RequestMethod.POST)
    public Course petGetCourse(@Param("courseId") String courseId){

        Long cid = Long.valueOf(courseId);
        Course course = courseService.queryCourseById(cid);
        return course;


    }

    @Transactional
    @ResponseBody
    @RequestMapping(value = "/trainerlogin", method = {RequestMethod.POST, RequestMethod.GET})
    public LoginResult loginTrainer(@Param("username") String username, @Param("password") String password){

        Trainer trainer = trainerService.login(username, password);
        LoginResult loginResult = new LoginResult();
        loginResult.setOpt("login");
        if (trainer!=null){
            //登陆成功
            loginResult.setCode(1);
            loginResult.setUser(trainer);
        }
        if (trainer==null){
            //登陆失败
            loginResult.setCode(0);
            loginResult.setUser(null);
        }

        return loginResult;

    }

    @Transactional
    @ResponseBody
    @RequestMapping(value = "/adminlogin", method = {RequestMethod.POST, RequestMethod.GET})
    public LoginResult loginAdmin(@Param("username") String username, @Param("password") String password){

        Admin admin = adminService.login(username, password);
        LoginResult loginResult = new LoginResult();
        loginResult.setOpt("login");
        if (admin!=null){
            //登陆成功
            loginResult.setCode(1);
            loginResult.setUser(admin);
        }
        if (admin==null){
            //登陆失败
            loginResult.setCode(0);
            loginResult.setUser(null);
        }

        return loginResult;

    }

    @Transactional
    @ResponseBody
    @RequestMapping(value = "/ownerlogin", method = {RequestMethod.POST, RequestMethod.GET})
    public LoginResult loginOwner(@Param("username") String username, @Param("password") String password){

        Owner owner = ownerService.loginByUsernameAndPassword(username, password);
        LoginResult loginResult = new LoginResult();
        loginResult.setOpt("login");
        if (owner!=null){
            //登陆成功
            loginResult.setCode(1);
            loginResult.setUser(owner);
        }
        if (owner==null){
            //登陆失败
            loginResult.setCode(0);
            loginResult.setUser(null);
        }

        return loginResult;

    }


    @Transactional
    @ResponseBody
    @RequestMapping(value = "/register", method = {RequestMethod.POST, RequestMethod.GET})
    public OperationResult registerOwner(@RequestParam("username") String username, @RequestParam("password") String password,
                                         @RequestParam("name") String name, @RequestParam("email") String email,
                                         @RequestParam("sex") String sex, @RequestParam("age") String age){
        Owner owner = new Owner();
        owner.setUsername(username);
        owner.setPassword(password);
        owner.setName(name);
        owner.setEmail(email);
        owner.setSex(sex);
        owner.setAge(Integer.valueOf(age));
        String jsonResult;
        int result = ownerService.add(owner);
        OperationResult registerResult = new OperationResult();
        registerResult.setOpt("register");
        if (result == 1){
            //注册成功
            registerResult.setCode(1);
            registerResult.setMessage(MESSAGE_REGISTER_SUCCESS);
        }
        if (result == 0){
            //注册失败，用户名重复
            registerResult.setCode(0);
            registerResult.setMessage(MESSAGE_REGISTER_FAILURE);
        }
        jsonResult = JSON.toJSONString(registerResult);

        logger.debug("jsonResult="+jsonResult);



        return registerResult;
    }

}
