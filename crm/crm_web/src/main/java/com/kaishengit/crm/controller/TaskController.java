package com.kaishengit.crm.controller;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Task;
import com.kaishengit.crm.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController extends ParentController{

    @Autowired
    private TaskService taskService;

    @GetMapping
    public String home(Model model , HttpSession session ,
                        @RequestParam( required = false,defaultValue = "")String show) {
        Account account = getAccountInSession(session);
        boolean showAll = "all".equals(show);

        List<Task> tasks = taskService.findAllByAccountId(account.getId(),showAll);
        model.addAttribute("taskList",tasks);
        return "task/home";
    }

    @GetMapping("/new")
    public String newTask() {
        return "task/new";
    }
}
