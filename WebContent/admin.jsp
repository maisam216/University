<%@ page import="com.main.DbHandler"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.main.Log"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
<meta charset="utf-8">
<title>Admin Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<jsp:include page="processingScreen.jsp"></jsp:include>
</head>
<style>
body {
	font-size: 12pt;
	font-family: myriad-pro, sans-serif;
	font-style: normal;
	font-weight: 400;
}

.screen {
	width: 100%;
	min-width: 1024px;
}

h2 {
	margin: 0;
	padding: 0;
}

table {
	border: 1px solid #D9E027;
	background: #1f803f;
	padding: 5px;
	width: 45%;
	text-align: left;
}

caption {
	font-family: myriad-pro, sans-serif;
	font-weight: Bold;
	font-style: Italic;
	font-size: 15pt;
	margin-bottom: 10px;
}

th {
	color: #FFFFFF;
}

button {
	color: #1f803f;
	margin: 10PX 5PX 0 5PX;
	cursor: pointer;
}

.spacer {
	height: 50px;
}

.log {
	border: 1px solid #1f803f;
	width: 50%;
	height: 60vh;
	float: right;
	overflow: auto;
}

.logTable {
	color: #000000;
	width: 100%;
	border-collapse: collapse;
	background: #ffffff;
	font-size: 10pt;
	font-family: myriad-pro, sans-serif;
	font-style: normal;
}

.logTable th, .logTable td {
	color: #000000;
	padding: 5px 0;
	text-align: left;
	border-bottom: 1px solid #ddd;
}

.logTable tr:hover {
	background-color: #f5f5f5;
}

a {
	padding-right: 10px;
}
</style>
<body>
	<div class="screen">
		<div
			style="width: 100%; text-align: center; background: #1f803f; color: white; line-height: 200%;">
			<a href="Logout" style="color: #ffffff; float: right;">Logout</a>
			<h2>Admin Area</h2>
		</div>
		<div class="spacer"></div>
		<div class="log">
			<div
				style="width: 100%; text-align: center; background: #1f803f; color: white; line-height: 200%;">
				<h2>Logs</h2>
			</div>
			<table class="logTable">
				<tr>
					<th>Last update</th>
					<th>RowNr</th>
					<th>Log</th>
					<th>Source</th>
				</tr>
				<%
					//Configure database
					String[] dbConfig = new String[3];
					dbConfig[0] = getServletContext().getInitParameter("dbhost");
					dbConfig[1] = getServletContext().getInitParameter("dbusername");
					dbConfig[2] = getServletContext().getInitParameter("dbpassword");
					DbHandler dbHandler = new DbHandler(dbConfig[0], dbConfig[1], dbConfig[2]);
					dbHandler.connect();
					ArrayList<Log> logList = dbHandler.getLogs();
					for (int i = logList.size() - 1; i >= 0; i--) {
				%>
				<tr>
					<td style="width: 25%;"><%=logList.get(i).getDate()%></td>
					<td style="width: 10%; padding-left: 10px;"><%=logList.get(i).getRowNr()%></td>
					<td style="width: 50%;"><%=logList.get(i).getLogtxt()%></td>
					<td style="width: 15%;"><%=logList.get(i).getSource()%></td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
		<form id="gvgSubmit" method="POST" action="UpdateGVG"
			autocomplete="off">
			<table>
				<caption>GVG DATA</caption>
				<tr>
					<th>Enter GVG Email</th>
					<td><input style="width: 90%; padding: 2px;" type="text"
						name="un" maxlength="50" title="enter GVG email"
						placeholder="enter GVG email" required></td>
				</tr>
				<tr>
					<th>Enter GVG Password</th>
					<td><input style="width: 90%; padding: 2px;" type="password"
						name="ps" maxlength="50" title="enter GVG password"
						placeholder="enter GVG password" required>
						<div style="color: white; font-size: 10pt;">
							Show Password<input style="padding-top: 10pt;" type="checkbox"
								name="ch" />
						</div></td>
				</tr>
				<tr>
					<th>Last update/Update from</th>
					<td><input name="lu" type="date"
						value="<%=dbHandler.lastSuccessLogDate()%>" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="reset" value="Reset Data">Reset</button>
						<button type="submit" value="Update Now">Update GVG</button>
					</td>
				</tr>
			</table>
		</form>
		<div class="spacer"></div>
		<form id="pas" method="POST" action="UpdatePass" autocomplete="off">
			<table>
				<caption>Update Admin Password</caption>
				<tr>
					<th>Enter New Password</th>
					<td><input style="width: 90%; padding: 2px;" type="password"
						name="pass" title="enter new password"
						placeholder="enter GVG password" id="pass" required></td>
				</tr>
				<tr>
					<th>Confirm New Password</th>
					<td><input style="width: 90%; padding: 2px;" type="password"
						name="confirm_pass" title="confirm new password"
						placeholder="confirm GVG password" id="confirm_pass" required></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="submit">Confirm</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<%
		dbHandler.disconnect();
	%>
	<script>
		var password = document.getElementById("pass"), confirm_password = document
				.getElementById("confirm_pass");

		function validatePassword() {
			if (password.value != confirm_password.value) {
				confirm_password.setCustomValidity("Passwords Don't Match");
			} else {
				confirm_password.setCustomValidity('');
			}

		}

		password.onchange = validatePassword;
		confirm_password.onkeyup = validatePassword;

		var password_gvg = document.querySelector("input[name=ps]"), checkbox = document
				.querySelector("input[name=ch]");

		checkbox.addEventListener('change', function() {
			if (this.checked) {
				password_gvg.type = 'text';
			} else {
				password_gvg.type = 'password';
			}
		});

		var gvgSubmit = document.getElementById("gvgSubmit");
		gvgSubmit.onsubmit = function() {
			document.getElementById("blackBack").style.display = "block";
			document.getElementById("message").style.display = "block";

			window.setInterval(
							function() {
								var client = new XMLHttpRequest();
								client.open('GET', 'log.dat');
								client.onreadystatechange = function() {
									document.getElementById("messageTxt").innerHTML = client.responseText;
								}
								client.send();
							}, 1000);

		};
	</script>
</body>
</html>
