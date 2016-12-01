$(document).ready(function () {
    writerPages();
    writerPosts();
});
var userEmail = $('#usr-email').val();
var pagesTable = $('#page-list');
var postTable = $('#post-list');
function writerPages() {
    clearPages();
    
    $.ajax({
        type: 'GET',
        url: '/CMS/writer/pages/' + userEmail
    }).success(function (data, status)
    {
        $.each(data, function (index, page) {

            pagesTable.append($('<tr>')
                    .append($('<td>')
                            .append($('<a>')
                                    .attr({'href': '/CMS/writer/page/edit/' + userEmail + '/' + page.id})
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
function writerPosts() {
    clearPosts();
    
    $.ajax({
        type: 'GET',
        url: '/CMS/writer/posts/recent' + userEmail
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
                            .append($('<button>')
                                    .attr({'class': 'btn btn-primary ' + queued})
                                    .text('Approve'))
                            )
                    );

        });
    });
}

function clearPages() {
    $('#pages-list').empty();
}
function clearPosts() {
    $('#posts-list').empty();
}
