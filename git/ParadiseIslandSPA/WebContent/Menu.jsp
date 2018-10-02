<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="menuStyle.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<title>Menu</title>
</head>
<body>
	<form action="Shop" method="POST">
		<div id="butoaneMeniu">
			<button type="submit" class="btn btn-primary" name="Zona">Zone</button>
			<button type="submit" class="btn btn-primary" name="Bar">Bar</button>
			<button type="submit" class="btn btn-primary" name="Restaurant">Restaurant</button>
			<button type="submit" class="btn btn-primary" name="Servicii">Servicii</button>
		</div>
	</form>
	
	<div id="butoaneZone">
		<button type="button" class="btn btn-success selected">Zona A</button>
		<button type="button" class="btn btn-success">Zona B</button>
		<button type="button" class="btn btn-success">Zona C</button>
		<button type="button" class="btn btn-success">Zona D</button>
	</div>
</body>
</html>