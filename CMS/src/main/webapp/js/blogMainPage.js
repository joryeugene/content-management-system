$(document).ready(function () {
    loadMainPagePosts();
});

function loadMainPagePosts() {
    $.ajax({
        type: 'GET',
        url: "/CMS/posts/recent"
    }).success(function (data, status) {
        console.log(data); // TESTING
        populateMainPagePost(data);
    });
}

function populateMainPagePost(data) {
    var mainPagePostsDiv = $('#main-page-posts');
    mainPagePostsDiv.empty();

    $.each(data, function (index, post) {
        if (post.content.length > 231) {
            content = post.content.substring(0, 231) + '...';
        } else {
            content = post.content
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
}