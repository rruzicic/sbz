<script>
    import axios from 'axios';
	import Input from '../../../lib/Input.svelte';
	import { goto } from '$app/navigation';
	import { toast } from '../../../lib/stores/toast';

	let creditRequest = {};

	function mapValues() {
		creditRequest.clientId = parseInt(creditRequest.clientId);
		creditRequest.clientIncome = parseFloat(creditRequest.clientIncome);
		creditRequest.moneySum = parseFloat(creditRequest.moneySum);
		creditRequest.rateNumber = parseInt(creditRequest.rateNumber);
		creditRequest.clientContractStart = new Date(creditRequest.clientContractStart);
		creditRequest.clientContractEnd = new Date(creditRequest.clientContractEnd);
		creditRequest.creditEndMin = new Date(creditRequest.creditEndMin);
		creditRequest.creditEndMax = new Date(creditRequest.creditEndMax);
	}

	let config = {
		headers: {
			Authorization: `Basic ${localStorage.getItem('access_token')}`
		}
	};

	function handleCreate() {
		mapValues();
		console.log(creditRequest);
		axios
			.post('http://localhost:8081/credit/new', creditRequest, config)
			.then((res) => {
				handleToast('Credit request', `Credit request sent!`);
				goto(`/`, true);
			})
			.catch((err) => {
				console.log(err);
				handleToast('Ooops!', 'Could not send credit request, try again later!');
			});
	}

	function handleToast(title, text) {
		$toast.id = 'liveToast';
		$toast.title = title;
		$toast.text = text;
		setTimeout(() => new bootstrap.Toast(document.querySelector('#liveToast')).show(), 10);
	}
</script>

<Input label="Income" type="number" bind:value={creditRequest.clientIncome} />
<Input label="Money sum" type="number" bind:value={creditRequest.moneySum} />
<Input label="Rate number" type="number" bind:value={creditRequest.rateNumber} />
Employment type:
<select class="form-select" bind:value={creditRequest.clientEmploymentStatus}>
	<option value="UNEMPLOYED">Unemployed</option>
	<option value="TEMPORARY">Temporary</option>
	<option value="INDEFINITE">Indefinite</option>
</select>
<Input label="Credit payment from" type="date" bind:value={creditRequest.creditEndMin} />
<Input label="Credit payment to" type="date" bind:value={creditRequest.creditEndMax} />

<Input
	label="Client contract start(optional)"
	type="date"
	bind:value={creditRequest.clientContractStart}
/>
<Input
	label="Client contract end(optional)"
	type="date"
	bind:value={creditRequest.clientContractEnd}
/>
<button on:click={handleCreate} class="btn btn-primary">Create credit request</button>
