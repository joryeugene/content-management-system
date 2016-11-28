$(document).ready(function () {
    loadMainPagePosts();
});

function loadMainPagePosts() {
    $.ajax({
        type: 'GET',
        url: "/CMS/posts/recent"
    }).success(function (data, status) {
        var moreLink = true;
        populateMainPagePost(data, moreLink);
    });
}

function populateMainPagePost(data, moreLink) {
    var mainPagePostsDiv = $('#main-page-posts');
    mainPagePostsDiv.empty();

    $.each(data, function (index, post) {
        var content = stripHtmlTags(post.content);

        if (content.length > 231) {
            content = content.substring(0, 231) + '...';
        }

        mainPagePostsDiv.append($('<div>')
                .attr({
                    'class': 'col-xs-6 col-lg-4'
                }).append($('<h2>').text(post.title)
                ).append($('<p>').text(content)

                ).append($('<p>')
                .append($('<a>')
                        .attr({
                            'class': 'btn btn-default',
                            'href': '/CMS/post/' + post.id,
                            'role': 'button'
                        }).text('More')
                        ) // ends the <a>
                ) // ends the 2nd <p>
                ); // ends the <div> 

        if ((index + 1) % 3 === 0) {
            mainPagePostsDiv.append($('<div class="clearfix visible-lg-block"></div>'));
        }
        if ((index + 1) % 2 === 0) {
            mainPagePostsDiv.append($('<div class="clearfix visible-xs-block visible-sm-block visible-md-block"></div>'));
        }
    });

//    if (moreLink) {
//        mainPagePostsDiv.append($('<p style="float:right;">')
//                .append($('<span style="cursor: pointer">')
//                        .attr({
//                            'id': 'get-more-posts'
//                        })
//                        .text('More...')
//                        )
//                );
//    }
}

//
//$('#get-more-posts').click(function (event) {
//    console.log("1");
//    $.ajax({
//        type: 'GET',
//        url: "/CMS/posts"
//    }).success(function (data, status) {
//        var moreLink = false;
//        populateMainPagePost(data, moreLink);
//    });
//});

var stripHtmlTags = function (string) {
    return string.replace(/(<([^>]+)>)/ig, "")
}

