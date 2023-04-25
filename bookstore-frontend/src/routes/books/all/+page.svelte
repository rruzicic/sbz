<script>
	import { onMount } from 'svelte';
	import Table from '../../../lib/Table.svelte';
	import GenericTable from '../../../lib/GenericTable.svelte';
	import axios from 'axios';
	import { toast } from '../../../lib/stores/toast';
	let books;
	
	let columns = ['Author', 'Name', 'Pub date', 'Genre', 'Price', 'Copies'];
	let columnNames = ['author.name', 'name', 'publishDate', 'genre', 'price', 'numberOfCopies'];
	let orderColumns = ['Book name', 'Quantity', 'Price']
	let orderColumnsNames = ['bookName', 'quantity', 'price']
	let orderData;
	let orderId = -1;
	let discount = 0;

	class Order {
    constructor(book, quantity) {
      this.bookName = book.name;
      this.quantity = quantity;
	  this.price = book.price * quantity;
    }}

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

	 async function previewOrder(){
		var inputs = document.querySelectorAll("table input[type=number]");
		orderData = []
		var items = []
  		for (var i = 0; i < inputs.length; i++) {
			if(inputs[i].value > 0){
				var value = Math.min(inputs[i].value, books[i].numberOfCopies);
				var orderItem = new Order(books[i], value)
				orderData.push(orderItem)
				var item = new Item(books[i], value)
				items.push(item)
			}
    	 
  		}
		orderData = orderData
		var newOrder = {
			items: items,
			discount: 0
		}
		const res = await axios
			.post('http://localhost:8080/order/new', newOrder)
			.then((res) => {
				handleToast('Success!', 'You have successfully created order!');
				discount = res.data.totalPrice
				orderId = res.data.order.id
				console.log(res.data)
	
			})
			.catch((err) => {
				handleToast('Error!', 'Error during creating order!');
				console.log(err)
			});
		
	
	}

	function handleToast(title, text) {
		$toast.id = 'liveToast';
		$toast.title = title;
		$toast.text = text;
		setTimeout(() => new bootstrap.Toast(document.querySelector('#liveToast')).show(), 10);
	}

	function cancelOrder(){
		orderData = []
		discount = 0
		if(orderId = -1) 
			return

		fetch(`http://localhost:8080/order/${orderId}`, {
      method: 'DELETE'
    })
      .then(response => {
        handleToast('Success!', 'You have successfully deleted order!');
			})
			.catch((err) => {
				handleToast('Error!', 'Error during deleting order!');
				console.log(err)
			});

		
			orderId = -1

	}

	async function  buyBooks(){
		const response = await fetch(`http://localhost:8080/order/buy/${orderId}`);

		books = await response.json();

		orderId = -1
		discount = 0
		orderData = []

	}
	

</script>

<h1>Books page</h1>

<div>
<Table data={books} {columns} {columnNames} />
<button on:click={previewOrder} style="float: right; width: 70px; margin-right: 5.5%;">Order</button>
<div>
	<h2>Confirm your order:</h2>
	<GenericTable data={orderData} columns={orderColumns} columnNames={orderColumnsNames}/>
	<h5>Total price with discount: {discount}</h5>
	<button on:click={cancelOrder}>Cancel</button>
	<button on:click={buyBooks} style="float: right; margin-right: 5.5%;">Cash on delivery</button>
</div>
</div>



