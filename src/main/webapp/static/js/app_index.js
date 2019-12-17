$('#copyrightYear').html(new Date().getFullYear());

var currentTab = 0; // Current tab is set to be the first tab (0)
showTab(currentTab); // Display the current tab

function showTab(n) {
	// This function will display the specified tab of the form...
	var x = document.getElementsByClassName("form-tab");
	x[n].style.display = "inline-flex";
	// ... and fix the Previous/Next buttons:
	if (n == 0) {
		document.getElementById("prevBtn").style.display = "none";
	} else {
		document.getElementById("prevBtn").style.display = "inline";
	}
	if (n == (x.length - 1)) {
		document.getElementById("termsAgreementDiv").style.display = "inline-flex";
		document.getElementById("nextBtn").innerHTML = "Submit";
	} else {
		document.getElementById("nextBtn").innerHTML = "Next";
	}
	// ... and run a function that will display the correct step indicator:
	fixStepIndicator(n)
}

function nextPrev(n) {
	if (n < 0) {
		document.getElementById("termsAgreementDiv").style.display = "none";
	}
	// This function will figure out which tab to display
	var x = document.getElementsByClassName("form-tab");
	
	//if (n < 0 || currentTab == x.length - 1){
	x[currentTab].style.display = "none";

		
	// Increase or decrease the current tab by 1:
	currentTab = currentTab + n;
	// if you have reached the end of the form...
	if (currentTab >= x.length) {
		// ... the form gets submitted:
		document.getElementById("regForm").submit();
		console.log("A");
		return false;
	} else {
		// Otherwise, display the correct tab:
		showTab(currentTab);
		console.log("B");
	}
}

function fixStepIndicator(n) {
	// This function removes the "active" class of all steps...
	var i, x = document.getElementsByClassName("step"), y = document
			.getElementsByClassName("nav-item");
	for (i = 0; i < x.length; i++) {
		x[i].className = x[i].className.replace(" active", "");
		y[i].className = y[i].className.replace(" active", "");
	}
	// ... and adds the "active" class on the current step:
	x[n].className += " active";
	y[n].className += " active";
}

// JS for slider
$('input[type="range"]').on('input', function() {
	var control = $(this);
	$("#income").text(control.val());
});

$("#signup-url, #navbar-signup").click(function() {
	$("#loginForm").hide("slow");
	$("#navbar-signup").addClass('active');
	$("#navbar-login").removeClass('active');
	$("#registrationDiv").show("slow");

});

$("#signin-url, #navbar-login").click(function() {
	$("#registrationDiv").hide("slow");
	$("#navbar-signup").removeClass('active');
	$("#navbar-login").addClass('active');
	$("#loginForm").show("slow");
});