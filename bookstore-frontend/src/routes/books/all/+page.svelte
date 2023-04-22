<script>
	import { onMount } from 'svelte';
	import Table from '../../../lib/Table.svelte';
	import GenericTable from '../../../lib/GenericTable.svelte';
	let books;
	
	let columns = ['Author', 'Name', 'Pub date', 'Genre', 'Price'];
	let columnNames = ['author.name', 'name', 'publishDate', 'genre', 'price'];
	let orderColumns = ['Book name', 'Quantity', 'Price']
	let orderColumnsNames = ['bookName', 'quantity', 'price']
	let orderData;//=[{bookName: 'Name', quantity: 12, price: 12}];

	class Order {
    constructor(book, quantity) {
      this.bookName = book.name;
      this.quantity = quantity;
	  this.price = book.price * quantity;
    }}

	onMount(() => {
		orderData=[]
		getBooks();
		
	});
	

	async function getBooks() {
		const response = await fetch('http://localhost:8080/book/all');

		books = await response.json();
		
		
	}

	 function previewOrder(){
		var inputs = document.querySelectorAll("table input[type=number]");
  
  		for (var i = 0; i < inputs.length; i++) {
			var order = new Order(books[i], inputs[i].value)
			orderData.push(order)
    		var value = inputs[i].value;
    		console.log(value); 
  		}
		orderData = orderData
	
	}
	function getOrderData(){
		console.log(orderData)
		return orderData
	}

</script>

<h1>Books page</h1>

<div>
<Table data={books} {columns} {columnNames} />
<button on:click={previewOrder} style="float: right; width: 70px; margin-right: 5.5%;">Order</button>
<div>
	<GenericTable data={orderData} columns={orderColumns} columnNames={orderColumnsNames}/>
</div>
</div>



