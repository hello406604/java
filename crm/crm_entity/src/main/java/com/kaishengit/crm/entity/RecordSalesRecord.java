package com.kaishengit.crm.entity;

import java.io.Serializable;
import java.util.Date;

public class RecordSalesRecord implements Serializable{
    public static final long serialVersionUID = 1L;

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSalesId() {
        return salesId;
    }


    public void setSalesId(Integer salesId) {
        this.salesId = salesId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private Integer salesId;
    private Date createTime;
    private  String content;
}
