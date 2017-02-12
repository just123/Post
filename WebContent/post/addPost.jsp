<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.post.model.*"%>
<%
PostVO postVO = (PostVO) request.getAttribute("postVO");
%>

<html>
<head>
<title>討論區文章資料新增 - addPost.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>討論區文章資料新增 - addPost.jsp</h3>
		</td>
		<td>
		   <a href="select_page.jsp"><img src="images/tomcat.gif" width="200" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>

<h3>addPost:</h3>
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

<FORM METHOD="post" ACTION="post.do" name="form1">
<table border="0">
	
		<jsp:useBean id="postSvc" scope="page" class="com.post.model.PostService" />
		<tr>
				<td>會員編號:<font color=red><b>*</b></font></td>
				<td><select size="1" name="mem_Id">
						<c:forEach var="postVO" items="${postSvc.all}">
							<option value="${postVO.mem_Id}">${postVO.mem_Id}
						</c:forEach>
				</select></td>
		</tr>
	<tr>
<!-- 		<td>文章分類:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="post_class"> -->
<%-- 			<c:forEach var="postVO" items="${postSvc.all}"> --%>
<%-- 				<option value="${postVO.post_class}">${postVO.post_class} --%>
<%-- 			</c:forEach> --%>
<!-- 			</select></td> -->
		<td>文章分類:<font color=red><b>*</b></font></td>
			<td><select size="1" name="post_class_Id$post_class">
				    <option value="1-自介" ${(param.post_class_Id$post_class=='1-自介')? 'selected':'' }>自介
					<option value="2-請問" ${(param.post_class_Id$post_class=='2-請問')? 'selected':'' }>請問
     				<option value="3-資訊" ${(param.post_class_Id$post_class=='3-資訊')? 'selected':'' }>資訊
					<option value="4-推薦" ${(param.post_class_Id$post_class=='4-推薦')? 'selected':'' }>推薦
					<option value="5-認養" ${(param.post_class_Id$post_class=='5-認養')? 'selected':'' }>認養
					<option value="6-協尋" ${(param.post_class_Id$post_class=='6-協尋')? 'selected':'' }>協尋
					<option value="7-捨獲" ${(param.post_class_Id$post_class=='7-捨獲')? 'selected':'' }>捨獲
					<option value="8-心得" ${(param.post_class_Id$post_class=='8-心得')? 'selected':'' }>心得
			</select></td>
	</tr>
	<tr>
		<td>文章標題:</td>
			<td><input type="TEXT" name="post_title" size="45"
				onfocus="if (value =='請输入標題'){value =''}"
				onblur="if (value ==''){value='請输入標題'}"
				value="<%=(postVO == null) ? "請输入標題" : postVO.getPost_title()%>" />
			</td>
	</tr>
	<tr>
		<td>文章內容:</td>
			<td><textarea rows="10" name="post_content" id="content"
					value="<%=(postVO == null) ? "" : postVO.getPost_content()%>">
			</textarea></td>
	</tr>
	<tr>
			<%	
				java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());
			%>
		<td>發布時間:</td>
			<td bgcolor="#CCCCFF">
		    	<input class="cal-TextBox" onFocus="this.blur()" size="9" readonly type="text" 
		    	name="post_time" value="<%= (postVO==null)? date_SQL :  postVO.getPost_time()%>">
			    <a class="so-BtnLink" href="javascript:calClick();return false;"
			      onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			      onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			      onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','post_time','BTN_date');return false;">
		        <img align="middle" border="0" name="BTN_date" src="images/btn_date_up.gif" width="22" height="17" alt="發布日期"></a>
		 	</td>
	</tr>
	<tr>
			<%
				java.sql.Date date_SQL1 = new java.sql.Date(System.currentTimeMillis());
			%>
		<td>修改時間:</td>
			<td bgcolor="#CCCCFF">
		    	<input class="cal-TextBox" onFocus="this.blur()" size="9" readonly type="text" 
		    	name="post_upDate" value="<%= (postVO==null)? date_SQL1 :  postVO.getPost_upDate()%>">
			    <a class="so-BtnLink" href="javascript:calClick();return false;"
			      onmouseover="calSwapImg('BTN_date1', 'img_Date_OVER',true);"
			      onmouseout="calSwapImg('BTN_date1', 'img_Date_UP',true);"
			      onclick="calSwapImg('BTN_date1', 'img_Date_DOWN');showCalendar('form1','post_upDate','BTN_date');return false;">
		         <img align="middle" border="0" name="BTN_date1" src="images/btn_date_up.gif" width="22" height="17" alt="修改時間"></a>
		    </td>
	</tr>
	
	<tr>
		<td>回覆數量:</td>
			<td><input type="TEXT" name="post_resNum" size="4"
				value="<%=(postVO == null) ? "1" : postVO.getPost_resNum()%>" /></td>
	</tr>
	</table>
		<br>
		<input type="hidden" name="action" value="insert">
		<input type="submit" value="送出新增">
	</FORM>

</body>
</html>
