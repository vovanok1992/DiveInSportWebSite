var x = 0;
$('.no_butt').hover(function() {
    if(x < 4) {
        $('.no_butt').animate({
            left:'+=100'
        },100);
        x++;
    }
    if(x >= 4){
        $('.no_butt').fadeOut();
    }
});

$('.yes_butt').click(function() {
    alert('Спасибо большое! :)');
    $('.no_butt').css('left', '0');
});


$('.yes_butt').hover(function() {


});

