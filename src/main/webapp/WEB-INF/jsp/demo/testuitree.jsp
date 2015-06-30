<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/demo.css">
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery.easyui.min.js"></script>
<title>tree</title>
</head>
<body>
<h2>Lazy Load Tree Nodes</h2>
	<div class="demo-info">
		<div class="demo-tip icon-tip"></div>
		<div>Get full hierarchical tree data but lazy load nodes level by level.</div>
	</div>
	<div style="margin:10px 0;"></div>
	<ul class="easyui-tree" data-options="url:'<%=basePath%>js/tree_data1.json',method:'get',loadFilter:myLoadFilter"></ul>
	<script>
		function myLoadFilter(data, parent){
			var state = $.data(this, 'tree');
			
		    function setData(){
		    	var serno = 1;
		        var todo = [];
		        for(var i=0; i<data.length; i++){
		            todo.push(data[i]);
		        }
		        while(todo.length){
		            var node = todo.shift();
		            if (node.id == undefined){
		            	node.id = '_node_' + (serno++);
		            }
		            if (node.children){
		                node.state = 'closed';
		                node.children1 = node.children;
		                node.children = undefined;
		                todo = todo.concat(node.children1);
		            }
		        }
		        state.tdata = data;
		    }
		    function find(id){
		    	var data = state.tdata;
		    	var cc = [data];
		    	while(cc.length){
		    		var c = cc.shift();
		    		for(var i=0; i<c.length; i++){
		    			var node = c[i];
		    			if (node.id == id){
		    				return node;
		    			} else if (node.children1){
		    				cc.push(node.children1);
		    			}
		    		}
		    	}
		    	return null;
		    }
		    
		    setData();
		    
		    var t = $(this);
		    var opts = t.tree('options');
		    opts.onBeforeExpand = function(node){
	    		var n = find(node.id);
	    		if (n.children && n.children.length){return}
		    	if (n.children1){
		    		var filter = opts.loadFilter;
		    		opts.loadFilter = function(data){return data;};
		    		t.tree('append',{
		    			parent:node.target,
		    			data:n.children1
		    		});
		    		opts.loadFilter = filter;
		    		n.children = n.children1;
		    	}
		    };
			return data;
		}
	</script>
</body>
</html>