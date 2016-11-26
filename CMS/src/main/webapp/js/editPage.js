
$(document).ready(function () {
    loadPageToEdit();
});
 var contentEditArea = $('#contentEditArea');
 var titleEditArea = $('#titleEditArea');

function loadPageToEdit(id) {
    clearPage();

    $.ajax({
        type: 'GET',
        url: '/edit/page/' + id
    }).success(function (data, status)
    {
        contentEditArea.append(data.content);
        titleEditArea.val(data.title);
        });
}

function clearPage(){
    $('#edit-page-content').empty();
}

