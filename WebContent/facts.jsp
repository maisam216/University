<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
<meta charset="utf-8">
<title>FACTS</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="CSS/style.css?v=1.0">
</head>
<body class="hide_scroll body_copy">
	<jsp:include page="nav.jsp"></jsp:include>
	<div class="container">
		<div class="box">
			<p class="Sub_headings">Facts</p>
			<div class="text_container">
				<jsp:include page="Text/factsData.jsp"></jsp:include>
			</div>
		</div>

		<div class="box">
			<jsp:include page="Text/gvgCopyRights.jsp"></jsp:include>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>