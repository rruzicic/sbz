
<script>
	import GenericTable from '../../../lib/GenericTable.svelte';
	import axios from 'axios';
	import { user } from '../../../lib/stores/user';
	import { onMount } from 'svelte';
	let transactions = [];
	let transactionColumnNames = ['amount', 'fraudulent'];
	let transactionColumns = ['Amount', 'Is fraudulent'];

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
                console.log("success");
				transactions = res.data;
			})
			.catch((err) => {
				console.log('could not fetch accounts for user');
			});
	}
</script>
<h1>Transactions</h1>
<GenericTable
	data={transactions}
	columns={transactionColumns}
	columnNames={transactionColumnNames}
/>
