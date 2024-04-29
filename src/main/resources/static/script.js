const apiUrl = '/items';


async function getItems() {
    try {
        let response = await fetch(apiUrl);
        return await response.json();
    } catch (error) {
        console.log(error);
    }
}


async function addItem() {
    let newItemName = document.getElementById("new_item_name").value;

    try {
        let response = await fetch(apiUrl, {
            method: 'POST', headers: {
                'Content-Type': 'application/json'
            },
            //JSON.stringify - преобразует обьект в json
            body: JSON.stringify({name: newItemName})
        });

        if (!response.ok) {
            throw new Error(`Failed to add item with name=${newItemName}`);
        }

        getItems().then(data => {
            displayList(data);
        });

    } catch (error) {
        console.log(error);
    }
}

async function deleteItem(id) {
    try {
        let response = await fetch(apiUrl + '/' + encodeURIComponent(id), {method: 'DELETE'});

        if (!response.ok) {
            throw new Error(`Failed to delete item with id=${id}`);
        }

        getItems().then(data => {
            displayList(data);
        });

    } catch (error) {
        console.log(error);
    }
}

async function editItem(id) {
    try {
        //encodeURIComponent(id) - кодирует специальные символы в URL-адресе
        let response = await fetch(apiUrl + '/' + encodeURIComponent(id), {method: 'PUT'});
        if (!response.ok) {
            throw new Error(`Failed to mark item with id=${id}`);
        }

        getItems().then(data => {
            displayList(data);
        });

    } catch (error) {
        console.log(error);
    }
}

function displayList(products) {
    let list = document.getElementById("list");
    list.innerHTML = "";

    for (let i = 0; i < products.length; i++) {
        let product = products[i];

        let li = document.createElement("li");
        li.appendChild(document.createTextNode(product.name));

        let deleteButton = document.createElement("button");
        deleteButton.classList.add('button', 'button-delete');
        deleteButton.appendChild(document.createTextNode("Удалить"));
        deleteButton.onclick = function () {
            deleteItem(product.id).then(r => r);
        };
        li.appendChild(deleteButton);

        let checkbox = document.createElement("input");
        checkbox.type = "checkbox";

        checkbox.checked = product.isChecked;
        checkbox.onclick = function () {
            editItem(product.id).then(r => r);
        };
        li.insertBefore(checkbox, li.firstChild);

        list.appendChild(li);
    }
}

getItems().then(data => {
    displayList(data);
});