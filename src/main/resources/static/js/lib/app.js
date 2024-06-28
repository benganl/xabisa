
$('#btnLogin').on('click', async (e) => {
	const form = document.getElementById("frmLogin");
	const formData = new FormData(form);
	const jsonData = {};

	let hasInvalidData = false;
	for (const [key, value] of formData.entries()) {
		hasInvalidData ||= dirtyKeyValue(key, value);
		jsonData[key] = value;
	}

	if (hasInvalidData !== false) {
		console.log('The form has invalid data!!!!!!!!!!');
		return;
	}

	let resp = await fetch('/login', {
		method: "POST",
		mode: 'cors',
		cache: 'no-cache',
		credentials: 'same-origin',
		headers: {
			'Content-Type': 'application/json'
		},
		redirect: 'follow',
		referrerPolicy: 'same-origin',
		body: JSON.stringify(jsonData)
	})
		.then(response => response.json()) // Parse the JSON response
		.then(data => {
			// Handle successful response here
			console.log("Login successful:", data);
			// Process the login data (e.g., store JWT, update UI)
		})
		.catch(error => {
			// Handle errors here
			console.error("Login error:", error);
			// Display error message to user
		});

	/*
	let resp = await fetch('/login', {
		method: "POST",
		mode: 'cors',
		cache: 'no-cache',
		credentials: 'same-origin',
		headers: {
			'Content-Type': 'application/json',
			// 'Content-Type': 'application/x-www-form-urlencoded',
		},
		redirect: 'follow',
		referrerPolicy: 'same-origin',
		// body: new URLSearchParams(formData),
		body: JSON.stringify(jsonData)
	}); */
});

$('#btnRegister').on('click', async (e) => {
	e.preventDefault();
	const form = document.getElementById("frmRegister");
	const formData = new FormData(form);
	const jsonData = {};

	let hasInvalidData = false;
	for (const [key, value] of formData.entries()) {
		hasInvalidData ||= dirtyKeyValue(key, value);
		jsonData[key] = value;
	}

	if (hasInvalidData !== false) {
		console.log('The form has invalid data!!!!!!!!!!');
		return;
	}

	let resp = await fetch('/register', {
		method: "POST",
		mode: 'cors',
		cache: 'no-cache',
		credentials: 'same-origin',
		headers: {
			'Content-Type': 'application/json',
			// 'Content-Type': 'application/x-www-form-urlencoded',
		},
		redirect: 'follow',
		referrerPolicy: 'same-origin',
		// body: new URLSearchParams(formData),
		body: JSON.stringify(jsonData)
	});
});


