<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>초기진입 jsp</title>
<script type="text/javascript">
var webUrl = "http://59.11.77.35:8000";
//var webUrl = "http://localhost:8000";
window.onload = function() 
{ 
    window.document.body.ownerDocument.clear();
    var ifrmFlag = document.getElementById('ifrm');
    if(ifrmFlag != null && ifrmFlag != "null" && ifrmFlag != "")
    {
        document.body.removeChild(ifrmFlag);
    }
    var iframe = document.createElement("iframe");
    iframe.id = "ifrm";
    iframe.src = webUrl;
    iframe.width = "100%";
    iframe.height = "100%";
    iframe.frameBorder = "0";
    iframe.scrolling = "no";
    document.body.appendChild(iframe);
};
</script>
</head>
<body topmargin="0" leftmargin="0">
	<jsp:forward page="/main"></jsp:forward>
</body>
</html>