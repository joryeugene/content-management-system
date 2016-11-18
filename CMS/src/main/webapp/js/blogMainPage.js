$(document).ready(function () {
    loadMainPagePosts();
});

function loadMainPagePosts() {
    $.ajax({
        type: 'GET',
        url: "/CMS/posts"
    }).success(function (data, status) {
        console.log(data);
        populateMainPagePost(data);

    });
}

function populateMainPagePost(data) {
    var mainPagePostsDiv = $('#main-page-posts');
    mainPagePostsDiv.empty();

    // need to reverse order and limit to 6 and add a button at bottom of page for MORE posts

    $.each(data, function (index, post) {

        mainPagePostsDiv.append($('<div>')
                .attr({
                    'class': 'col-xs-6 col-lg-4'
                }).append($('<h2>').text(post.title)

                ).append($('<p>').text(post.content)

                ).append($('<p>')
                .append($('<a>')
                        .attr({
                            'class': 'btn btn-default',
                            'href': '/CMS/post/' + post.id,
                            'role': 'button'
                        }).text('View More')
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

//
//.append($('<p id="edit-delete-container">')
//                .append($('<a>').attr({
//                    'onClick': 'deleteDVD(' + dvd.id + ')'
//        })
//                .append('<span class="glyphicon glyphicon-remove" id="delete-btn" aria-hidden="true"></span><br>')
//                ) // end <a>
//                .append($('<a>').attr({
//                    'data-dvd-id': dvd.id,
//            'data-toggle': 'modal',
//            'data-target': '#edit-modal'
//        })
//                .append('<span class="glyphicon glyphicon-cog" id="edit-btn" aria-hidden="true"></span>')
//                ) // end <a>
//                ) // end <p>
//                .append($('<a style="cursor:pointer;">')
//                .attr({
//                    'data-dvd-id': dvd.id,
//            'data-toggle': 'modal',
//            'data-target': '#details-modal'
//        }).append($('<div>')
//                .attr({
//                    'class': 'dvd-inner-tile'
//        })
//                .append($('<img>')
//                .attr({
//                    'class': 'dvd-image img-responsive',
//            'id': imgDiv,
//            'src': dvd.image
//        })
//                ) // ends the <img>
//                )// ends the <div> 
//                .append($('<p>').attr({
//                    'class': 'dvd-title'
//        })
//                .text(dvd.title)
//                ) // ends <p>
//                ) // ends the <a> tag