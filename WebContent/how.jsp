<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
<meta charset="utf-8">
<title>HOW IT WORKS!</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="CSS/style.css">
</head>
<body class="hide_scroll body_copy">
	<jsp:include page="nav.jsp"></jsp:include>
	<div class="container">
		<div class="box">
			<p class="Sub_headings">How does it works</p>
			<div class="text_container">
				<jsp:include page="Text/howData.jsp"></jsp:include>
				<button class="button transparent-bg dark m-1" onclick="window.location.href='download/User Documentation.pdf'">Download User Manual</button>
			</div>
		</div>

		<div class="box">
			<jsp:include page="Text/gvgCopyRights.jsp"></jsp:include>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>