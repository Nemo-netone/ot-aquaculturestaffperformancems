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
                        <label>员工信息</label>
                        <select class="form-control" v-model="uid" v-html="userHtml">
                        </select>
                    </div>
                    <div class="form-group">
                        <label>奖惩原因</label>
                        <input type="text" class="form-control" v-model="reason" placeholder="请输入奖惩原因" autocomplete="off">
                    </div>
                    <div class="form-group">
                        <label>奖惩类型</label>
                        <select class="form-control" v-model="type">
                            <option value="">请选择奖惩类型</option>
                            <option >奖</option>
                            <option >惩</option>
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
                uid: '',
                reason: '',
                type: '',
                userHtml: '<option value="">请选择员工信息</option>'
            }
        },
        mounted(){
            //获取信息
            axios.post("/api/user/list.do", {
            }).then((response) => {
                // 判断结果
                if(JSON.parse(response.data.status)){
                    var dataHtml='<option value="">请选择员工信息</option>';
                    var list = response.data.data;
                    for(var i= 0 ; i<list.length;i++){
                        dataHtml += '<option value="' + list[i].uid + '">' + list[i].department.dname + '_' + list[i].position.pname + '_' + list[i].username +'</option>';
                    }
                    //展示数据
                    this.userHtml=dataHtml;
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
            btnSubmit(){
                //检查输入
                if(this.uid.trim().length==0 || this.reason.trim().length==0 || this.type.trim().length==0  ){
                    window.parent.swalForWarning("提示", "请先完善奖惩信息！");
                    return;
                }
                //请求后端
                axios.post("/api/reward/add.do", {
                    uid: this.uid,
                    reason: this.reason,
                    type: this.type
                }).then((response) => {
                    // 判断结果
                    if(JSON.parse(response.data.status)){
                        //新增成功
                        window.parent.swalForSuccess("提示","新增奖惩信息成功！");
                        //刷新页面
                        window.location.reload();
                    } else {
                        //新增失败
                        window.parent.swalForError("提示", "新增奖惩信息失败，可能是当前奖惩信息已存在！");
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

