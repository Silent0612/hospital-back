package com.dzqc.cloud.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dzqc.cloud.common.Message;
import com.dzqc.cloud.common.ResultObject;
import com.dzqc.cloud.entity.Userinfo;
import com.dzqc.cloud.service.UserService;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
@RequestMapping("/api")
public class UserinfoController {
    @Autowired
    private UserService userService;
    
    /**
     * 创建测试用户（仅用于测试）
     */
    @GetMapping("/user/createTest")
    public ResultObject createTestUser() {
        try {
            // 创建测试用户1
            Userinfo user1 = new Userinfo();
            user1.setUsername("测试医生");
            user1.setPassword("123456");
            user1.setRoleid(1);
            user1.setPhone("13000000003");
            user1.setGender("1");
            user1.setAddress("测试地址");
            user1.setState(1);
            user1.setBirthday(new Date());
            user1.setDiType(1);
            user1.setInstatus(1);
            user1.setCid("110101199001011234");
            userService.insertUser(user1);

            // 创建测试用户2
            Userinfo user2 = new Userinfo();
            user2.setUsername("测试医生2");
            user2.setPassword("123456");
            user2.setRoleid(1);
            user2.setPhone("13724741064");
            user2.setGender("1");
            user2.setAddress("测试地址2");
            user2.setState(1);
            user2.setBirthday(new Date());
            user2.setDiType(1);
            user2.setInstatus(1);
            user2.setCid("110101199001011235");
            userService.insertUser(user2);

            return ResultObject.success("测试用户创建成功");
        } catch (Exception e) {
            return ResultObject.error("创建测试用户失败：" + e.getMessage());
        }
    }

    /**
     * 获取所有用户信息（测试用）
     */
    @GetMapping("/user/list")
    public ResultObject listUsers() {
        try {
            return ResultObject.success(userService.selectAll());
        } catch (Exception e) {
            return ResultObject.error(Message.SERVER_ERROR);
        }
    }

    /**
     * 根据手机号查询用户信息
     * @return
     */
    @RequestMapping("/user/findByPhone")
    public ResultObject findByPhone(String phone) {
        try {
            Userinfo userinfo = this.userService.selectByPhone(phone);
            if (userinfo != null) {
                return ResultObject.success(userinfo);
            } else {
                return ResultObject.error("查询的" + phone + "手机号不存在");
            }
        } catch (Exception e) {
            return ResultObject.error(Message.SERVER_ERROR);
        }
    }

    /**
     * 退出
     * @param request
     * @return
     */
    @RequestMapping("/shiro/logout")
    public ResultObject logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("userinfo");
        return ResultObject.success("成功注销");
    }

    /**
     * 用户登录
     * @param phone 手机号
     * @param password 密码
     * @return
     */
    @PostMapping("/shiro/login")
    public ResultObject login(@RequestParam String phone, @RequestParam String password, HttpServletRequest request) {
        try {
            System.out.println("接收到登录请求：phone=" + phone + ", password=" + password);
            
            if (phone == null || phone.trim().isEmpty()) {
                return ResultObject.error("手机号不能为空");
            }
            if (password == null || password.trim().isEmpty()) {
                return ResultObject.error("密码不能为空");
            }

            // 根据手机号查询用户
            Userinfo userinfo = userService.selectByPhone(phone);
            System.out.println("查询到的用户信息：" + userinfo);
            
            if (userinfo == null) {
                return ResultObject.error("手机号未注册");
            }
            
            // 验证密码
            if (!password.equals(userinfo.getPassword())) {
                return ResultObject.error("密码错误");
            }
            
            // 保存用户信息到session
            HttpSession session = request.getSession();
            session.setAttribute("userinfo", userinfo);
            
            // 返回成功结果
            return ResultObject.success(userinfo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObject.error("登录失败：" + e.getMessage());
        }
    }

    /**
     * 病人注册
     */
    @PostMapping("/patient/register")
    public ResultObject patientRegister(@RequestBody Userinfo userinfo) {
        try {
            System.out.println("接收到注册请求：" + userinfo);
            
            // 参数校验
            if (userinfo.getUsername() == null || userinfo.getUsername().trim().isEmpty()) {
                return ResultObject.error("用户名不能为空");
            }
            if (userinfo.getPhone() == null || userinfo.getPhone().trim().isEmpty()) {
                return ResultObject.error("手机号不能为空");
            }
            if (userinfo.getPassword() == null || userinfo.getPassword().trim().isEmpty()) {
                return ResultObject.error("密码不能为空");
            }
            if (userinfo.getBirthday() == null) {
                return ResultObject.error("出生日期不能为空");
            }
            if (userinfo.getGender() == null || userinfo.getGender().trim().isEmpty()) {
                return ResultObject.error("性别不能为空");
            }

            // 检查手机号是否已存在
            Userinfo existUser = userService.selectByPhone(userinfo.getPhone());
            if (existUser != null) {
                return ResultObject.error("该手机号已被注册");
            }

            // 设置用户角色为普通用户
            userinfo.setRoleid(2); // 2-普通用户
            userinfo.setState(1); // 1-启用
            userinfo.setInstatus(1); // 1-未住院
            
            // 保存用户信息
            int result = userService.insertUser(userinfo);
            if (result > 0) {
                return ResultObject.success("注册成功");
            } else {
                return ResultObject.error("注册失败，请稍后重试");
            }
        } catch (Exception e) {
            e.printStackTrace();
            String errorMsg = e.getMessage();
            if (errorMsg != null && errorMsg.contains("Duplicate entry")) {
                if (errorMsg.contains("username")) {
                    return ResultObject.error("该用户名已被使用");
                } else if (errorMsg.contains("phone")) {
                    return ResultObject.error("该手机号已被注册");
                }
            }
            return ResultObject.error("注册失败：系统错误，请稍后重试");
        }
    }

    /**
     * 病人登录
     */
    @PostMapping("/patient/login")
    public ResultObject patientLogin(@RequestBody Userinfo loginInfo) {
        try {
            System.out.println("接收到登录请求：" + loginInfo);
            
            if (loginInfo.getPhone() == null || loginInfo.getPhone().trim().isEmpty()) {
                return ResultObject.error("手机号不能为空");
            }
            if (loginInfo.getPassword() == null || loginInfo.getPassword().trim().isEmpty()) {
                return ResultObject.error("密码不能为空");
            }

            // 根据手机号查询用户
            Userinfo userinfo = userService.selectByPhone(loginInfo.getPhone());
            System.out.println("查询到的用户信息：" + userinfo);
            
            if (userinfo == null) {
                return ResultObject.error("手机号未注册");
            }
            
            // 验证密码
            if (!loginInfo.getPassword().equals(userinfo.getPassword())) {
                return ResultObject.error("密码错误");
            }
            
            // 检查是否是病人用户
            if (userinfo.getRoleid() != 2) {
                return ResultObject.error("非病人用户不能登录");
            }
            
            // 返回成功结果
            return ResultObject.success(userinfo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObject.error("登录失败：" + e.getMessage());
        }
    }

    @GetMapping("/user/department/{departmentId}")
    public ResultObject getDoctorsByDepartment(@PathVariable Integer departmentId) {
        try {
            // 查询指定科室的医生列表
            List<Userinfo> doctors = userService.selectByDepartment(departmentId);
            return ResultObject.success(doctors);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObject.error("获取医生列表失败");
        }
    }
}