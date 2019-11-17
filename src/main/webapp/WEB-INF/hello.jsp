<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<head>
<style>
   body{
     align: center;
   }
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
	function testmethod() {
		console.log("in test method");
		var id = 15;
		$.ajax({
			type : 'GET',
			url : "/result/" + id,
			contentType : "application/json",
			success : function(data) {
				console.log('success', data);

			},
			error : function(exception) {
				alert('Exeption:' + exception);
				console.log(exception);
			}
		});

	}
</script>
</head>
<body>
		<h1>Math Query Library</h1>
		<a href="getMathQueries">View All Queries</a><br/></br>
		<a href="addQuery">Add New Query</a> <br/><br/>
		<a href="addCategory">Add New Category</a><br/><br/>
</body>
</html>