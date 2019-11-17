<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<style>
.addCtgForm {
	align-items: center;
}

.error-msg {
	color: red;
	font-size: 15px;
}

.flex-container {
	display: flex;
	flex-wrap: nowrap;
}

.flex-container>div {
	width: 500px;
	margin: 10px;
	text-align: center;
}

.success-msg {
	color: green;
	font-size: 15px;
}
</style>
</head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	function validate() {
		var ctName = document.getElementById("ctgName").value;
		if (ctName && ctName.trim() === '') {
			alert("Please enter some value");
			return "false";
		}
		return "true";
	}
</script>
<body>

	<div class="flex-container">
		<div class="addCtgForm">
			<form action="postCtg" method="post" onsubmit="return validate()">
				<label>Add Category: </label> <input type="text" id="ctgName"
					name="categoryName"> <br /> <br /> <input type="submit"
					value="SAVE">

			</form>
			<br /> <br /> <span class="success-msg">${successmsg}</span> <span
				class="error-msg">${errormsg}</span> <br /> <br /> <a href="home">Return
				to Home</a>
		</div>
		<div>
			<label>Available Categories</label><br/><br/>
			<c:forEach items="${categoryList}" var="ctg" varStatus="track">
	
					<c:out value="${track.index + 1}. ${ctg.categoryName}" />
				
				<br />
			</c:forEach>
		</div>
	</div>
</body>
</html>