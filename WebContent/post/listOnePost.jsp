<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="com.post.model.*"%>
<%
PostVO postVO = (PostVO) request.getAttribute("postVO"); //PostServlet.java(Concroller), 存入req的postVO物件
%>
<html>
<head>
<title>文章 - listOnePost.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>文章 - listOnePost.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>文章編號</th>
		<th>會員編號(發文者)</th>
		<th>文章分類</th>
		<th>文章分類編號</th>
		<th>文章標題</th>
		<th>文章內容</th>
		<th>發佈時間</th>
		<th>修改時間</th>
		<th>回覆數量</th>
	</tr>
	<tr align='center' valign='middle'>
		<td><%=postVO.getPost_Id()%></td>
			<td><%=postVO.getMem_Id()%></td>
			<td><%=postVO.getPost_class()%></td>
			<td><%=postVO.getPost_class_Id()%></td>
			<td><%=postVO.getPost_title()%></td>
			<td><%=postVO.getPost_content()%></td>
			<td><%=postVO.getPost_time()%></td>
			<td><%=postVO.getPost_upDate()%></td>
			<td><%=postVO.getPost_resNum()%></td>
	</tr>
</table>

</body>
</html>
