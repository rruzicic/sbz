<script>
    import axios from 'axios';
	import Input from '../../../lib/Input.svelte';

	let balance = 0;
	let config = {
		headers: {
			Authorization: `Basic ${localStorage.getItem('access_token')}`
		}
	};
	function handleCreate() {
		axios
			.post('http://localhost:8081/bank-account/new', { owner: { id: 2 }, balance: balance }, config)
			.then((res) => {
				handleToast('Bank account', `Bank account created!`);
				goto(`/`, true);
			})
			.catch((err) => {
				handleToast('Ooops!', 'Could not create bank account, try again later!');
			});
	}
</script>

<h1>Create new bank account</h1>
<Input label="Deposit" type="number" bind:value={balance} />

<button on:click={handleCreate} class="btn btn-primary" disabled={balance < 0}>Create bank account</button>
