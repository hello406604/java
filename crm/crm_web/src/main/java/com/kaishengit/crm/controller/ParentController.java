package com.kaishengit.crm.controller;

import com.kaishengit.crm.entity.Account;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

@Controller
public class ParentController {
    public Account getAccountInSession(HttpSession session) {
        return (Account)session.getAttribute("curr_user");
    }
}
