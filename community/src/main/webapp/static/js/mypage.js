
    function readURL(input) {
    if (input.files && input.files[0]) {
    const reader = new FileReader();
    reader.onload = function(e) {
    $('#imagePreview').css('background-image', 'url('+e.target.result +')');
    $('#imagePreview').hide();
    $('#imagePreview').fadeIn(650);
}
    reader.readAsDataURL(input.files[0]);
}
}
    $("#files").change(function() {
        console.log("image Upload", this);
    readURL(this);
});
