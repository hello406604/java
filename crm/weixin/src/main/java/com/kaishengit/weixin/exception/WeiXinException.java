package com.kaishengit.weixin.exception;

import java.util.concurrent.ExecutionException;

public class WeiXinException extends RuntimeException {

    public WeiXinException() {}

    public WeiXinException(String message) {
        super(message);
    }

    public WeiXinException(String message,Throwable throwable) {
        super(message,throwable);
    }

    public WeiXinException(Throwable e) {
    }
}
