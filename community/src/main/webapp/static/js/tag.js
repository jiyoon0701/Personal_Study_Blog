// TODO: Abstract
// TODO: masking: examples match
const tags = [

];
const text = document.getElementById('text');
const list = document.getElementById('list');
const remove = document.getElementById('remove');

const makeTag = (text) => {
    let output = `<li class="tags__item"><span class="tags__inner">${text}</span><button type="button" class="tags__remove" aria-label="Remove ${text} tag">&times;</button></li>`;
    list.insertAdjacentHTML('beforeend', output);
}

/**
 * Add items when typing
 */
// Only check each time user types space, tab or uses arrows? For performance
const onInputKeyUp = function(e) {
    console.log(e, this.value);

    // Don't allow just space or comma
    if(this.value == ' ' || this.value == ',') {
        this.value = '';
    }

    // Comma separate tags
    if (this.value !== '' && e.key === ',') {
        makeTag(this.value.replace(',', ''));
        this.value = '';
    }
}

/**
 * Remove items if backspacing
 */
const onInputKeyDown = function(e) {
    console.log(this.value);
    let items = list.children;

    if ((e.keyCode == 8 || e.keyCode == 46) && items.length && this.value == '') {
        let lastItem = items[items.length-1];
        this.value = lastItem.children[0].innerText;
        lastItem.outerHTML = '';
    }
}

const onEmptyClick = function() {
    list.innerHTML = '';
}

const deleteTag = function(e) {
    e.preventDefault();

    if (e.target !== this && e.target.matches('.tags__remove')) {
        e.target.parentElement.outerHTML = '';
    }
}

text.addEventListener('keyup', onInputKeyUp, false);
text.addEventListener('keydown', onInputKeyDown, false);

remove.addEventListener('click', onEmptyClick, false);

list.addEventListener('click', deleteTag);