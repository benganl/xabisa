$('#btnRegister').on('click', function(e) {
	e.preventDefault();
	// alert("Registration...");
	window.location.href = "/register";	
});

$('#btnLogin').on('click', function(e) {
	e.preventDefault();
	// alert("Login...");
	window.location.href = "/login";
});
