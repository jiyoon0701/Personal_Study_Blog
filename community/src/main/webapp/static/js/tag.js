// TODO: Abstract
// TODO: masking: examples match
const tags = [];
const text = document.getElementById('text');
const list = document.getElementById('list');
const remove = document.getElementById('remove');
function imageUpload(formData) {
    let imageURL;

    $.ajax({
        type: "POST",
        url: "/board/imageupload",
        async: false,
        data: formData,
        async : false,
        processData: false,
        contentType: false,
        success: function (data) {
            imageURL = data;
            console.log(imageURL);
        },
        error: function (request, status, error) {
            alert(request + ", " + status + ", " + error);
        },
    });

    return imageURL;
}
const Editor = toastui.Editor;

const editor = new Editor({
    el: document.querySelector('#editor'),
    height: '600px',
    initialEditType: 'markdown',
    previewStyle: 'vertical',
    hooks : {
        addImageBlobHook: function (blob, callback) {
            const formData = new FormData();
            formData.append("image", blob);
            formData.append("uri", window.location.pathname);
            const imageURL = imageUpload(formData);
            callback(imageURL, "image");
        },
    }
});

function callback(files,callbacks){ // 파일 이름 컴온
    const formData = new FormData();

    console.log(files)
    formData.append("uri", window.location.pathname);
    formData.append("image", files);
    const imageUrl = imageUpload(formData);
    callbacks(imageUrl);
}

function imageUrl(imageURL){
    alert("imageURL", imageURL);
    const imageUrlSplit = imageURL.split('/');
    const fileName = imageUrlSplit[6]; // 파일 이미지

    $.ajax({
        type: "POST",
        url: "/board/save",
        data: {
            TITLE : $("#TITLE").val(),
            content_HTML : editor.getHTML().toString(),
            content_MARK : editor.getMarkdown().toString(),
            file_NAME : fileName.toString(),
            TAG : tags.toString(),
            STATE : $(".saveBtn").val()
        },
        async: false,
        success: function (data) {
            alert("등록 완료")
            window.location.href = "/board/view/"+this.data.STATE;
        },
        error: function (request, status, error) {
            alert(request + ", " + status + ", " + error);
        },
    });
}

function save(){
    const files = document.querySelector(".pre").files[0];
    console.log(files)
    callback(files, imageUrl);
}

seeHtml = function(){
    alert(editor.getHTML());
}
seeMd = function(){
    alert(editor.getMarkdown());
}
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
        tags.push(this.value.replace(',', ''));
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