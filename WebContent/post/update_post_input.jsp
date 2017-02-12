<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.post.model.*"%>
<%
PostVO postVO = (PostVO) request.getAttribute("postVO"); //PostServlet.java (Concroller), �s�Jreq��postVO���� (�]�A�������X��postV, �]�]�A��J��ƿ��~�ɪ�postV����)
%>
<html>
<head>
<title>post��ƭק� - update_post_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>post��ƭק� - update_post_input.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></td>
	</tr>
</table>

<h3>��ƭק�:</h3>
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
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
		<td>�峹�s��:<font color=red><b>*</b></font></td>
		<td><%=postVO.getPost_Id()%></td>
	</tr>
	<tr>
		<td>�|���s��(�o���):</td>
		<td><input type="TEXT" name="mem_Id" size="5" value="<%=postVO.getMem_Id()%>" /></td>
	</tr>
	<jsp:useBean id="postSvc" scope="page" class="com.post.model.PostService" />
	<tr>
		<td>�峹����:<font color=red><b>*</b></font></td>
		<td><select size="1" name="post_class_Id$post_class">
		    <option value="1-�ۤ�" ${(param.post_class_Id$post_class=='1-�ۤ�')? 'selected':'' }>�ۤ�
			<option value="2-�а�" ${(param.post_class_Id$post_class=='2-�а�')? 'selected':'' }>�а�
			<option value="3-��T" ${(param.post_class_Id$post_class=='3-��T')? 'selected':'' }>��T
			<option value="4-����" ${(param.post_class_Id$post_class=='4-����')? 'selected':'' }>����
			<option value="5-�{�i" ${(param.post_class_Id$post_class=='5-�{�i')? 'selected':'' }>�{�i
			<option value="6-��M" ${(param.post_class_Id$post_class=='6-��M')? 'selected':'' }>��M
			<option value="7-����" ${(param.post_class_Id$post_class=='7-����')? 'selected':'' }>����
			<option value="8-�߱o" ${(param.post_class_Id$post_class=='8-�߱o')? 'selected':'' }>�߱o
			</select></td>
	</tr>
	<tr>
		<td>�峹���D:</td>
		<td><input type="TEXT" name="post_title" size="80" value="<%=postVO.getPost_title()%>" /></td>
	</tr>
	<tr>
		<td>�峹���e:</td>
		<td><input type="TEXT" name="post_content" size="10" value="<%=postVO.getPost_content()%>" /></td>
	</tr>
	<tr>
		<td>�o�G�ɶ�:</td>
		<td bgcolor="#CCCCFF"><input class="cal-TextBox"
			onFocus="this.blur()" size="9" readonly type="text"
			name="post_time" value="<%=postVO.getPost_time()%>">
		<a class="so-BtnLink" href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','post_time','BTN_date');return false;">
			<img align="middle" border="0" name="BTN_date" src="images/btn_date_up.gif" width="22" height="17" alt="�o�����">
		</a></td>
	</tr>
	<tr>
		<td>�ק�ɶ�:</td>
		<td bgcolor="#CCCCFF"><input class="cal-TextBox"
			onFocus="this.blur()" size="9" readonly type="text"
			name="post_upDate" value="<%=postVO.getPost_upDate()%>">
		 <a class="so-BtnLink" href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','post_upDate','BTN_date');return false;">
			<img align="middle" border="0" name="BTN_date" src="images/btn_date_up.gif" width="22" height="17" alt="�ק���">
		</a></td>
	</tr>
	<tr>
		<td>�^�мƶq:</td>
		<td><input type="TEXT" name="post_resNum" size="4" value="<%=postVO.getPost_resNum()%>" /></td>
	</tr>
	


<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>����:<font color=red><b>*</b></font></td> -->
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
<input type="submit" value="�e�X�ק�"></FORM>

</body>
</html>
