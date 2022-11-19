# 若无该数据库则执行建库语句,若已有该库则直接运行该文件
# create database db_erent;

use db_erent;
drop table if exists tb_req;
drop table if exists tb_cost;
drop table if exists tb_house;
drop table if exists tb_user;


-- 房屋信息
create table tb_house
(
    -- id 主键
    id        int primary key auto_increment,
    -- 房主id
    owner_id  int,
    -- 房屋地址
    addr      varchar(50),
    -- 房屋类型
    type      varchar(20),
    -- 房屋状态 0-未出租 1-已出租 2-已被预约
    status    int,
    -- 租金
    rent      int,
    -- 发布时间
    time      DATETIME,
    -- 租客
    renter_id int
);

-- 用户信息
create table tb_user
(
    -- id 主键
    id       int primary key auto_increment,
    -- 用户名
    username varchar(20),
    -- 用户密码
    password varchar(20),
    -- 用户类型 0-管理员 1-房主 2-租赁者
    type     int,
    -- 住址
    addr     varchar(50),
    -- 电话
    phone    varchar(20),
    -- 性别
    gender   char(1),
    -- 出生年月
    birth    DATE
);

-- 请求看房信息
create table tb_req
(
    -- id
    id          int primary key auto_increment,
    -- 房屋id
    house_id    int,
    -- 租客id
    req_id      int,
    -- 房主id
    owner_id    int,
    -- 当前进度 0-待处理 1-同意看房请求 2-拒绝看房请求 3-请求完成(成功租房) 9-取消请求
    current     int,
    -- 预约看房时间
    appointment DATETIME,
    -- 请求发出时间
    req_time    DATETIME
);

-- 费用表单
create table tb_cost
(
    -- id
    id         int primary key auto_increment,
    -- 需要缴费的人
    arrears_id int,
    -- 费用 挂房子单次20,预约单次5
    cost       int
);


insert into tb_house(addr, owner_id, type, status, rent, time, renter_id)
values ('西安市雁塔区高新开发区科技五路3号', 2, '1室1厅1卫', 0, 1500, '2022-9-7', 0),
       ('西安市雁塔区太白南路33号', 3, '2室2厅1卫', 0, 3200, '2022-7-16', 0),
       ('西安市雁塔区西安高新区科技四路西口', 4, '3室2厅1卫', 0, 5400, '2022-8-6', 0),
       ('西安市雁塔区高新区丈八街道丈八北路205号附近', 5, '2室1厅1卫', 0, 2100, '2022-10-1', 0),
       ('西安市莲湖区劳动路7附近', 2, '1室1厅1卫', 1, 1350, '2022-9-28', 10),
       ('西安市新城区东五路与尚勤路交叉路口', 5, '2室2厅1卫', 0, 3600, '2022-9-30', 0);

-- test测试用例(不需要就掉)
insert into tb_house(addr, owner_id, type, status, rent, time, renter_id)
values ('test1',2,'test1',0,1000,'2022-11-15',0),
       ('test2',2,'test2',0,1500,'2022-11-15',0),
       ('test3',2,'test3',0,2000,'2022-11-15',0),
       ('test4',2,'test4',0,2500,'2022-11-15',0),
       ('test5',2,'test5',0,3000,'2022-11-15',0),
       ('test6',2,'test6',0,3500,'2022-11-15',0),
       ('test7',2,'test7',0,4000,'2022-11-15',0),
       ('test8',2,'test8',0,4500,'2022-11-15',0),
       ('test9',2,'test9',0,5000,'2022-11-15',0),
       ('test10',2,'test10',0,5500,'2022-11-15',0);

-- 爷是管理员
insert into tb_user(id, username, password, type, addr, phone, gender, birth)
VALUES (1, 'lijiayun', '55112233', 0, '西安', '18329623588', '男', '2002-5-8');

insert into tb_user(username, password, type, addr, phone, gender, birth)
VALUES ('xiaohong', '123456', 1, '西安', '18973024638', '男', '1966-7-13'),
       ('xiaoli', '123456', 1, '西安', '18437157220', '男', '1978-6-21'),
       ('xiaona', '123456', 1, '西安', '18759235114', '男', '1997-10-27'),
       ('xiaohua', '123456', 1, '西安', '18058937018', '男', '1987-4-18'),
       ('daming', '123456', 2, '西安', '16637206837', '男', '1987-1-27'),
       ('dahong', '123456', 2, '北京', '15905026297', '男', '1988-2-25'),
       ('dali', '123456', 2, '上海', '17672968450', '男', '1996-6-16'),
       ('dachen', '123456', 2, '广州', '13924545061', '男', '1993-5-13'),
       ('dazhang', '123456', 2, '天津', '15964537644', '男', '1957-4-5'),
       ('dawang', '123456', 2, '兰州', '15552798106', '男', '2000-11-3');

insert into tb_req (house_id, req_id, owner_id, current, appointment, req_time)
values (5,10,2,3,'2022-10-01 00:00:00','2022-9-30 19:44:24');

insert into tb_cost (arrears_id, cost)
values (2, 20),
       (3, 20),
       (4, 20),
       (6, 20),
       (2, 20),
       (5, 20),
       (10, 5);
