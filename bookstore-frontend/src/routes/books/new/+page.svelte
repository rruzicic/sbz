<script>
	import Input from '../../../lib/Input.svelte';
	import { required, validate } from '../../../lib/util/validate';
	import axios from 'axios';
	import { toast } from '../../../lib/stores/toast';
	let book = {
		name: '',
		author: '',
		publishDate: null,
		description: ''
	};

	let bookValidator = {
		name: [required],
		author: [required],
		publishDate: [required],
		description: [required]
	};

	$: [errorMessages, valid] = validate(book, bookValidator);

	async function handleSubmit() {
		const res = await axios
			.post('http://localhost:8080/book/new', book)
			.then((res) => {
				handleToast('Success!', 'You have successfully added new book to our bookstore!');
			})
			.catch((err) => {
				handleToast('Error!', 'Could not save book to bookstore!');
				console.log(err)
			});
	}

	function handleToast(title, text) {
		$toast.id = 'liveToast';
		$toast.title = title;
		$toast.text = text;
		setTimeout(() => new bootstrap.Toast(document.querySelector('#liveToast')).show(), 10);
	}

	function sendToast() {
		handleToast('Toast notification', 'Some toast notification!');
	}

</script>

<h1>Add a new book</h1>

<Input label="Name" bind:value={book.name} errors={errorMessages.name} />
<Input label="Author" bind:value={book.author} errors={errorMessages.author} />
<Input
	label="Publish date"
	type="date"
	bind:value={book.publishDate}
	errors={errorMessages.publishDate}
/>

Description:
<textarea
	type="textarea"
	class="form-control"
	rows="3"
	bind:value={book.description}
	errors={errorMessages.description}
/>
<span class="text-danger">
	{#each errorMessages.description as error}
		{#if error !== ''}
			{error}<br />
		{/if}
	{/each}
</span>

<button on:click={handleSubmit} class="btn btn-primary" disabled={!valid}>Submit</button>

<button type="button" class="btn btn-danger" on:click={sendToast}>Join us</button>
