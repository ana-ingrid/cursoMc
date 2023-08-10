package com.ingridsantos.cursomc.util;

public class StandardError {

    private Integer statusHttp;
    private String msg;
    private Long timeStamp;

    public StandardError(Integer statusHttp, String msg, Long timeStamp) {
        this.statusHttp = statusHttp;
        this.msg = msg;
        this.timeStamp = timeStamp;
    }

    public Integer getStatusHttp() {
        return statusHttp;
    }

    public void setStatusHttp(Integer statusHttp) {
        this.statusHttp = statusHttp;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
