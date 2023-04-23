<script>
	import axios from "axios";
	import { onMount } from "svelte";
    import BookSelect from "../../../lib/BookSelect.svelte" 
	import { toast } from "../../../lib/stores/toast";
    let bookId = 0;
    let rating = 0;
    
    let books = [];

    

    onMount(() => {
        axios.get("http://localhost:8080/book/all",)
        .then((res) => {
            books = res.data;
        });
    });
    
    function handleSubmit() {
        let config = {
        headers: {
            Authorization: `Basic ${localStorage.getItem('access_token')}`,
        }
        };
        axios.post(`http://localhost:8080/review/${bookId}/${rating}`, null, config)
        .then((res) => {
            handleToast('Review', 'Successful review submision!')
        });
    }

    function handleToast(title, text) {
		$toast.id = 'liveToast';
		$toast.title = title;
		$toast.text = text;
		setTimeout(() => new bootstrap.Toast(document.querySelector('#liveToast')).show(), 10);
	}
    
</script>

<h1>Review a book</h1>

<BookSelect bookList={books} bind:selected={bookId}/>
Rating:<br>
<input class="form-control" type="number" bind:value={rating} step="1" min="1" max="5">
<button on:click={handleSubmit} class="btn btn-primary" disabled={false}>Submit</button>

<br> {bookId + ' ' + rating}