package com.kaishengit.dto;

/**
 * Created by 帅灏灏 on 2017/7/17.
 */
public class AjaxResult {

    public static final String SUCCESS = "success";
    public static final String ERROR = "error";

    private String state;
    private String message;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private Object data;

    public static AjaxResult success () {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.state = SUCCESS;
        return ajaxResult;
    }

    public static AjaxResult success(Object data) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.state = SUCCESS;
        ajaxResult.data = data;
        return ajaxResult;
    }

    public static AjaxResult error() {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.state = ERROR;
        return  ajaxResult;
    }

    public static AjaxResult error(String message) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.state = ERROR;
        ajaxResult.message = message;
        return  ajaxResult;
    }
}
