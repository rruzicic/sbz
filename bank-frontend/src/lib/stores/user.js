import { writable } from "svelte/store";


export const user = writable({
    email: '', 
    password: '',
    base64token: '',
    role: '',
    });

