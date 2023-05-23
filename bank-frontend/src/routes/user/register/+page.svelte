<script>
	import axios from 'axios';
	import GenreSelect from '../../../lib/GenreSelect.svelte';
	import Input from '../../../lib/Input.svelte';
	import { required, validateEmail, validate } from '../../../lib/util/validate';
	import { goto } from '$app/navigation';
	import { toast } from '../../../lib/stores/toast';

	let user = {
		name: '',
		email: '',
		password: '',
		favouriteGenres: ''
	};

	let userValidation = {
		name: [required],
		email: [required, validateEmail],
		password: [required],
		favouriteGenres: []
	};

	$: [userErrOut, valid] = validate(user, userValidation);

	function handleSubmit() {
		axios.post('http://localhost:8081/user/register', user)
		.then((res) => {
			handleToast('Yay!', 'You can now buy and review books!')
			goto(`/`, true) 
		})
		.catch((err) => {
			handleToast('Ooops!', 'Could not create new user!')
		})
	}
	
	function handleToast(title, text) {
		$toast.id = 'liveToast';
		$toast.title = title;
		$toast.text = text;
		setTimeout(() => new bootstrap.Toast(document.querySelector('#liveToast')).show(), 10);
	}
</script>

<h1>Register page</h1>
<form>
	<Input label="Name" errors={userErrOut.name} bind:value={user.name} />
	<Input label="Email" errors={userErrOut.email} bind:value={user.email} />
	<Input label="Password" errors={userErrOut.password} bind:value={user.password} />
	<GenreSelect bind:genre={user.favouriteGenres}/>


	<button type="button" class="btn btn-primary" on:click={handleSubmit} disabled={!valid}>
		Submit
	</button>
</form>

