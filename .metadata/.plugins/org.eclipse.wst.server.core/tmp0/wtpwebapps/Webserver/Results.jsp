<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
</head>

<body>
	<!--header-->
	<div>
		<nav class="navbar navbar-default navbar-static-top">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
		            <span class="sr-only">Toggle navigation</span>
		            <span class="icon-bar"></span>
		            <span class="icon-bar"></span>
		            <span class="icon-bar"></span>
		         	</button>
		         	<a class="navbar-brand" href="#">Search Engine</a>
				</div>
			</div>
		</nav>
	</div>

	<div class="container">
	
	<!-- input box -->
	<div class="col-lg-6">
		<div class="input-group">
			<form method="get" action="doGet()">
				<input type="text" class="form-control" placeholder="Search for..." onclick="the get function">
				<span class="input-group-btn">
					<button class="btn btn-default" type="button">Go</button>
				</span>
			</form>
		</div>
	</div>	
	
	<!-- returned results-->
	<div class="jumbotron">
		<h1>Search Results:</h1>
		<form method="post" action="doPost()">
				<button class="btn btn-default" type="button">get results</button>
				</span>
		</form>
	</div>
	
	</div>
</body>
</html>