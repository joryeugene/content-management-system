$(document).ready(function () {
    loadPage();
});

var userTable = $('#user-list');

function loadPage() {
    loadUsers();
    loadAddUser();
}

function loadUsers() {
    clearUsers();
    $.ajax({
        type: 'GET',
        url: 'users'
    }).success(function (data, status) {
        $.each(data, function (index, user) {
            userTable.append($('<tr>')
                    .append($('<td>')
                            .append($('<a>')
                                    .attr({
                                        'onClick': 'editUser(' + user.id + ')'
                                    })
                                    .text(user.id)
                                    )
                            )
                    .append($('<td>')
                            .text(user.email)
                            )
                    .append($('<td>')
                            .text(user.displayName)
                            )
                    .append($('<td>')
                            .text(user.authority)
                            )
                    .append($('<td>')
                            .append($('<i>')
                                    .attr({
                                        'class': "align-right btn btn-default btn-sm glyphicon glyphicon-remove",
                                        'style': "color : red; float: right",
                                        'onclick': "deleteUser(" + user.id + ")"
                                    })))
                    );
        });
    });
}

function clearUsers() {
    $('#user-list').empty();
}

function clearFields() {
    $('#user-id').empty();
    $('#add-edit-title').empty();
    $('#user-button').empty();

    $('#user-email').val('');
    $('#user-display-name').val('');
    $('#user-authority').val('');
    $('#user-avatar-url').val('');
    $('#user-password').val('');
}

function loadAddUser() {
    clearFields();
    $('#add-edit-title').append("Add User");
    $('#user-button').append($('<a>')
            .attr({
                'onClick': 'doAddUser()'
            })
            .text("Add User"));
}

function doAddUser() {
    event.preventDefault();
    $('#validationErrors').empty();
    $('#user-password').attr({
        
    });

    $.ajax({
        type: 'POST',
        url: 'user',
        // Build a JSON object from the data in the form
        data: JSON.stringify({
            email: $('#user-email').val(),
            displayName: $('#user-display-name').val(),
            authority: $('#user-authority').val(),
            avatarUrl: $('#user-avatar-url').val(),
            password: $('#user-password').val()
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (data, status) {
        // If the call succeeds, clear the form and reload the summary table
        loadPage();
    }).error(function (data, status) {
        // #2 - Go through each of the fieldErrors and display the associated error
        // message in the validationErrors div
        $.each(data.responseJSON.fieldErrors, function (index, validationError) {
            var errorDiv = $('#validationErrors');
            errorDiv.append(validationError.message).append($('<br>'));
        });
    });
}

function editUser(id) {
    clearFields();
    $('#user-id').append(id);
    $('#add-edit-title').append("Edit User");
    $.ajax({
        type: 'GET',
        url: 'user/' + id
    }).success(function (user) {
        $('#user-button').append($('<a>')
                .attr({
                    'onClick': 'doEditUser(' + id + ')'
                })
                .text("Update User"));
        $('#user-email').val(user.email);
        $('#user-display-name').val(user.displayName);
        $('#user-authority').val(user.authority);
        $('#user-avatar-url').val(user.avatarUrl);
        $('#user-password').val(user.password);
    })
}

function doEditUser(id) {
    $.ajax({
            type: 'PUT',
            url: 'user/' + id,
            data: JSON.stringify({
                userId: id,
                email: $('#user-email').val(),
                displayName: $('#user-display-name').val(),
                authority: $('#user-authority').val(),
                avatarUrl: $('#user-avatar-url').val(),
                password: $('#user-password').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function () {
            loadPage();
        });
}

function deleteUser(id) {
    var answer = confirm("Do you really want to delete this User?");
    if (answer === true) {
        $.ajax({
            type: 'DELETE',
            url: 'user/' + id
        }).success(function () {
            loadUsers();
        });
    }
}