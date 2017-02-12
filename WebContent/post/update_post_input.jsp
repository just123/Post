<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.post.model.*"%>
<%
PostVO postVO = (PostVO) request.getAttribute("postVO"); //PostServlet.java (Concroller), 存入req的postVO物件 (包括幫忙取出的postV, 也包括輸入資料錯誤時的postV物件)
%>
<html>
<head>
<title>post資料修改 - update_post_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>post資料修改 - update_post_input.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></td>
	</tr>
</table>

<h3>資料修改:</h3>
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
	<tr>
		<td>文章編號:<font color=red><b>*</b></font></td>
		<td><%=postVO.getPost_Id()%></td>
	</tr>
	<tr>
		<td>會員編號(發文者):</td>
		<td><input type="TEXT" name="mem_Id" size="5" value="<%=postVO.getMem_Id()%>" /></td>
	</tr>
	<jsp:useBean id="postSvc" scope="page" class="com.post.model.PostService" />
	<tr>
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
		<td><input type="TEXT" name="post_title" size="80" value="<%=postVO.getPost_title()%>" /></td>
	</tr>
	<tr>
		<td>文章內容:</td>
		<td><input type="TEXT" name="post_content" size="10" value="<%=postVO.getPost_content()%>" /></td>
	</tr>
	<tr>
		<td>發佈時間:</td>
		<td bgcolor="#CCCCFF"><input class="cal-TextBox"
			onFocus="this.blur()" size="9" readonly type="text"
			name="post_time" value="<%=postVO.getPost_time()%>">
		<a class="so-BtnLink" href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','post_time','BTN_date');return false;">
			<img align="middle" border="0" name="BTN_date" src="images/btn_date_up.gif" width="22" height="17" alt="發布日期">
		</a></td>
	</tr>
	<tr>
		<td>修改時間:</td>
		<td bgcolor="#CCCCFF"><input class="cal-TextBox"
			onFocus="this.blur()" size="9" readonly type="text"
			name="post_upDate" value="<%=postVO.getPost_upDate()%>">
		 <a class="so-BtnLink" href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','post_upDate','BTN_date');return false;">
			<img align="middle" border="0" name="BTN_date" src="images/btn_date_up.gif" width="22" height="17" alt="修改日期">
		</a></td>
	</tr>
	<tr>
		<td>回覆數量:</td>
		<td><input type="TEXT" name="post_resNum" size="4" value="<%=postVO.getPost_resNum()%>" /></td>
	</tr>
	


<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="post_Id" value="<%=postVO.getPost_Id()%>">
<input type="submit" value="送出修改"></FORM>

</body>
</html>
