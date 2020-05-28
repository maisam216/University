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
	z-index: 950;
	top: 33%;
	margin: 0 auto;
	height: 30vh;
	max-width: 450px;
	background-color: #ffffff;
	padding: 1.4vh;
	opacity: 0;
	box-shadow: 0px 6px 9px rgba(0, 0, 0, .5);
	-webkit-transition-duration: 0.6s;
	transition-duration: 0.6s;
	width: 90%;
	position: fixed;
	top: -60vh;
	left: 50%;
	transform: translate(-50%, 0);
	border-width : 2px; 
	border-style : solid; 
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
	height: 22vh;
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
	height: 22vh;
	position: relative;
	text-align: center;
	font-size: 16px;
	color: #000000;
	font-family: "Roboto", Times, serif;
	overflow: auto;
}
</style>
<div id="blackBack"></div>
<div id="message">
	<div id=messageHeader></div>
	<Img alt="close" src="Img/close1.png" id="closeBN" class="messageClose" onclick="closeMessage();">
	<div id="messageTxtBox">
		<div id="messageTxt"></div>
	</div>
</div>
<script>

	function showInfoMessage(msg) {
		document.getElementById("blackBack").style.display = "block";
		document.getElementById("message").style.top = "28vh";
		document.getElementById("message").style.opacity = ".9";
		document.getElementById("messageTxt").innerHTML = msg;
		document.getElementById("messageHeader").innerHTML = "Info <Img alt=\"close\" src=\"Img/info.png\" id=\"msgIcon\">";
		document.getElementById("messageHeader").style.color =  "#00000";
		document.getElementById("message").style.borderColor = "#1f803f";
	}

	function closeMessage() {
		document.getElementById("blackBack").style.display = "none";
		document.getElementById("message").style.top = "-60vh";
		document.getElementById("message").style.opacity = "0";
		document.getElementById("messageTxt").innerHTML = "";
	}
</script>