$(document).ready(function () {
   startTagit();
});

//Initialize Tiny MCE
tinymce.init({
    selector: '#contentEditArea',
    plugins: "image",
    menubar: "file edit insert view format table tools",
    toolbar: "image",
    image_caption: true
});


function doAddCategory() {
    event.preventDefault();
    $('#validationErrors').empty();
    $('#addCategory').attr({
    });

    $.ajax({
        type: 'POST',
        url: '/CMS/admin/add/category',
        // Build a JSON object from the data in the form
        data: JSON.stringify({
            name: $('#createCategory').val()

        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (data, status) {
        // If the call succeeds, clear the form and reload the summary table
        location.reload();
    }).error(function (data, status) {
        // #2 - Go through each of the fieldErrors and display the associated error
        // message in the validationErrors div
        $.each(data.responseJSON.fieldErrors, function (index, validationError) {
            var errorDiv = $('#validationErrors');
            errorDiv.append(validationError.message).append($('<br>'));
        });
    });
}

function startTagit() {
    $.ajax({
        type: 'GET',
        url: '/CMS/admin/hashtags'

    }).success(function (data, success) {
        tags = data;
        $("#hashtags").tagit({
            availableTags: tags,
            singleField: true
        });

    });


}
