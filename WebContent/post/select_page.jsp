<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>IBM Post: Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>IBM Post: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for IBM Post: Home</p>

<h3>��Ƭd��:</h3>
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

<ul>
  <li><a href='listAllPost.jsp'>List</a> all Posts. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="post.do">
		<b>��J�峹�s�� (�p10001):</b>
		<input type="text" name="post_Id">
		<input type ="submit" value="�e�X">
		<input type="hidden" name="action" value="getOne_For_Display">
	</FORM>
  </li>
	<jsp:useBean id="postSvc" scope="page" class="com.post.model.PostService" />
 
  <li>
    <FORM METHOD="post" ACTION="post.do">
		<b>��ܤ峹�s��:</b>
		<select size="1" name="post_Id">
			<c:forEach var="postVO" items="${postSvc.all}">
				<option value="${postVO.post_Id}">${postVO.post_Id}
			</c:forEach>
		</select>
		<input type="submit" value="�e�X">
		<input type="hidden" name="action" value="getOne_For_Display">
	</FORM>
  </li>
  
  <li>
     <FORM METHOD="post"ACTION="post.do">
		<b>��ܷ|���s��:</b>
		<select size="1" name="mem_Id">
			<c:forEach var="postVO" items="${postSvc.all}">
				<option value="${postVO.mem_Id}">${postVO.mem_Id}
			</c:forEach>
		</select>
		<input type="submit" value="�e�X">
		<input type="hidden" name="action" value="getOne_For_Display">
	</FORM>
  </li>
</ul>

<h3>�Q�װϺ޲z</h3>

<ul>
  <li><a href='addPost.jsp'>Add</a> a new Post.</li>
</ul>

</body>
</html>
