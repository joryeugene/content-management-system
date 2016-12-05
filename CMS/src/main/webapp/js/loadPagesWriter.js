$(document).ready(function () {
    $("#th-to-remove").remove();
    loadPages();
    loadPosts();
});
var pagesTable = $('#page-list');
var postTable = $('#post-list');
function loadPages() {
    clearPages();
    $.ajax({
        type: 'GET',
        url: '/CMS/admin/user/pages/6'
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
                            .text(page.title)
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
        url: '/CMS/admin/user/allposts/6'
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
             else category = post.category.name;
            

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
                    );

        });
    });
}

function clearPages() {
    $('#page-list').empty();
}
function clearPosts() {
    $('#post-list').empty();
}
