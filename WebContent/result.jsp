<%@ page import="com.main.DbHandler"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.main.Log"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Result</title>
</head>
<style>
.logTable {
	margin: auto;
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
	padding: 8px;
	text-align: left;
	border-bottom: 1px solid #ddd;
}

.logTable tr:hover {
	background-color: #f5f5f5;
}

input {
	background: #1f803f;
	color: #ffffff;
	margin: 10PX 5PX 0 5PX;
	cursor: pointer;
	width: 100px;
	border: 1px solid #D9E027;
	padding: 5px;
}
</style>
<body>
	<table class="logTable">
		<tr>
			<th>Time</th>
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
			int i = logList.size() - 1;
		%>
		<tr>
			<td style="width: 20%;"><%=logList.get(i).getDate()%></td>
			<td style="width: 70%;"><%=logList.get(i).getLogtxt()%></td>
			<td style="width: 10%;"><%=logList.get(i).getSource()%></td>
		</tr>
	</table>
	<input type="button" value="Back"
		onclick="window.location.href='Admin'" />
</body>
</html>