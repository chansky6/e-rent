package com.erent.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer type;
    private String addr;
    private String phone;
    private char gender;
    private Date birth;
    private Integer paid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Integer getPaid() {
        return paid;
    }

    public void setPaid(Integer paid) {
        this.paid = paid;
    }

    //逻辑视图
    public String getTypeStr(){
        if (type == null){
            return "未知";
        }else if (type == 0){
            return "管理员";
        }else if(type == 1){
            return "用户:房主";
        }else{
            return "用户:租赁者";
        }
    }

    // 时间转字符串
    public String getBirthStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date time1 = this.getBirth();
        String format = sdf.format(time1);
        return format;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", addr='" + addr + '\'' +
                ", phone='" + phone + '\'' +
                ", gender=" + gender +
                ", birth=" + birth +
                ", paid=" + paid +
                '}';
    }
}
