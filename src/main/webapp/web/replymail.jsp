<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>回复邮件</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css"></link>
    <script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="../js/bootstrap.min.js"></script>
    <style type="text/css">
  .col-sm-10 {
  width: 15%;
  }
  .col-sm-2 {
    width: 35%;
}
  </style>
  </head>
  
  <body>
        <form class="form-horizontal" role="form">
    <div align="center">
    <h1>给用户回信</h1>
    </div>
    <div class="form-group" align="center" style="margin-top: 50px;">
      <label  class="col-sm-2 control-label">收信人：</label>
      <div class="col-sm-10">
         <input type="text" class="form-control" placeholder="收信人"></input>     
      </div>
      <div class="col-sm-10" style="width: 7%;padding:0px;">
      <input type="radio" id="all">所有用户
      </div>
      <div class="col-sm-10" style="width: 5%;padding:0px;">
      <input type="radio" id="VIP" >VIP用户
      </div>
      <div class="col-sm-10" style="width: 7%;padding:0px;">
      <input type="radio" id="common" >普通用户
      </div>
   </div>
    <div class="form-group" align="center">
    <label for="name" style="float: left;width: 9%;margin-left: 355px;">内容：</label>
    <textarea class="form-control" rows="3" style="width: 35%;height:50%;float: left;"></textarea>
  </div>
  <div class="form-group" align="center">
   &emsp;&emsp;&emsp;&emsp;<button type="submit" class="btn btn-default">提交</button>
   &emsp;&emsp;&emsp;&emsp;<button type="reset" class="btn btn-default">重置</button>
   </div>
    </form>
  </body>
</html>
