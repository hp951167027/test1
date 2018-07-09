<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<%  
    String path = request.getContextPath();  
    String basePath = request.getScheme() + "://"  
            + request.getServerName() + ":" + request.getServerPort()  
            + path + "/";  
%>  
  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
<head>  
<base href="<%=basePath%>">  
  
<title>欢迎注册</title>  
<meta http-equiv="pragma" content="no-cache">  
<meta http-equiv="cache-control" content="no-cache">  
<meta http-equiv="expires" content="0">  
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">  
<meta http-equiv="description" content="This is my page">  
<!-- 
    <link rel="stylesheet" type="text/css" href="styles.css"> 
    -->  
</head>  
  
<body>  
    <form action="${pageContext.request.contextPath }/user/regist" method="POST">  
    <!--  也可以使用user.username自动装入user属性，但在这里不是重点，所以就在后台手动获取其值-->  
        用户名：<input type="text" name="account"><br> 密  
          码：<input type="password" name="password"><br>  
        <input type="submit" value="注册">  
    </form>  
</body>  
</html>