<script>
	import { onMount } from 'svelte';
	import Table from '../../../lib/Table.svelte';

	let books;
	/*let books = [
        {name: 'Of Mice and Men', author: 'John Steinbeck', publishDate: new Date("10.10.2000."), description: 'description......', rating: 4.5},
        {name: 'Steppenwolf', author: 'Hermann Hesse', publishDate: new Date("10.11.2000."), description: 'description......', rating: 4.0},
        {name: 'Gone with the Wind', author: 'Margaret Mitchell', publishDate: new Date("10.12.2000."), description: 'description......', rating: 4.2},
    ];*/
	let columns = ['Author', 'Name', 'Pub date', 'Description', 'Rating'];
	let columnNames = ['author', 'name', 'publishDate', 'description', 'rating'];

	onMount(() => {
		getBooks();
	});

	async function getBooks() {
		const response = await fetch('http://localhost:8080/book/all');

		books = await response.json();
	}
</script>

<h1>Books page</h1>

<Table data={books} {columns} {columnNames} />
