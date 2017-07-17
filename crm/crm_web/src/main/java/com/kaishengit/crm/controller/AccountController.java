package com.kaishengit.crm.controller;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Dept;
import com.kaishengit.crm.service.AccountService;
import com.kaishengit.crm.service.DeptService;
import com.kaishengit.dto.AjaxResult;
import com.kaishengit.dto.ZTreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by 帅灏灏 on 2017/7/17.
 */
@Controller()
@RequestMapping("/manage/account")
public class AccountController {
    @Autowired
    private AccountService  accountService;

    @Autowired
    private DeptService deptService;

//    @GetMapping
//    @ResponseBody
//    public List<Account> findAll(){
//        return accountService.findAllAccount();
//    }
    @GetMapping
    public String acc() {
        return "manage/accounts";
    }

    @PostMapping("/depts.json")
    @ResponseBody
    public List<ZTreeNode> loadDeptData() {
        List<Dept> depts = deptService.findAll();
        for(Dept dept : depts) {
            System.out.println(dept.getId() + "  " + dept.getDeptName() + "  " + dept.getpId() );
        }
        List<ZTreeNode> zTreeNodes = Lists.newArrayList(Collections2.transform(depts, new Function<Dept, ZTreeNode>() {

            @Nullable
            @Override
            public ZTreeNode apply(@Nullable Dept dept) {
                ZTreeNode zTreeNode = new ZTreeNode();
                zTreeNode.setId(dept.getId());
                zTreeNode.setName(dept.getDeptName());
                zTreeNode.setpId(dept.getpId());
                return zTreeNode;
            }
        }));
        return zTreeNodes;
    }

    @PostMapping("/dapt/new")
    @ResponseBody
    public AjaxResult addDept(Dept dept) {
        deptService.addDept(dept);
        return AjaxResult.success();
    }
}
