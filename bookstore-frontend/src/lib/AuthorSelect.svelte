<script>
	export let authorList;
    export let selected = 0;
</script>

Author:<br>
<select class="form-select" bind:value={selected}>
	{#each authorList as author}
		<option value={author.id}>{author.name}</option>
	{/each}
</select>
