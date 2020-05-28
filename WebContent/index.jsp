<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
<meta charset="utf-8">
<title>HOME</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="CSS/style.css?v=1.0">
<link rel="stylesheet" href="CSS/selsectBox.css?v=1.0">
<jsp:include page="message.jsp"></jsp:include>
</head>
<body class="hide_scroll body_copy">
	<jsp:include page="nav.jsp"></jsp:include>
	<div class="container">
		<div class="box">
			<p class="Sub_headings">Enter two cars details to be compared</p>
			<div id="entriesMainBox"></div>
			<div>		
				<button class="Sub_headings" id="AddAnotherCar">Add Another Car</button>
				<button class="Sub_headings" id="getResults">Compare Cars</button>
			</div>
		</div>
		<div id="results" class="box none_display">
			<p class="Sub_headings">Compared cars results</p>
			<br>
			<div id="results_summary"></div>			
		</div>
		<div class="box">
			<jsp:include page="Text/gvgCopyRights.jsp"></jsp:include>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
	<script src="JS/controllers.js?v=1.0"></script>
</body>
</html>
