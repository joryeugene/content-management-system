$(document).ready(function () {
    loadPages();
});

var pagesTable = $('#page-list');

function loadPages() {
    clearPages();
    $.ajax({
        type: 'GET',
        url: '/CMS/pages'
    }).success(function (data, status) {
        $.each(data, function (index, page) {
            pagesTable
                    .append($('<tr>')
                            .append($('<td>')
                                    .append($('<a>')
                                            .attr({'href': '/CMS/admin/page/edit/' + page.id})
                                            .text(page.id)
                                            ) // end <a> tag
                                    ) // end <td> tag
                            .append($('<td>')
                                    .text(page.title)
                                    ) // end <td> tag
                            .append($('<td>')
                                    .text(page.user.displayName)
                                    ) // end <td> tag
                            ); // end <tr> tag
        });
    });
}

function clearPages() {
    pagesTable.empty();
}
