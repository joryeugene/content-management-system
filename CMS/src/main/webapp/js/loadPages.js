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
        url: '/CMS/admin/user/pages/3'
    }).success(function (data, status)
    {
        $.each(data, function (index, page) {

            pagesTable.append($('<tr>')
                    .append($('<td>')
                            .append($('<a>')
                                    .attr({'href': '/CMS/admin/page/edit/' + page.id})
                                    .text(page.id)
                                    )

                            )
                    .append($('<td>')
                            .append($('<a>')
                                    .attr({'href': '/CMS/admin/page/edit/' + page.id})
                                    .text(page.title)
                                    )
                            )
                    .append($('<td>')
                            .text(page.user.displayName)
                            )
//                  **POSSIBLE FUTURE FUNCTIONALITY**                            
//                    .append($('<td>')
//                            .append($('<button>')
//                                
//                                    .attr({'class': 'btn btn-primary'})
//                                    .text('Approve'))
//                            )
                    );
        });
    });
}

//load posts
function loadPosts() {
    clearPosts();

    $.ajax({
        type: 'GET',
        url: '/CMS/admin/user/allposts/5'
    }).success(function (data, status)
    {
        $.each(data, function (index, post) {
            var queued;
            var approve;
            var category;
            if (post.queued === false) {
                queued = "disabled";
                approve = "Approved";
            } else {
                queued = "";
                approve = "Approve";
            }
            if (post.category === null)
                category = "";
            else
                category = post.category.name;


            postTable.append($('<tr>')
                    .append($('<td>')
                            .append($('<a>')
                                    .attr({'href': '/CMS/edit/post/' + post.id})
                                    .text(post.id)
                                    )

                            )
                    .append($('<td>')
                            .append($('<a>')
                                    .attr({'href': '/CMS/edit/post/' + post.id})
                                    .text(post.title)
                                    )

                            )
                    .append($('<td>')
                            .text(post.author.displayName)
                            )
                    .append($('<td>')
                            .text(category)
                            )
                    .append($('<td>')
                            .text(post.stringStartDate)
                            )
                    .append($('<td>')
                            .text(post.stringEndDate)
                            )
                    .append($('<td>')
                            .append($('<button>')
                                    .attr({
                                        'class': 'btn btn-primary' + queued,
                                        'style': 'width: 6.1em',
                                        'onClick': 'approve(' + post.id + ')'
                                    })
                                    .text(approve))
                            )
                    );

        });
    });
}

function approve(id) {
    $.ajax({
        type: 'GET',
        url: '/CMS/admin/post/approve/' + id
    }).success(function (user) {
        loadPosts();
    });
}

function approveStatic(id) {
    $.ajax({
        type: 'GET',
        url: '/CMS/admin/post/approve/' + id
    }).success(function (user) {
        location.reload();
    });
}

function clearPages() {
    $('#page-list').empty();
}
function clearPosts() {
    $('#post-list').empty();
}
