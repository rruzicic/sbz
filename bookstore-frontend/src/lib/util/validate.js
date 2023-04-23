export function validateEmail(mail) {
	if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(mail)) {
		return '';
	}
	return 'Email address must be of correct format!';
}

export function required(field) {
	if (field === '' || field === null) {
		return 'This field is required!';
	}
	return '';
}

export function validate(model, modelValidation, modelErrMsg = undefined) {
	let isValid = true;
	let modelErrOut = initErrorOutput(modelValidation);
	Object.keys(model).forEach((key) => {
		modelValidation[key].forEach((validator, idx) => {
			let message = validator(model[key]);
			if (message !== '') {
				if (modelErrMsg === undefined) {
					modelErrOut[key][idx] = message;
				} else {
					modelErrOut[key][idx] = modelErrMsg[key][idx];
				}
				isValid = false;
			} else {
				modelErrOut[key][idx] = '';
			}
		});
	});
	return [modelErrOut, isValid];
}

function initErrorOutput(modelValidation) {
	let errorOutput = {};
	Object.keys(modelValidation).forEach((key) => {
		var num = modelValidation[key].length;
		errorOutput[key] = [];
		for (let i = 0; i < num; i++) {
			errorOutput[key].push('');
		}
	});
	return errorOutput;
}
