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
<div class="row">
    <div class="col-xl-12 col-lg-4">
        <div class="card">
            <div class="card-header d-flex justify-content-between">
                <div class="header-title">
                    <h6 class="card-title">请完善以下信息</h6>
                </div>
            </div>
            <div class="card-body">
                <form id="app">
                    <div class="form-group">
                        <label>部门名称</label>
                        <select class="form-control" v-model="did" v-html="departmentHtml" @change="getPositionList">
                            <option value="">请选择部门名称</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>岗位名称</label>
                        <select class="form-control" v-model="pid" v-html="positionHtml">
                            <option value="">请选择岗位名称</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>用户名</label>
                        <input type="text" class="form-control" v-model="username" placeholder="请输入用户名" autocomplete="off">
                    </div>
                    <div class="form-group">
                        <label>登录密码</label>
                        <input type="password" class="form-control" v-model="password" placeholder="请输入登录密码" autocomplete="off">
                    </div>
                    <div class="form-group">
                        <label>用户身份</label>
                        <select class="form-control" v-model="identify" >
                            <option value="">请选择员工身份</option>
                            <option>普通员工</option>
                            <option>管理员</option>
                        </select>
                    </div>
                    <button type="button" class="btn btn-primary btn-lg mt-3" @click="btnSubmit">添加信息</button>
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
<!-- Vue.js Vendors -->
<script src="static/vendor/vue/vue.global.js"></script>
<script src="static/vendor/vue/axios.min.js"></script>
<!-- Custom Javascript -->
<script>
    //创建Vue实例
    const app = Vue.createApp({
        data() {
            return {
                did: '',
                pid: '',
                username: '',
                password: '',
                identify: '',
                name: '-',
                sex: '-',
                age: '',
                birthdate: '-',
                idcard: '-',
                phone: '-',
                avatar: 'user.jpg',
                departmentHtml:'<option value="">请选择部门名称</option>',
                positionHtml:'<option value="">请选择岗位名称</option>'
            }
        },
        mounted(){
            //获取信息
            axios.post("/api/department/list.do", {
            }).then((response) => {
                // 判断结果
                if(JSON.parse(response.data.status)){
                    var dataHtml='<option value="">请选择部门名称</option>';
                    var list = response.data.data;
                    for(var i= 0 ; i<list.length;i++){
                        dataHtml += '<option value="' + list[i].did + '">' + list[i].dname +'</option>';
                    }
                    //展示数据
                    this.departmentHtml=dataHtml;
                } else {
                    // 显示提示
                    window.parent.swalForError('提示','数据加载失败，请稍后重试！');
                }
            }).catch((error) => {
                // 弹出错误信息
                window.parent.swalForError('提示','网络请求失败，请稍后重试！');
            });
        },
        methods: {
            getPositionList(event){
                //获取信息
                axios.post("/api/position/listBydid.do", {
                    did:event.target.value
                }).then((response) => {
                    // 判断结果
                    if(JSON.parse(response.data.status)){
                        var dataHtml='<option value="">请选择岗位名称</option>';
                        var list = response.data.data;
                        for(var i= 0 ; i<list.length;i++){
                            dataHtml += '<option value="' + list[i].pid + '">' + list[i].pname +'</option>';
                        }
                        //展示数据
                        this.positionHtml=dataHtml;
                    } else {
                        // 显示提示
                        window.parent.swalForError('提示','数据加载失败，请稍后重试！');
                    }
                }).catch((error) => {
                    // 弹出错误信息
                    window.parent.swalForError('提示','网络请求失败，请稍后重试！');
                });
            },
            btnSubmit(){
                //检查输入
                if(this.did.trim().length==0 || this.pid.trim().length==0 || this.username.trim().length==0 || this.password.trim().length==0 || this.identify.trim().length==0 ){
                    window.parent.swalForWarning("提示", "请先完善员工信息！");
                    return;
                }
                //请求后端
                axios.post("/api/user/add.do", {
                    did: this.did,
                    pid: this.pid,
                    username: this.username,
                    password: this.password,
                    identify: this.identify,
                    name: this.name,
                    sex: this.sex,
                    age: this.age,
                    birthdate: this.birthdate,
                    phone: this.phone,
                    idcard: this.idcard,
                    avatar: this.avatar
                }).then((response) => {
                    // 判断结果
                    if(JSON.parse(response.data.status)){
                        //新增成功
                        window.parent.swalForSuccess("提示","新增员工信息成功！");
                        //刷新页面
                        window.location.reload();
                    } else {
                        //新增失败
                        window.parent.swalForError("提示", "新增员工信息失败，可能是当前员工信息已存在！");
                    }
                }).catch((error) => {
                    // 弹出错误信息
                    window.parent.swalForError('提示','网络请求失败，请稍后重试！');
                });
            }
        }
    });
    //挂载实例
    app.mount('#app');
</script>
</body>
</html>

