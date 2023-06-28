import { writable } from "svelte/store";


export const toast = writable({
    id: '', 
    title: '', 
    text: ''}
);

