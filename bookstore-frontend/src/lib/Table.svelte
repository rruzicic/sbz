<script>
	export let data = [];
	export let columns = [];
	export let columnNames = getKeyNames(data[0]);

	function getKeyNames(obj) {
		return Object.keys(obj);
	}
</script>

<table class="table">
	<thead>
		<tr>
			{#each columns as column}
				<th>{column}</th>
			{/each}
		</tr>
	</thead>
	<tbody>
		{#each data as row}
			<tr>
				{#each columnNames as key}
					{#if Object.prototype.toString.call(row[key]) !== '[object Date]'}
						<td>{@html row[key]}</td>
					{:else}
						<td>{@html row[key].toDateString()}</td>
					{/if}
				{/each}
			</tr>
		{/each}
	</tbody>
</table>
