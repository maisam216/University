<style>
#blackBack {
	display: none;
	position: fixed;
	z-index: 950;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	overflow: auto;
	background-color: rgba(0, 0, 0, 0.65);
	align-items: center;
	justify-content: center;
}

#message {
	display: none;
	z-index: 950;
	margin: 0 auto;
	height: 60vh;
	max-width: 800px;
	background-color: #000000;
	padding: 1.4vh;
	box-shadow: 0px 6px 9px rgba(0, 0, 0, .5);
	width: 90%;
	position: fixed;
	left: 50%;
	top: 20%;
	transform: translate(-50%, 0);
	border-width : 1px; 
	border-style : solid; 
	border-color: #ffffff;
}

.messageClose {
	width: 2.5vh;
	float: right;
	cursor: pointer;
}

#msgIcon {
	width: 3vh;
	float: right;
	margin-left: 5px;
}

#messageTxtBox {
	height: 55vh;
	padding: 0 .2vh;
	margin-top: 5vh;
}

#messageHeader{
font-family: "Roboto", Times, serif;
float: left;
font-size: 22pxh;
font-weight: bold;
}

#messageTxt {
	height: 55vh;
	position: relative;
	text-align: left;
	font-size: 14px;
	color: #ffffff;
	font-family: "Roboto", Times, serif;
	overflow: auto;
}
</style>
<div id="blackBack"></div>
<div id="message">
	<div id=messageHeader></div>
	<Img alt="close" src="Img/close1.png" id="closeBN" class="messageClose" onclick="closeMessage();">
	<div id="messageTxtBox">
		<div id="messageTxt">
			
		</div>
	</div>
</div>
<script>

	function closeMessage() {
		document.getElementById("blackBack").style.display = "none";
		document.getElementById("message").style.display = "none";
		document.getElementById("messageTxt").innerHTML = "";
	}
</script>