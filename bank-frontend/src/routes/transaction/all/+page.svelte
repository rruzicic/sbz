<script>
	import GenericTable from '../../../lib/GenericTable.svelte';
	import axios from 'axios';
	import { user } from '../../../lib/stores/user';
	import { onMount } from 'svelte';
	let transactions = [];
	let columnNames = ['id', 'amount', 'fraudulent', 'timeOfTransaction'];
	let columns = ['Transaction ID', 'Amount', 'Is fraudulent', 'Time of transaction'];

	let config = {
		headers: {
			Authorization: `Basic ${localStorage.getItem('access_token')}`
		}
	};

	onMount(() => {
		if ($user.role === 'CLERK') {
			getAllTransactions();
		} else if ($user.role === 'USER') {
			getMyTransactions();
		}
	});

	function getMyTransactions() {
		axios
			.get('http://localhost:8081/transaction/my', config)
			.then((res) => {
				console.log('success');
				transactions = res.data;
			})
			.catch((err) => {
				console.log('could not fetch transactions for user');
			});
	}

	function getAllTransactions() {
		axios
			.get('http://localhost:8081/transaction/all', config)
			.then((res) => {
				console.log('success');
				transactions = res.data;
			})
			.catch((err) => {
				console.log('could not fetch transactions for clerk');
			});
	}
</script>

<h1>Transactions</h1>
<GenericTable
	bind:data={transactions}
	bind:columnNames
	bind:columns
/>
<!--
<table class="table">
	<thead>
		<tr>
			<th>Sender</th>
			<th>Receiver</th>
			<th>Amount</th>
			<th>Is fraudulent</th>
		</tr>
	</thead>
	{#each transactions as transaction}
		<tbody>
			<tr>
				<td>{transaction.sender.id}</td>
				<td>{transaction.receiver.id}</td>
				<td>{transaction.amount.id}</td>
				<td>{transaction.fraudulent.id}</td>
			</tr>
		</tbody>
	{/each}
</table>
-->