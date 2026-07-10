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
                        <label>考核周期</label>
                        <input type="text" class="form-control" v-model="period" readonly="readonly" autocomplete="off">
                    </div>
                    <div class="form-group">
                        <label>考核结果</label>
                        <input type="text" class="form-control" v-model="result" readonly="readonly" autocomplete="off">
                    </div>
                    <div class="form-group">
                        <label>申诉原因</label>
                        <input type="text" class="form-control" v-model="appeal" placeholder="请输入申诉原因" autocomplete="off">
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
                pfmcid: <%= request.getParameter("pfmcid")%>,
                uid: '',
                period: '',
                result: '',
                status: '',
                appeal: ''
            }
        },
        mounted(){
            //获取信息
            axios.post("/api/performance/get.do", {
                pfmcid: this.pfmcid
            }).then((response) => {
                // 判断结果
                if(JSON.parse(response.data.status)){
                    //显示信息
                    this.uid=response.data.data.uid.toString();
                    this.period=response.data.data.period;
                    this.result=response.data.data.result;
                    this.status=response.data.data.status;
                    this.appeal=response.data.data.appeal;
                }
            }).catch((error) => {
                // 弹出错误信息
                window.parent.swalForError('提示','网络请求失败，请稍后重试！');
            });
        },
        methods: {
            btnSubmit(){
                //检查输入
                if(this.uid.trim().length==0||this.period.trim().length==0|| this.result.trim().length==0 || this.status.trim().length==0 || this.appeal.trim().length==0){
                    window.parent.swalForWarning("提示", "请先完善绩效考核信息！");
                    return;
                }
                //请求后端
                axios.post("/api/performance/modify.do", {
                    pfmcid: this.pfmcid,
                    uid: this.uid,
                    period: this.period,
                    result: this.result,
                    status: '申诉中',
                    appeal: this.appeal
                }).then((response) => {
                    // 判断结果
                    if(JSON.parse(response.data.status)){
                        //新增成功
                        window.parent.swalForSuccess("提示","提交绩效考核信息成功！");
                        //跳转修改页面
                        window.parent.showPage("userPerformance");
                    }else{
                        //新增失败
                        window.parent.swalForError("提示", "提交绩效考核信息失败，可能是当前绩效考核信息不存在！");
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

