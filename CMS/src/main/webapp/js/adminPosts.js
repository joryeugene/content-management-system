$(document).ready(function () {
    loadPosts();
});

var postTable = $('#post-list');

//load posts
function loadPosts() {
    clearPosts();
    
    $.ajax({
        type: 'GET',
        url: '/CMS/posts'
    }).success(function (data, status)
    {
        $.each(data, function (index, post) {
            var queued;
            var category;
             if(post.queued === false){
                 queued = "disabled";
             } else {
                 queued = "";
             }
             if (post.category === null)
                 category = "";
             else category = post.category.name;
            

            postTable.append($('<tr>')
                    .append($('<td>')
                            .append($('<a>')
                                    .attr({'href': '/edit/post/' + post.id})
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
                                    .attr({'class': 'btn btn-primary ' + queued})
                                    .text('Approve'))
                            )
                    );

        });
    });
}

function clearPosts() {
    postTable.empty();
}
