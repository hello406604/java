package com.kaishengit.crm.controller;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.service.AccountService;
import com.kaishengit.dto.AjaxResult;
import com.kaishengit.exception.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * Created by 帅灏灏 on 2017/7/19.
 */
@Controller
public class LoginController {

    @Autowired
    private AccountService accountService;



    @GetMapping("/")
    public String login() {
        return "login";
    }

    @PostMapping("/")
    @ResponseBody
    public AjaxResult login(String mobile, String password, HttpSession session) {
        try {
            Account account = accountService.findAccountByMobile(mobile, password);
            session.setAttribute("curr_user",account);
        } catch (AuthenticationException ex) {
            return AjaxResult.error(ex.getMessage());
        }
        return AjaxResult.success();
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session , RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("message","你已安全退出");
        return "redirect:/";
    }

}
