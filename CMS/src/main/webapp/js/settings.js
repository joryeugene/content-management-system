function set() {
    $.ajax({
        type: 'POST',
        url: "/CMS/config",
        data: JSON.stringify({
            title: $('#blog-title').val(),
            subtitle: $('#blog-subtitle').val(),
            navColor: $('#nav-color').val(),
            textColor: $('#text-color').val(),
            image: $('#blog-image').val()
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (data, status) {
        configTemplate(data, status);
    });
}

function setDefault() {
    $.ajax({
        type: 'POST',
        url: "/CMS/config",
        data: JSON.stringify({
            title: "CrossFit Guild",
            subtitle: "Most Recent Posts",
            navColor: "#101010",
            textColor: "#9d9d9d",
            image: "squat.jpg"
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (data, status) {
        configTemplate(data, status);
    });
}