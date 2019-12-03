<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="//cdn.muicss.com/mui-0.10.0/css/mui.min.css"
	rel="stylesheet" type="text/css" />
<script src="//cdn.muicss.com/mui-0.10.0/js/mui.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript">
    function search(){
    	var searchKey = document.getElementById("search").value;
    	console.log(searchKey);
    	
    	if(searchKey!=null&&searchKey!=''){

    		window.location.href= "/questions/search/keywords?keywords="+searchKey;
    		
    		}else{
    			alert("Enter keyword");
    			
    			}
    	
        }
  
	function showInlineForm(id) {

		document.getElementById("addkeyword"+id).style.display = "none";
		document.getElementById("keywordform"+id).style.display = "block";
	}

	function showKeywordForm(id) {
		
		var keyword = document.getElementById("keyword"+id).value;
		console.log(id+"  "+ keyword);

		if(keyword!=null&&keyword!=''){

 		$.ajax({
			type : 'GET',
			contentType : 'application/json',
			url : '/questions/add-keyword?questionId='+id+'&keyword='+keyword,
			async : true,
			success : function(response) {
				console.log(response);
				if(response==="error"){
					document.getElementById("message"+id).innerHTML = response;
					}else{
						document.getElementById("addkeyword"+id).style.display = "block";
						document.getElementById("keywordform"+id).style.display = "none";
						}
				
			},
			error : function(data) {
				document.getElementById("message"+id).innerHTMl = data;
			}
		});  
		}else{
			alert("Enter keyword");
			document.getElementById("keywordform"+id).style.display = "none";
			document.getElementById("addkeyword"+id).style.display = "block";
			}
		
	}
</script>
<title>Math Question Bank</title>
<style type="text/css">
.heading {
	text-align: center;
	color: #ff8c1a;
	font-family: monospace;
}

.category-dropdown {
	padding: 10px;
	font-size: 15px;
	text-transform: capitalize;
	text-align: center;
	display: block;
}

li.category-dropdown:hover {
	color: #8080ff;
	background-color: #e6e6ff;
}

.questions-list {
	font-size: 18px;
	padding: 10px;
}
</style>
</head>
<body>
	<div class="mui-container-fluid">
		<h1 class="heading">
			<i>Math Question Library</i>
		</h1>
		<div class="mui-divider"></div>
		<div class="mui-row">
			<div class="mui-col-md-5"></div>
			<div class="mui-col-md-5">
				<div class="mui-form--inline">
					<div class="mui-textfield">
						<input type="text" placeholder="Search by keywords" id="search"
							style="width: 300px;">
					</div>
					<button type="button" class="mui-btn mui-btn--accent"
						onclick="search()" style="background-color: green; color: white;">Search</button>
				</div>
			</div>
			<div class="mui-col-md-2">
				<div class="mui-dropdown">
					<button class="mui-btn mui-btn--accent" data-mui-toggle="dropdown">
						Category <span class="mui-caret"></span>
					</button>
					<ul class="mui-dropdown__menu">
						<li class="category-dropdown"><a href="all">All</a></li>
						<c:forEach items="${category}" var="category">
							<li class="category-dropdown"><a
								href="category?categoryId=${category.categoryId}&categoryName=${category.categoryName}">${category.categoryName}</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<br> <br> <br>
		<div class="mui-row">
			<c:if test="${empty questions}">
				<p class="questions-list">No questions are available...</p>
			</c:if>
			<c:forEach items="${questions}" var="question">
				<div class="mui-col-md-8">
					<ul>
						<li class="questions-list"><i>${question.questionDesc}</i></li>
					</ul>
				</div>
				<div class="mui-col-md-4">
					<button type="button" class="mui-btn mui-btn--accent"
						id="addkeyword${question.questionId}"
						onclick="showInlineForm(${question.questionId})">Add
						Keyword</button>
					<div class="mui-form--inline" style="display: none;"
						id="keywordform${question.questionId}">
						<div class="mui-textfield">
							<input type="text" placeholder="keywords"
								id="keyword${question.questionId}" style="width: 250px;">
						</div>
						<button type="button" class="mui-btn mui-btn--accent"
							onclick="showKeywordForm(${question.questionId})">submit</button>
					</div>
					<span id="message${question.questionId}" style="color: red;"></span>
				</div>
			</c:forEach>
		</div>


	</div>
</body>
</html>