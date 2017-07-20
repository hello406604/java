package com.kaishengit.crm.controller;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Dept;
import com.kaishengit.crm.service.AccountService;
import com.kaishengit.crm.service.DeptService;
import com.kaishengit.dto.AjaxResult;
import com.kaishengit.dto.DataTableResult;
import com.kaishengit.dto.ZTreeNode;
import com.kaishengit.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public String acc(Model model, @RequestParam(required = false,defaultValue = "2") Integer deptId) {
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

    @PostMapping("/dept/new")
    @ResponseBody
    public AjaxResult addDept(Dept dept) {
        deptService.addDept(dept);
        return AjaxResult.success();
    }

    @PostMapping("/new")
    @ResponseBody
    public AjaxResult savaAccount(Account account, Integer[] deptId) {
        accountService.saveAccount(account,deptId);
        return AjaxResult.success();
    }

    @GetMapping("/load.json")
    @ResponseBody
    public DataTableResult<Account> loadAccountData(HttpServletRequest request) {
        String draw = request.getParameter("draw");
        String deptId = request.getParameter("deptId");
        Integer id = null;
        if(StringUtils.isNoneEmpty(deptId)) {
            id = Integer.valueOf(deptId);
        }
        //获取account的总数
        Long total = accountService.countAll();
        //获取account过滤后的数量
        Long  filtedTotal = accountService.countByDeptId(id);
        //当前页的数据
        List<Account> accountList = accountService.findByDeptId(id);

        return new DataTableResult<>(draw,total,filtedTotal,accountList);
    }

    @PostMapping("/del/{id:\\d+}")
    @ResponseBody
    public AjaxResult delById (@PathVariable Integer id){
        accountService.delById(id);
        return AjaxResult.success();
    }

    @GetMapping("/profile")
    public String profile() {
        return "manage/profile";
    }

    @PostMapping("/profile")
    @ResponseBody
    public AjaxResult profile(String oldPassword, String password , HttpSession httpSession){
        Account account = (Account) httpSession.getAttribute("curr_user");
        try {
            accountService.changePassword(oldPassword, password, account);
            httpSession.invalidate();
            return AjaxResult.success();
        } catch (ServiceException e) {
            return AjaxResult.error(e.getMessage());
        }
    }

}
