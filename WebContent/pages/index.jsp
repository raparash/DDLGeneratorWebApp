<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="snippet/scripts-snippet.html"%>
</head>
<body >


	<div data-role="main" class="ui-content" style="height:90%">

		<div class="ui-grid-b ui-responsive">
			<div class="ui-block-a" style="width: 15%"></div>

			<div class="ui-block-b" style="width: 70%;background-color:#AEDAE8">
				<div class="ui-grid-b ui-responsive">
					<div class="ui-block-a" style="width: 10%"></div>
					<div class="ui-block-b" style="width:80%">	
						
							<div class="ui-field-contain">
								<label for="input1">Data Model Sheet (.xls,.xlsx)</label> 
								<input type="file" name="upload_file" id="input1" placeholder="Browse" data-clear-btn="true">
							</div>
							<div class="ui-field-contain">
								<label for="email">E-mail</label> 
								<input type="email" name="email" id="email" placeholder="Your email.." data-clear-btn="true" required="required">
							</div>
							<div class="ui-field-contain">
								<fieldset data-role="controlgroup">
									<legend>Options</legend>
									<label for="checkbox1">Table Drop Enabled</label> 
									<input	id="checkbox1" type="checkbox" value="drop" name="drop">
									<label for="checkbox2">Execute Queries</label> 
									<input	id="checkbox2" type="checkbox" value="execute" name="execute">
								</fieldset>
							</div>
							<input data-inline="true" value="Submit" type="button">
						
					</div>
				<div class="ui-block-c"></div>
				</div>
			</div>
			<div class="ui-block-c"></div>


		</div>
	</div>

</body>
</html>