<script>
    export let id;
    export let title;
    export let text;
</script>

<div id="{id}" class="toast hide" role="alert" aria-live="assertive" aria-atomic="true">
    <div class="toast-header">
        <strong class="me-auto">{title}</strong>
        <small></small>
        <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close" />
    </div>
    <div class="toast-body">{text}</div>
</div>