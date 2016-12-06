$(document).ready(function () {
    config();
});

function config() {
    $.ajax({
        type: 'GET',
        url: "/CMS/config"
    }).success(function (data, status) {
        configTemplate(data, status);
    });
}

function configTemplate(data, status) {
    $('a').css("color", data.highlightColor);
    
    if ($('#category-header').length) {
        $('#category-header').css("color", "white");
        $('#category-header').css("background-color", data.highlightColor);
    }
    
    if ($('.panel-body a').length) {
        $('.panel-body a').css("color", "black");
    }
    
    if ($('#main-blog-title').length) {
        $('#main-blog-title').text(data.title);
    }
    
    if ($('#config-subtitle').length) {
        $('#config-subtitle').text(data.subtitle);
    }    

    if ($('.navbar-brand').length) {
        $('.navbar-brand').text(data.title);
        $('nav').css("background-color", data.navColor);
        $('nav a').css("color", data.textColor);
    }

    if ($('#header-img').length) {
        $('#header-img').css("background-image", "url(/CMS/img/" + data.image + ")");
    }

    if ($('#settings').length) {
        $('#nav-color').val(data.navColor);
        $('#text-color').val(data.textColor);
        $('#highlight-color').val(data.highlightColor);
        $('#blog-title').val(data.title);
        $('#blog-subtitle').val(data.subtitle);
        $('#blog-image').val(data.image);
    }
}