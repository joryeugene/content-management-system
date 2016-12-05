$(document).ready(function () {
    loadPosts();
});

var postTable = $('#post-list');

//load posts
function loadPosts() {
    $.ajax({
        type: 'GET',
        url: '/CMS/admin/user/allposts/1000'
    }).success(function (data, status) {

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


            postTable.append($('<tr>').attr({'id': 'row-' + post.id})

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
                            .append($('<button>')
                                    .attr({
                                        'class': 'btn btn-primary ' + queued,
                                        'onClick': 'approve(' + post.id + ')'
                                    })
                                    .text(approve)
                                    )

                            .append($('<p class="text-right" style="float: right;">')
                                    .append($('<a>')
                                            .attr({'onClick': 'deletePost(' + post.id + ')'})
                                            .append('<span style="color:red; cursor:pointer"  class="glyphicon glyphicon-remove pointer" id="delete-btn" aria-hidden="true"></span><br>')
                                            ) // end <a>
                                    ) // end <p>
                            ) // end <td>
                    );
        });

        $('#posts-table').DataTable({
            "order": [[4, "desc"]]
        });
    });
}

function approve(id) {
    $.ajax({
        type: 'GET',
        url: '/CMS/admin/post/approve/' + id
    }).success(function (user) {
        var trToApprove = "#row-" + id + " button";
        $(trToApprove).addClass("disabled");
        $(trToApprove).text("Approved");
    });
}

function deletePost(id) {
    var answer = confirm("Do you really want to delete this post?");
    if (answer === true) {
        $.ajax({
            type: 'DELETE',
            url: '/CMS/admin/post/delete/' + id
        }).success(function () {
            var trToRemove = "#row-" + id;
            $(trToRemove).remove();
        });
    }
}
