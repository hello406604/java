package com.kaishengit.crm.controller;

import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.controller.exception.ForbiddenException;
import com.kaishengit.crm.controller.exception.NotFoundException;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.entity.RecordSalesRecord;
import com.kaishengit.crm.entity.SalesRecord;
import com.kaishengit.crm.service.CustomerService;
import com.kaishengit.crm.service.RescordSalesService;
import com.kaishengit.crm.service.SalesService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.rmi.MarshalledObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/saleChance")
public class SaleChanceController extends ParentController {

    @Autowired
    private SalesService salesService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RescordSalesService rescordSalesService;

    /**
     * 请求转发到销售机会主页
     *
     * @param model
     * @param session
     * @return
     */

    @GetMapping("/my")
    public String mySales(Model model, HttpSession session, @RequestParam(required = false, defaultValue = "1") Integer p
            , @RequestParam(required = false, defaultValue = "") String keyword
    ) {
        Account account = getAccountInSession(session);
        if (account == null) {
            throw new ForbiddenException();
        }
        PageInfo<SalesRecord> pageInfo = salesService.showAllByAccId(account.getId(), p);
        System.out.println(pageInfo.getTotal());
        System.out.println(pageInfo.getPages());
        model.addAttribute("pageInfo", pageInfo);
        return "sales/saleChance";
    }

    /**
     * 请求转发到新增销售机会页面
     *
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/my/new")
    public String mySalesNew(Model model, HttpSession session) {
        Account account = getAccountInSession(session);
        if (account == null) {
            throw new ForbiddenException();
        }
        List<Customer> customerList = customerService.finByAccount(account.getId());
        List<String> currentProgress = salesService.getCurrentProgress();
        model.addAttribute("customerList", customerList);
        model.addAttribute("currentProgress", currentProgress);
        return "sales/saleChanceNew";
    }

    @PostMapping("/my/new")
    public String mySalesNew(SalesRecord salesRecord, Integer customerId, RedirectAttributes redirectAttributes,
                             String content, HttpSession session) {
        Account account = getAccountInSession(session);
        if (account == null) {
            throw new ForbiddenException();
        }
        salesService.saveSales(salesRecord, account.getId(), customerId);
        rescordSalesService.save(content, salesRecord.getId());
        redirectAttributes.addFlashAttribute("message", "保存成功");
        return "redirect:/saleChance/my";
    }

    @GetMapping("/my/{id:\\d+}")
    public String mySale(HttpSession session, @PathVariable Integer id, Model model) {
        Account account = getAccountInSession(session);
        SalesRecord salesRecord = salesService.findById(id);
        if (salesRecord == null) {
            throw new NotFoundException();
        }
        if (!salesRecord.getAccountId().equals(account.getId())) {
            throw new ForbiddenException();
        }
        List<String> currentProgress = salesService.getCurrentProgress();

        model.addAttribute("progressList", currentProgress);
        model.addAttribute("saleChance", salesRecord);
        return "sales/chance";
    }

    @PostMapping("/my/{id:\\d+}/progress/update")
    public String updatePro(@PathVariable Integer id, String progress, HttpSession session) {
        Account account = getAccountInSession(session);
        SalesRecord salesRecord = salesService.findById(id);
        if (salesRecord == null) {
            throw new NotFoundException();
        }
        if (!salesRecord.getAccountId().equals(account.getId())) {
            throw new ForbiddenException();
        }
        System.out.println(id);
        System.out.println(progress);
        rescordSalesService.updateSaleChance(id,progress);

        return  "redirect:/saleChance/my/"+id;
}

    @PostMapping("/my/new/record")
    public String newRecord(HttpSession session,  RecordSalesRecord salesRecord) {
        Account account = getAccountInSession(session);
        if (salesRecord == null) {
            throw new NotFoundException();
        }
        rescordSalesService.saveNewChance(salesRecord);

        return "redirect:/saleChance/my/"+salesRecord.getId();
    }

    @GetMapping("my/del/{id:\\d+}")
    public String mySalesDel(@PathVariable Integer id, RedirectAttributes redirectAttributes, HttpSession session) {
        Account account = getAccountInSession(session);
        SalesRecord salesRecord = salesService.findById(id);
        if (salesRecord == null) {
            throw new NotFoundException();
        }
        if (!salesRecord.getAccountId().equals(account.getId())) {
            throw new ForbiddenException();
        }
        return "";
    }

}
