<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>DDLGenerator Tool</title>
	<%@ include	file="snippet/scripts-snippet.html"%>
	<script>
$(document).ready(function(){
    $("#upload").click(function(event){
    	event.preventDefault();
    	var formData = new FormData();
    	console.log("fileName="+$("#inputFile").get(0).files[0]);
    	formData.append("uploadFile",$("#inputFile").get(0).files[0]);
    	formData.append("email",$("#email").val());
        $.ajax({
        	type:"POST",
        	url:"upload_file.wss",
        	aynsc:true,
        	data: formData,
        	contentType:false,
        	processData: false,
        	success:function(msg){
        		alert(msg);
        		$("#email").val("");
        		$("#inputFile").val("");
        	},
        	error:function(msg){
        		alert("Failed to upload file");
        	}
        });
    });
});
</script>
</head>
<body id="main_body">

	<img id="top" src="images/top.png" alt="">
		<div id="form_container">

			<h1>
				<a>DDLGenerator Tool</a>
			</h1>
			<form id="form_41746" class="appnitro">
				<div class="form_description">
					<h2>DDLGenerator Tool</h2>
					<p></p>
				</div>
				<ul>

					<li id="li_1"><label class="description" for="element_1">Upload
							a Data Model Sheet </label>
						<div>
							<input type="file" name="file" id="inputFile" placeholder="Browse" data-clear-btn="true">
						</div>
						<p class="guidelines" id="guide_1">
							<small>Data Model sheet should be .xls or .xlsx</small>
						</p></li>
					<li id="li_2"><label class="description" for="element_2">Email
					</label>
						<div>
							<input id="email" name="email" type="email" value=""
								data-clear-btn="true" placeholder="Your Email..." />
						</div>
						<p class="guidelines" id="guide_2">
							<small>Enter your email</small>
						</p></li>

					<li class="buttons"><div align="right"><input data-inline="true" value="Upload" type="button" id="upload" ></div></li>
				</ul>
			</form>
			<div id="footer">
			</div>
		</div> <img id="bottom" src="images/bottom.png" alt="">
</body>
</html>