<script>
    //员工注册
    function doRegister(){
        //获取输入的信息
        var username=$("#username").val();
        var password=$("#password").val();
        var identify=$("#identify").val();
        //检查输入
        if(username.length==0 || password.length==0 || identify.length==0){
            alert("请先补全注册信息!");
            return;
        }
        //开始提交后台处理登录信息
        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/api/user/register.do",
            data: {username:username,password:password,identify:identify},
            success: function(response){
                if(JSON.parse(response.status)){
                    alert(response.message);
                    window.location.reload();
                }else{
                    alert(response.message);
                }
            }
        });
    }
</script>
