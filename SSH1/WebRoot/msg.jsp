<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'msg.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="http://code.jquery.com/jquery-latest.js"></script> 
	<script type="text/javascript">
	$(function (){
			$.ajax({
			    url:'user/find.action',
			    type:'POST', //GET
			    async:true,    //或false,是否异步
			    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
			    success:function(data,textStatus,jqXHR){
			        alert(data);
			    },
			    error:function(XMLHttpRequest, textStatus, errorThrown){
			        // 状态码
                    alert(XMLHttpRequest.status);
                    // 状态
                   alert(XMLHttpRequest.readyState);
                    // 错误信息   
                    alert(textStatus);
			    },
			    complete:function(){
			        console.log('结束')
			    }
			})
			});
	</script>
  </head>
  
  <body>
    AAAAA<br>
  </body>
</html>
