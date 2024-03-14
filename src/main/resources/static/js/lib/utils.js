
const sanitize = (value) => {
	if (typeof data === 'string') {
		// Escape characters that have special meaning in HTML, XML, and JavaScript
		return data
			.replace(/&/g, '&amp;')
			.replace(/</g, '&lt;')
			.replace(/>/g, '&gt;')
			.replace(/"/g, '&quot;')
			.replace(/'/g, '&#39;');
	} else if (typeof data === 'object') {
		// Recursively sanitize object properties
		for (const key in data) {
			if (data.hasOwnProperty(key)) {
				data[key] = sanitize(data[key]);
			}
		}
	}
	return data;
};

function isDirtyChars(value) {
	if (typeof value !== 'string') {
		return false;
	}
	const regex = /[^\w\s!@#$%^&*()._<>\-]/g;
	return regex.test(value);
}

const dirtyKeyValue = (key, value) => {
	console.log("Checking key: " + key + " and value: " + value);
	return isDirtyChars(key) && isDirtyChars(value);
};
