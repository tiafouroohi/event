<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1><c:out value="${event.name }"></c:out></h1>

<a href="/edit/event/${event.id }">Edit</a>

<h3>Date:<c:out value="${event.date }"></c:out></h3>
<h3>Location:<c:out value="${event.location}"></c:out></h3>


<h3>Hosted by:<c:out value="${event.host.email}"></c:out></h3>
<h3>Created at:<c:out value="${event.getCreatedAt()}"></c:out></h3>

<h3>Updated at:<c:out value="${event.getUpdatedAt()}"></c:out></h3>

<c:forEach items="${event.getUsers() }" var="user">

<h3>People joining this event:<c:out value="${user.getName()}"></c:out></h3>

</c:forEach>


make a formform page takes model attribute

<h3>Message Wall</h3>
<form action="/event/${event.id }" method="post">
<p>
<label path="comment">Add a Comment</label> 
<errors path="comment"/>
<input name="comment"/>
</p>
<input type="submit" value="Submit"/>
</form>

<h3>Comments</h3>
<c:forEach items="${event.comments }" var="comment">
<c:out value="${comment.name }"></c:out>
</c:forEach>

<a href="/">Back to Dashboard</a>

</body>
</html>