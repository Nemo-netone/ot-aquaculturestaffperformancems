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
    <link rel="stylesheet" href="static/css/mapbox-gl.css">
    <link rel="stylesheet" href="static/css/sweetalert.css">
    <!-- 公告栏样式 -->
    <style>
        .announcement {
            padding: 25px;
            background-color: #ffffff;
            border: 2px solid #e9ecef; /* 添加边框，颜色为灰色 */
            margin-bottom: 20px;
            font-size: 20px;
            text-align: center; /* 文字居中 */
            max-width: 1500px; /* 调整最大宽度 */
            margin-left: auto; /* 自动边距 */
            margin-right: auto; /* 自动边距 */
        }
        .announcement h4 {
            margin-top: 0;
            font-size: 24px;
            font-weight: bold;
            font-style: italic;
        }
        .announcement-time {
            color: #6c757d; /* 暗灰色，用于公告时间 */
            font-size: 0.575em; /* 较小的字体大小 */
            margin-top: 8px; /* 与上文内容保持一定距离 */
        }
        .announcement-title {
            text-align: center; /* 标题居中 */
            margin-bottom: 20px; /* 与公告栏保持一定距离 */
        }
    </style>
</head>
<body class="email-chimp ">
<div class="d-grid grid-cols-1 custom-grid-media">
    <div class="">
        <div class="card border subs-card">
            <div class="card-body" id="app">
                <div class="">
                    <div class="">
                        <% User loginUser=(User)request.getSession().getAttribute("loginUser"); %>
                        <h2 class="mb-2">Hi , <%=loginUser!=null?loginUser.getUsername():""%>(<%=loginUser!=null?loginUser.getIdentify():""%>)您好！</h2>
                        <h4 class="pb-3">欢迎进入养殖人员绩效管理系统!</h4>
                    </div>
                    <br/>
                    <br/>
                    <br/>
                    <br/>
                    <!-- 公告栏标题 -->
                    <h3 class="announcement-title">公告栏</h3>
                    <!-- 公告栏 -->
                    <div class="announcement" v-html="noticeHtml">

                    </div>
                    <br/>
                    <br/>
                    <br/>
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
<!-- SweetAlert JavaScript -->
<script src="static/js/sweetalert.min.js"></script>
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
<script>
    //创建Vue实例
    const app = Vue.createApp({
        data() {
            return {
                noticeHtml:''
            }
        },
        mounted(){
            //请求后端加载数据
            axios.post("/api/notice/list.do", {
            }).then((response) => {
                // 判断结果
                if(JSON.parse(response.data.status)){
                    var list = response.data.data;
                    var tbodyHtml='';
                    for(var i = 0 ; i<list.length;i++) {
                        tbodyHtml += '<h4 style="font-weight: bold;font-style: italic;">'+list[i].title+'</h4>';
                        tbodyHtml += '<pre style="margin-top: 10px;margin-left: 7px;">'+list[i].notice+'</pre>';
                        tbodyHtml += '<p class="announcement-time">公告时间：'+list[i].ctime+'</p>';
                        tbodyHtml += '<hr>';
                    }
                    //展示数据
                    this.noticeHtml=tbodyHtml;
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
</script>
</body>
</html>
