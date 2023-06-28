<script>
	import GenericTable from '../../../lib/GenericTable.svelte';
	import { user } from '../../../lib/stores/user';
	import axios from 'axios';
    import { onMount } from 'svelte';

	let creditRequestColumns = ['ID', 'Income', 'Loan amount', 'Loan rate', 'Recommend approval'];
	let creditRequestColumnsNames = ['id', 'clientIncome', 'moneySum', 'rateNumber', 'recommendApproval'];
	let creditRequests;

	onMount(() => {
		if ($user.role === 'CLERK') {
			getCreditRequestsForClerk();
		} else if ($user.role === 'USER') {
			getCreditRequestsForUser();
		}
	});

	let config = {
		headers: {
			Authorization: `Basic ${localStorage.getItem('access_token')}`
		}
	};

	function getCreditRequestsForClerk() {
		axios
			.get('http://localhost:8081/credit/all', config)
			.then((res) => {
				creditRequests = res.data;
			})
			.catch((err) => {
				console.log('could not fetch creditRequests for clerk');
			});
	}

	function getCreditRequestsForUser() {
		axios
			.get('http://localhost:8081/credit/my', config)
			.then((res) => {
				creditRequests = res.data;
			})
			.catch((err) => {
				console.log('could not fetch creditRequests for user');
			});
	}
</script>

<h1>All bank credit requests</h1>

<GenericTable data={creditRequests} columns={creditRequestColumns} columnNames={creditRequestColumnsNames} />