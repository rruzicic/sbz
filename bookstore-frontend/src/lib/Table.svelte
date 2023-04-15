<script>
  export let data = [];
  export let columns = [];
  export let columnNames = [];

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
        {#each columns as column, i}
          <td>
            {#if columnNames[i] === 'author.name'}
              {@html row.author.name}
            {:else}
              {#if Object.prototype.toString.call(row[columnNames[i]]) !== '[object Date]'}
                {@html row[columnNames[i]]}
              {:else}
                {@html row[columnNames[i]].toDateString()}
              {/if}
            {/if}
          </td>
        {/each}
      </tr>
    {/each}
  </tbody>
</table>

