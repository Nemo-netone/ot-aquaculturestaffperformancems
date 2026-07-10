<%@ page import="com.aspms.entity.User" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title></title>
    <link rel="stylesheet" href="static/css/backend-plugin.min.css">
    <link rel="stylesheet" href="static/css/backend-1.0.2.css">
    <link rel="stylesheet" href="static/css/all.min.css">
    <link rel="stylesheet" href="static/css/line-awesome.min.css">
    <link rel="stylesheet" href="static/css/remixicon.css">
    <link rel="stylesheet" href="static/css/dripicons.css">
    <link rel='stylesheet' href='static/css/main.css'>
    <link rel='stylesheet' href='static/css/main1.css'>
    <link rel='stylesheet' href='static/css/main2.css'>
    <link rel='stylesheet' href='static/css/main3.css'>
    <link rel="stylesheet" href="static/css/mapbox-gl.css">  </head>
<body class="  ">
<%
    User loginUser=(User) request.getSession().getAttribute("loginUser");
    if(loginUser==null){return;}
%>
<div class="row">
    <div class="col-xl-12 col-lg-4">
        <div class="card">
            <div class="card-header d-flex justify-content-between">
                <div class="header-title">
                    <h6 class="card-title">请完善以下信息</h6>
                </div>
            </div>
            <div class="card-body">
                <form style="display:none;" id="iform" action="/api/user/upload.do" method="post" enctype="multipart/form-data">
                    <input type="file" id="file" name="file" onclick="showChoose()" accept="image/*" >
                </form>
                <form id="app">
                    <div class="form-group">
                        <label>职位</label>
                        <input type="text" class="form-control" v-model="dp" readonly="readonly" autocomplete="off">
                    </div>
                    <div class="form-group">
                        <label>用户名</label>
                        <input type="text" class="form-control" v-model="username" readonly="readonly" autocomplete="off">
                    </div>
                    <div class="form-group">
                        <label>登录密码</label>
                        <input type="password" class="form-control" v-model="password" placeholder="请输入登录密码" autocomplete="off">
                    </div>
                    <div class="form-group">
                        <label>姓名</label>
                        <input type="text" class="form-control" v-model="name" placeholder="请输入姓名" autocomplete="off">
                    </div>
                    <div class="form-group">
                        <label>性别</label>
                        <select class="form-control" v-model="sex" >
                            <option value="">请选择性别</option>
                            <option>男</option>
                            <option>女</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>年龄</label>
                        <input type="text" class="form-control" v-model="age" placeholder="请输入年龄" autocomplete="off">
                    </div>
                    <div class="form-group">
                        <label>出生日期</label>
                        <input type="date" class="form-control" v-model="birthdate" placeholder="请输入出生日期" autocomplete="off">
                    </div>
                    <div class="form-group">
                        <label>身份证号</label>
                        <input type="text" class="form-control" v-model="idcard" placeholder="请输入身份证号" autocomplete="off">
                    </div>
                    <div class="form-group">
                        <label>联系方式</label>
                        <input type="text" class="form-control" v-model="phone" placeholder="请输入联系方式" autocomplete="off">
                    </div>
                    <div class="form-group">
                        <label>员工照片</label>
                        <input type="text" class="form-control" v-model="avatar" onclick="clickChoose()" readonly="readonly" placeholder="<点击上传员工照片>" autocomplete="off">
                        <img id="img_pic"  class="mb-4" style="height:80px;width:80px;border:1px solid #cccccc;margin-top:10px;/*display: none;*/" alt="请上传员工照片"/>
                    </div>
                    <button type="button" class="btn btn-primary btn-lg mt-3" @click="btnSubmit">提交信息</button>
                </form>

            </div>
        </div>
    </div>
</div>
<!-- Backend Bundle JavaScript -->
<script src="static/js/backend-bundle.min.js"></script>
<!-- Flextree Javascript-->
<!-- Table Treeview JavaScript -->
<script src="static/js/table-treeview.js"></script>
<!-- Masonary Gallery Javascript -->
<!-- Mapbox Javascript -->
<!-- Fullcalender Javascript -->
<script src='static/js/main.js'></script>
<script src='static/js/main1.js'></script>
<script src='static/js/main2.js'></script>
<script src='static/js/main3.js'></script>
<!-- Vectoe Map JavaScript -->
<script src="static/js/vector-map-custom.js"></script>
<!-- Chart Custom JavaScript -->
<script src="static/js/chart-custom.js"></script>
<!-- slider JavaScript -->
<script src="static/js/slider.js"></script>
<!-- app JavaScript -->
<script src="static/js/app.js"></script>
<!-- Jquery form JavaScript -->
<script src="static/js/jquery-form.js"></script>
<!-- Vue.js Vendors -->
<script src="static/vendor/vue/vue.global.js"></script>
<script src="static/vendor/vue/axios.min.js"></script>
<!-- Custom Javascript -->
<script>
    //创建Vue实例
    const app = Vue.createApp({
        data() {
            return {
                uid: <%= loginUser.getUid()%>,
                pid: '',
                did: '',
                username: '',
                password: '',
                identify: '',
                name: '',
                sex: '',
                age:'',
                idcard: '',
                phone: '',
                birthdate: '',
                avatar:'',
                dp:''
            }
        },
        mounted(){
            //获取信息
            axios.post("/api/user/get.do", {
                uid: this.uid
            }).then((response) => {
                // 判断结果
                if(JSON.parse(response.data.status)){
                    //显示信息
                    this.pid=response.data.data.pid.toString();
                    this.did=response.data.data.did.toString();
                    this.username=response.data.data.username;
                    this.password=response.data.data.password;
                    this.identify=response.data.data.identify;
                    this.name=response.data.data.name;
                    this.sex=response.data.data.sex;
                    this.age=response.data.data.age;
                    this.idcard=response.data.data.idcard;
                    this.phone=response.data.data.phone;
                    this.birthdate=response.data.data.birthdate;
                    this.avatar=response.data.data.avatar;
                    $("#img_pic").attr("src","/img/"+response.data.data.avatar);
                    this.dp=response.data.data.department.dname+" "+response.data.data.position.pname;

                }
            }).catch((error) => {
                // 弹出错误信息
                window.parent.swalForError('提示','网络请求失败，请稍后重试！');
            });
            // 监听外部触发的自定义事件
            window.addEventListener('doUpdateVariableValue', (event) => {
                this.doUpdateVariableValue(event.detail.obj,event.detail.val);
            });
        },
        methods: {
            btnSubmit(){
                //检查输入
                if(this.username.trim().length==0||this.password.trim().length==0|| this.identify.trim().length==0||this.name.trim().length==0||this.sex.trim().length==0||this.idcard.trim().length==0||this.phone.trim().length==0||this.birthdate.trim().length==0||this.avatar.trim().length==0||this.age.trim().length==0){
                    window.parent.swalForWarning("提示", "请先完善员工信息！");
                    return;
                }
                //请求后端
                axios.post("/api/user/modify.do", {
                    uid: this.uid,
                    pid: this.pid,
                    did: this.did,
                    username: this.username,
                    password: this.password,
                    identify: this.identify,
                    name: this.name,
                    sex: this.sex,
                    age: this.age,
                    idcard: this.idcard,
                    phone: this.phone,
                    birthdate: this.birthdate,
                    avatar: this.avatar
                }).then((response) => {
                    // 判断结果
                    if(JSON.parse(response.data.status)){
                        //新增成功
                        window.parent.swalForSuccess("提示","提交信息成功！");
                        //跳转修改页面
                        window.parent.showPage("userUser");
                        window.parent.modifyAvatar(this.avatar);
                    }else{
                        //新增失败
                        window.parent.swalForError("提示", "提交信息失败，可能是当前信息不存在！");
                    }
                }).catch((error) => {
                    // 弹出错误信息
                    window.parent.swalForError('提示','网络请求失败，请稍后重试！');
                });
            },
            //更新变量值
            doUpdateVariableValue(obj,val){
                if(obj=="avatar"){
                    this.avatar=val;
                }
            }
        }
    });
    //挂载实例
    app.mount('#app');
    //开始选择图片
    function clickChoose(){
        $("#file").click();
    }
    //选择图片并上传
    function showChoose() {
        var fileTag = document.getElementById('file');
        fileTag.onchange = function () {
            var file = fileTag.files[0];
            var fileReader = new FileReader();
            fileReader.onloadend = function () {
                if (fileReader.readyState == fileReader.DONE) {
                    $("#iform").ajaxSubmit(function (data) {
                        if(data=="false"){
                            window.parent.swalForError("提示", "很抱歉,上传图片失败!");
                        }else{
                            $("#img_pic").attr("src","/img/"+data);
                            $("#img_pic").css("display","block");
                            const obj='avatar';
                            const val=data;
                            window.dispatchEvent(new CustomEvent('doUpdateVariableValue', { detail: { obj,val } }));
                        }
                    });
                }
            };
            fileReader.readAsDataURL(file);
        };
    }
</script>
</body>
</html>

