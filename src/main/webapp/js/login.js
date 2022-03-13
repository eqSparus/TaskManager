import {alertDanger, buildMessage} from "./builders/buildMessage.js";

document.querySelector("#btn_login").onclick = () => {

    const login = document.querySelector("#login").value;
    const password = document.querySelector("#password").value

    fetch("login", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            login: login,
            password: password
        })
    }).then(response => {

        if (response.ok) {
            response.json().then(value => {
                window.location.href = value.html
            })
        } else {
            response.json().then(value => {
                const messageDiv = document.querySelector('#message')
                messageDiv.appendChild(buildMessage(value.message, alertDanger))
            })
        }
    })
}