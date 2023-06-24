<script>
	export let value;
	export let type = 'text';
	export let label;
	export let errors = [];

	function typeAction(node) {
		node.type = type;
		if (type === 'number') {
			node.setAttribute('min', '0'); 
		}
	}
</script>

{label}:
<input class="form-control" use:typeAction bind:value />
<span class="text-danger">
	{#each errors as error}
		{#if error !== ''}
			{error}<br />
		{/if}
	{/each}
</span>
