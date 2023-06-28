<script>
	export let bookList;
    export let selected = 0;
</script>

Book:<br>
<select class="form-select" bind:value={selected}>
	{#each bookList as book}
		<option value={book.id}>{book.name}</option>
	{/each}
</select>
