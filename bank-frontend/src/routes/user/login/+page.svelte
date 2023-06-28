<script>
	import axios from 'axios';
	import Input from '../../../lib/Input.svelte';
	import { required, validate, validateEmail } from '../../../lib/util/validate';
	import { toast } from '../../../lib/stores/toast';
	import { user } from '../../../lib/stores/user';
	import { goto } from '$app/navigation';

	let credentials = {
		email: '',
		password: ''
	};

	let credentialsValidation = {
		email: [required, validateEmail],
		password: [required]
	};

	$: [credentialsErrOut, valid] = validate(credentials, credentialsValidation);

	function handleLogin() {
		axios.post('http://localhost:8081/user/login', credentials)
		.then((res) => {
			$user.email = credentials.email;
			$user.password = credentials.password;
			$user.base64token = btoa(credentials.email + ':' + credentials.password);
			$user.role = res.data.role;
			localStorage.setItem('access_token', btoa(credentials.email + ':' + credentials.password));
			handleToast('Login', `Hi ${res.data.name}, welcome back!`)
			goto(`/`, true) 
		})
		.catch((err) => {
			handleToast('Ooops!', 'Wrong user credentials!')
		})
	}

	function handleToast(title, text) {
		$toast.id = 'liveToast';
		$toast.title = title;
		$toast.text = text;
		setTimeout(() => new bootstrap.Toast(document.querySelector('#liveToast')).show(), 10);
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
<!--<p>
	{JSON.stringify(credentials)}
</p>-->
