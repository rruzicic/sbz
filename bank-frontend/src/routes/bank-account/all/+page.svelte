<script>
	import GenericTable from '../../../lib/GenericTable.svelte';
	import { user } from '../../../lib/stores/user';
	import axios from 'axios';
    import { onMount } from 'svelte';

	let accountColumns = ['Balance', 'CVV', 'Expiry date'];
	let accountColumnsNames = ['balance', 'cvv', 'creditCardExpiryDate'];
	let accounts;

	onMount(() => {
		if ($user.role === 'CLERK') {
			getAccountsForClerk();
		} else if ($user.role === 'USER') {
			getAccountsForUser();
		}
	});

	let config = {
		headers: {
			Authorization: `Basic ${localStorage.getItem('access_token')}`
		}
	};

	function getAccountsForClerk() {
		axios
			.get('http://localhost:8081/bank-account/all', config)
			.then((res) => {
				accounts = res.data;
			})
			.catch((err) => {
				console.log('could not fetch accounts for clerk');
			});
	}

	function getAccountsForUser() {
		axios
			.get('http://localhost:8081/bank-account/my-accounts', config)
			.then((res) => {
				accounts = res.data;
			})
			.catch((err) => {
				console.log('could not fetch accounts for user');
			});
	}
</script>

<h1>All bank accounts</h1>

<GenericTable data={accounts} columns={accountColumns} columnNames={accountColumnsNames} />
