package com.erent.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Request {
    private int id;
    private int houseId;
    private int reqId;
    private int ownerId;
    private int current;
    private Date appointment;
    private Date reqTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public int getReqId() {
        return reqId;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public Date getAppointment() {
        return appointment;
    }

    public void setAppointment(Date appointment) {
        this.appointment = appointment;
    }

    public Date getReqTime() {
        return reqTime;
    }

    public void setReqTime(Date reqTime) {
        this.reqTime = reqTime;
    }

    // 时间转字符串
    public String getAppointmentStr(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time1 = this.getAppointment();
        String format = sdf.format(time1);
        return format;
    }
    public String getReqTimeStr(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time1 = this.getReqTime();
        String format = sdf.format(time1);
        return format;
    }

    // 逻辑视图
    public String getCurrentStr() {
        if (this.current == 0) {
            return "看房申请未通过";
        } else if (this.current == 1) {
            return "看房申请已通过";
        } else if (this.current == 2) {
            return "申请已拒绝";
        } else if (this.current == 3) {
            return "已出租";
        }
        return null;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", houseId=" + houseId +
                ", reqId=" + reqId +
                ", ownerId=" + ownerId +
                ", current=" + current +
                ", appointment=" + appointment +
                ", reqTime=" + reqTime +
                '}';
    }
}
