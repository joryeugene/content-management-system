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
    if ($('#main-blog-title').length) {
        $('#main-blog-title').text(data.title);
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
        $('#blog-title').val(data.title);
        $('#blog-image').val(data.image);
    }
}