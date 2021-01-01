<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
	 	<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
	 	<title>Data view</title>
	 	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
		<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
		<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<link rel="stylesheet" type="text/css" href="/pszm/css/style.css">
 	</head>
	<body>
	 	<div id="data">
	 		<div class="login-form">
                <div id="forma">
                    <h2 class="text-center">Your data</h2>       
                    <div class="form-group">
                    	<label>Firstname</label>
                        <input name="firstname" value="${korisnik.firstname}" type="text" class="form-control" readonly>
                    </div>
                    <div class="form-group">
                    	<label>Lastname</label>
                        <input name="lastname" value="${korisnik.firstname}" type="text" class="form-control" readonly>
                    </div>
                    <div class="form-group">
                    	<label>Email</label>
                        <input name="email" value="${korisnik.email}" type="email" class="form-control" readonly>
                    </div>
                    <div class="form-group">
                    	<label>Username</label>
                        <input name="username" value="${korisnik.username}" type="text" class="form-control" readonly>
                    </div>  
                    <div class="form-group">
                        <a href="/pszm/logout"><input type="button" value="Logout" class="btn btn-danger btn-block"/></a>
                    </div> 
                </div>
            </div>
	 	</div>
	</body>
</html>