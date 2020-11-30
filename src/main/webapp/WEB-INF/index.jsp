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


<h1>EVENTS</h1>
<h2>Welcome, <c:out value="${user.firstname }"/></h2> <a href="/logout">Logout</a>

<h3>Here are some events in your state:</h3>
<c:forEach items="${allEvents}" var="event">
<h4><a href="/event/${event.id }"><c:out value="${event.name}"/></a></h4>
<p>Date: <c:out value="${event.date}"/></p>
<p>Location: <c:out value="${event.location}"/></p>
<p>People attending: <c:out value="${count }"/>

<a href="/join/${event.id }">Join this event!</a>

<a href="/edit/event/${event.id }">Edit Event</a>

</c:forEach>


<h3>Here are some events in another state:</h3>



<h1>Create an Event</h1>
<form:form action="/" method="post" modelAttribute="event">
<p>
<form:label path="name">Name</form:label>
<form:errors path="name"></form:errors>
<form:input path="name"></form:input>
</p>
<p>
<form:label path="Date">Date</form:label>
<form:errors path="Date"></form:errors>
<form:input type="date" path="Date"></form:input>
</p>
<p>
<form:label path="Location">Location</form:label>
<form:errors path="Location"></form:errors>
<form:input path="Location"></form:input>
</p>
<input type="submit" value="Submit"/>

</form:form>
</body>
</html>