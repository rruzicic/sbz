<script>
	import { onMount } from 'svelte';
	import GenericTable from '../../../lib/GenericTable.svelte';
	import axios from 'axios';
	import RecommendedBooksTable from '../../../lib/RecommendedBooksTable.svelte';
	import { user } from '../../../lib/stores/user';
	let data = [];
	let columns = ['Name', 'Author', 'Publish date', 'Added to bookstore', ''];
	let columnNames = ['name', 'author.name', 'publishDate', 'addedToBookstoreDate', ''];
	onMount(() => {
		if ($user.base64token === '') {
			axios.get('http://localhost:8080/book/recommend/unauth').then((res) => {
				data = res.data;
			});
		} else {
			let config = {
				headers: {
					Authorization: `Basic ${localStorage.getItem('access_token')}`
				}
			};
			axios.get('http://localhost:8080/book/recommend/regular', config).then((res) => {
				data = res.data;
			});
		}
	});
</script>

<h1>Recommended books</h1>

<RecommendedBooksTable {data} />
