<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
	<head>
		<meta charset="utf-8">
		<title>Admin Login</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
	</head>
	<body style="font-size: 12pt;
	font-family: myriad-pro, sans-serif;
	font-style: normal;
	font-weight: 400;">
		<form action="Login" method="post" autocomplete="on">
			<table style="border:1px solid #D9E027; background:#1f803f; margin:auto; margin-top:10%; padding:5px; width:400px" >
				<caption style="font-family: myriad-pro, sans-serif;font-weight: Bold;
				font-style: Italic;font-size: 15pt; margin-bottom:10px;">Admin Login</caption>
				<tr>
					<th style="color:#FFFFFF">Enter your username</th>
					<td><input style="width:90%; padding:2px;" type="text" name="un" id="un1" maxlength="50" title="enter your username" placeholder="enter your username" required></td>
				</tr>
				<tr>
					<th style="color:#FFFFFF">Enter your password</th>
					<td><input style="width:90%; padding:2px;" type="password" name="ps" id="ps1" maxlength="50" title="enter your password" placeholder="enter your password" required></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input style="margin:10PX 5PX 0 5PX; cursor: pointer;" type="reset" value="Reset Data"/>
						<input style="margin:10PX 5PX 0 5PX; cursor: pointer;" type="submit" value="Login"/>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>