$(document).ready(function () {
    loadPages();
});
var pagesTable = $('#page-list');
function loadPages() {
    clearPages();
    $.ajax({
        type: 'GET',
        url: '../pages'
    }).success(function (data, status)
    {
        $.each(data, function(index, page){

                       pagesTable.append($('<tr>')
                                .append($('<td>')
                                        .append($('<a>')
                                                .attr({'href': '../edit/page/' + page.id})
                                                .text(page.id)
                                                )

                                        )
                                .append($('<td>')
                                        .text(page.title)
                                        )
                                .append($('<td>')
                                        .text(page.user.displayName)
                                        )
                                .append($('<td>')
                                        .append($('<button>')
                                                .attr({'class':'btn btn-primary'})
                                                .text('Approve'))
                                        )
                                );
        });   
    });
}

function clearPages() {
    $('#pages-table').empty();
}
