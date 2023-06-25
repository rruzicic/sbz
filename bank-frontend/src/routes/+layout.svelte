<script>
	import '../app.scss';
	import Toast from '../lib/Toast.svelte';
	import { toast } from '../lib/stores/toast';
	import { goto } from '$app/navigation';
	import { user } from '../lib/stores/user';

	function handleLogout() {
		localStorage.removeItem('access_token')
		$user.email = '';
		$user.password = '';
		$user.base64token = '';
		$user.role = '';
		goto(`/`, true)
	}
</script>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
	<div class="container-fluid">
		<a class="navbar-brand" href="/">Bank</a>
		<button
			class="navbar-toggler"
			type="button"
			data-bs-toggle="collapse"
			data-bs-target="#navbarNav"
			aria-controls="navbarNav"
			aria-expanded="false"
			aria-label="Toggle navigation"
		>
			<span class="navbar-toggler-icon" />
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<!-- RENDER IN ANY CASE -->
				<li class="nav-item">
					<a class="nav-link" aria-current="page" href="/">Home</a>
				</li>

				<!-- RENDER FOR CLERK -->
				{#if $user.role === 'CLERK'}
					<span></span>
				{/if}

				<!-- RENDER FOR USER -->
				{#if $user.role === 'USER'}
				<li class="nav-item">
					<a class="nav-link" href="/bank-account/new">New bank account</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/transaction/new">New transaction</a>
				</li>
				<li class="nav-item" >
					<a class="nav-link" href="/credit-request/new">New credit requests</a>
				</li>
				{/if}

				<!-- RENDER FOR UNAUTHENTICATED -->
				{#if $user.role === ''}
				<li class="nav-item" >
					<a class="nav-link" href="/user/login">Login</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/user/register">Register</a>
				</li>
				{/if}

				<!-- RENDER FOR AUTHENTICATED -->
				{#if $user.role !== ''}
				<li class="nav-item" >
					<a class="nav-link" href="/transaction/all">Transactions</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/bank-account/all">Bank accounts</a>
				</li>
				<li class="nav-item" >
					<a class="nav-link" href="/credit-request/all">Credit requests</a>
				</li>
				<li class="nav-item" >
					<button class="nav-link" on:click={handleLogout}>Logout</button>
				</li>
				{/if}
			</ul>
		</div>
	</div>
</nav>
<br>
<div class="container">
	<slot />
</div>

<div class="toast-container position-fixed bottom-0 end-0 p-3" style="z-index: 11">
		<Toast id="{$toast.id}" title="{$toast.title}" text="{$toast.text}"></Toast>
</div>