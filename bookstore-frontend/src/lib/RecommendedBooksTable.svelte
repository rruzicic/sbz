<script>
	export let data;
</script>

<table class="table">
	<thead>
		<tr>
			<th>Name</th>
			<th>Author</th>
			<th>Pub. date</th>
			<th>Added to bookstore</th>
			<th>Average rating</th>
            <th>Tags</th>
		</tr>
	</thead>
	<tbody>
        {#each data as row}
		<tr>
                <td>{row.name}</td>
                <td>{row.author.name}</td>
                <td>{row.publishDate}</td>
                <td>{row.addedToBookstoreDate}</td>
                <td>{Math.round(row.averageRating * 100) / 100}</td>
                <td>
                    {#if row.new_ === true}
                        <span class="badge rounded-pill bg-primary">New</span>
                    {/if}
                    {#if row.popular === true}
                        <span class="badge rounded-pill bg-warning text-dark">Popular</span>
                    {/if}
                    {#if row.rating === 'NEUTRAL'}
                        <span class="badge rounded-pill bg-secondary">Neutral</span>
                    {/if}
                    {#if row.rating === 'GOOD'}
                        <span class="badge rounded-pill bg-success">Good</span>
                    {/if}
                    {#if row.rating === 'BAD'}
                        <span class="badge rounded-pill bg-danger">Bad</span>
                    {/if}
                    
                </td>
            </tr>
            {/each}
	</tbody>
</table>
