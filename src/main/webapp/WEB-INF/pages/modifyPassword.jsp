<script>
   //修改密码信息
   function doSubmit(){
      //获取输入的信息
      var opassword=$("#opassword").val();
      var npassword=$("#npassword").val();
      var snpassword=$("#snpassword").val();
      //检查输入信息
      if(opassword.length==0 || npassword.length==0 || snpassword.length==0){
         alert("请先补全密码信息!");
         return;
      }
      //检查确认新密码
      if(npassword!=snpassword){
         alert("请输入两次一致的新密码!");
         return;
      }
      //开始提交后台处理修改信息
      $.ajax({
         type: "post",
         url: "${pageContext.request.contextPath}/api/user/modifyPassword.do",
         data: {password:opassword,identify:snpassword},
         success: function(response){
            if(JSON.parse(response.status)){
               //修改成功
               alert(response.message);
               //刷新本页
               window.location.reload();
            }else{
               //修改失败
               alert(response.message);
            }
         }
      });
   }
</script>
