<script>
	import { onMount } from 'svelte';
	import Input from '../../../lib/Input.svelte';
	import AuthorSelect from '../../../lib/AuthorSelect.svelte';
	import axios from 'axios';
	import { required, validate } from '../../../lib/util/validate';
	import { toast } from '../../../lib/stores/toast';
	import GenreSelectBook from '../../../lib/GenreSelectBook.svelte';

	let book = {
		name: '',
		author: {id: null},
		publishDate: null,
		price: 0,
		genre: 'EDUCATION',
		numberOfCopies: 1
	};

	let bookValidator = {
		name: [required],
		author: [],
		publishDate: [required],
		price: [required],
		genre: [],
		numberOfCopies: []
	};

	$: [errorMessages, valid] = validate(book, bookValidator);

	async function handleSubmit() {
		let config = {
        headers: {
            Authorization: `Basic ${localStorage.getItem('access_token')}`,
        }
        };
		const res = await axios
			.post('http://localhost:8080/book/new', book, config)
			.then((res) => {
				handleToast('Success!', 'You have successfully added new book to our bookstore!');
			})
			.catch((err) => {
				handleToast('Error!', 'Could not save book to bookstore!');
				console.log(err)
				console.log(book)
			});
	}
	let authorList = [];

	onMount(() => {
		axios.get("http://localhost:8080/author/all")
		.then((res) => {
			authorList = res.data;
		})
	});

	function handleToast(title, text) {
		$toast.id = 'liveToast';
		$toast.title = title;
		$toast.text = text;
		setTimeout(() => new bootstrap.Toast(document.querySelector('#liveToast')).show(), 10);
	}
</script>

<h1>Add a new book</h1>

<Input label="Name" bind:value={book.name} errors={errorMessages.name} />
<AuthorSelect authorList={authorList} bind:selected={book.author.id}/>
<Input
	label="Publish date"
	type="date"
	bind:value={book.publishDate}
	errors={errorMessages.publishDate}
/>
<Input type="number" label="Price" bind:value={book.price} errors={errorMessages.price} />
<Input label="NumberOfCopies" bind:value={book.numberOfCopies} errors={errorMessages.numberOfCopies} />
<GenreSelectBook bind:genre={book.genre}/>


<button on:click={handleSubmit} class="btn btn-primary" disabled={!valid}>Submit</button>
