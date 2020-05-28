<script src="JS/nav.js?v=1.0"></script>
<nav class ="noselect" id="navbar">
	<input type="checkbox" id="check"> <label for="check"
		class="checkbtn"> <i class="fas fa-bars"></i>
	</label><label onclick="location.href='Home';"><img id="logoImg" src="Img/NRSPP-logo-no-green.png" alt="Logo" class="logo"></label>
	<ul id="ulNav">
		<li  onclick="location.href='Home';"><a>HOME</a></li>
		<li  onclick="location.href='How';"><a>HOW IT WORKS!</a></li>
		<li  onclick="location.href='Calculator';"><a>CALCULATOR</a></li>
		<li  onclick="location.href='Facts';"><a>FACTS</a></li>
		<li onclick="location.href='About';"><a>ABOUT US</a></li>
	</ul>
</nav>
<script>
	document.onload = selected_line_nav();
	window.onscroll = function() {
		scrollFunction()
	};

	function selected_line_nav() {
		var Ul = document.getElementById("ulNav");
		var LI = Ul.getElementsByTagName("LI");
		for (i = 0; i < LI.length; i++) {
			var a = LI[i].getElementsByTagName("A");
			var aText = a[0].textContent.toLowerCase();
			var pageTitle = document.title.toLowerCase();
			if (pageTitle == aText) {
				a[0].classList.add("active");
			}
		}
	}

	function scrollFunction() {
		if (document.body.scrollTop > 125
				|| document.documentElement.scrollTop > 125) {
			document.getElementById("navbar").style.top = "-125px";
		} else {
			document.getElementById("navbar").style.top = "0";
		}
	}
</script>
