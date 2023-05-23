<script>
	import { onMount } from "svelte";
	import GenericTable from "../../../lib/GenericTable.svelte";
	import axios from "axios";
	import RecommendedBooksTable from "../../../lib/RecommendedBooksTable.svelte";
    let data = [];
    let columns = ['Name', 'Author', 'Publish date', 'Added to bookstore', ''];
    let columnNames = ['name', 'author.name', 'publishDate', 'addedToBookstoreDate', ''];
    onMount(() => {
        axios.get("http://localhost:8080/book/recommend/unauth").then((res) => {data = res.data; })
    });

</script>

<h1>Recommended books</h1>

<RecommendedBooksTable {data} />