package com.kaishengit.dto;

/**
 * Created by 帅灏灏 on 2017/7/17.
 */
public class ZTreeNode {

    private Integer id;
    private String name;
    private Integer pId;
    private Boolean open = true;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }
}
