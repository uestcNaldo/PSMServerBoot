package com.uestc.naldo.controller;

import com.uestc.naldo.domain.*;
import com.uestc.naldo.service.*;
import com.uestc.naldo.util.Static;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.util.List;


@Controller
@RequestMapping("/web")
public class WebManagementController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private OwnerService ownerService;
    @Autowired
    private TrainerService trainerService;
    @Autowired
    private PetService petService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private WorkplanService workplanService;
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private PhotoService photoService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping(value = "/index")
    public ModelAndView accessToIndex(){
        ModelAndView modelAndView = new ModelAndView("index");

        logger.debug("get index");

        return modelAndView;
    }

    @RequestMapping(value = "/login")
    public String accessToLogin(Model model){

        model.addAttribute("adminLoginForm", new Admin());


        return "login";

    }

    @RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
    public ModelAndView adminLogin(@ModelAttribute(value = "adminLoginForm") @Valid Admin adminInfo, BindingResult bindingResult,ModelAndView mv){

        Admin admin = adminService.login(adminInfo.getUsername(), adminInfo.getPassword());

        if (admin!=null){
            logger.debug("查询成功");
            mv.setViewName("redirect:/web/index");
        }
        else {
            logger.debug("管理员信息匹配失败");
            mv.addObject("login_message","用户名或密码错误，请重新输入");
            mv.setViewName("forward:/web/login");
        }
        logger.debug("login");

        return mv;
    }

    @RequestMapping("/register")
    public String accessToRegister(Model model){
        model.addAttribute("adminRegisterForm", new Admin());

        return "register";
    }

    @RequestMapping(value = "/adminRegister", method = RequestMethod.POST)
    public ModelAndView adminRegister(@ModelAttribute(value = "adminRegisterForm") @Valid Admin admin, BindingResult bindingResult,ModelAndView mv){

        int registerResult = adminService.add(admin);

        logger.debug("register result = "+registerResult);

        if (registerResult != 0){

            mv.addObject("register_message","注册成功");
            mv.setViewName("forward:/web/login");

        }
        else {
            mv.addObject("register_message","用户名存在，请重新输入");
            mv.setViewName("forward:/web/register");
        }
        return mv;
    }
    //宠物
    @RequestMapping("/petlist")
    public ModelAndView accessToPetList(){
        ModelAndView mv = new ModelAndView("petlist");
        mv.addObject("searchPetForm", new Pet());
        List<Pet> petListAll = petService.queryPetListAll();
        mv.addObject("petlist", petListAll);

        return mv;
    }
    //按照名字搜索
    @RequestMapping("/searchpetlist")
    public ModelAndView searchPetListByName(@ModelAttribute(value = "searchPetForm") @Valid Pet petForSearch, BindingResult bindingResult, ModelAndView mv){

        List<Pet> petListForSearch = petService.queryPetListByName(petForSearch.getName());
        mv.setViewName("petlist");
        mv.addObject("petlist",petListForSearch);
        if (petListForSearch.isEmpty()){
            mv.addObject("searchResultMessage","没有结果");
        }
        return mv;
    }

    //查看宠物信息界面
    @RequestMapping("/petdetail")
    public ModelAndView accessToPetDetail(@RequestParam("id") String id){
        ModelAndView mv = new ModelAndView("petdetail");
        Pet pet = petService.queryPetById(Long.valueOf(id));
        Owner owner = ownerService.queryOwnerById(pet.getOid());
        Course course = courseService.queryCourseById(pet.getCid());
        List<Course> courseList = courseService.queryCourseListAll();
        List<Owner> ownerList = ownerService.queryOwnerListAll();

        mv.addObject("petUpdateForm",new Pet());
        mv.addObject("pet",pet);
        mv.addObject("owner",owner);
        mv.addObject("course",course);
        mv.addObject("ownerList", ownerList);
        mv.addObject("courseList",courseList);

        return mv;
    }
    //更新宠物信息
    @RequestMapping(value = "/updatepet", method = RequestMethod.POST)
    public ModelAndView updatePetDetail(@RequestParam("id") Long id, @ModelAttribute(value = "petUpdateForm") @Valid Pet petUpdateForm, BindingResult bindingResult){
        ModelAndView mv = new ModelAndView();
        petUpdateForm.setId(id);
        int result = petService.update(petUpdateForm);
        logger.debug("Pet Update = "+result);
        mv.setViewName("redirect:/web/petlist");
        return mv;
    }

    //添加宠物界面获取
    @RequestMapping("/petadd")
    public ModelAndView accessToPetAdd(){
        ModelAndView mv = new ModelAndView("petadd");
        List<Course> courseList = courseService.queryCourseListAll();
        List<Owner> ownerList = ownerService.queryOwnerListAll();

        mv.addObject("petAddForm",new Pet());
        mv.addObject("ownerList", ownerList);
        mv.addObject("courseList",courseList);


        return mv;
    }
    //添加宠物操作

    @RequestMapping(value = "/addpet", method = RequestMethod.POST)
    public ModelAndView addPet(@ModelAttribute(value = "petAddForm") @Valid Pet petAddForm, BindingResult bindingResult){
        ModelAndView mv = new ModelAndView();
        logger.debug("hrStatus = "+petAddForm.getHrStatus());
        int result = petService.add(petAddForm);

        logger.debug("Result = "+result);

        mv.setViewName("redirect:/web/petlist");
        return mv;
    }

    //删除宠物操作
    @RequestMapping("/deletepet")
    public ModelAndView deletePet(@RequestParam("id") String id){
        ModelAndView mv = new ModelAndView();
        int result = petService.deleteById(Long.valueOf(id));
        if(result == 1){

            mv.addObject("deleteResultMessage","删除成功");
            mv.setViewName("forward:/web/petlist");
        }

        logger.debug("Result Delete = "+result);

        return mv;
    }


    //课程
    @RequestMapping("/courselist")
    public ModelAndView accessToCourseList(){
        ModelAndView mv = new ModelAndView("courselist");
        mv.addObject("searchCourseForm", new Course());
        List<Course> courseListAll = courseService.queryCourseListAll();
        mv.addObject("courselist", courseListAll);

        return mv;
    }
    //按照名字搜索
    @RequestMapping("/searchcourselist")
    public ModelAndView searchCourseListByName(@ModelAttribute(value = "searchCourseForm") @Valid Course courseForSearch, BindingResult bindingResult, ModelAndView mv){

        List<Course> courseListForSearch = courseService.queryCourseListByName(courseForSearch.getName());
        mv.setViewName("courselist");
        mv.addObject("courselist",courseListForSearch);
        if (courseListForSearch.isEmpty()){
            mv.addObject("searchResultMessage","没有结果");
        }
        return mv;
    }

    //查看课程信息
    @RequestMapping("/coursedetail")
    public ModelAndView accessToCourseDetail(@RequestParam("id") String id){
        ModelAndView mv = new ModelAndView("coursedetail");
        Course course = courseService.queryCourseById(Long.valueOf(id));

        mv.addObject("courseUpdateForm",new Course());

        mv.addObject("course",course);

        return mv;
    }

    //更新课程信息操作
    @RequestMapping(value = "updatecourse", method = RequestMethod.POST)
    public ModelAndView updateCourseDetail(@RequestParam("id") Long id, @ModelAttribute(value = "courseUpdateForm") @Valid Course courseUpdateForm, BindingResult bindingResult){
        ModelAndView mv = new ModelAndView();

        courseUpdateForm.setId(Long.valueOf(id));
        int result = courseService.update(courseUpdateForm);


        mv.setViewName("redirect:/web/courselist");
        return mv;
    }
    //获取课程添加界面
    @RequestMapping("/courseadd")
    public ModelAndView accessToCourseAdd(){
        ModelAndView mv = new ModelAndView("courseadd");

        mv.addObject("courseAddForm",new Course());

        return mv;
    }
    //添加课程
    @RequestMapping("/addcourse")
    public ModelAndView addCourse(@ModelAttribute(value = "courseAddForm") @Valid Course courseAddForm, BindingResult bindingResult){
        ModelAndView mv = new ModelAndView();

        int result = courseService.add(courseAddForm);

        mv.setViewName("redirect:/web/courselist");

        return mv;
    }
    //删除课程
    @RequestMapping("/deletecourse")
    public ModelAndView deleteCourse(@RequestParam("id") String id){
        ModelAndView mv = new ModelAndView();
        int result = courseService.deleteById(Long.valueOf(id));
        if(result == 1){
            mv.addObject("deleteResultMessage","删除成功");
            mv.setViewName("forward:/web/courselist");
        }

        logger.debug("Result Delete = "+result);

        return mv;
    }

    //训练师列表
    @RequestMapping("/trainerlist")
    public ModelAndView accessToTrainerList(){
        ModelAndView mv = new ModelAndView("trainerlist");

        mv.addObject("searchTrainerForm", new Trainer());
        List<Trainer> trainerListAll = trainerService.queryTrainerListAll();
        mv.addObject("trainerlist", trainerListAll);

        return mv;
    }
    //按照训练师名字搜索
    @RequestMapping("/searchtrainerlist")
    public ModelAndView searchTrainerListByName(@ModelAttribute(value = "searchTrainerForm") @Valid Trainer trainerForSearch, BindingResult bindingResult, ModelAndView mv){

        List<Trainer> trainerListForSearch = trainerService.queryTrainerByName(trainerForSearch.getName());
        mv.setViewName("trainerlist");
        mv.addObject("trainerlist",trainerListForSearch);
        if (trainerListForSearch.isEmpty()){
            mv.addObject("searchResultMessage","没有结果");
        }
        return mv;
    }

    //查看训犬师详细信息
    @RequestMapping("/trainerdetail")
    public ModelAndView accessToTrainerDetail(@RequestParam("id") String id){
        ModelAndView mv = new ModelAndView("trainerdetail");
        Trainer trainer = trainerService.queryTrainerById(Long.valueOf(id));

        mv.addObject("trainerUpdateForm",new Trainer());

        mv.addObject("trainer",trainer);

        return mv;
    }

    //更新训练师信息操作

    @RequestMapping(value = "updatetrainer", method = RequestMethod.POST)
    public ModelAndView updateTrainerDetail(@RequestParam("id") Long id, @ModelAttribute(value = "trainerUpdateForm") @Valid Trainer trainerUpdateForm, BindingResult bindingResult){
        ModelAndView mv = new ModelAndView();

        trainerUpdateForm.setId(id);
        int result = trainerService.update(trainerUpdateForm);

        mv.setViewName("redirect:/web/trainerlist");
        return mv;
    }
    //获取训练师添加界面
    @RequestMapping("/traineradd")
    public ModelAndView accessToTrainerAdd(){
        ModelAndView mv = new ModelAndView("traineradd");
        mv.addObject("trainerAddForm",new Trainer());

        return mv;
    }
    //添加训练师
    @RequestMapping("/addtrainer")
    public ModelAndView addTrainer(@ModelAttribute(value = "trainerAddForm") @Valid Trainer trainerAddForm, BindingResult bindingResult){
        ModelAndView mv = new ModelAndView();

        int result = trainerService.add(trainerAddForm);
        if (result == 0){
            //添加不成功，用户名重复
            mv.addObject("addResultMessage","注册失败，用户名重复");
            mv.setViewName("forward:/web/traineradd");
        }else {
            mv.addObject("addResultMessage","注册成功");
            mv.setViewName("forward:/web/trainerlist");
        }
        return mv;
    }
    //删除训练师操作
    @RequestMapping("/deletetrainer")
    public ModelAndView deleteTrainer(@RequestParam("id") String id){
        ModelAndView mv = new ModelAndView();
        int result = trainerService.deleteById(Long.valueOf(id));
        if(result == 1){
            mv.addObject("deleteResultMessage","删除成功");
            mv.setViewName("forward:/web/trainerlist");
        }

        logger.debug("Result Delete = "+result);

        return mv;
    }

    //考勤记录列表

    @RequestMapping("/attendancelist")
    public ModelAndView accessToAttendanceList(){
        ModelAndView mv = new ModelAndView("attendancelist");
        mv.addObject("searchAttendanceItemForm",new AttendanceItem());
        List<AttendanceItem> attendanceItemListAll = attendanceService.queryAttendanceItemListAll();
        mv.addObject("attendanceitemlist",attendanceItemListAll);

        return mv;
    }
    //按照姓名和日期搜索
    @RequestMapping(value = "/searchattendanceitemlist", method = RequestMethod.POST)
    public ModelAndView searchAttendanceItemListByNameAndDate(@ModelAttribute(value = "searchAttendanceItemForm") @Valid AttendanceItem attendanceItemForSearch, BindingResult bindingResult, ModelAndView mv){
        mv.setViewName("attendancelist");

        List<AttendanceItem> attendanceItemListByNameAndDate = attendanceService.queryAttendanceItemListByNameAndDate(attendanceItemForSearch.getTname(), attendanceItemForSearch.getDate());

        mv.addObject("attendanceitemlist", attendanceItemListByNameAndDate);
        if (attendanceItemListByNameAndDate.isEmpty()){
            mv.addObject("searchResultMessage","没有结果");
        }
        return mv;
    }

    //查看考勤记录详细信息
    @RequestMapping("/attendanceitemdetail")
    public ModelAndView accessToAttendanceItemDetail(@RequestParam("id") String id){
        ModelAndView mv = new ModelAndView("attendancedetail");
        Attendance attendance = attendanceService.queryAttendanceById(Long.valueOf(id));

        Trainer trainer = trainerService.queryTrainerById(attendance.getTid());

        mv.addObject("attendanceUpdateForm",new Attendance());
        mv.addObject("attendance",attendance);
        mv.addObject("trainer",trainer);


        return mv;
    }
    //更新考勤记录信息
    @RequestMapping(value = "/updateattendance", method = RequestMethod.POST)
    public ModelAndView updateAttendance(@RequestParam("id") Long id, @ModelAttribute(value = "attendanceUpdateForm") @Valid Attendance attendanceUpdateForm, BindingResult bindingResult){
        ModelAndView mv = new ModelAndView();
        attendanceUpdateForm.setId(id);
        int result = attendanceService.update(attendanceUpdateForm);

        if (result != 0){
            mv.addObject("updateResultMessage","考勤信息更新成功");
            mv.setViewName("forward:/web/attendancelist");
        }else {
            mv.addObject("updateResultMessage","考勤信息更新失败");
        }

        return mv;
    }

    //删除考勤记录
    @RequestMapping("/deleteattendanceitem")
    public ModelAndView deleteAttendance(@RequestParam("id") String id){
        ModelAndView mv = new ModelAndView();
        int result = attendanceService.deleteById(Long.valueOf(id));
        if(result == 1){
            mv.addObject("deleteResultMessage","删除成功");
            mv.setViewName("forward:/web/attendancelist");
        }else {
            mv.addObject("deleteResultMessage","删除失败");
            mv.setViewName("forward:/web/attendancelist");
        }

        logger.debug("Result Delete = "+result);

        return mv;
    }


    //主人列表
    @RequestMapping("/ownerlist")
    public ModelAndView accessToOwnerList(){
        ModelAndView mv = new ModelAndView("ownerlist");
        mv.addObject("searchOwnerForm",new Owner());
        List<Owner> ownerListAll = ownerService.queryOwnerListAll();
        mv.addObject("ownerlist",ownerListAll);

        return mv;
    }
    //按照主人名字搜索
    @RequestMapping("/searchownerlist")
    public ModelAndView searchOwnerListByName(@ModelAttribute(value = "searchOwnerForm") @Valid Owner ownerForSearch, BindingResult bindingResult, ModelAndView mv){

        List<Owner> ownerListForSearch = ownerService.queryOwnerListByName(ownerForSearch.getName());
        mv.setViewName("ownerlist");
        mv.addObject("ownerlist",ownerListForSearch);
        if (ownerListForSearch.isEmpty()){
            mv.addObject("searchResultMessage","没有结果");
        }
        return mv;
    }
    //查看主人详细信息
    @RequestMapping("/ownerdetail")
    public ModelAndView accessToOwnerDetail(@RequestParam("id") String id){
        ModelAndView mv = new ModelAndView("ownerdetail");
        Long oid = Long.valueOf(id);
        Owner owner = ownerService.queryOwnerById(oid);
        List<Pet> petListByOid = petService.queryPetListByOid(oid);
        mv.addObject("ownerUpdateForm",new Owner());

        mv.addObject("owner", owner);
        mv.addObject("petlist",petListByOid);
        return mv;
    }
    //更新主人信息
    @RequestMapping(value = "updateowner", method = RequestMethod.POST)
    public ModelAndView updateOwnerDetail(@RequestParam("id") Long id, @ModelAttribute(value = "ownerUpdateForm") @Valid Owner ownerUpdateForm, BindingResult bindingResult){
        ModelAndView mv = new ModelAndView();

        ownerUpdateForm.setId(Long.valueOf(id));
        int result = ownerService.update(ownerUpdateForm);

        if (result != 0){
            mv.addObject("updateResultMessage","主人信息更新成功");
        }

        mv.setViewName("redirect:/web/ownerlist");
        return mv;
    }
    //获取宠物主人添加界面
    @RequestMapping("/owneradd")
    public ModelAndView accessToOwnerAdd(){
        ModelAndView mv = new ModelAndView("owneradd");
        mv.addObject("ownerAddForm",new Owner());

        return mv;
    }
    //添加宠物主人
    @RequestMapping("/addowner")
    public ModelAndView addOwner(@ModelAttribute(value = "ownerAddForm") @Valid Owner ownerAddForm, BindingResult bindingResult){
        ModelAndView mv = new ModelAndView();

        int result = ownerService.add(ownerAddForm);
        if (result == 0){
            //添加不成功，用户名重复
            mv.addObject("addResultMessage","注册失败，用户名重复");
            mv.setViewName("forward:/web/owneradd");
        }else {
            mv.addObject("addResultMessage","注册成功");
            mv.setViewName("forward:/web/ownerlist");
        }
        return mv;
    }
    //删除宠物主人
    @RequestMapping("/deleteowner")
    public ModelAndView deleteOwner(@RequestParam("id") String id){
        ModelAndView mv = new ModelAndView();
        int result = ownerService.deleteById(Long.valueOf(id));
        if(result == 1){
            mv.addObject("deleteResultMessage","删除成功");
            mv.setViewName("forward:/web/ownerlist");
        }

        logger.debug("Result Delete = "+result);

        return mv;
    }

    //反馈信息列表查询
    @RequestMapping("/feedbacklist")
    public ModelAndView accessToFeedbackList(){
        ModelAndView mv = new ModelAndView("feedbacklist");
        mv.addObject("searchFeedbackItemForm",new FeedbackItem());

        List<FeedbackItem> feedbackItemListAll = feedbackService.queryFeedbackItemListAll();
        mv.addObject("feedbackitemlist",feedbackItemListAll);

        return mv;
    }
    //按照主人名和日期搜索
    @RequestMapping(value = "/searchfeedbackitemlist", method = RequestMethod.POST)
    public ModelAndView searchFeedbackItemListByNameAndDate(@ModelAttribute(value = "searchFeedbackItemForm") @Valid FeedbackItem feedbackItemForSearch, BindingResult bindingResult, ModelAndView mv){
        mv.setViewName("feedbacklist");

        List<FeedbackItem> feedbackItemListForSearch = feedbackService.queryFeedbackItemListByNameAndDate(feedbackItemForSearch.getOname(), feedbackItemForSearch.getDate());

        mv.addObject("feedbackitemlist", feedbackItemListForSearch);
        if (feedbackItemListForSearch.isEmpty()){
            mv.addObject("searchResultMessage","没有结果");
            mv.setViewName("forward:/web/feedbacklist");
        }

        return mv;
    }
    //查看反馈详细信息
    @RequestMapping("/feedbackdetail")
    public ModelAndView accessToFeedbackDetail(@RequestParam("id") String id){
        ModelAndView mv = new ModelAndView("feedbackdetail");
        Long fbid = Long.valueOf(id);
        Feedback feedback = feedbackService.queryFeedbackById(fbid);
        Owner owner = ownerService.queryOwnerById(feedback.getOid());

        mv.addObject("feedback", feedback);
        mv.addObject("owner", owner);

        return mv;
    }
    @RequestMapping("/deletefeedback")
    public ModelAndView deleteFeedback(@RequestParam("id") String id){
        ModelAndView mv = new ModelAndView();
        int result = feedbackService.deleteById(Long.valueOf(id));
        if(result == 1){
            mv.addObject("deleteResultMessage","反馈删除成功");
            mv.setViewName("forward:/web/feedbacklist");
        }else {
            mv.addObject("deleteResultMessage","反馈删除失败");
            mv.setViewName("forward:/web/feedbacklist");
        }
        return mv;
    }

    //获取通知列表
    @RequestMapping("/notifilist")
    public ModelAndView accessToNotifiList(){
        ModelAndView mv = new ModelAndView("notificationlist");

        mv.addObject("searchNotifiForm", new Workplan());
        List<Workplan> notifiListAll = workplanService.queryWorkplanListAll();
        mv.addObject("notifilist",notifiListAll);


        return mv;
    }
    //按照标题和日期搜索
    @RequestMapping(value = "/searchnotifilist", method = RequestMethod.POST)
    public ModelAndView searchNotifiListByTitleAndDate(@ModelAttribute(value = "searchNotifiForm") @Valid Workplan notifiForSearch, BindingResult bindingResult, ModelAndView mv){
        mv.setViewName("notificationlist");

        List<Workplan> notifiListForSearch = workplanService.queryWorkplanListByTitleAndDate(notifiForSearch.getTitle(),notifiForSearch.getDate());
        mv.addObject("notifilist",notifiListForSearch);
        if (notifiListForSearch.isEmpty()){
            mv.addObject("searchResultMessage","没有结果");
        }

        return mv;
    }
    //查看通知详细信息
    @RequestMapping("/notifidetail")
    public ModelAndView accessToNotifiDetail(@RequestParam("id") String id){
        ModelAndView mv = new ModelAndView("notificationdetail");
        Long wpid = Long.valueOf(id);
        Workplan notifi = workplanService.queryWorkplanById(wpid);
        Admin admin = adminService.queryAdminById(notifi.getAid());
        mv.addObject("notifiUpdateForm",new Workplan());

        mv.addObject("notifi",notifi);
        mv.addObject("admin",admin);
        return mv;
    }
    //更新通知信息
    @RequestMapping(value = "updatenotifi", method = RequestMethod.POST)
    public ModelAndView updateNotifiDetail(@RequestParam("id") Long id, @ModelAttribute(value = "notifiUpdateForm") @Valid Workplan notifiUpdateForm, BindingResult bindingResult){
        ModelAndView mv = new ModelAndView();

        notifiUpdateForm.setId(Long.valueOf(id));
        int result = workplanService.update(notifiUpdateForm);

        if (result != 0){
            mv.addObject("updateResultMessage","通知信息更新成功");
        }else {
            mv.addObject("updateResultMessage","保存失败");
        }

        mv.setViewName("forward:/web/notifilist");
        return mv;
    }

    //获取添加通知界面
    @RequestMapping("/notifiadd")
    public ModelAndView accessToNotifiAdd(){
        ModelAndView mv = new ModelAndView("notificationadd");
        mv.addObject("notifiAddForm",new Workplan());
        List<Admin> adminListAll = adminService.queryAdminListAll();
        mv.addObject("adminList",adminListAll);

        return mv;
    }
    //发布通知
    @RequestMapping("/addnotifi")
    public ModelAndView addOwner(@ModelAttribute(value = "notifiAddForm") @Valid Workplan notifiAddForm, BindingResult bindingResult){
        ModelAndView mv = new ModelAndView();

        int result = workplanService.add(notifiAddForm);
        if (result == 0){
            //添加不成功，用户名重复
            mv.addObject("addResultMessage","发布失败");
            mv.setViewName("forward:/web/notifiadd");
        }else {
            mv.addObject("addResultMessage","发布通知成功");
            mv.setViewName("forward:/web/notifilist");
        }
        return mv;
    }

    //删除通知
    @RequestMapping("/deletenotifi")
    public ModelAndView deleteNotifi(@RequestParam("id") String id){
        ModelAndView mv = new ModelAndView();
        int result = workplanService.deleteById(Long.valueOf(id));
        if(result == 1){
            mv.addObject("deleteResultMessage","删除成功");
            mv.setViewName("forward:/web/notifilist");
        }

        logger.debug("Result Delete = "+result);

        return mv;
    }

    //获取图片列表界面
    @RequestMapping(value = "/photolist")
    public ModelAndView accessToPhotoList(){
        ModelAndView mv = new ModelAndView("photolist");
        List<Photo> photoListAll = photoService.queryPhotoListAll();
        mv.addObject("photolistall",photoListAll);
        return mv;
    }

    //获取图片添加界面
    @RequestMapping(value = "/photoadd", method = RequestMethod.GET)
    public ModelAndView accessToPhotoAdd(){
        ModelAndView mv = new ModelAndView("photoadd");

        return mv;
    }
    //添加图片操作
    @RequestMapping(value = "/addphoto", method = RequestMethod.POST)
    public ModelAndView addPhoto(@RequestParam("photo")MultipartFile photoFile, ModelAndView mv, HttpSession session) throws Exception{
        String path = Static.IMAGEPATH;
        String fileName = photoFile.getOriginalFilename();

        photoFile.transferTo(new File(path+File.separator+fileName));
        Photo photo = new Photo();
        photo.setName(fileName);
        photo.setPath(path);
        int result = photoService.add(photo);
        if (result == 1){
            mv.addObject("AddResult","添加成功");
        }
        if (result == 0){
            mv.addObject("AddResult","添加失败");
        }
        mv.setViewName("forward:/web/photolist");
        return mv;
    }



    //获取管理员列表
    @RequestMapping("/adminlist")
    public ModelAndView accessToAdminList(){
        ModelAndView mv = new ModelAndView("adminlist");
        List<Admin> adminListAll = adminService.queryAdminListAll();
        mv.addObject("adminList",adminListAll);

        return mv;
    }

    //查看管理员详细信息
    @RequestMapping("/admindetail")
    public ModelAndView accessToAdminDetail(@RequestParam("id") String id){
        ModelAndView mv = new ModelAndView("admindetail");

        Admin admin = adminService.queryAdminById(Long.valueOf(id));
        mv.addObject("adminUpdateForm",new Admin());

        mv.addObject("admin",admin);
        return mv;
    }
    //更新管理员信息
    @RequestMapping(value = "updateadmin", method = RequestMethod.POST)
    public ModelAndView updateAdminDetail(@RequestParam("id") Long id, @ModelAttribute(value = "adminUpdateForm") @Valid Admin adminUpdateForm, BindingResult bindingResult){
        ModelAndView mv = new ModelAndView();

        adminUpdateForm.setId(Long.valueOf(id));
        int result = adminService.update(adminUpdateForm);

        if (result != 0){
            mv.addObject("updateResultMessage","管理员信息更新成功");
        }else {
            mv.addObject("updateResultMessage","信息更新失败");
        }

        mv.setViewName("forward:/web/adminlist");
        return mv;
    }
    //删除管理员
    @RequestMapping("/deleteadmin")
    public ModelAndView deleteAdmin(@RequestParam("id") String id){
        ModelAndView mv = new ModelAndView();
        int result = adminService.deleteById(Long.valueOf(id));
        if(result == 1){
            mv.addObject("deleteResultMessage","删除成功");
            mv.setViewName("forward:/web/adminlist");
        }

        logger.debug("Result Delete = "+result);

        return mv;
    }

}
