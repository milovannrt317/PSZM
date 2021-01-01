<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
	 	<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
	 	<title>Update password</title>
	 	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
		<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<link rel="stylesheet" type="text/css" href="/pszm/css/style.css">
	</head>
	<body>
		<h3 align="center">${(poruka!=null) ? poruka : ""} </h3><br/><br/>
	 	<div id="data">
	 		<div class="login-form">
                <div id="forma">
                	<form action="update" method="POST">
                   		<h2 class="text-center">Change password!</h2>       
                    	<div class="form-group">
                        	<input name="username" value="${username}" type="text" class="form-control" readonly>
                    	</div>
                    	<div class="form-group">
	                        <input id="pass" name="password" type="password" class="form-control" placeholder="Password" required="required" oninput="checkPass(this)">
	                    </div>
	                    <div class="clearfix">
	                        <label class="pull-left checkbox-inline"><input type="checkbox" onclick="showpass()"> Show password</label>
	                    </div>
	                    <div id="message">
							  <h5>Password must contain the following:</h5>
							  <p id="letter" class="invalid">A <b>lowercase</b> letter</p>
							  <p id="capital" class="invalid">A <b>uppercase</b> letter</p>
							  <p id="number" class="invalid">A <b>number</b></p>
							  <p id="length" class="invalid">Minimum <b>8 characters</b></p>
						</div>
	                    <br> 
                     	<div class="form-group">
                      		<label>Captcha:</label>
	                      	<img id="captcha" src="/pszm/captcha" width="150">
	                    </div>
	                    <div class="form-group">
	                         <input id="cpt" name="captcha" type="text" class="form-control" placeholder="Captcha" required="required">
                        </div>	
                     	<br>   
                     	<div class="form-group">
                         	<input type="submit" value="Change password" class="btn btn-primary btn-block"/>
                         	<input type="reset" value="Cancel" class="btn btn-warning btn-block"/>
                      	</div> 
                      	
                      </form> 
                    </div>
                </div>
               <p class="text-center"><a href="/pszm/">Don't want to change?</a></p>
	 	</div>
	 	<script src="/pszm/js/script.js"></script>
	</body>
</html>