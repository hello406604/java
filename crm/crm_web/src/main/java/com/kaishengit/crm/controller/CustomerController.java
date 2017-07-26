package com.kaishengit.crm.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.kaishengit.crm.controller.exception.ForbiddenException;
import com.kaishengit.crm.controller.exception.NotFoundException;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.service.AccountService;
import com.kaishengit.crm.service.CustomerService;
import com.kaishengit.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller()
@RequestMapping("/customer")
public class CustomerController extends ParentController{
    @Autowired
    private AccountService accountService;

    @Autowired
    private CustomerService customerService;

    /**
     * 跳转至我的首页
     * @param pageNo
     * @param keyword
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/my")
    public String myCustomer (@RequestParam(required = false, defaultValue = "1",value = "p")Integer pageNo,
            @RequestParam(required = false,defaultValue = "")String keyword
            ,Model model, HttpSession session) {

        Account account = (Account) session.getAttribute("curr_user");
        Integer accId = account.getId();

        Map<String,Object> param = Maps.newHashMap();
        if (StringUtils.isNoneEmpty(keyword)){
            try {
                keyword = new String(keyword.getBytes("ISO8859-1"),"UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new ServiceException("编码异常");
            }
        }
        param.put("pageNo",pageNo);
        param.put("keyword",keyword);
        param.put("accId",accId);

        PageInfo<Customer> customerPageInfo = customerService.findByParam(param);

        model.addAttribute("pageInfo",customerPageInfo);
        return "customer/myCust";
    }

    /**
     * 跳转至新增客户页面
     * @param model
     * @return
     */
    @GetMapping("/my/new")
    public String newCustomer (Model model) {
        List<String> tradeList = customerService.findAllTrade();
        List<String> sourceList =customerService.findAllSource();
        model.addAttribute("tradeList",tradeList);
        model.addAttribute("sourceList",sourceList);
        return "customer/new_customer";
    }

    /**
     * 新增客户
     * @param customer
     * @param redirectAttributes
     * @param session
     * @return
     */
    @PostMapping("/my/new")
    public String newCustomer(Customer customer, RedirectAttributes redirectAttributes,HttpSession session){
        Account account = (Account) session.getAttribute("curr_user");
        customer.setAccountId(account.getId());
        customer.setCreateTime(new Date());
        customerService.save(customer);
        redirectAttributes.addFlashAttribute("message","添加客户成功");
        return "redirect:/customer/my";
    }

    /**
     *跳转至我的客户详情页
     * @param id
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/my/{id:\\d+}")
    public String myCust(@PathVariable Integer id, Model model,HttpSession session) {
        Account account = getAccountInSession(session);
        Customer customer = customerService.findById(id);
        if (customer == null) {
            throw new NotFoundException();
        }
        if (!account.getId().equals(customer.getAccountId())) {
            throw new ForbiddenException();
        }
        List<Account> accounts = accountService.findAllAccount();
        model.addAttribute("accList",accounts);
        model.addAttribute("customer",customer);
        return "customer/info";
    }

    /**
     * 跳转至我的客户修改页
     * @param id
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/my/edit/{id:\\d+}")
    public String editById(@PathVariable Integer id, Model model, HttpSession session) {
        Account account = getAccountInSession(session);
        Customer customer = customerService.findById(id);
        List<String> tradeList = customerService.findAllTrade();
        List<String> sourceList = customerService.findAllSource();
        if (customer == null) {
            throw new NotFoundException();
        }
        if (!account.getId().equals(customer.getAccountId())) {
            throw new ForbiddenException();
        }
        model.addAttribute("customer",customer);
        model.addAttribute("tradeList",tradeList);
        model.addAttribute("sourceList",sourceList);
        return "customer/edit_customer";
    }

    /**
     * 修改我的客户信息
     * @param customer
     * @param session
     * @return
     */
    @PostMapping("/my/edit/{id:\\d+}")
    public String editById(Customer customer, HttpSession session) {
        Account account = getAccountInSession(session);
        if (!account.getId().equals(customer.getAccountId())) {
            throw new ForbiddenException();
        }
        customerService.updateCustomer(customer);
        return "redirect:/customer/my/" + customer.getId();
    }

    /**
     * 根据ID删除客户
     * @param id
     * @param session
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/my/del/{id:\\d+}")
    public String delById(@PathVariable Integer id, HttpSession session, RedirectAttributes redirectAttributes) {
        Account account = getAccountInSession(session);
        Customer customer = customerService.findById(id);
        if (customer == null) {
            throw new NotFoundException();
        }
        if (!account.getId().equals(customer.getAccountId())) {
            throw new ForbiddenException();
        }
        redirectAttributes.addFlashAttribute("message","删除成功");
        customerService.delById(id);
        return "redirect:/customer/my";
    }

    /***
     * 将客户放入公海
     * @param id
     * @param session
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/my/share/{id:\\d+}")
    public String shareById(@PathVariable Integer id, HttpSession session , RedirectAttributes redirectAttributes) {
        Account account = getAccountInSession(session);
        Customer customer = customerService.findById(id);
        if (customer == null) {
            throw new NotFoundException();
        }
        if (!account.getId().equals(customer.getAccountId())) {
            throw new ForbiddenException();
        }
        redirectAttributes.addFlashAttribute("message","成功将 ["
                + customer.getCustName() + "] 放入公海");
        customerService.pullPubicCustomer(customer,account);
        return "redirect:/customer/my";
    }

    /**
     * 将客户转交给他人
     * @param id
     * @param accountId
     * @param session
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/my/{id:\\d+}/transfer/{accountId:\\d+}")
    public String transferById(@PathVariable Integer id,
                               @PathVariable Integer accountId,
                               HttpSession session , RedirectAttributes redirectAttributes) {
        Customer customer = customerService.findById(id);
        Account account = getAccountInSession(session);
        if (customer == null) {
            throw new NotFoundException();
        }
        if (!account.getId().equals(customer.getAccountId())) {
            throw new ForbiddenException();
        }
        Account aimAccount = accountService.findAccountById(accountId);
        if (aimAccount == null){
            throw new NotFoundException();
        }
        redirectAttributes.addFlashAttribute("message","成功将 [" +
                customer.getCustName() + "] 转交给" + "  " + aimAccount.getUserName());
        customerService.transferCustomer(customer,account,accountId);
        return "redirect:/customer/my";
    }

    /**
     * 导出excel
     * @param session
     * @param response
     * @throws IOException
     */
    @GetMapping("my/export")
    public void exportExcel(HttpSession session, HttpServletResponse response) throws IOException {

        Account account = (Account) session.getAttribute("curr_user");

        if(account == null) {
            throw new ForbiddenException();
        }
        //告诉浏览器输出的内容和MIME
        response.setContentType("application/vnd.ms-excel");
        //设置弹出对话框的文件名称
        response.addHeader("Content-Disponsition","attachment;filename=\"customer.xls\"");

        customerService.exportAccountCustomerToExcel(account,response.getOutputStream());
    }

    /**
     * 跳转至公海客户首页
     * @param pageNo
     * @param keyword
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/public")
    public String publicCust (@RequestParam(required = false, defaultValue = "1",value = "p")Integer pageNo,
                @RequestParam(required = false,defaultValue = "")String keyword
                ,Model model, HttpSession session) {

            Map<String,Object> param = Maps.newHashMap();
            if (StringUtils.isNoneEmpty(keyword)){
                try {
                    keyword = new String(keyword.getBytes("ISO8859-1"),"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    throw new ServiceException("编码异常");
                }
            }
            param.put("pageNo",pageNo);
            param.put("keyword",keyword);
            param.put("accId",null);

            PageInfo<Customer> customerPageInfo = customerService.findByParam(param);

            model.addAttribute("pageInfo",customerPageInfo);
        return "/public/publicCust";
    }

    /**
     * 新增公海客户页面
     * @param model
     * @return
     */
    @GetMapping("/public/new")
    public String newPublicCustomer (Model model) {
        List<String> tradeList = customerService.findAllTrade();
        List<String> sourceList =customerService.findAllSource();
        model.addAttribute("tradeList",tradeList);
        model.addAttribute("sourceList",sourceList);
        model.addAttribute("public1","public1");
        return "customer/new_customer";
    }

    /**
     * 新增公海客户
     * @param customer
     * @param redirectAttributes
     * @param session
     * @return
     */
    @PostMapping("/public/new")
    public String newPublicCustomer(Customer customer, RedirectAttributes redirectAttributes,HttpSession session){
        customer.setAccountId(null);
        customer.setCreateTime(new Date());
        customerService.save(customer);
        redirectAttributes.addFlashAttribute("message","添加客户成功");
        return "redirect:/customer/public";
    }

    /**
     * 公海客户详情页
     * @param id
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/public/{id:\\d+}")
    public String publicCust(@PathVariable Integer id, Model model,HttpSession session) {

        Customer customer = customerService.findById(id);
        if (customer == null) {
            throw new NotFoundException();
        }
        model.addAttribute("customer",customer);
        model.addAttribute("public1","public1");
        return "customer/info";
    }

    /**
     *抢占公海客户
     * @param id
     * @param session
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/public/share/{id:\\d+}")
    public String occupyPublicById(@PathVariable Integer id, HttpSession session , RedirectAttributes redirectAttributes) {
        Account account = getAccountInSession(session);
        Customer customer = customerService.findById(id);
        if (customer == null) {
            throw new NotFoundException();
        }
        if(customer.getAccountId() == null) {
            redirectAttributes.addFlashAttribute("message", "成功抢到 ["
                    + customer.getCustName() + "]");
            customerService.occupyCustomer(customer, account);
        } else  {
            redirectAttributes.addFlashAttribute("message", "抢占失败 ");
        }
        return "redirect:/customer/my";
    }

    @GetMapping("public/export")
    public void exportPublicExcel(HttpSession session, HttpServletResponse response) throws IOException {

        Account account = (Account) session.getAttribute("curr_user");
        if(account == null) {
            throw new ForbiddenException();
        }
        //告诉浏览器输出的内容和MIME
        response.setContentType("application/vnd.ms-excel");
        //设置弹出对话框的文件名称
        response.addHeader("Content-Disponsition","attachment;filename=\"customer.xls\"");

        customerService.exportPublicCustomerToExcel(account,response.getOutputStream());
    }
}
