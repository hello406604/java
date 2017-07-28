package com.kaishengit.crm.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Task implements Serializable {
    private Integer id;

    private String title;

    private Integer salesrecordId;

    private SalesRecord salesRecord;

    private Integer accountId;

    private Integer customerId;

    private Customer customer;

    private Boolean done;

    private Date finshTime;

    private Date reminder;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSalesrecordId() {
        return salesrecordId;
    }

    public void setSalesrecordId(Integer salesrecordId) {
        this.salesrecordId = salesrecordId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Date getFinshTime() {
        return finshTime;
    }

    public void setFinshTime(Date finshTime) {
        this.finshTime = finshTime;
    }

    public Date getReminder() {
        return reminder;
    }

    public void setReminder(Date reminder) {
        this.reminder = reminder;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}