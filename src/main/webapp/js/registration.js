import {alertDanger, alertWarning, buildMessage} from "./builders/buildMessage.js";

document.querySelector('#btn_reg').onclick = (e) => {

    const login = document.querySelector('#login').value
    const password = document.querySelector('#password').value
    const repeatPassword = document.querySelector('#repeat_password').value

    const regLogin = /\w{1,50}/
    const regPass = /[a-zA-Z0-9]{8,50}/

    if (password.toString().match(regPass) && login.toString().match(regLogin)) {

        if (password === repeatPassword) {
            fetch("reg", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    login: login,
                    password: password
                })
            }).then(response => {

                if (response.ok) {
                    response.json().then(value => window.location.href = value.html)
                } else {
                    response.json().then(value => {
                        const alert = buildMessage(value.message, alertDanger)
                        document.querySelector('#message').appendChild(alert)
                    })
                }
            })
        } else {
            const alert = buildMessage("Passwords do not match", alertWarning)
            document.querySelector('#message').appendChild(alert)
        }


    } else {
        const alert = buildMessage('All fields must be filled in and the password must be 8 characters or more',
            alertWarning)
        document.querySelector('#message').appendChild(alert)
    }

}