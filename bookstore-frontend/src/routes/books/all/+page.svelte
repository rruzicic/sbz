<script>
	import { onMount } from 'svelte';
	import Table from '../../../lib/Table.svelte';
	import GenericTable from '../../../lib/GenericTable.svelte';
	import axios from 'axios';
	import { toast } from '../../../lib/stores/toast';
	import { user } from '../../../lib/stores/user';
	import Input from '../../../lib/Input.svelte';
	let books;

	let columns = ['Author', 'Name', 'Pub date', 'Genre', 'Price', 'Copies'];
	let columnNames = ['author.name', 'name', 'publishDate', 'genre', 'price', 'numberOfCopies'];
	let orderColumns = ['Book name', 'Quantity', 'Price'];
	let orderColumnsNames = ['bookName', 'quantity', 'price'];
	let orderData;
	let orderId = -1;
	let discount = 0;
	let isCash = false;
	let email = '';
	let password = '';

	let transaction = {};

	let config = {
		headers: {
			Authorization: `Basic ${$user.base64token}`
		}
	};

	class Order {
		constructor(book, quantity) {
			this.bookName = book.name;
			this.quantity = quantity;
			this.price = book.price * quantity;
		}
	}

	class Item {
		constructor(book, quantity) {
			this.book = book;
			this.quantity = quantity;
			this.discount = 0;
		}
	}

	onMount(() => {
		getBooks();
	});

	async function getBooks() {
		const response = await fetch('http://localhost:8080/book/all');

		books = await response.json();
	}

	async function previewOrder() {
		var inputs = document.querySelectorAll('table input[type=number]');
		orderData = [];
		var items = [];
		for (var i = 0; i < inputs.length; i++) {
			if (inputs[i].value > 0) {
				var value = Math.min(inputs[i].value, books[i].numberOfCopies);
				var orderItem = new Order(books[i], value);
				orderData.push(orderItem);
				var item = new Item(books[i], value);
				items.push(item);
			}
		}
		orderData = orderData;
		var newOrder = {
			items: items,
			discount: 0
		};
		const res = await axios
			.post('http://localhost:8080/order/new', newOrder, config)
			.then((res) => {
				handleToast('Success!', 'You have successfully created order!');
				discount = res.data.totalPrice;
				orderId = res.data.order.id;
				console.log(res.data);
			})
			.catch((err) => {
				handleToast('Error!', 'Error during creating order!');
				console.log(err);
			});
	}

	function handleToast(title, text) {
		$toast.id = 'liveToast';
		$toast.title = title;
		$toast.text = text;
		setTimeout(() => new bootstrap.Toast(document.querySelector('#liveToast')).show(), 10);
	}

	function cancelOrder() {
		orderData = [];
		discount = 0;
		if ((orderId = -1)) return;

		axios
			.delete(`http://localhost:8080/order/${orderId}`)
			.then((response) => {
				handleToast('Success!', 'You have successfully canceled order!');
			})
			.catch((err) => {
				handleToast('Error!', 'Error during deleting order!');
				console.log(err);
			});

		orderId = -1;
	}

	function buyBooks() {
		if (isCash) {
			buyBooksCash();
		} else {
			buyBooksCreditCard();
		}
	}

	async function buyBooksCash() {
		axios
			.get(`http://localhost:8080/order/buy/${orderId}`, config)
			.then((response) => {
				books = response.data;
			})
			.catch((error) => {
				console.log(error);
			});

		//books = await response.json();

		orderId = -1;
		discount = 0;
		orderData = [];
	}

	function prepareTransaction() {
		transaction.receiverId = 4;
		transaction.cvv = parseInt(transaction.cvv);
		transaction.expiryDate = new Date(transaction.expiryDate);
		transaction.amount = discount;
		transaction.location = {
			longitude: 19.843213,
			latitude: 45.244715
		};
	}

	async function buyBooksCreditCard() {
		let bankConfig = {
			headers: {
				Authorization: `Basic ${btoa(email + ':' + password)}`
			}
		};
		prepareTransaction();

		console.log(transaction);
		console.log(orderId);

		axios
			.post(`http://localhost:8081/transaction/submit`, transaction, bankConfig)
			.then((response) => {
				console.log(orderId);

				axios
					.get(`http://localhost:8080/order/buy/${orderId}`, config)
					.then((response) => {
						books = response.data;
					})
					.catch((error) => {
						console.log(error);
					});
				orderId = -1;
				discount = 0;
				orderData = [];
			})
			.catch(() => {
				handleToast(
					'Ooops!',
					'Somthing is wrong, it could either be because of: wrong credentials, wrong cvv, wrong expiry date, missing funds!'
				);
				orderId = -1;
				discount = 0;
				orderData = [];
			});
	}
</script>

<h1>Books page</h1>

<div>
	<Table data={books} {columns} {columnNames} />

	{#if $user.base64token !== ''} 
	<button
		on:click={previewOrder}
		class="btn btn-primary"
		style="float: right; width: 70px; margin-right: 5.5%;"
	>
		Order
	</button>
	<div>
		<h2>Confirm your order:</h2>
		<GenericTable data={orderData} columns={orderColumns} columnNames={orderColumnsNames} />
		<button class="btn btn-primary" on:click={cancelOrder}>Cancel</button>
		<h5>Total price with discount: {discount}</h5>
		<h5>Payment method:</h5>
		<label>
			<input type="radio" bind:group={isCash} value={false} />
			Credit card
		</label>

		<label>
			<input type="radio" bind:group={isCash} value={true} />
			Cash
		</label>
		<br />
		{#if !isCash}
			<Input label="Email" type="email" bind:value={email} />
			<Input label="Password" type="password" bind:value={password} />
			<Input label="CVV" type="number" bind:value={transaction.cvv} />
			<Input label="Expiry date" type="month" bind:value={transaction.expiryDate} />
			<br />
		{/if}
		<button
			class="btn btn-primary"
			on:click={buyBooks}
			style="float: right;"
			disabled={orderId === -1}
		>
			Buy
		</button>
		<br />
	</div>
	{/if}
</div>
