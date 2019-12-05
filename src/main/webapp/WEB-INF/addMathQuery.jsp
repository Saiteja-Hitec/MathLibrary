<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
<style>
   body{
     align: center;
   }
   
   .error {
     color: red;
     font-size: 10px;
   }
</style>
</head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	
<script>
     function validate() {
    	 var queryval = document.getElementById("querydesc").value;
    	 var ctgSel = document.getElementById("ctgname").value;
    	 var keywordRes = document.getElementById("keyWord").value;
    	 document.getElementById("valMsgQDes").innerHTML = "";
    	 document.getElementById("valMsgCtg").innerHTML = "";
    	 document.getElementById("valKeyword").innerHTML = "";
    	 var ctgRes;
    	 if(!isNaN(ctgSel)) {
    		 ctgRes = ctgSel.toString();
    	 }
    	 console.log(queryval, ctgRes);
    	 if(isValid(queryval) && ctgRes !== '-1' && validateKeywords(keywordRes)) {
    		 return true;
    	 } else if(!isValid(queryval)) {
    		 document.getElementById("valMsgQDes").innerHTML = "This field is required";
    	 } else if(ctgRes === '-1') {
    		 document.getElementById("valMsgCtg").innerHTML = "This field is required";
    	 } else if(!validateKeywords(keywordRes)) {
    		 document.getElementById("valKeyword").innerHTML = "Each Keyword should not exceed 25chars and max keywords allowed are 3 separated by comma";
    	 }
    	 return false;
     }
     
     function isValid(value){
    	 if(value && value.trim() !== '') {
    		 return true;
    	 }
    	 return false;
     }
     
     function validateKeywords(value) {
    	 if(value && value.trim() !== '') {
 			var srList = value.trim().split(',');
 	        if(srList.length > 3){
 	        	return false;
 	        } else if(srList.length == 1 && srList[0].trim().length > 25) {
 	        	return false;
 	        } else if(srList.length > 1) {
 	        	for(let item of srList) {
 	        		if(item.trim().length > 25) {
 	        			return false;
 	        		}
 	        	}
 	        }
 	        return true;
 		}
 		return true;
     }
</script>

<body>
		<form action="postQuery" method="post" onsubmit="return validate()">
		     <label>Enter Math Query here</label>
		     <textarea rows="10" cols="40" name="queryDesc" id="querydesc" placeholder="Type here.." required>
		      
		     </textarea>
		       <span class="error" id="valMsgQDes"></span>
		     <br/><br/>
		     <label>Choose Category</label>
             <select id="ctgname" name="categoryId" required>
	              <option value="-1">--Select--</option>
	              <c:forEach var="category" items="${categoryList}">
	              <option value="${category.categoryId}">${category.categoryName}</option>
	
	              </c:forEach>
             </select>
               <span class="error" id="valMsgCtg"></span>
             <br/><br/>
             <label>Enter Keywords(Optional)</label>
             <input type="text" name="keyword" id="keyWord"/>
             <span class="error" id="valKeyword"></span>
             <br/><br/>
             <input type="submit" value="SAVE">
		     
		</form><br/><br/>
		<a href="home">Return to Home</a>
</body>
</html>