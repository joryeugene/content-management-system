$(document).ready(function () {
    loadPages();
    loadPosts();
});
var pagesTable = $('#page-list');
var postTable = $('#post-list');
function loadPages() {
    clearPages();
    $.ajax({
        type: 'GET',
        url: 'pages'
    }).success(function (data, status)
    {
        $.each(data, function(index, page){

                       pagesTable.append($('<tr>')
                                .append($('<td>')
                                        .append($('<a>')
                                                .attr({'href': 'edit/page/' + page.id})
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

//load posts
function loadPosts() {
    clearPosts();
    $.ajax({
        type: 'GET',
        url: 'posts'
    }).success(function (data, status)
    {
        $.each(data, function(index, post){

                       postTable.append($('<tr>')
                                .append($('<td>')
                                        .append($('<a>')
                                                .attr({'href': 'edit/post/' + post.id})
                                                .text(post.id)
                                                )

                                        )
                                .append($('<td>')
                                        .text(post.title)
                                        )
                                .append($('<td>')
                                        .text(post.user.displayName)
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
function clearPosts() {
    $('#posts-table').empty();
}
