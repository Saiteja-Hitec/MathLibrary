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
     margin-right: 20px;
   }
   
   .card-container {
    display: flex;
	justify-content: center;
	align-items: center;
	margin-top: 50px;
	height: 100%;
   }
   
   .card{
    width: 500px;
	height: 100%;
	padding: 15px;
   }
   
   .error {
     color: red;
     font-size: 10px;
   }
   
   .success-msg {
	color: green;
	font-size: 15px;
   }

</style>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">

	function validateSearch() {
		var searchValue = document.getElementById("searchBox").value;
		document.getElementById("valMsgQDes").innerHTML = "";
		if(isValid(searchValue)) {
			var srList = searchValue.trim().split(',');
			window.location.href = "/searchByKeyword?searchTerm="+searchValue;
		} else if(searchValue && searchValue.trim() !== ''){
			var srList = searchValue.trim().split(',');
	        if(srList.length > 3){
	        	document.getElementById("valMsgQDes").innerHTML = "Maximum keywords allowed to search are 3";
	        }
		} else {
			document.getElementById("valMsgQDes").innerHTML = "Enter any search term";
		}
	} 
	
	function isValid(value) {
		if(value && value.trim() !== '') {
			var srList = value.trim().split(',');
			console.log(srList);
	        if(srList.length > 3){
	        	return false;
	        }
	        return true;
		}
		return false;
	}
	
	function openKeywordInput(queryId, dispindex) {
		$("#qIndex").text(dispindex);
		document.getElementById("queryId").value = queryId;
	}
	
	function validateAndSaveKeywords() {
		document.getElementById("successMsg").innerHTML = "";
		document.getElementById("errorMsg").innerHTML = "";
		var searchValue = document.getElementById("keyWord").value;
		var queryID = document.getElementById("queryId").value;
		console.log(searchValue, queryID);
		if(isValid(searchValue) && queryID !== "NA") {
			$.ajax({
				type : 'GET',
				contentType : 'application/json',
				url : '/postKeywordsToQuery?queryId='+queryID+'&keyword='+searchValue,
				async : true,
				success : function(response) {
					console.log(response);
					document.getElementById("successMsg").innerHTML = "Saved successfully";
				},
				error : function(data) {
					console.log(data);
					document.getElementById("errorMsg").innerHTML = "Error: Invalid/Duplicate entry"
				}

			});
		} else {
			document.getElementById("errorMsg").innerHTML = "Not Valid"
		}
	}
</script>
</head>
<body>
		<h1>Math Query Library</h1><br/><br/>
		
		    <input type="text" id="searchBox"/>
		    <span class="error" id="valMsgQDes"></span>
		    <button onclick="validateSearch()">SEARCH</button><br/><br/>
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
         
         <div class="card-container">
          
            <div id="dispList" class="card">
               <c:forEach items="${mathList}" var="query" varStatus="track">
                 <p class="formulae">
                 <c:out value="${track.index + 1}. ${query.queryDesc}"/>
                 </p>
                  <button onclick="openKeywordInput(${query.queryId}, ${track.index + 1})">Enter Keywords</button>
                <br/>
               </c:forEach>
               <span class="not-found">${notFound}</span>
            </div>
            
            <div class="card">
              Enter Keywords to associate with query having index(choose from left): <span id="qIndex"></span>
              <input type="text" id="keyWord" name="keyword">
              <input type="hidden" id="queryId" value="NA">
              <span class="success-msg" id="successMsg"></span>
              <span class="error" id="errorMsg"></span><br/>
              <button onclick="validateAndSaveKeywords()">SAVE</button>
            </div>
         </div>
        <br/><br/>
        <a href="home">Return to Home</a>
        
        
        <script src="webjars/jquery/1.9.1/jquery.min.js"></script>
        <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        </body>
</html>