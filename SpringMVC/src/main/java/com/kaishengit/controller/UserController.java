package com.kaishengit.controller;

import com.kaishengit.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 帅灏灏 on 2017/7/13.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/hello")
    public String hello() {
        System.out.println("hello mvc");
        return "redirect:/show";
    }

    @GetMapping("/show")
    @ResponseBody
    public User show() {
        User user = new User();
        user.setAddress("北疆");
        user.setUserName("hello");
        return user;
    }

    @GetMapping
    public ModelAndView list(@RequestParam(required = false , defaultValue = "1")Integer page) {
        System.out.println("page:" + page);
        ModelAndView modelAndView = new ModelAndView("/user/list");
        User user = new User();
        user.setAddress("北疆");
        user.setUserName("hello");
        modelAndView.addObject(user);
        return modelAndView;
    }

    @GetMapping(value = "/validate/${userName}")
    @ResponseBody
    public String validateUser(@PathVariable String userName) {
        if ("tom".equals(userName)) {
            return "该账号已存在";
        } else {
            return "该账号可以使用";
        }
    }

    @GetMapping("/save")
    public String save () {
        return "user/save";
    }
}
