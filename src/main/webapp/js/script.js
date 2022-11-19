
    new Vue({
    el: "#app",

    mounted() {
    this.selectByPAC();
},

    methods: {
    // 拒绝看房请求
    rejectReq(row) {
    this.$confirm('此操作将拒绝预约, 是否继续?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
}).then(() => {
    axios({
    method: "get",
    url: "http://localhost:8080/e-rent/request/rejectReq?id=" + row.id,
}).then(resp => {
    if (resp.data === "success") {
    this.changeHouseStatus(row.houseId,0);
    this.selectByPAC();
    // 删除成功
    this.hrefToMsg();
    // 成功弹窗
    this.$message({
    showClose: true,
    message: '恭喜你，拒绝成功',
    type: 'success'
});
}
})
}).catch(() => {
    this.$message({
    type: 'info',
    message: '取消确认'
});
});
},

    // 接受看房请求
    comfirmReq(row) {
    this.$confirm('此操作将确认预约, 是否继续?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
}).then(() => {
    axios({
    method: "get",
    url: "http://localhost:8080/e-rent/request/comfirmReq?id=" + row.id,
}).then(resp => {
    if (resp.data === "success") {
    // 删除成功
    this.hrefToMsg();
    // 成功弹窗
    this.$message({
    showClose: true,
    message: '恭喜你，确认成功',
    type: 'success'
});
}
})
}).catch(() => {
    this.$message({
    type: 'info',
    message: '取消确认'
});
});
},

    // 取消看房请求
    cancelReq(row) {
    this.$confirm('此操作将取消预约, 是否继续?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
}).then(() => {
    axios({
    method: "get",
    url: "http://localhost:8080/e-rent/request/cancelReq?id=" + row.id,
}).then(resp => {
    if (resp.data === "success") {
    this.changeHouseStatus(row.houseId,0);
    // 删除成功
    this.hrefToMsg();
    this.selectByPAC();
    // 成功弹窗
    this.$message({
    showClose: true,
    message: '恭喜你，取消预约成功',
    type: 'success'
});
}
})
}).catch(() => {
    this.$message({
    type: 'info',
    message: '未取消'
});
});
},

    changeHouseStatus(houseId,status) {
    axios({
    method:"get",
    url:"http://localhost:8080/e-rent/house/changeHouseStatus?id="
    + houseId
    +"&status="
    + status
}).then(resp=>{
})
},

    // 提交预约
    addAppointment() {
    this.req["appointment"] = this.appointmentTime;
    axios({
    method: "post",
    url: "http://localhost:8080/e-rent/request/addReq?",
    data: this.req,
}).then(resp => {
    if (resp.data === 'success') {
    this.changeHouseStatus(this.req.houseId,2);
    this.selectByPAC();
    // 成功弹窗
    this.$message({
    showClose: true,
    message: "恭喜您，预约成功",
    type: 'success'
});
    this.pickTimeVisible = false;
} else {
    // 失败弹窗
    this.$message({
    showClose: true,
    message: "出错了，预约失败",
    type: 'error'
});
}
})
},

    // 预约信息存储
    appointment(row) {
    if (this.isLogin === true) {
    this.pickTimeVisible = true;
    this.req.houseId = row.id;
    this.req.reqId = this.user.id;
    this.req.ownerId = row.ownerId;
} else {
    this.notLogin();
}
},

    // 个人信息卡片
    showUserInfo() {
    if (this.isLogin === true) {
    this.userInfoDialogVisible = true;
} else {
    this.notLogin();
}
},

    // 消息抽屉
    hrefToMsg() {
    if (this.isLogin === true) {
    axios({
    method: "get",
    url: "http://localhost:8080/e-rent/request/selectByReqId?id=" + this.user.id
}).then(resp => {
    this.message_ReqMsg = resp.data;
});
    axios({
    method: "get",
    url: "http://localhost:8080/e-rent/request/selectByOwnerId?id=" + this.user.id
}).then(resp => {
    this.message_OwnerMsg = resp.data;
})
    this.userMsgDrawerVisible = true;
} else {
    this.notLogin();
}
},

    // 租房抽屉
    hrefToHouse() {
    if (this.isLogin === true) {
    axios({
    method: "get",
    url: "http://localhost:8080/e-rent/house/selectByOwnerId?id=" + this.user.id
}).then(resp => {
    this.house_self = resp.data;
});
    axios({
    method: "get",
    url: "http://localhost:8080/e-rent/house/selectByRenterId?id=" + this.user.id
}).then(resp => {
    this.house_rented = resp.data;
})
    this.houseRentDrawerVisible = true;
} else {
    this.notLogin();
}
},

    // 添加房屋信息
    addHouse() {
    axios({
    method: "post",
    url: "http://localhost:8080/e-rent/house/addHouse?ownerId=" + this.user.id,
    data: this.house
}).then(resp => {
    if (resp.data === 'success') {
    // 成功弹窗
    this.$message({
    showClose: true,
    message: "恭喜您，发布成功",
    type: 'success'
});
    this.closeAddHouseDialog();
    this.cleanHouseDate();
    this.selectByPAC();
} else {
    // 失败弹窗
    this.$message({
    showClose: true,
    message: "出错了，发布失败",
    type: 'error'
});
}
})
},

    // 重置房屋信息
    cleanHouseDate() {
    this.house.id = '';
    this.house.ownerId = '';
    this.house.addr = '';
    this.house.type = '';
    this.house.status = '';
    this.house.rent = '';
    this.house.time = '';
    this.value[0]=0;
    this.value[1]=10000;
},

    // 添加费用账单
    generateExpenseBill(cost) {
    this.serviceFee = cost;
    axios({
    method: "get",
    url: "http://localhost:8080/e-rent/expenseBill/generateExpenseBill?arrearsId="
    + this.user.id
    + "&cost="
    + this.serviceFee
}).then(resp => {
    if (resp.data == "success") {
    this.$message({
    showClose: true,
    message: '缴费成功('
    + this.serviceFee
    + '元)',
    type: 'success'
});
}
})
},

    // 清除注册信息
    cleanRegisterData() {
    this.register.username = '';
    this.register.password = '';
    this.register.type = '2';
    this.register.addr = '';
    this.register.phone = '';
    this.register.gender = '男';
    this.register.birth = '';
},

    // 判断是否登录
    notLogin() {
    this.$message({
    showClose: true,
    message: '您尚未登录',
    type: 'error'
});
    this.openLoginDialog();
},

    // select by page and condition
    selectByPAC() {
    axios({
    method: "post",
    url: "http://localhost:8080/e-rent/house/selectByPageAndCondition?currentPage="
    + this.currentPage
    + "&pageSize="
    + this.pageSize
    + "&lower="
    + this.value[0]
    + "&upper="
    + this.value[1],
    data: this.house
}).then(resp => {
    this.tableData = resp.data.rows;
    this.totalCount = resp.data.totalCount;
})
    this.selectDialogVisible = false;
},

    // 登出操作
    checkout() {
    // 修改可见性
    this.visible = true;
    this.disabled = false;
    // 修改登录状态
    this.isLogin = false;
    // 清空登录
    this.isLogin = false;
    for (let loginKey in this.login) {
    this.login[loginKey] = '';
}
    // 成功弹窗
    this.$message({
    showClose: true,
    message: '您已退出登录',
    type: 'success'
});
},

    // 用户登录
    userLogin() {
    if (this.login.username == null || this.login.password == null) {
    // 失败弹窗
    this.$message({
    showClose: true,
    message: '账号密码不能为空',
    type: 'error'
});
    return;
} else {
    axios({
    method: "get",
    url: "http://localhost:8080/e-rent/user/login?username="
    + this.login.username
    + "&password="
    + this.login.password
}).then(resp => {
    if (resp.data !== 'failed') {
    // 登录成功
    this.closeLoginDialog();
    // 成功弹窗
    this.$message({
    showClose: true,
    message: '登录成功,欢迎您,' + this.login.username,
    type: 'success'
});
    // 修改可见性
    this.visible = false;
    this.disabled = true;
    // 修改登录状态
    this.isLogin = true;
} else {
    // 失败弹窗
    this.$message({
    showClose: true,
    message: '账号或密码错误，请重新输入',
    type: 'error'
});
    this.cleanLoginData();
}
    this.user = resp.data;
});
}
},

    //打开登录表单
    openLoginDialog() {
    this.loginDialogVisible = true;
},
    //关闭登录表单
    closeLoginDialog() {
    this.loginDialogVisible = false;
},

    //打开注册表单
    openRegisterDialog() {
    this.registerDialogVisible = true;
},
    //关闭注册表单
    closeRegisterDialog() {
    this.registerDialogVisible = false;
},

    // 打开房屋添加窗口
    openAddHouseDialog(){
    this.addDialogVisible = true;
},
    // 关闭房屋添加窗口
    closeAddHouseDialog(){
    this.addDialogVisible = false;
},
    // 用户注册(并登录)
    userRegister(formName) {
    this.$refs[formName].validate((valid) => {
    if (valid) {
    axios({
    method: 'post',
    url: 'http://localhost:8080/e-rent/user/register',
    data: this.register
}).then(resp => {
    if (resp.data) {
    this.$message({
    showClose: true,
    message: '注册成功',
    type: 'success'
});
    this.login.username = this.register.username;
    this.login.password = this.register.password;
    this.login.remember = true;
    this.userLogin();
    this.registerDialogVisible = false;
    this.cleanRegisterData();
} else {
    this.$message({
    showClose: true,
    message: '注册失败',
    type: 'error'
});
}
})
} else {
    return false;
}
});

},

    indexMethod(index) {
    return index + 1;
},

    //分页
    handleSizeChange(val) {
    this.pageSize = val;
    this.selectByPAC(this.currentPage, this.pageSize);
},
    handleCurrentChange(val) {
    this.currentPage = val;
    this.selectByPAC(this.currentPage, this.pageSize)
},

    // 颜色区分
    mainTable({row, rowIndex}) {
    if (this.tableData[rowIndex]["status"] === 1) {
    this.appointmentButtonClickable = true;
    return 'sold-row';
} else {
    this.appointmentButtonClickable = false;
    return 'warning-row';
}
},

    // 预约按钮是否可按下
    checkAppointmentButtonClickable(row) {
    if (row.status == 0) {
    //可点击
    return false;
}else {
    //不可点击
    return true;
}
},

    // 租房
    isRent(row) {
    this.$confirm('此操作将确认租该房, 是否继续?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
}).then(() => {
    axios({
    method: "get",
    url: "http://localhost:8080/e-rent/request/isRent?reqId="
    + row.id
    + "&houseId="
    + row.houseId
    + "&renterId="
    + this.user.id,
}).then(resp => {
    if (resp.data === "success") {
    this.changeHouseStatus(row.houseId,1);
    this.hrefToMsg();
    this.selectByPAC();
    // 成功弹窗
    this.$message({
    showClose: true,
    message: '恭喜你，租房成功',
    type: 'success'
});
}
})
}).catch(() => {
    this.$message({
    type: 'info',
    message: '取消确认'
});
});
},

    // 租房按钮是否可按下
    checkIsRentButtonClickable(row) {
    if (row.current == 1) {
    return false;
} else {
    return true;
}
},

    //退房
    checkOutHouse(row) {
    this.$confirm('此操作将退房, 是否继续?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
}).then(() => {
    axios({
    method: "get",
    url: "http://localhost:8080/e-rent/house/checkOut?houseId=" + row.id,
}).then(resp => {
    if (resp.data === "success") {
    this.changeHouseStatus(row.id,0);
    this.hrefToHouse();
    this.selectByPAC();
    // 成功弹窗
    this.$message({
    showClose: true,
    message: '恭喜你，退房成功',
    type: 'success'
});
}
})
}).catch(() => {
    this.$message({
    type: 'info',
    message: '取消确认'
});
});
},

    // 接受,拒绝请求按钮是否可按
    checkReqComfirmButtonClickable(row) {
    if (row.current == 0) {
    //可点击
    return false;
} else {
    //不可点击
    return true;
}
},

    // 下架房屋
    deleteHouseById(row) {
    this.$confirm('此操作将下架该房屋, 是否继续?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
}).then(() => {
    axios({
    method: "get",
    url: "http://localhost:8080/e-rent/house/deleteHouseById?id=" + row.id,
}).then(resp => {
    if (resp.data === "success") {
    this.hrefToHouse();
    this.selectByPAC();
    // 成功弹窗
    this.$message({
    showClose: true,
    message: '恭喜你，下架成功',
    type: 'success'
});
}
})
}).catch(() => {
    this.$message({
    type: 'info',
    message: '取消确认'
});
});
},

    // 更改租金
    updateHouseRentById(row) {
    this.$prompt('请输入租金', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    inputPattern: /^\d{1,4}?/,
    inputErrorMessage: '请输入一个0到9999的一个整数'
}).then(({value}) => {
    axios({
    method: "get",
    url: "http://localhost:8080/e-rent/house/updateHouseRentById?id="
    + row.id
    + "&rent="
    + value,
}).then(resp => {
    if (resp.data === "success") {
    this.hrefToHouse();
    this.selectByPAC();
    // 成功弹窗
    this.$message({
    showClose: true,
    message: '恭喜你，租金修改成功',
    type: 'success'
});
}
})
}).catch(() => {
    this.$message({
    type: 'info',
    message: '取消修改'
});
});
},

    // 房屋信息更改按钮是否可按
    checkChangeButtonClickable(row) {
    if (row.status == 0) {
    //可点击
    return false;
} else {
    //不可点击
    return true;
}
}

},

    data() {
    //注册用户名查询重复
    var checkUsername = (rule, value, callback) => {
    axios({
    method: 'get',
    url: 'http://localhost:8080/e-rent/user/selectByUsername?username=' + this.register.username
}).then(resp => {
    setTimeout(() => {
    if (resp.data) {
    //用户名存在
    return callback(new Error('用户名已存在'));
} else {
    callback();
}
}, 1000);
})
};

    return {

    // 用户id
    userId: '',

    // 是否登录
    isLogin: false,

    // 登录信息
    login: {
    username: '',
    password: '',
    remember: ''
},

    // 可见性
    visible: true,

    // 权限
    disabled: false,

    // 预约时间
    appointmentTime: '',

    // 时间选择框显示
    pickTimeVisible: false,

    // 消息抽屉显示
    userMsgDrawerVisible: false,

    // 租房抽屉显示
    houseRentDrawerVisible: false,

    // 个人信息框显示
    userInfoDialogVisible: false,

    //登录框显示
    loginDialogVisible: false,

    //注册框显示
    registerDialogVisible: false,

    //添加房屋框显示
    addDialogVisible: false,

    //查询框显示
    selectDialogVisible: false,

    // 价格区间查询
    value: [0, 10000],

    // 区间显示
    marks: {
    500: '500',
    2500: "2500",
    4500: "4500",
    6500: "6500",
    8500: "8500",
},

    //总记录数
    totalCount: 100,

    // 当前页码
    currentPage: 1,

    // 每页显示条数
    pageSize: 10,

    // 房屋信息模型
    house: {
    id: '',
    ownerId: '',
    addr: '',
    type: '',
    status: '',
    rent: '',
    time: ''
},

    // 用户信息模板
    user: {
    id: '',
    username: '',
    password: '',
    type: '',
    addr: '',
    phone: '',
    gender: '',
    birth: '',
    paid: ''
},

    // 看房请求模板
    req: {
    id: '',
    houseId: '',
    reqId: '',
    ownerId: '',
    appointment: '',
    reqTime: ''
},

    // 请求消息表数据
    message_ReqMsg: [],

    // 待处理消息数据
    message_OwnerMsg: [],

    //已经租入的房
    house_rented: [],

    //我发布的房屋信息
    house_self: [],

    // 主表格数据
    tableData: [],

    //注册信息
    register: {
    username: '',
    password: '',
    type: '2',
    addr: '',
    phone: '',
    gender: '男',
    birth: ''
},

    // 服务费用 (初始为5,每次登记房屋,预约看房后收取,默认已付款)
    serviceFee: 5,

    // 注册规则
    registerRules: {
    username: [
{required: true, message: '请输入用户名', trigger: "blur"},
{min: 4, max: 20, message: '长度在 4 到 20 个字符', trigger: 'blur'},
{validator: checkUsername, trigger: 'blur'}
    ],
    password: [
{required: true, message: '请输入密码', trigger: 'blur'},
{min: 4, max: 10, message: '长度在 4 到 10 个字符', trigger: 'blur'}
    ],
    addr: [
{required: true, message: '请输入地址', trigger: 'blur'}
    ],
    type: [],
    gender: [],
    birth: [
{required: true, message: '请选择日期', trigger: 'change'}
    ],
    phone: [
{required: true, message: '请输入手机号', trigger: 'blur'},
{min: 11, max: 11, message: '手机号长度为 11 位', trigger: 'blur'}
    ],
}
};
}
})

