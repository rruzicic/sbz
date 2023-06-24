<script>
    import axios from 'axios';
	import Input from '../../../lib/Input.svelte';
    import { toast } from '../../../lib/stores/toast';
    import { goto } from '$app/navigation';
	import LeafletMap from '../../../lib/LeafletMap.svelte';

	let transaction = {location: {longitude: 0, latitude: 0}};
	/*let config = {
		headers: {
			Authorization: `Basic ${localStorage.getItem('access_token')}`
		}
	};
*/
    function mapValues() {
        transaction.cvv = parseInt(transaction.cvv);
        transaction.receiverId = parseInt(transaction.receiverId);
        transaction.amount = parseFloat(transaction.amount);
        transaction.expiryDate = new Date(transaction.expiryDate);
        transaction.location.latitude = parseFloat(transaction.location.latitude);
        transaction.location.longitude = parseFloat(transaction.location.longitude);
    }

	function handleCreate() {
        mapValues();
		console.log(transaction);
        axios
			.post('http://localhost:8081/transaction/submit', transaction, config)
			.then((res) => {
				handleToast('Transaction', `Money sent!`);
				goto(`/`, true);
			})
			.catch((err) => {
                console.log(err);
				handleToast('Ooops!', 'Could not send money, try again later!');
			});
	}

    function handleToast(title, text) {
		$toast.id = 'liveToast';
		$toast.title = title;
		$toast.text = text;
		setTimeout(() => new bootstrap.Toast(document.querySelector('#liveToast')).show(), 10);
	}
</script>

<h1>Make a transaction</h1>
<Input label="CVV" type="number" bind:value={transaction.cvv} />
<Input label="Receiver ID" type="number" bind:value={transaction.receiverId} />
<Input label="Expiry date" type="month" bind:value={transaction.expiryDate} />
<Input label="Amount" type="number" bind:value={transaction.amount} />
<br>
<button on:click={handleCreate} class="btn btn-primary">Send money</button>
<br>
Lon:{transaction.location.longitude}, lat: {transaction.location.latitude}
<LeafletMap bind:location={transaction.location}/>
