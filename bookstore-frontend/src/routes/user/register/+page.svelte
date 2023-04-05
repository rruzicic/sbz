<script>
	import Input from '../../../lib/Input.svelte';
	import { required, validateEmail, validate } from '../../../lib/util/validate';
	let user = {
		firstName: '',
		lastName: '',
		email: '',
		password: ''
	};

	let userValidation = {
		firstName: [required],
		lastName: [required],
		email: [required, validateEmail],
		password: [required]
	};

	$: [userErrOut, valid] = validate(user, userValidation);

	function handleSubmit() {
		// TODO:api request
	}
</script>

<h1>Register page</h1>
<form>
	<Input label="First name" errors={userErrOut.firstName} bind:value={user.firstName} />
	<Input label="Last name" errors={userErrOut.lastName} bind:value={user.lastName} />
	<Input label="Email name" errors={userErrOut.email} bind:value={user.email} />
	<Input label="Password name" errors={userErrOut.password} bind:value={user.password} />

	<button type="button" class="btn btn-primary" on:click={handleSubmit} disabled={!valid}>
		Submit
	</button>
</form>
<p>
	{JSON.stringify(user)}
</p>
<br />
<p>
	{JSON.stringify(userErrOut)}
</p>
