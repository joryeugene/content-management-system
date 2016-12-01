$(document).ready(function () {
    loadPages();
});

var pagesTable = $('#page-list');

function loadPages() {
    clearPages();
    $.ajax({
        type: 'GET',
        url: '/CMS/pagenav'
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
                                    .append($('<a>')
                                            .attr({'href': '/CMS/admin/page/edit/' + page.id})
                                            .text(page.title)
                                            ) // end <a> tag
                                    ) // end <td> tag
                            .append($('<td>')
                                    .text(page.nav.menuName)
                                    ) // end <td> tag
                            .append($('<td>')
                                    .text(page.nav.position)
                                    ) // end <td> tag
                            .append($('<td>')
                                    .text(page.user.displayName)
                                    ) // end <td> tag
                            ); // end <tr> tag
        });
    });
}

function editNavTitle() {
    clearPages();
    $("#edit-nav-title").replaceWith('<span id="edit-nav-title"><a onclick="submitNavTitle();" class="pointer">SUBMIT</a> | <a onclick="cancelNavTitle();" class="pointer">Cancel</a></span>');
    $.ajax({
        type: 'GET',
        url: '/CMS/pagenav'
    }).success(function (data, status) {
        $.each(data, function (index, page) {
            pagesTable
                    .append($('<tr>')
                            .append($('<td>')
                                    .append($('<a class="page-id">')
                                            .attr({'href': '/CMS/admin/page/edit/' + page.id})
                                            .text(page.id)
                                            ) // end <a> tag
                                    ) // end <td> tag
                            .append($('<td>')
                                    .append($('<a>')
                                            .attr({'href': '/CMS/admin/page/edit/' + page.id})
                                            .text(page.title)
                                            ) // end <a> tag
                                    ) // end <td> tag
                            .append($('<td>')
                                    .append($('<input class="page-title">')
                                            .val(page.nav.menuName)
                                            ) // end <input> tag
                                    ) // end <td> tag
                            .append($('<td>')
                                    .append($('<input class="page-position" style="width: 40px;">')
                                            .val(page.nav.position)
                                            ) // end <input> tag
                                    ) // end <td> tag
                            .append($('<td>')
                                    .text(page.user.displayName)
                                    ) // end <td> tag
                            ); // end <tr> tag
        });
    });
}

function cancelNavTitle() {
    $("#edit-nav-title").replaceWith('<a id="edit-nav-title" onclick="editNavTitle();" class="pointer">Edit Nav Title / Order</a>');
    loadPages();
}

function submitNavTitle() {
    var navs = [];

    $('#page-list tr').each(function () {

        var id = $('.page-id', this).text();
        var pos = $('.page-position', this).val();
        var name = $('.page-title', this).val();

        var nav = {
            pageId: id,
            position: pos,
            menuName: name
        }
        navs.push(nav);
    });

    console.log(navs);

    $.ajax({
        type: 'POST',
        url: '/CMS/admin/navs/update',
        data: JSON.stringify(navs),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function() {
        cancelNavTitle();
    });
}

function clearPages() {
    pagesTable.empty();
}
