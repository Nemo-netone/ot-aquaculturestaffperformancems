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
    <link rel="stylesheet" href="static/css/mapbox-gl.css">
</head>
<body class=" color-light ">
<div class="row">
    <div class="col-sm-12">
        <div class="card">
            <div class="card-header d-flex justify-content-between">
                <div class="header-title">
                    <h6 class="card-title">绩效考核列表</h6>
                </div>
            </div>
            <div class="card-body" id="app">
                <div class="table-responsive">
                    <table class="table data-table table-striped table-bordered" style="width: 99%;">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>员工信息</th>
                            <th>考核周期</th>
                            <th>考核结果</th>
                            <th>考核状态</th>
                            <th>申诉原因</th>
                            <th>更新时间</th>
                            <th>信息操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
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
            }
        },
        mounted(){
            //请求后端加载数据
            axios.post("/api/performance/list.do", {
            }).then((response) => {
                // 判断结果
                if(JSON.parse(response.data.status)){
                    var list = response.data.data;
                    var tbodyHtml='';
                    for(var i = 0 ; i<list.length;i++){
                        tbodyHtml += '<tr data-id="' + list[i].pfmcid + '">';
                        tbodyHtml += '<td>' + (i+1) + '</td>';
                        tbodyHtml += '<td>' + list[i].user.username +  '</td>';
                        tbodyHtml += '<td>' + list[i].period + '</td>';
                        tbodyHtml += '<td>' + list[i].result + '</td>';
                        tbodyHtml += '<td>' + list[i].status + '</td>';
                        tbodyHtml += '<td style="max-width: 180px; overflow: hidden;">' + list[i].appeal + '</td>';
                        tbodyHtml += '<td>' + list[i].utime + '</td>';
                        if(list[i].status=="申诉中"){
                            tbodyHtml += '<td><button type="button" class="btn btn-primary" onclick="doAppeal(this)">处理申诉</button></td>';
                        }else if(list[i].status=="考核中"){
                            tbodyHtml += '<td><button type="button" class="btn btn-primary" onclick="doPerformance(this)">开始考核</button></td>';
                        } else{
                            tbodyHtml += '<td><button type="button" class="btn btn-info" onclick="doModify(this)">修改</button>&nbsp;<button type="button" class="btn btn-warning" onclick="doDelete(this)">删除</button></td>';
                        }

                        tbodyHtml += '</tr>';
                    }
                    //展示数据
                    $("tbody").html(tbodyHtml);
                    //初始化DataTable
                    $("table").DataTable({
                        ordering: false,
                        language: {
                            "lengthMenu": "显示 _MENU_ 条记录",
                            "zeroRecords": "没有找到匹配项",
                            "info": "显示 _START_ 到 _END_ 条记录，总共 _TOTAL_ 条记录",
                            "infoEmpty": "没有记录可显示",
                            "infoFiltered": "(从 _MAX_ 条记录中过滤)",
                            "search": "请输入搜索内容",
                            "paginate": {
                                "first": "首页",
                                "previous": "上一页",
                                "next": "下一页",
                                "last": "尾页"
                            }
                        }
                    });
                } else {
                    // 显示提示
                    window.parent.swalForError('提示','数据加载失败，请稍后重试！');
                }
            }).catch((error) => {
                // 弹出错误信息
                window.parent.swalForError('提示','网络请求失败，请稍后重试！');
            });
        }
    });
    //挂载实例
    app.mount('#app');
    //修改信息
    function doModify(_this){
        //获取ID
        var pfmcid=$(_this).parent().parent().attr("data-id");
        //跳转修改页面
        window.parent.showPage("/performanceModify?pfmcid="+pfmcid);
    }
    function doAppeal(_this){
        //获取ID
        var pfmcid=$(_this).parent().parent().attr("data-id");
        //跳转修改页面
        window.parent.showPage("/performanceAppeal?pfmcid="+pfmcid);
    }
    function doPerformance(_this){
        //获取ID
        var pfmcid=$(_this).parent().parent().attr("data-id");
        //跳转修改页面
        window.parent.showPage("/adminPerformance?pfmcid="+pfmcid);
    }
    //删除信息
    function doDelete(_this){
        //获取ID
        var pfmcid=$(_this).parent().parent().attr("data-id");
        //询问是否确认删除
        window.parent.swalForQuestion("提示","请确认是否删除该绩效考核信息?",function(){
            axios.post("/api/performance/delete.do", {
                pfmcid:pfmcid
            }).then((response) => {
                // 判断结果
                if(JSON.parse(response.data.status)){
                    //删除成功
                    window.parent.swalForSuccess("提示","删除绩效考核信息成功！",function(){});
                    //刷新页面
                    window.location.reload();
                } else {
                    //删除失败
                    window.parent.swalForError("提示","删除绩效考核信息失败，请检查绩效考核信息是否存在!");
                }
            }).catch((error) => {
                // 弹出错误信息
                window.parent.swalForError('提示','网络请求失败，请稍后重试！');
            });
        });
    }
</script>
</body>
</html>

