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
        url: '/CMS/admin/users'
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
                            .append($('<a>')
                                    .attr({
                                        'onClick': 'editUser(' + user.id + ')'
                                    })
                                    .text(user.email)
                                    )
                            )
                    .append($('<td>')
                            .text(user.displayName)
                            )
                    .append($('<td>')
                            .text(user.authority)
                            )
                    .append($('<td>')
                            .append($('<a>')
                                    .attr({
                                        'class': "align-right glyphicon glyphicon-remove pointer",
                                        'style': "color : red; float: right",
                                        'onclick': "deleteUser(" + user.id + ")"
                                    }))
                            )
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

    $('#user-email').val('');
    $('#user-display-name').val('');
    $('#user-authority').val('');
    $('#user-avatar-url').val('');
    $('#user-password').val('');
}

function loadAddUser() {
    clearFields();
    $('#add-edit-title').append("Add User");
}

function doAddUser() {
    event.preventDefault();
    $('#validationErrors').empty();
    $('#user-password').attr({
    });

    $.ajax({
        type: 'POST',
        url: '/CMS/admin/user',
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
    $.ajax({
        type: 'GET',
        url: '/CMS/admin/user/' + id
    }).success(function (user) {
        clearFields();
        $('#add-edit-title').append("Edit User");
        $('#user-btn').replaceWith('<button onclick="doEditUser(' + id + ');" id="user-btn" class="btn btn-primary">Update</button>');
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
            url: '/CMS/admin/user/' + id
        }).success(function () {
            loadUsers();
        });
    }
}