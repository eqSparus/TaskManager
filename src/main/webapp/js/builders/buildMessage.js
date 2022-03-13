export const alertWarning = 'alert-warning'
export const alertDanger = 'alert-danger'


export const buildMessage = (message, color) => {
    const messageDiv = document.querySelector("#message > div")
    if (messageDiv !== null) {
        messageDiv.remove()
    }

    const div = document.createElement('div')

    div.className = 'alert ' + color.toString()
    div.textContent = message.toString()

    return div
}
