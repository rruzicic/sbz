<script>
	import Input from '../../../lib/Input.svelte';
	import { required, validate, validateEmail } from '../../../lib/util/validate';
	let credentials = {
		email: '',
		password: ''
	};

	let credentialsValidation = {
		email: [required, validateEmail],
		password: [required]
	};

	/*
	// you can pass this object as last parameter to validate function to create custom error messages
	let credentialsErrMsg = {
		email: ['Email is required', 'Email is not of correct format'],
		password: ['Password is required']
	};
	*/

	$: [credentialsErrOut, valid] = validate(credentials, credentialsValidation);

	function handleLogin() {
		// TODO: api request
	}
</script>

<h1>Login page</h1>
<form>
	<Input
		label="Email"
		type="email"
		errors={credentialsErrOut.email}
		bind:value={credentials.email}
	/>
	<Input
		label="Password"
		type="password"
		errors={credentialsErrOut.password}
		bind:value={credentials.password}
	/>

	<button on:click={handleLogin} class="btn btn-primary" disabled={!valid}>Login</button>
</form>
<p>
	{JSON.stringify(credentials)}
</p>
