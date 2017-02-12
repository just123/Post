<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.post.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    PostService postSvc = new PostService();
    List<PostVO> list = postSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有Post資料 - listAllPost.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有Post資料 - listAllPost.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<table border='1' bordercolor='#CCCCFF' width='800'>
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
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="postVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${postVO.post_Id}</td>
			<td>${postVO.mem_Id}</td>
			<td>${postVO.post_class}</td>
			<td>${postVO.post_class_Id}</td>
			<td>${postVO.post_title}</td>
			<td>${postVO.post_content}</td>
			<td>${postVO.post_time}</td>
			<td>${postVO.post_upDate}</td>
			<td>${postVO.post_resNum}</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/post/post.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="post_Id" value="${postVO.post_Id}">
			     <input type="hidden" name="post_class_Id$post_class" value="${postVO.post_class_Id}-${postVO.post_class}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>

			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/post/post.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="post_Id" value="${postVO.post_Id}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

<br>本網頁的路徑:<br><b>
   <font color=blue>request.getServletPath():</font> <%= request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%= request.getRequestURI()%> </b>

</body>
</html>
