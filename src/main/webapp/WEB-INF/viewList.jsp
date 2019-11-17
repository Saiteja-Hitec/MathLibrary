<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
<style>
   body{
     align: center;
   }
   
   .formulae {
     font-family: Arial, Helvetica, sans-serif;
     font-size: 18px;
   }

</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
		<h1>Math Query Library</h1><br/><br/>
		 <form action="getMathQueries" method="post">
		 <select id="ctgname" name="categoryId" required>
	              <option value="-1">--ALL--</option>
	              <c:forEach var="category" items="${categoryList}">
	              <option value="${category.categoryId}">${category.categoryName}</option>
	              </c:forEach>
         </select>
         <input type="submit" value="FETCH">
         </form>
         <span class="hint">Showing results for: ${ActiveCategory}</span>
         <br/><br/>
		<c:forEach items="${mathList}" var="query" varStatus="track">
            <p class="formulae">
            <c:out value="${track.index + 1}. ${query.queryDesc}"/>
            </p><br/>
        </c:forEach>
        <span class="not-found">${notFound}</span>
        <br/><br/>
        <a href="home">Return to Home</a>
        </body>
</html>