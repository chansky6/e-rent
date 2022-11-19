package com.erent.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class House {
    // id 主键
    private Integer id;
    // 房主 id
    private Integer ownerId;
    // 地址
    private String addr;
    // 房屋类型
    private String type;
    // 房屋状态
    private Integer status;
    // 租金
    private Integer rent;

    private Integer renterId;

    public int getRenterId() {
        return renterId;
    }

    public void setRenterId(int renterId) {
        this.renterId = renterId;
    }

    // 提交时间
    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getRent() {
        return rent;
    }

    public void setRent(Integer rent) {
        this.rent = rent;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    //逻辑视图
    public String getStatusStr() {
        if (status == 0) {
            return "待租赁";
        } else if (status == 1) {
            return "已出租";
        } else if (status == 2) {
            return "已预约";
        } else{
            return "未知";
        }
    }

    public String getRenterIdStr() {
        if (renterId == 0) {
            return "无";
        } else {
            String s = String.valueOf(renterId);
            return s;
        }
    }

    // 时间转字符串
    public String getTimeStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time1 = this.getTime();
        return sdf.format(time1);
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", ownerId=" + ownerId +
                ", addr='" + addr + '\'' +
                ", type='" + type + '\'' +
                ", status=" + status +
                ", rent=" + rent +
                ", renterId=" + renterId +
                ", time=" + time +
                '}';
    }
}
