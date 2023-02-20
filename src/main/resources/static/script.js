let newUserForm = document.getElementById('newForm');
let newUserRoles = document.querySelector('#roles').selectedOptions;
newUserForm.addEventListener('submit', addNewUser);
const roles = getRoles();
roles.then(
    (roles) => {
        roles.forEach( (role) => {
                document.getElementById("roles").append(new Option(role.name, role.id));
                document.getElementById("edit_role").append(new Option(role.name, role.id));
            }
        )
    }
);

async function getAll() {
    let response = await fetch('http://localhost:8080/api/admin/users');
    return await response.json();
}

async function getCurrent() {
    let response = await fetch('http://localhost:8080/api/user');
    return await response.json();
}

async function getUserById(id) {
    let response = await fetch('http://localhost:8080/api/admin/users/' + id);
    return await response.json();
}

async function getRoles() {
    let response = await fetch('http://localhost:8080/api/admin/roles');
    return await response.json();
}

function getRolesStr(roles) {
    let rolesTxt = "";
    roles.forEach(role => {
        rolesTxt += role.name + ' ';
    })
    return rolesTxt;
}

async function loadAdminPage() {
    let users = getAll();
    users.then(
        (users) => {
            let adminHTML = "";
            users.forEach(user => {
                adminHTML +=
                    `<tr>
                        <td>${user.id}</td>
                        <td>${user.login}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.age}</td>
                        <td>${user.email}</td>
                        <td>${getRolesStr(user.roles)}</td>
                        <td>
                            <button type="button" class="btn btn-primary" data-bs-toogle="modal"
                            data-bs-target="#editModal" 
                            onclick="editModalPage(${user.id})">Edit</button>
                        </td>
                        <td>
                            <button class="btn btn-danger" data-bs-toogle="modal"
                            data-bs-target="#deleteModal" 
                            onclick="deleteModalPage(${user.id})">Delete</button>
                        </td>
                </tr>`;
            })
            document.getElementById("adminBody").innerHTML = adminHTML;
        }
    )
}

async function editModalPage(id) {
    let user = (getUserById(id));
    let modal = new bootstrap.Modal(document.getElementById('editModal'));
    user.then((user) => {
        document.getElementById("edit_id").value = `${user.id}`;
        document.getElementById("edit_login").value = `${user.login}`;
        document.getElementById("edit_first_name").value = `${user.firstName}`;
        document.getElementById("edit_last_name").value = `${user.lastName}`;
        document.getElementById("edit_age").value = `${user.age}`;
        document.getElementById("edit_email").value = `${user.email}`;
    })
    modal.show();
}

async function editUser() {
    let form = document.getElementById("editForm");
    let roles = [];
    try{
        for (let i = 0; i < form.roles.options.length; i++) {
            if (form.roles.options[i].selected) {
                let tmp={id: form.roles.options[i].value};
                roles.push(tmp);
            }
        }
    let method = {
        method: 'PATCH',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            id: form.id.value,
            firstName: form.firstName.value,
            lastName: form.lastName.value,
            login: form.login.value,
            age: form.age.value,
            email: form.email.value,
            password: form.password.value,
            roles: roles
        })
    }
    await fetch('api/admin/users/' + document.getElementById("edit_id").value, method).then(() => {
        document.getElementById("editClose").click();
        loadAdminPage();
    })
    }
    catch (e) {
        alert(e);
    }
}


async function deleteModalPage(id) {
    let user = (getUserById(id));
    let modal = new bootstrap.Modal(document.getElementById('deleteModal'));
    user.then((user) => {
        document.getElementById("id_del").value = `${user.id}`;
        document.getElementById("login_del").value = `${user.login}`;
        document.getElementById("name_del").value = `${user.firstName}`;
        document.getElementById("lastname_del").value = `${user.lastName}`;
        document.getElementById("age_del").value = `${user.age}`;
        document.getElementById("email_del").value = `${user.email}`;
        document.getElementById("role_del").value = user.roles.map(r=>r.name).join(", ");
    })
    modal.show();
}

async function deleteUser() {
    let method = {
        method: 'DELETE',
        headers: {
            "Content-Type": "application/json"
        }
    }

    fetch('api/admin/users/' + document.getElementById("id_del").value, method).then(() => {
        document.getElementById("closeDelete").click();
        loadAdminPage();
    })
}

async function addNewUser(event) {
    try{
        event.preventDefault();
        let roles = [];
        for (let i = 0; i < newUserRoles.length; i++) {
            roles.push({
                id:newUserRoles[i].value
            });
        }
        let method = {
            method: 'POST',
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                login: newUserForm.login.value,
                firstName: newUserForm.firstname.value,
                lastName: newUserForm.lastname.value,
                age: newUserForm.age.value,
                email: newUserForm.exampleInputEmail1.value,
                password: newUserForm.exampleInputPassword1.value,
                roles: roles
            })
        }
        await fetch('api/admin/users',method).then(() => {
            newUserForm.reset();
            loadAdminPage();

            //далее переход сделан по примеру, но:
            //document.querySelectorAll('#myTab a') - возвращает empty - почему?

            /*
            let tabs = [].slice.call(document.querySelectorAll('#myTab a'));
            tabs.forEach(function (triggerEl) {
                let tabTrigger = new bootstrap.Tab(triggerEl);
                triggerEl.addEventListener('click', function (event) {
                    event.preventDefault()
                    tabTrigger.show()
                })
            })
            let triggerEl = document.querySelector('#myTab a[href="#home"]');
            bootstrap.Tab.getInstance(triggerEl).show();
            */
        });
    }
    catch (e) {
        alert(e);
    }

}

async function loadUserPage() {
    let current = getCurrent();
    current.then((user) => {
        document.getElementById("email").innerHTML = user.email;
        let rolesTxt = getRolesStr(user.roles);
        document.getElementById("top-roles").innerHTML = rolesTxt;
        let bodyHTML =
            `<tr>
                <td>${user.id}</td>
                <td>${user.login}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.age}</td>
                <td>${user.email}</td>
                <td>${rolesTxt}</td>
            </tr>`;
        document.getElementById("userBody").innerHTML = bodyHTML;
    })
}


