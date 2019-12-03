<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="//cdn.muicss.com/mui-0.10.0/css/mui.min.css"
	rel="stylesheet" type="text/css" />
<script src="//cdn.muicss.com/mui-0.10.0/js/mui.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript">
	function addCategory() {

		var categoryData = new Object();

		categoryData.categoryName = document.getElementById("category").value;

		console.log(categoryData.categoryName);

		if (categoryData.categoryName != null
				&& categoryData.categoryName != "") {

			$.ajax({
				type : 'GET',
				contentType : 'application/json',
				url : '/questions/add-category/validate?category='
						+ categoryData.categoryName,
				async : true,
				success : function(response) {
					document.getElementById("message").innerHTML = response;
				},
				error : function(data) {
					document.getElementById("message").innerHTMl = data;
				}
			});
		} else {
			alert("Required Fields Must be entered (Category)");
		}

	}
</script>
</head>

<body>

	<div class="mui-container-fluid"
		style="display: flex; justify-content: center; margin-top: 200px;">
		<div class="mui-panel" style="width: 500px;">
			<span id="message"></span>
			<div class="mui-form">
				<legend>Add a Category</legend>
				<div class="mui-divider"></div>
				<div class="mui-textfield">
					<input type="text" placeholder="Add category" name="category"
						id="category">
				</div>
				<button type="button" class="mui-btn mui-btn--raised"
					style="float: right; background-color: green; color: white;"
					onclick="addCategory()">Submit</button>
			</div>
		</div>
	</div>
</body>
</html>
</html>