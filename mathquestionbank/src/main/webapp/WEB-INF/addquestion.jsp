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
	function addQuestion() {

		var questionData = new Object();

		questionData.questionDesc = document.getElementById("question").value;
		questionData.category = document.getElementById("category").value;
		keywords = document.getElementById("keywords").value;
		questionData.keywords = (keywords === null) || (keywords === '') ? ''
				: keywords;

		console.log(questionData.category.slice(1));

		if (questionData.questionDesc != null
				&& questionData.questionDesc != ""
				&& questionData.category != null && questionData.category != "") {

			$.ajax({
				type : 'GET',
				contentType : 'application/json',
				url : '/questions/add-question/validate?question='
						+ questionData.questionDesc + '&categoryId='
						+ questionData.category[0] + '&categoryName='
						+ questionData.category.slice(1) + '&keywords='
						+ questionData.keywords,
				async : true,
				success : function(response) {
					document.getElementById("message").innerHTML = response;
				},
				error : function(data) {
					document.getElementById("message").innerHTMl = data;
				}
			});
		} else {
			alert("Required Fields Must be entered (Question & Category)");
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
				<legend>Add a Question</legend>
				<div class="mui-divider"></div>
				<div class="mui-textfield">
					<input type="text" placeholder="Add question" name="question"
						required="required" id="question">
				</div>
				<div class="mui-select">
					<select name="category" id="category" required="required">
						<c:forEach items="${category}" var="category">
							<option value="${category.categoryId}${category.categoryName}">${category.categoryName}</option>
						</c:forEach>
					</select> <label>Select Category</label>
				</div>
				<div class="mui-textfield">
					<input type="text" placeholder="Add keyword" name="keywords"
						id="keywords">
				</div>
			</div>
			<button type="button" class="mui-btn mui-btn--raised"
				onclick="addQuestion()"
				style="float: right; background-color: green; color: white;">Submit</button>
		</div>
	</div>
</body>
</html>