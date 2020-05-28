<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
<meta charset="utf-8">
<title>CALCULATOR</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="CSS/style.css?v=1.0">
<link rel="stylesheet" href="CSS/selsectBox.css?v=1.0">
</head>
<body class="hide_scroll body_copy">
	<jsp:include page="nav.jsp"></jsp:include>
	<div class="container">
		<div class="box">
			<p class="Sub_headings">Calculator of fuel and CO2</p>
			<br>
			<div id="calculatorMainBox">
				<div class="box_inner" id="0003">
					<table id="tableCalc" class="table_D">
						<tr>
							<td>
								<p>Unit of distance</p>
							</td>
							<td>
								<form name="formA">
									<input type="radio" id="Km" name="unit" value="Km"
										checked="checked"> <label for="Km">Km</label> <input
										type="radio" id="Mile" name="unit" value="Mile"> <label
										for="Mile">Mile</label>
								</form>
							</td>
						</tr>
						<tr>
							<td>Trip Distance:</td>
							<td><input type="number" id="tripDistance"
								name="tripDistance"> <label for="tripDistance"
								id="tripDistance_label">Km</label></td>
						</tr>
						<tr>
							<td>Fuel Efficiency:</td>
							<td><input type="number" id="fuelEfficiency"
								name="fuelEfficiency"> <label for="fuelEfficiency"
								id="fuelEfficiency_label">L/100km</label></td>
						</tr>
						<tr>
							<td>Fuel Price:</td>
							<td><input type="number" id="fuelPrice" name="fuelPrice">
								<label for="fuelPrice" id="fuelPrice_label">Cent per L</label></td>
						</tr>
						<tr>
							<td>CO2 Emissions:</td>
							<td><input type="number" id="CO2Emissions"
								name="CO2Emissions"> <label for="CO2Emissions"
								id="CO2Emissions_label">G/km</label></td>
						</tr>
						<tr>
							<td>Total fuel cost:</td>
							<td><input type="number" id="fuelCost" name="fuelCost"
								readonly
								style="background-color: #f9f9f9; color: #8DC640; font-weight: bold; border: 1px solid #8DC640;"
								value="0"> <label for="fuelCost" id="fuelCost_label">$</label></td>
						</tr>
						<tr>
							<td>Total CO2 emission:</td>
							<td><input type="number" id="CO2Cost" name="CO2Cost"
								readonly readonly
								style="background-color: #f9f9f9; color: #8DC640; font-weight: bold; border: 1px solid #8DC640;"
								value="0"> <label for="CO2Cost" id="CO2Cost_label">Tonnes</label></td>
						</tr>
					</table>
					<br>
					<p id="0002" style="color: red; text-align: left; margin: 0 5px;">
						*To load data from specific model <a class="noselect"
							style="cursor: pointer; font-weight: bold" id="0001">click
							here</a>
					</p>
				</div>
				<div>
					<button class="Sub_headings" id="CalculateBtn">CALCULATE</button>
				</div>
			</div>
		</div>

		<div class="box">
			<jsp:include page="Text/gvgCopyRights.jsp"></jsp:include>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
	<script src="JS/calculator.js?v=1.0"></script>
</body>
</html>