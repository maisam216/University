var calculatorLink =  document.getElementById("0001");
var calculatorMainBox = document.getElementById("calculatorMainBox"); 
var linkLine = document.getElementById("0002"); 
var oldcontent;
document.onload = radiouslistener();
document.onload = CalculateBtnlistener();

//Main selects options
var selects = new Array();
selects[0]="Select Year:";
selects[1]="Select Make:";
selects[2]="Select Model:";
selects[3]="Select Version:";
selects[4]="Select Variant:";
selects[5]="Select Fuel Type:";

//Calculator Link Handler
calculatorLink.onclick = function(){
	addCompareBox(calculatorMainBox,selects);
	fillYears();
	document.getElementById("Km").checked = true;
	document.getElementById("tripDistance_label").innerHTML = "Km";
	document.getElementById("fuelEfficiency_label").innerHTML = "L/100km";
	document.getElementById("CO2Emissions_label").innerHTML = "G/km";
	radiouslistener();
	CalculateBtnlistener();
	var element = document.getElementById("00004");
	elementGraphIndicator(element,1000,7,"red");

	var fuelEfficiency =  document.getElementById("fuelEfficiency");
	var CO2Emissions =  document.getElementById("CO2Emissions");
	fuelEfficiency.value= mileToKm(fuelEfficiency.value);
	CO2Emissions.value= mileToKm(CO2Emissions.value);
};


function scrollToBox(id){

	var element = document.getElementById(id);
	element.scrollIntoView();

	//indicator for div
	var element_1 = document.getElementById("fuelEfficiency");
	var element_2 = document.getElementById("CO2Emissions");

	elementGraphIndicator(element_1,1000,4,"red");
	elementGraphIndicator(element_2,1000,4,"red");
}

//Example elementGraphIndicator("makeAsSelected","makeAsNoneSelected",element,3000,3);
function elementGraphIndicator(element,maxTime,cycles,color){
	tmp = element.style.borderColor;
	for(var i = 0; i <= maxTime; i+=(maxTime/cycles)){
		setTimeout(function(){ 
			element.style.borderColor = color;
		},(maxTime/cycles)/2+i);
		setTimeout(function(){ 
			element.style.borderColor = tmp;
		},(maxTime/cycles)+i);
	}
}

//Action listeners for radious buttons
function radiouslistener(){
	var radios = document.forms["formA"].elements["unit"];
	var fuelEfficiency =  document.getElementById("fuelEfficiency");
	var CO2Emissions =  document.getElementById("CO2Emissions");
	for(var i = 0, max = radios.length; i < max; i++) {
		radios[i].onclick = function() {
			if(this.value=='Km'){
				document.getElementById("tripDistance_label").innerHTML = "Km";
				document.getElementById("fuelEfficiency_label").innerHTML = "L/100km";
				document.getElementById("CO2Emissions_label").innerHTML = "G/km";
				fuelEfficiency.value= mileToKm(fuelEfficiency.value);
				CO2Emissions.value= mileToKm(CO2Emissions.value);
				document.getElementById("tripDistance").value = "";
				document.getElementById("fuelCost").value = "";
				document.getElementById("CO2Cost").value = "";
			}else{
				document.getElementById("tripDistance_label").innerHTML = "mile";
				document.getElementById("fuelEfficiency_label").innerHTML = "L/100mile";
				document.getElementById("CO2Emissions_label").innerHTML = "G/mile";
				fuelEfficiency.value= kmToMile(fuelEfficiency.value);
				CO2Emissions.value= kmToMile(CO2Emissions.value);
				document.getElementById("tripDistance").value = "";
				document.getElementById("fuelCost").value = "";
				document.getElementById("CO2Cost").value = "";
			}
			//clear 0 vales
			if(fuelEfficiency.value==0){
				fuelEfficiency.value="";
			}
			if(CO2Emissions.value==0){
				CO2Emissions.value="";
			}
		}
	}
}

//Buttons listeners
function CalculateBtnlistener(){
	var CalculateBtn =  document.getElementById("CalculateBtn");
	CalculateBtn.onclick = function(){
		fuelCalc();
		CO2Calc();
	};
}

//km to mile converters
function kmToMile(km){
	return (km*1.609344).toFixed(2);
}

//Mile to km converters
function mileToKm(mile){
	return (mile*0.621371192).toFixed(2);
}

//Calculate fuel costs
function fuelCalc(){
	var tripDistance = document.getElementById("tripDistance").value;
	var fuelEfficiency = document.getElementById("fuelEfficiency").value;
	var price = document.getElementById("fuelPrice").value;
	var requiredFuel = ((tripDistance*fuelEfficiency)/100).toFixed(2);
	var cost = requiredFuel * price / 100; //price in dollars
	document.getElementById("fuelCost").value = cost.toFixed(2);
}

//Calculate CO2 Emissions
function CO2Calc(){
	var tripDistance = document.getElementById("tripDistance").value;
	var CO2Emissions = document.getElementById("CO2Emissions").value;
	var emission = (tripDistance*CO2Emissions)/1000000;
	document.getElementById("CO2Cost").value = emission.toFixed(2);
}

//Fill year drop box from database
function fillYears() {
	var data = {
			req:'getYears',
			year: 0,
			make: "",
			model: "",
			version: "",
			variant : "",
			fuel : ""
	};

	var http = new XMLHttpRequest();
	http.open("POST", "DropBoxes", true);
	http.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
	http.onreadystatechange = function () {
		if (http.readyState === 4 && http.status === 200) {
			var json = JSON.parse(http.responseText);
		}
	};
	var params = JSON.stringify(data);
	http.send(params);
	http.onload = function() {
		var resp = this.responseText;
		if (resp != null && resp != "") {
			var jsonData = JSON.parse(resp);
			var element =  document.getElementById("selectID_x_1");
			if(element.value==0){
				for(var i = 0; i < jsonData.OptionsList.length; i++){
					var option = document.createElement("OPTION");
					option.value = jsonData.OptionsList[i];
					option.text = jsonData.OptionsList[i];
					element.add(option);
				}
			}
		}
	}
}

//Fill make drop box from database
function fillMakes() {
	var year = document.getElementById("selectID_x_1");
	var make = document.getElementById("selectID_x_2");
	var model = document.getElementById("selectID_x_3");
	var version = document.getElementById("selectID_x_4");
	var variant = document.getElementById("selectID_x_5");
	var fuel = document.getElementById("selectID_x_6");

	resetSelectElement(make);
	resetSelectElement(model);
	resetSelectElement(version);
	resetSelectElement(variant);
	resetSelectElement(fuel);

	var data = {
			req:'getMakes',
			year: year.value,
			make: "",
			model: "",
			version: "",
			variant : "",
			fuel : ""
	};

	var http = new XMLHttpRequest();
	http.open("POST", "DropBoxes", true);
	http.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
	http.onreadystatechange = function () {
		if (http.readyState === 4 && http.status === 200) {
			var json = JSON.parse(http.responseText);
		}
	};
	var params = JSON.stringify(data);
	http.send(params);
	http.onload = function() {
		var resp = this.responseText;
		if (resp != null && resp != "") {
			var jsonData = JSON.parse(resp);	
			for(var i = 0; i < jsonData.OptionsList.length; i++){
				var option = document.createElement("OPTION");
				option.value = jsonData.OptionsList[i];
				option.text = jsonData.OptionsList[i];
				make.add(option);
			}
		}
	}
}

//Fill model drop box from database
function fillModels() {
	var year = document.getElementById("selectID_x_1");
	var make = document.getElementById("selectID_x_2");
	var model = document.getElementById("selectID_x_3");
	var version = document.getElementById("selectID_x_4");
	var variant = document.getElementById("selectID_x_5");
	var fuel = document.getElementById("selectID_x_6");

	resetSelectElement(model);
	resetSelectElement(version);
	resetSelectElement(variant);
	resetSelectElement(fuel);

	var data = {
			req:'getModels',
			year: year.value,
			make: make.value,
			model: "",
			version: "",
			variant : "",
			fuel : ""
	};

	var http = new XMLHttpRequest();
	http.open("POST", "DropBoxes", true);
	http.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
	http.onreadystatechange = function () {
		if (http.readyState === 4 && http.status === 200) {
			var json = JSON.parse(http.responseText);
		}
	};
	var params = JSON.stringify(data);
	http.send(params);
	http.onload = function() {
		var resp = this.responseText;
		if (resp != null && resp != "") {
			var jsonData = JSON.parse(resp);	
			for(var i = 0; i < jsonData.OptionsList.length; i++){
				var option = document.createElement("OPTION");
				option.value = jsonData.OptionsList[i];
				option.text = jsonData.OptionsList[i];
				model.add(option);
			}
		}
	}
}

//Fill version drop box from database
function fillVersions(id) {
	var year = document.getElementById("selectID_x_1");
	var make = document.getElementById("selectID_x_2");
	var model = document.getElementById("selectID_x_3");
	var version = document.getElementById("selectID_x_4");
	var variant = document.getElementById("selectID_x_5");
	var fuel = document.getElementById("selectID_x_6");

	resetSelectElement(version);
	resetSelectElement(variant);
	resetSelectElement(fuel);

	var data = {
			req:'getVersions',
			year: year.value,
			make: make.value,
			model: model.value,
			version: "",
			variant : "",
			fuel : ""
	};

	var http = new XMLHttpRequest();
	http.open("POST", "DropBoxes", true);
	http.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
	http.onreadystatechange = function () {
		if (http.readyState === 4 && http.status === 200) {
			var json = JSON.parse(http.responseText);
		}
	};
	var params = JSON.stringify(data);
	http.send(params);
	http.onload = function() {
		var resp = this.responseText;
		if (resp != null && resp != "") {
			var jsonData = JSON.parse(resp);	
			for(var i = 0; i < jsonData.OptionsList.length; i++){
				var option = document.createElement("OPTION");
				if(jsonData.OptionsList[i]=="..."){
					option.text = "All";
				}else{
					option.text = jsonData.OptionsList[i];
				}
				option.value = jsonData.OptionsList[i];
				version.add(option);
			}
		}
	}
}

//Fill variant drop box from database
function fillVariants() {
	var year = document.getElementById("selectID_x_1");
	var make = document.getElementById("selectID_x_2");
	var model = document.getElementById("selectID_x_3");
	var version = document.getElementById("selectID_x_4");
	var variant = document.getElementById("selectID_x_5");
	var fuel = document.getElementById("selectID_x_6");

	resetSelectElement(variant);
	resetSelectElement(fuel);

	var data = {
			req:'getVariants',
			year: year.value,
			make: make.value,
			model: model.value,
			version: version.value,
			variant : "",
			fuel : ""
	};

	var http = new XMLHttpRequest();
	http.open("POST", "DropBoxes", true);
	http.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
	http.onreadystatechange = function () {
		if (http.readyState === 4 && http.status === 200) {
			var json = JSON.parse(http.responseText);
		}
	};
	var params = JSON.stringify(data);
	http.send(params);
	http.onload = function() {
		var resp = this.responseText;
		if (resp != null && resp != "") {
			var jsonData = JSON.parse(resp);	
			for(var i = 0; i < jsonData.OptionsList.length; i++){
				var option = document.createElement("OPTION");
				if(jsonData.OptionsList[i]=="..."){
					option.text = "All";
				}else{
					option.text = jsonData.OptionsList[i];
				}
				option.value = jsonData.OptionsList[i];
				variant.add(option);
			}
		}
	}
}

//Fill fuel types drop box from database
function fillFuels() {
	var year = document.getElementById("selectID_x_1");
	var make = document.getElementById("selectID_x_2");
	var model = document.getElementById("selectID_x_3");
	var version = document.getElementById("selectID_x_4");
	var variant = document.getElementById("selectID_x_5");
	var fuel = document.getElementById("selectID_x_6");

	resetSelectElement(fuel);

	var data = {
			req:'getFuels',
			year: year.value,
			make: make.value,
			model: model.value,
			version: version.value,
			variant : variant.value,
			fuel : ""
	};

	var http = new XMLHttpRequest();
	http.open("POST", "DropBoxes", true);
	http.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
	http.onreadystatechange = function () {
		if (http.readyState === 4 && http.status === 200) {
			var json = JSON.parse(http.responseText);
		}
	};
	var params = JSON.stringify(data);
	http.send(params);
	http.onload = function() {
		var resp = this.responseText;
		if (resp != null && resp != "") {
			var jsonData = JSON.parse(resp);	
			for(var i = 0; i < jsonData.OptionsList.length; i++){
				var option = document.createElement("OPTION");
				option.value = jsonData.OptionsList[i];
				option.text = jsonData.OptionsList[i];
				fuel.add(option);
			}
		}
	}
}

//This function is to fill car details from server
function getCarDetails() {

	var year = document.getElementById("selectID_x_1");
	var make = document.getElementById("selectID_x_2");
	var model = document.getElementById("selectID_x_3");
	var version = document.getElementById("selectID_x_4");
	var variant = document.getElementById("selectID_x_5");
	var fuel = document.getElementById("selectID_x_6");

	var data = {
			Car: []
	};

	data.Car.push({ 
		year : year.value,
		make : make.value,
		model : model.value,
		version : version.value,
		variant : variant.value,
		fuel : fuel.value
	});
	var http = new XMLHttpRequest();
	http.open("POST", "GetCars", true);
	http.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
	http.onreadystatechange = function () {
		if (http.readyState === 4 && http.status === 200) {
			var json = JSON.parse(http.responseText);
		}
	};
	var params = JSON.stringify(data);
	http.send(params);
	http.onload = function() {
		var resp = this.responseText;
		if (resp != null && resp != "") {
			var jsonData = JSON.parse(resp);
			document.getElementById("fuelEfficiency").value =  jsonData.Car[0].fuelConsumption;
			document.getElementById("CO2Emissions").value = jsonData.Car[0].CO2Emissions;
			scrollToBox("0003");
		}
	}
}

//Reset drop boxes
function resetSelectElement(response){
	response.selectedIndex = "0";
	var option = response.getElementsByTagName("OPTION");
	var tmp = option[0];
	response.innerHTML = '';
	response.appendChild(tmp);  

}

//Filter drop boxes handler
function onChangeController(key){

	if(key==1){
		fillMakes();
	} else if(key==2){
		fillModels();
	} else if(key==3){
		fillVersions();
	} else if (key==4){
		fillVariants();
	} else if (key == 5){
		fillFuels();
	} else if (key == 6){
		getCarDetails();
	}
}

//Add compare box function
function addCompareBox(div,selects){
	linkLine.innerHTML="";
	var content = "<div id =\"00004\" class=\"box_inner\">"+
	"<p>select a car to extract the required data</p>"+
	"<br>"+
	"<table id =\"table_D\" class=\"table_D\">";

	for(var i = 1 ; i <= selects.length ; i++){
		var id= "selectID_x_"+i;
		var cls= "selectCls_"+i;
		content += "<tr>"+
		"<td class=\"tdComBox\" width=\"40%\">"+selects[i-1]+"</td>"+
		"<td width=\"60%\"><select class=\"select-css "+cls+"\" id=\""+id+"\" onchange=\"onChangeController('"+i+"')\">"+
		"<option value=\"0\">"+selects[i-1]+"</option>"+
		"</select>"+
		"</tr>";
	}

	content+="</table>"+
	"</div>";

	//Add content
	oldcontent = div.innerHTML;
	div.innerHTML = content + oldcontent
}