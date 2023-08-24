const adminurl = '/api/admin';

const currentUser = fetch(adminurl).then(response => response.json())
currentUser.then(user => {
        let roles = ''
        user.roles.forEach(role => {
            roles += ' '
            roles += role.rolename
        })
        document.getElementById("navbar-email").innerHTML = user.email
        document.getElementById("navbar-roles").innerHTML = roles
    }
)

async function getAdminPage() {
    let page = await fetch(adminurl);

    if(page.ok) {
        let listAllUser = await  page.json();
        loadTableData(listAllUser);
    } else {
        alert(`Error, ${page.status}`)
    }
}
function loadTableData(listAllUser) {
    const tableBody = document.getElementById('admintbody');
    let dataHtml = '';
    for (let user of listAllUser) {
        let roles = [];
        for (let role of user.roles) {
            roles.push(" " + role.rolename.toString()
                .replaceAll("ROLE_", ""))
        }
        dataHtml +=
            `<tr>
    <td>${user.id}</td>
    <td>${user.firstName}</td>
    <td>${user.lastName}</td>
    <td>${user.age}</td>
    <td>${user.email}</td>
    <td>${roles}</td>
    <td>
        <button type="button" class="btn btn-primary" data-bs-toogle="modal"
        data-bs-target="#editModal" 
        onclick="loadDataForEditModal(${user.id})">Edit</button>
    </td>
        
    <td>
        <button class="btn btn-danger" data-bs-toogle="modal"
        data-bs-target="#deleteModal" 
        onclick="deleteModalData(${user.id})">Delete</button>
    </td>
   
</tr>`
    }
    tableBody.innerHTML = dataHtml;
}

async function getUsPage() {
    let page = await fetch(userurl);

    if(page.ok) {
        let user = await  page.json();
        getInfoUser(user);
    } else {
        alert(`Error, ${page.status}`)
    }
}
function  getInfoUser(user) {
    const tableBody = document.getElementById('usertbody');
    let dataHtml = '';
    let roles = [];
    console.log('userSata', JSON.stringify(user))
    for (let role of user.roles) {roles
        roles.push(" " + role.rolename.toString()
            .replaceAll("ROLE_", ""))
    }
    dataHtml =
        `<tr>
    <td>${user.id}</td>
    <td>${user.firstName}</td>
    <td>${user.lastName}</td>
    <td>${user.age}</td>
    <td>${user.email}</td>
    <td>${roles}</td>   
</tr>`

    tableBody.innerHTML = dataHtml;
}

getAdminPage();
getUsPage();

function addNewUser(event) {
    event.preventDefault();

    const form = document.getElementById('formForNewUser');
    const selectedRoles = document.querySelector('#roles').selectedOptions;

    const user = {
        firstName: form.firstname.value,
        lastName: form.lastname.value,
        age: form.age.value,
        email: form.email.value,
        password: form.password.value,
        roles: Array.from(selectedRoles).map(option => ({ id: option.value }))
    };

    const url = 'api/admin/newAddUser';

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    }).then(response => {
        if (response.ok) {
            // User added successfully
            form.reset();
            getAdminPage(); // Refresh the user table
            const userTableTabButton = document.querySelector('button[data-bs-target="#user_table"]');
            if (userTableTabButton) {
                userTableTabButton.click();
            } else {
                console.error('Не удалось найти ссылку на вкладку "user_table".');
            }
        } else {
            // Error adding user
            alert('Failed to add user. Please try again.');
        }
    }).catch(error => {
        console.error('Error adding user:', error);
        alert('An error occurred while adding the user. Please try again.');
    });
}


const form_new = document.getElementById('formForNewUser');
form_new.addEventListener('submit', addNewUser);

const id_del = document.getElementById('id_del');
const name_del = document.getElementById('name_del');
const lastname_del = document.getElementById('lastname_del');
const age_del = document.getElementById('age_del');
const email_del = document.getElementById('email_del');
const role_del = document.getElementById("delete-role")
const deleteModal = document.getElementById("deleteModal");
const closeDeleteButton = document.getElementById("closeDelete")
const bsDeleteModal = new bootstrap.Modal(deleteModal);

async function deleteModalData(id) {
    const  urlForDel = 'api/admin/' + id;
    let usersPageDel = await fetch(urlForDel);
    if (usersPageDel.ok) {
        let userData =
            await usersPageDel.json().then(user => {
                id_del.value = `${user.id}`;
                name_del.value = `${user.firstName}`;
                lastname_del.value = `${user.lastName}`;
                age_del.value = `${user.age}`;
                email_del.value = `${user.email}`;
                role_del.value = user.roles.map(r=>r.rolename).join(", ");
            })

        bsDeleteModal.show();
    } else {
        alert(`Error, ${usersPageDel.status}`)
    }
}
async function deleteUser() {
    let urlDel = 'api/admin/' + id_del.value;
    let method = {
        method: 'DELETE',
        headers: {
            "Content-Type": "application/json"
        }
    }

    fetch(urlDel, method).then(() => {
        closeDeleteButton.click();
        getAdminPage();
    })
}


const form_ed = document.getElementById('formForEditing');
const id_ed = document.getElementById('edit-id');
const name_ed = document.getElementById('edit-first_name');
const lastname_ed = document.getElementById('edit-last_name');
const age_ed = document.getElementById('edit-age');
const email_ed = document.getElementById('edit-email');
const editModal = document.getElementById("editModal");
const closeEditButton = document.getElementById("editClose")
const bsEditModal = new bootstrap.Modal(editModal);

async function loadDataForEditModal(id) {
    const  urlDataEd = 'api/admin/' + id;
    let usersPageEd = await fetch(urlDataEd);
    if (usersPageEd.ok) {
        // let userData =
        await usersPageEd.json().then(user => {
            console.log('userData', JSON.stringify(user))
            id_ed.value = `${user.id}`;
            name_ed.value = `${user.firstName}`;
            lastname_ed.value = `${user.lastName}`;
            age_ed.value = `${user.age}`;
            email_ed.value = `${user.email}`;
        })
        console.log("id_ed: " + id_ed.value + " !!")
        bsEditModal.show();
    } else {
        alert(`Error, ${usersPageEd.status}`)
    }
}
async function editUser() {
    let urlEdit = 'api/admin/' + id_ed.value;
    let listOfRole = [];
    console.dir(form_ed)
    for (let i=0; i<form_ed.roles.options.length; i++) {
        if (form_ed.roles.options[i].selected) {
            let tmp={};
            tmp["id"]=form_ed.roles.options[i].value
            listOfRole.push(tmp);
        }
    }
    let method = {
        method: 'PATCH',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            firstName: form_ed.firstName.value,
            lastName: form_ed.lastName.value,
            age: form_ed.age.value,
            email: form_ed.email.value,
            password: form_ed.password.value,
            roles: listOfRole
        })
    }
    console.log(urlEdit,method)
    await fetch(urlEdit,method).then(() => {
        closeEditButton.click();
        getAdminPage();
    })
}

