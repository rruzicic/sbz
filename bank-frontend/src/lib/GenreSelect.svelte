<script>    
    export let genre;
    
	let genreList = ['EDUCATION', 'NOVEL', 'ROMANCE'];

    
</script>

Genre:<br>
<select class="form-select" bind:value={genre} multiple>
	{#each genreList as genre}
		<option value={genre}>{genre}</option>
	{/each}
</select>