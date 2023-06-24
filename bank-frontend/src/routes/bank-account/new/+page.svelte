<script>
    import axios from 'axios';
	import Input from '../../../lib/Input.svelte';
    import { toast } from '../../../lib/stores/toast';
    import { goto } from '$app/navigation';
	


	let balance = 0;
	let config = {
		headers: {
			Authorization: `Basic ${localStorage.getItem('access_token')}`
		}
	};
	function handleCreate() {
		axios
			.post('http://localhost:8081/bank-account/new', { balance: parseFloat(balance) }, config)
			.then((res) => {
				handleToast('Bank account', `Bank account created!`);
				goto(`/`, true);
			})
			.catch((err) => {
                console.log(err);
				handleToast('Ooops!', 'Could not create bank account, try again later!');
			});
	}

    function handleToast(title, text) {
		$toast.id = 'liveToast';
		$toast.title = title;
		$toast.text = text;
		setTimeout(() => new bootstrap.Toast(document.querySelector('#liveToast')).show(), 10);
	}
</script>

<h1>Create new bank account</h1>
<Input label="Deposit" type="number" bind:value={balance} />

<button on:click={handleCreate} class="btn btn-primary" disabled={balance < 0}>Create bank account</button>
