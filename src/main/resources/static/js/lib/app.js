
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
	
	if(hasInvalidData !== false) {
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

$('#btnLogin').on('click', async (e) => {
	e.preventDefault();
	var form = document.getElementById("frmLogin");
	const data = new FormData(form);
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


