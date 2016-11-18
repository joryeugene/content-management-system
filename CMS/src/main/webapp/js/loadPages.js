$(document).ready(function () {
    loadPages();
});
 var pagesTable = $('#pages-table');

function loadPages() {
    clearPages();

    $.ajax({
        type: 'GET',
        url: '../pages'
    }).success(function (data, status)
    {
        $.each(data, function (index, page) {
            pagesTable.append($('<table>')
                    .attr({'class': 'table table-striped'})
                    .append($('<thead><tr><th>Page ID</th><th>Title</th><th>Author</th><th>Approve</th>')
                            )
                    .append($('<tbody>')
                            .append($('<tr>')
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
                                    )

                            )

                    );


        });

    });

}

function clearPages(){
    $('#pages-table').empty();
}
