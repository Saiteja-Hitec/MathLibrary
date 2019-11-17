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
    	 document.getElementById("valMsgQDes").innerHTML = "";
    	 document.getElementById("valMsgCtg").innerHTML = "";
    	 var ctgRes;
    	 if(!isNaN(ctgSel)) {
    		 ctgRes = ctgSel.toString();
    	 }
    	 console.log(queryval, ctgRes);
    	 if(isValid(queryval) && ctgRes !== '-1') {
    		 return true;
    	 } else if(!isValid(queryval)) {
    		 document.getElementById("valMsgQDes").innerHTML = "This field is required";
    	 } else if(ctgRes === '-1') {
    		 document.getElementById("valMsgCtg").innerHTML = "This field is required";
    	 }
    	 return false;
     }
     
     function isValid(value){
    	 if(value && value.trim() !== '') {
    		 return true;
    	 }
    	 return false;
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
             <input type="submit" value="SAVE">
		     
		</form><br/><br/>
		<a href="home">Return to Home</a>
</body>
</html>