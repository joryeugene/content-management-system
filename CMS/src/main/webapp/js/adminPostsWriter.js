$(document).ready(function () {
    loadPosts();
});

var postTable = $('#post-list');

//load posts
function loadPosts() {
    clearPosts();

    $.ajax({
        type: 'GET',
        url: '/CMS/admin/user/allposts/100'
    }).success(function (data, status)
    {
        $.each(data, function (index, post) {
            var approved;
            var category;
             if(post.queued === false){
                 approved = "Yes";
             } else {
                 approved = "Awaiting Approval";
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
                            .text(post.title)
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
                            .text(approved)
                            )
                    .append($('<td>')
                            .append($('<p style="float: right;">')
                                    .append($('<a>').attr({
                                        'onClick': 'deletePost(' + post.id + ')'
                                    })
                                            .append('<span style="color:red; cursor:pointer"  class="glyphicon glyphicon-remove pointer" id="delete-btn" aria-hidden="true"></span><br>')
                                            ) // end <a>
                                    )
                            )
                    );

        });
    });
}

function approve(id) {
    $.ajax({
        type: 'POST',
        url: '/CMS/admin/post/approve/' + id
    }).success(function (user) {
        loadPosts();
    });
}

function deletePost(id) {
    var answer = confirm("Do you really want to delete this post?");
    if (answer === true) {
        $.ajax({
            type: 'DELETE',
            url: '/CMS/admin/post/delete/' + id
        }).success(function () {
            loadPosts();
        });
    }
}

function clearPosts() {
    postTable.empty();
}
