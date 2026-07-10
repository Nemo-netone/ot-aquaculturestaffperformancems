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
                <form style="display:none;" id="iform" action="/api/work/upload.do" method="post" enctype="multipart/form-data">
                    <input type="file" id="file" name="file" onclick="showChoose()" accept="image/*" >
                </form>
                <form id="app">
                    <div class="form-group">
                        <label>平均日增重(kg/只)</label>
                        <input type="number" class="form-control" v-model="data" placeholder="请输入平均日增重(kg/只)" autocomplete="off">
                    </div>
                    <div class="form-group">
                        <label>动物物种</label>
                        <input type="text" class="form-control" v-model="remarks" placeholder="请输入动物物种" autocomplete="off">
                    </div>
                    <div class="form-group">
                        <label>工作清单</label><br>
                        <textarea style="width: 1000px;height: 100px" v-model="worklist" placeholder="请输入工作清单" autocomplete="off"></textarea>
                    </div>
                    <div class="form-group">
                        <label>记录照片</label>
                        <input type="text" class="form-control" v-model="photo" onclick="clickChoose()" readonly="readonly" placeholder="<点击上传照片>" autocomplete="off">
                        <img id="img_pic"  class="mb-4" style="height:80px;width:80px;border:1px solid #cccccc;margin-top:10px;display: none;" alt="请上传照片"/>
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
<!-- Jquery form JavaScript -->
<script src="static/js/jquery-form.js"></script>
<!-- Vue.js Vendors -->
<script src="static/vendor/vue/vue.global.js"></script>
<script src="static/vendor/vue/axios.min.js"></script>
<script src="tinymce/tinymce.min.js"></script>
<script src="tinymce/langs/zh_CN.js" type="text/javascript" ></script>
<!-- Custom Javascript -->
<script>
    tinymce.init({
        selector: '#mytextarea',
        language:'zh_CN',
        /*plugins: 'image',
        toolbar: 'image',
        images_upload_url: '/demo/upimg.php',
        images_upload_base_path: '/demo',*/
    });
    //创建Vue实例
    const app = Vue.createApp({
        data() {
            return {
                data: '',
                remarks: '',
                photo:'',
                worklist:''
            }
        },
        mounted(){
            // 监听外部触发的自定义事件
            window.addEventListener('doUpdateVariableValue', (event) => {
                this.doUpdateVariableValue(event.detail.obj,event.detail.val);
            });
        },
        methods: {
            btnSubmit(){
                const dataStr = String(this.data);
                //检查输入
                if(dataStr.trim().length==0 || this.remarks.trim().length==0 || this.photo.trim().length==0||this.worklist.trim().length==0){
                    window.parent.swalForWarning("提示", "请先完善工作信息！");
                    return;
                }
                //请求后端
                axios.post("/api/work/add.do", {
                    data: dataStr,
                    remarks: this.remarks,
                    photo: this.photo,
                    worklist: this.worklist
                }).then((response) => {
                    // 判断结果
                    if(JSON.parse(response.data.status)){
                        //新增成功
                        window.parent.swalForSuccess("提示","新增工作信息成功！");
                        //刷新页面
                        window.parent.showPage("userWork");
                    } else {
                        //新增失败
                        window.parent.swalForError("提示", "新增工作信息失败，可能是当前工作信息已存在！");
                    }
                }).catch((error) => {
                    // 弹出错误信息
                    window.parent.swalForError('提示','网络请求失败，请稍后重试！');
                });
            },
            //更新变量值
            doUpdateVariableValue(obj,val){
                if(obj=="photo"){
                    this.photo=val;
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
                            const obj='photo';
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

