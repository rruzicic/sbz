<script>
	export let text;
	export let max = 100;
	let readMore = false;

	function handleReadMore() {
		readMore = true;
	}

	function handleReadLess() {
		readMore = false;
	}
</script>

<p>
	{text.slice(0, max)}
	{#if readMore}
		{text.slice(max)} <button on:click={handleReadLess}>Read less</button>
	{:else}
		<button on:click={handleReadMore}>Read more</button>
	{/if}
</p>
