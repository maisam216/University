//settings
var levelRangeDivision=28;
var carsNumber = 0;
var MaxCarsNumbers = 5;
var carSummaryNr = 0;

//Main selects options
var selects = new Array();
selects[0]="Select Year:";
selects[1]="Select Make:";
selects[2]="Select Model:";
selects[3]="Select Version:";
selects[4]="Select Variant:";
selects[5]="Select Fuel Type:";

//Main divs and Buttons click events
var entriesMainBox = document.getElementById("entriesMainBox");
var AddAnotherCar =  document.getElementById("AddAnotherCar");
var getResults =  document.getElementById("getResults");

//Start WebApp
document.onload = run();


function run(){
	application();
}

function application(){

	//setup started boxes
	addCompareBox(entriesMainBox,selects);
	addCompareBox(entriesMainBox,selects);

	//update years
	fillYears();
}

//AddAnotherCar button handler
AddAnotherCar.onclick = function(){
	if(carsNumber<MaxCarsNumbers){
		addCompareBox(entriesMainBox,selects);

		//update years
		fillYears();

		//Scroll down to it
		scrollToBox("entryBox_"+carsNumber);


	}else{
		showInfoMessage("Oops. The max number of cars to be compared is "+MaxCarsNumbers);
	}
};

//Compare button handler
getResults.onclick = function(){
	carSummaryNr = 0;
	getCarDetails();
};

//Action listener for drope boxes
function onChangeController(id){

	var arr = idNrExtracots(id);
	var x = arr[0];
	var y = arr[1];

	if(y==1){
		fillMakes(id);
	} else if(y==2){
		fillModels(id);
	} else if(y==3){
		fillVersions(id);
	} else if (y==4){
		fillVariants(id);
	} else if (y == 5){
		fillFuels(id);
	}
}

//This function is to scroll to specific box
function scrollToBox(id){

	var element = document.getElementById(id);
	element.scrollIntoView();

	//indicator for div
	elementGraphIndicator("makeAsSelected","makeAsNoneSelected",element,1000,7);

}

//Example elementGraphIndicator("makeAsSelected","makeAsNoneSelected",element,3000,3);
function elementGraphIndicator(clsActive,clsNoneActive,element,maxTime,cycles){
	for(var i = 0; i <= maxTime; i+=(maxTime/cycles)){
		setTimeout(function(){ 
			element.classList.remove("flashRed");
			element.classList.add(clsNoneActive);
		},(maxTime/cycles)/2+i);
		setTimeout(function(){ 
			element.classList.remove(clsNoneActive);
			element.classList.add("flashRed");
		},(maxTime/cycles)+i);
	}
	setTimeout(function(){ 
	element.classList.remove("flashRed");
	element.classList.remove(clsNoneActive);
	element.classList.add(clsActive);
	},(maxTime+50));
}


//Extract X and Y from id
function idNrExtracots(id){
	var arr = new Array();
	try{
		var splits = id.split('_')
		arr[0]=splits[1];
		arr[1]=splits[2];
	}catch(e){

	}
	return(arr);
}

//Fill year function
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
			for(var x = 1; x <= carsNumber; x++){
				var element =  document.getElementById("selectID_"+x+"_1");
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
}


//Fill makes function
function fillMakes(id) {

	var arr = idNrExtracots(id);
	var x = arr[0];
	var y = arr[1];

	var year = document.getElementById("selectID_"+x+"_1");
	var make = document.getElementById("selectID_"+x+"_2");
	var model = document.getElementById("selectID_"+x+"_3");
	var version = document.getElementById("selectID_"+x+"_4");
	var variant = document.getElementById("selectID_"+x+"_5");
	var fuel = document.getElementById("selectID_"+x+"_6");


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


//Fill models function
function fillModels(id) {

	var arr = idNrExtracots(id);
	var x = arr[0];
	var y = arr[1];

	var year = document.getElementById("selectID_"+x+"_1");
	var make = document.getElementById("selectID_"+x+"_2");
	var model = document.getElementById("selectID_"+x+"_3");
	var version = document.getElementById("selectID_"+x+"_4");
	var variant = document.getElementById("selectID_"+x+"_5");
	var fuel = document.getElementById("selectID_"+x+"_6");

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

//fill versions function
function fillVersions(id) {

	var arr = idNrExtracots(id);
	var x = arr[0];
	var y = arr[1];

	var year = document.getElementById("selectID_"+x+"_1");
	var make = document.getElementById("selectID_"+x+"_2");
	var model = document.getElementById("selectID_"+x+"_3");
	var version = document.getElementById("selectID_"+x+"_4");
	var variant = document.getElementById("selectID_"+x+"_5");
	var fuel = document.getElementById("selectID_"+x+"_6");

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

//fill variants functions
function fillVariants(id) {

	var arr = idNrExtracots(id);
	var x = arr[0];
	var y = arr[1];

	var year = document.getElementById("selectID_"+x+"_1");
	var make = document.getElementById("selectID_"+x+"_2");
	var model = document.getElementById("selectID_"+x+"_3");
	var version = document.getElementById("selectID_"+x+"_4");
	var variant = document.getElementById("selectID_"+x+"_5");
	var fuel = document.getElementById("selectID_"+x+"_6");

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

//fill fuel functions
function fillFuels(id) {

	var arr = idNrExtracots(id);
	var x = arr[0];
	var y = arr[1];

	var year = document.getElementById("selectID_"+x+"_1");
	var make = document.getElementById("selectID_"+x+"_2");
	var model = document.getElementById("selectID_"+x+"_3");
	var version = document.getElementById("selectID_"+x+"_4");
	var variant = document.getElementById("selectID_"+x+"_5");
	var fuel = document.getElementById("selectID_"+x+"_6");

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

//Get cars details from server
function getCarDetails() {

	var data = {
			Car: []
	};

	if (validateCarEnteries()){

		for(var x = 1; x <= carsNumber; x++){

			var year = document.getElementById("selectID_"+x+"_1");
			var make = document.getElementById("selectID_"+x+"_2");
			var model = document.getElementById("selectID_"+x+"_3");
			var version = document.getElementById("selectID_"+x+"_4");
			var variant = document.getElementById("selectID_"+x+"_5");
			var fuel = document.getElementById("selectID_"+x+"_6");

			data.Car.push({ 
				year : year.value,
				make : make.value,
				model : model.value,
				version : version.value,
				variant : variant.value,
				fuel : fuel.value
			});
		}
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
				var resp = this.responseText;
				if (resp != null && resp != "") {
					var jsonData = JSON.parse(resp);
					compare(jsonData.Car);
				}
			}
		}
	}else{
		showInfoMessage("Oops. You missed some required information.");
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

//Make selected list in the NAV active depends on opened page 
makeSelectedElementInNavActive();
function makeSelectedElementInNavActive(){
	var Ul = document.getElementById("ulNav");
	var LI = Ul.getElementsByTagName("LI");
	for (i = 0; i < LI.length; i++) {
		var a = LI[i].getElementsByTagName("A");
		var aText = a[0].textContent.toLowerCase();
		var pageTitle = document.title.toLowerCase();
		if(pageTitle==aText){
			a[0].classList.add("active");
		}
	}
}

//Validat that all the drop boxes are settings by the user
function validateCarEnteries(){
	var year = document.getElementById("selectID_1_1");
	var make = document.getElementById("selectID_1_2");
	var model = document.getElementById("selectID_1_3");
	var version = document.getElementById("selectID_1_4");
	var variant = document.getElementById("selectID_1_5");
	var fuel = document.getElementById("selectID_1_6");
	if(year.value !=0 && make.value !=0 && model.value !=0 && version.value !=0 && variant.value !=0 && fuel.value !=0){
		return true;
	}else{
		return false;
	}
}

//compare cars function
function compare(opjects){

	//Show result box
	var result_div = document.getElementById("results");
	result_div.classList.remove("none_display");

	//Delete previous summary
	deleteElementInDivById("results_summary");


	//Create compare CO2 Emissions results
	var headers = new Array("CO2 Emissions...", "CO2 Emissions Extra Urban...", "CO2 Emissions Urban...");
	var max = new Array(600, 360, 970);
	var metaDataNames = new Array("CO2Emissions","CO2EmissionsExtraUrban","CO2EmissionsUrban");
	var unitsNames = new Array("(g/km)","(g/km)","(g/km)");
	var data = extractOpjectKeysToValues(opjects,metaDataNames);
	creatCompareCarResult("results_summary",opjects,headers,data,max,"CO2 Emissions Results",unitsNames);

	//Create compare fuel Consumption results
	var headers = new Array("Fuel Consumption...", "Fuel Consumption Extra Urban...", "Fuel Consumption Urban...");
	var max = new Array(25, 15, 42);
	var metaDataNames = new Array("fuelConsumption","fuelConsumptionExtraUrban","fuelConsumptionUrban");
	var unitsNames = new Array("(L/100km)","(L/100km)","(L/100km)");
	var data = extractOpjectKeysToValues(opjects,metaDataNames);
	creatCompareCarResult("results_summary",opjects,headers,data,max,"Fuel Consumption Results",unitsNames);

	//create summary results
	for(var i = 0 ; i < opjects.length ; i++){
		if(opjects[i]!=null){
			creatSummaryCarResult(opjects[i],"results_summary");
		}
	}

	//Scroll Into View
	results.scrollIntoView();
}

//This function is to read array data and link it with headers
//returns a array has headers : metaDataNames
//returns data objects data
function extractOpjectKeysToValues(objects,metaDataNames){
	var data = new Array();
	for(var i = 0 ; i < objects.length ; i++){
		data[i] = new Array;
		if(objects[i]!=null){
			var tmpKeys = Object.keys(objects[i]) ;
			var tmpValues = Object.values(objects[i]);
			for(var j = 0 ; j < tmpKeys.length ; j++){
				for(var x = 0 ; x < metaDataNames.length ; x++){
					if(tmpKeys[j]==metaDataNames[x]){
						data[i][x]=tmpValues[j];
					}			
				}		
			}
		}
	}
	return data;
}

//This method is to fill serials of boxes between two colors
function hexArrayGradients(clr1,clr2,levelsNr){
	var colours = new Array();

	var clr1_R = clr1[1] + clr1[2];
	var clr1_G = clr1[3] + clr1[4];
	var clr1_B = clr1[5] + clr1[6];

	var clr2_R = clr2[1] + clr2[2];
	var clr2_G = clr2[3] + clr2[4];
	var clr2_B = clr2[5] + clr2[6];

	clr1_R = parseInt(clr1_R, 16);
	clr2_R = parseInt(clr2_R, 16);
	clr1_G = parseInt(clr1_G, 16);
	clr2_G = parseInt(clr2_G, 16);
	clr1_B = parseInt(clr1_B, 16);
	clr2_B = parseInt(clr2_B, 16);	

	var fullRange_R = clr1_R-clr2_R;
	var levelRange_R = (fullRange_R/(levelsNr-1));
	levelRange_R = Math.round(levelRange_R);

	var fullRange_G = clr1_G-clr2_G;
	var levelRange_G = (fullRange_G/(levelsNr-1));
	levelRange_G = Math.round(levelRange_G);

	var fullRange_B = clr1_B-clr2_B;
	var levelRange_B = (fullRange_B/(levelsNr-1));
	levelRange_B = Math.round(levelRange_B);

	colours[0] = clr1;
	colours[levelsNr-1] = clr2;

	for(var i = 1 ; i < levelsNr-1 ; i++){
		clr1_R -= levelRange_R;
		clr1_G -= levelRange_G;
		clr1_B -= levelRange_B;

		var tmp_R = "#";
		var tmp_G;
		var tmp_B;

		if(clr1_R.toString(16).length>1){
			tmp_R += clr1_R.toString(16);
		}else{
			tmp_R += "0" + clr1_R.toString(16);
		}

		if(clr1_B.toString(16).length>1){
			tmp_B = clr1_B.toString(16);
		}else{
			tmp_B = "0" + clr1_B.toString(16);
		}

		if(clr1_G.toString(16).length>1){
			tmp_G = clr1_G.toString(16);
		}else{
			tmp_G = "0" + clr1_G.toString(16);
		}

		colours[i]= tmp_R + tmp_G + tmp_B;
	}
	return colours;
}

//View data as levels or percentages
function dataViewerAsLevels(value,max,levelRangeDivision,unit){

	var element ="";
	var colours = hexArrayGradients("#8DC640","#ff0000",levelRangeDivision);
	if(value != -1){
		var j = 0;
		for (var i = 0 ; i < value ; i = i + (max / levelRangeDivision)){
			element+= "<div style=\"background-color:"+colours[j]+"\" class=\"svg_square\"></div>";
			j++;
		}
		element+=  value + " " +unit;
	}else{
		element = "<div><p style=\"color:blue\">No data to show.</p></div>";
	}

	return element;
}

//delete elemnt by its ID
function deleteElementInDivById(id){
	var element = document.getElementById(id);
	while (element.firstChild) { //Delete previous data
		element.removeChild(element.lastChild);
	}
	element.innerHTML = ""; //Delete previous data
}

function creatSummaryCarResult(car,id){

	//Add one car summary
	carSummaryNr++;


	//Remove -1 values
	for(var c in car) {
		if(car[c]==-1 || car[c]=='...'){
			car[c] = "-";
		}
	}
	var name = car.make+ " " + car.marketingModel + " " + car.modelReleaseVersion+ " " + car.modelReleaseYear;

	var summaryID = "summaryID_"+carSummaryNr;
	var content = "<div class=\"compare summary makeAsNoneSelected\" id=\""+summaryID+"\">"+
	"<p class=\"Sub_headings\">Car Data Summary</p>"+
	"<br><p class=\"base_color\">" +
	name+
	"</p>"+
	"<table class=\"table_C\">"+
	"<tr>"+
	"<td><br></td>"+
	"<td><br></td>"+
	"</tr>"+
	"<tr>"+
	"<td>Body Style</td>"+
	"<td>"+car.bodyStyleDesc+"</td>"+
	"</tr>"+
	"<tr>"+
	"<td>CO2 Emissions</td>"+
	"<td>"+car.CO2Emissions+" (g/km)</td>"+
	"</tr>"+
	"<tr>"+
	"<td>CO2 Emissions Extra Urban</td>"+
	"<td>"+car.CO2EmissionsExtraUrban+" (g/km)</td>"+
	"</tr>"+
	"<tr>"+
	"<td>CO2 Emissions Urban</td>"+
	"<td>"+car.CO2EmissionsUrban+" (g/km)</td>"+
	"</tr>"+
	"<tr>"+
	"<td>Certification Level</td>"+
	"<td>"+car.certificationLevelDesc+"</td>"+
	"</tr>"+
	"<tr>"+
	"<td>Driving Wheels	</td>"+
	"<td>"+car.drivingWheelsDesc+"</td>"+
	"</tr>"+
	"<tr>"+
	"<td>Electric Range</td>"+
	"<td>"+car.electricRangeKm+" km</td>"+
	"</tr>"+
	"<tr>"+
	"<td>Energy Consumption</td>"+
	"<td>"+car.energyConsumptionWhkm+" (Wh/km)</td>"+
	"</tr>"+
	"<tr>"+
	"<td>Engine Configuration</td>"+
	"<td>"+car.engineConfigurationDesc+"</td>"+
	"</tr>"+
	"<tr>"+
	"<td>Engine Displacement</td>"+
	"<td>"+car.engineDisplacementDesc+"</td>"+
	"</tr>"+
	"<tr>"+
	"<td>Forward Gears</td>"+
	"<td>"+car.forwardGearsDesc+"</td>"+
	"</tr>"+
	"<tr>"+
	"<td>Fuel Consumption</td>"+
	"<td>"+car.fuelConsumption+" (L/100km)</td>"+
	"</tr>"+
	"<tr>"+
	"<td>Fuel Consumption Extra Urban</td>"+
	"<td>"+car.fuelConsumptionExtraUrban+" (L/100km)</td>"+
	"</tr>"+
	"<tr>"+
	"<td>Fuel Consumption Urban</td>"+
	"<td>"+car.fuelConsumptionUrban+" (L/100km)</td>"+
	"</tr>"+
	"<tr>"+
	"<td>Fuel Type</td>"+
	"<td>"+car.fuelTypeDesc+"</td>"+
	"</tr>"+
	"<tr>"+
	"<td>Induction</td>"+
	"<td>"+car.inductionDesc+"</td>"+
	"</tr>"+
	"<tr>"+
	"<td>Is Current Model</td>"+
	"<td>"+car.isCurrentModelDesc+"</td>"+
	"</tr>"+
	"<tr>"+
	"<td>Make</td>"+
	"<td>"+car.make+"</td>"+
	"</tr>"+
	"<tr>"+
	"<td>Marketing Model</td>"+
	"<td>"+car.marketingModel+"</td>"+
	"</tr>"+
	"<tr>"+
	"<td>Model Release Version</td>"+
	"<td>"+car.modelReleaseVersion+"</td>"+
	"</tr>"+
	"<tr>"+
	"<td>Model Release Year</td>"+
	"<td>"+car.modelReleaseYear+"</td>"+
	"</tr>"+
	"<tr>"+
	"<td>Non Current On Date</td>"+
	"<td>"+car.nonCurrentOnDate+"</td>"+
	"</tr>"+
	"<tr>"+
	"<td>Published Date</td>"+
	"<td>"+car.publishedDate+"</td>"+
	"</tr>"+
	"<tr>"+
	"<td>Seating Capacity</td>"+
	"<td>"+car.seatingCapacityDesc+"</td>"+
	"</tr>"+
	"<tr>"+
	"<td>Side Doors Desc</td>"+
	"<td>"+car.sideDoorsDesc+"</td>"+
	"</tr>"+
	"<tr>"+
	"<td>Stationary Noise Data</td>"+
	"<td>"+car.stationaryNoiseDataDesc+" @ 4000 rpm</td>"+
	"</tr>"+
	"<tr>"+
	"<td>Test Speed</td>"+
	"<td>"+car.testSpeedDesc+"</td>"+
	"</tr>"+
	"<tr>"+
	"<td>Transmission Type</td>"+
	"<td>"+car.transmissionTypeDesc+"</td>"+
	"</tr>"+
	"<tr>"+
	"<td>Variant</td>"+
	"<td>"+car.variant+"</td>"+
	"</tr>"+
	"<tr>"+
	"<td>Vehicle Class</td>"+
	"<td>"+car.vehicleClassDesc+"</td>"+
	"</tr>"+
	"</table>" +
	"</div>";

	//Add content
	div = document.getElementById(id);
	div.innerHTML += content;


	window.addEventListener('click', function(e){   
		element=document.getElementById(summaryID);
		if (element.contains(e.target)){
			// Clicked in box
			element.classList.remove("makeAsNoneSelected");
			element.classList.add("makeAsSelected");
		} else{
			// Clicked outside the box
			element.classList.add("makeAsNoneSelected");
			element.classList.remove("makeAsSelected");
		}
	});
}

function creatCompareCarResult(id,opjects,headers,data,max,title,unitsNames){

	var content = "<!-- child info here -->"+
	"<p class=\"Sub_headings\">"+title+"</p>"+
	"<table class=\"table_B\">";
	for(var i = 0 ; i < headers.length ; i++){
		content +=
			"<!-- child info here -->"+
			"<tr>"+
			"<td><br></td>"+
			"</tr>"+
			"<tr class=\"f6-color\">"+
			"<td colspan=\"2\">"+headers[i]+"</td>"+
			"</tr>";

		for(var j = 0 ; j < opjects.length ; j++){
			if(opjects[j]!=null){
				var name = opjects[j].make.substr(0, 10)+ "..." + " " + opjects[j].modelReleaseYear+" ";
				content +=	
					"<tr>"+
					"<td width=\"35%\">"+
					"<div style=\"cursor:pointer\" class=\"base_color link\" onclick=\"scrollToBox('summaryID_"+(j+1)+"')\">"+name+"<i class=\"arrow down\"></i></div>"+
					"</td>"+
					"<td width=\"65%\">" +
					dataViewerAsLevels(data[j][i],max[i],levelRangeDivision,unitsNames[i])+
					"</td>"+
					"</tr>";
			}
		}
	}
	content +=
		"</table>";


	//Add content
	div = document.getElementById(id);
	var tmp = document.createElement("DIV");
	tmp.classList.add("compare");
	tmp.classList.add("makeAsNoneSelected");
	tmp.innerHTML += content;
	div.appendChild(tmp);
}

function addCompareBox(div,selects){

	//Add one car
	carsNumber ++;

	var content = "<p class=\"Sub_headings\">Car Details</p>"+
	"<br>"+
	"<table id =\"table_A\" class=\"table_A\">";

	for(var i = 1 ; i <= selects.length ; i++){
		var id= "selectID_"+carsNumber+"_"+i;
		var cls= "selectCls_"+i;
		content += "<tr>"+
		"<td class=\"tdComBox\" width=\"40%\">"+selects[i-1]+"</td>"+
		"<td width=\"60%\"><select class=\"select-css "+cls+"\" id=\""+id+"\" onchange=\"onChangeController('"+id+"')\">"+
		"<option value=\"0\">"+selects[i-1]+"</option>"+
		"</select>"+
		"</tr>";
	}

	content += "</table>";
	//Add content
	var tmp = document.createElement("DIV");
	tmp.id = "entryBox_"+carsNumber;
	tmp.classList.add("compare");
	tmp.classList.add("makeAsNoneSelected");
	tmp.innerHTML += content;
	div.appendChild(tmp);

	//add selecting listener
	window.addEventListener('click', function(e){   
		element=document.getElementById(tmp.id);
		if (element.contains(e.target)){
			// Clicked in box
			element.classList.remove("makeAsNoneSelected");
			element.classList.add("makeAsSelected");
		} else{
			// Clicked outside the box
			element.classList.add("makeAsNoneSelected");
			element.classList.remove("makeAsSelected");
		}
	});
}