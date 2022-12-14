$(document).ready(function (value1, value2){

    let signup = $(".links").find("#signup");
    let signin = $(".links").find("#signin");
    let reset  = $(".links").find("#reset");
    let first_input = $("form").find(".first-input");
    let hidden_input = $("form").find(".input__block").find(".repeat__password");
    let hidden_select = $("form").find(".selectIT");

    let signin_btn  = $("form").find(".signin__btn");

    //----------- sign up ---------------------
    signup.on("click",function(e){
        e.preventDefault()


        $(this).parent().parent().siblings("h1").text("SIGN UP");
        $(this).parent().css("opacity","1");
        $(this).parent().siblings().css("opacity",".6");
        first_input.removeClass("first-input__block").addClass("signup-input__block");

        hidden_input.css({
            "opacity" : "1",
            "display" : "block"
        });

        hidden_select.css({
            "opacity" : "1",
            "display" : "block"
        });
        signin_btn.text("Sign up");
    });
    //----------- sign in ---------------------
    signin.on("click",function(e){
        e.preventDefault();
       // $("#form").attr("action", "/user/login");
        $(this).parent().parent().siblings("h1").text("SIGN IN");
        $(this).parent().css("opacity","1");
        $(this).parent().siblings().css("opacity",".6");
        first_input.addClass("first-input__block")
            .removeClass("signup-input__block");

        hidden_input.css({
            "opacity" : "0",
            "display" : "none"
        });

        hidden_select.css({
            "opacity" : "0",
            "display" : "none"
        });
        signin_btn.text("Sign in");
    });

    //----------- reset ---------------------
    reset.on("click",function(e){
        e.preventDefault();
        $(this).parent().parent().siblings("form")
            .find(".input__block").find(".input").val("");
    })

    //---------------------select Stack-----------------------------------
    $('body').on('change', '.formui-checkbox input[type="checkbox"]', function () {
        var $opt = $(this);
        $opt.parent('.option').toggleClass('checked');
    });
});