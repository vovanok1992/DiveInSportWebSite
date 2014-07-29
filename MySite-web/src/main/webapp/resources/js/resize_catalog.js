/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(window).on('resize', handleresize);

$(window).on('load', function() {
    handleresize();
    $("#smallmenu").draggable();
});

function handleresize() {
    

    var win = $(this); //this = window
    if (win.width() <= 1000) {
        $('#menu').hide();
        $('#content').css('margin-left', '5px');
        $('#page').css('width', '100%');
        $('#menubutton').show();
    }
    else {
        $('#menu').show();
        $('#content').css('margin-left', '215px');
        $('#page').css('width', '1000px');
        $('#menubutton').hide();
        $('#smallmenu').fadeOut("fast");
    }

}