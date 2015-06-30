<%--
/** Title:			channel_select.jsp
 *  Description:
 *		应用知识主维度
 *		
 *  Copyright:		www.trs.com.cn
 *  Company:		TRS Info. Ltd.
 *  Author:			NZ
 *  Created:		2011年10月25日
 *  Vesion:			1.0
 *  Last EditTime:	
 *	Update Logs:
 *		高二军@2011年11月24日 产生页面
 *		高二军@2012年10月30日 兼容不显示的子维度可选，父维度不可选的情况。修改select为ul
 *
 *
 *  Parameters:
 *		see channel_select.xml
 *
 */
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="GBK" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>发送到WCM，请进行栏目选择</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">   
	<link rel="stylesheet" type="text/css" href="../css/easyui.css">
	<link rel="stylesheet" type="text/css" href="../css/icon.css">
	<link rel="stylesheet" type="text/css" href="../css/demo.css">
	<script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
	<script type="text/javascript">

function getChecked(){
	
	var t = $('#tt2').combotree('tree');	// combotree获取树对象
	var n = t.tree('getChecked');		// 获取选择的多节点  这个获取的是单节点  如果是单节点t.tree('getSelected')
	alert(n.length);


	
    var nodes = $('#tt2').tree('getChecked');// tree获取树对象
    alert(nodes.length);
    var s = '';
    for (var i = 0; i < n.length; i++) {
        if (s != '') 
            s += ',';
        s += n[i].id+"@"+n[i].text+"@"+n[i].attributes;//attributes
    }
    alert(s);
}

$(function(){
    $('#tt2').combotree({
        checkbox: true,
        url: 'http://localhost:8080/wcm/channel/getWcmTree.jsp?website=2',
        onClick: function(node){
            $(this).tree('toggle', node.target);
            //alert('you dbclick '+node.text);
        },
        onContextMenu: function(e, node){
            e.preventDefault();
            $('#tt2').tree('select', node.target);
            $('#mm').menu('show', {
                left: e.pageX,
                top: e.pageY
            });
        }
    });
	
})
</script>
</head>

<body>
<div onclick="getChecked()">ddddddd</div>
	<select id="tt2" multiple="multiple"  class="easyui-combotree"cascadeCheck="false" style="width:200px"></select>
</body>
</html>
