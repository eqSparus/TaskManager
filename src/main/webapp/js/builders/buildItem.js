import {ACTIVE, DONE, FAILED, FROZEN, utilField} from "../utilities/utilField.js";

export const buildItem = (task, type) => {

    const tr = document.createElement('tr')
    const thTitle = document.createElement('td')
    const thDescription = document.createElement('td')
    const thDateStart = document.createElement('td')
    const thDateStop = document.createElement('td')

    thTitle.className = 'text-dark fs-5'
    thTitle.style.cssText = 'word-wrap:break-word'

    thDescription.className = 'text-dark fs-5 td_text'
    thDescription.style.cssText = 'word-wrap:break-word'

    thDateStart.className = 'text-dark fs-5'
    thDateStop.className = 'text-dark fs-5'

    thTitle.textContent = task.title
    thDescription.textContent = task.description
    thDateStart.textContent = new Date(task.createAt).toLocaleDateString()
    thDateStop.textContent = new Date(task.completionAt).toLocaleDateString()

    tr.appendChild(thTitle)
    tr.appendChild(thDescription)
    tr.appendChild(thDateStart)
    tr.appendChild(thDateStop)

    switch (type) {

        case ACTIVE:
            thTitle.setAttribute("contenteditable", true)
            thTitle.setAttribute("spellcheck", false)
            thDescription.setAttribute("contenteditable", true)
            thDescription.setAttribute("spellcheck", false)
            doneButton(tr, task.id)
            updateButton(tr, task.id, thTitle, thDescription)
            frozenButton(tr, task.id)
            deleteButton(tr, task.id)
            break

        case DONE:
            deleteButton(tr, task.id)
            break

        case FROZEN:
            thTitle.setAttribute("contenteditable", true)
            thTitle.setAttribute("spellcheck", false)
            thDescription.setAttribute("contenteditable", true)
            thDescription.setAttribute("spellcheck", false)
            activeButton(tr, task.id)
            updateButton(tr, task.id, thTitle, thDescription)
            deleteButton(tr, task.id)
            break

        case FAILED:
            deleteButton(tr, task.id)
            break
    }

    return tr;
}


const activeButton = (tr, id) => {
    const thActiveTask = document.createElement('td')
    const activeButton = document.createElement('button')
    activeButton.className = 'btn btn-warning text-dark fs-5'
    activeButton.textContent = 'Active task'
    activeButton.onclick = () => {
        const urlParam = new URLSearchParams({
            "id": id,
            "status": "active"
        })
        fetch(`task/status?${urlParam}`, {
            method: "PUT"
        }).then(response => {
            if (response.ok) {
                tr.remove()
                response.json()
                    .then(value => {
                        const tableDone = document.querySelector('#field_active')
                        const itemDone = buildItem(value, ACTIVE)
                        tableDone.appendChild(itemDone)
                    })
            }
        })
    }
    thActiveTask.appendChild(activeButton)
    tr.appendChild(thActiveTask)
}

const doneButton = (tr, id) => {
    const thDoneTask = document.createElement('td')
    const doneButton = document.createElement('button')
    doneButton.className = 'btn btn-warning text-dark fs-5'
    doneButton.textContent = 'Done task'
    doneButton.onclick = () => {
        const urlParam = new URLSearchParams({
            "id": id,
            "status": "done"
        })
        fetch(`task/status?${urlParam}`, {
            method: "PUT"
        }).then(response => {
            if (response.ok) {
                tr.remove()
                response.json()
                    .then(value => {
                        const tableDone = document.querySelector('#field_done')
                        const itemDone = buildItem(value, DONE)
                        tableDone.appendChild(itemDone)
                    })
            }
        })
    }
    thDoneTask.appendChild(doneButton)
    tr.appendChild(thDoneTask)
}

const frozenButton = (tr, id) => {
    const thFrozenTask = document.createElement('td')
    const frozenButton = document.createElement('button')
    frozenButton.className = 'btn btn-info text-dark fs-5'
    frozenButton.textContent = 'Frozen task'
    frozenButton.onclick = () => {
        const urlParam = new URLSearchParams({
            "id": id,
            "status": "frozen"
        })
        fetch(`task/status?${urlParam}`, {
            method: "PUT"
        }).then(response => {
            if (response.ok) {
                tr.remove()
                response.json()
                    .then(value => {
                        const tableDone = document.querySelector('#field_frozen')
                        const itemDone = buildItem(value, FROZEN)
                        tableDone.appendChild(itemDone)
                    })
            }
        })
    }
    thFrozenTask.appendChild(frozenButton)
    tr.appendChild(thFrozenTask)
}

const deleteButton = (tr, id) => {
    const thDeleteTask = document.createElement('td')
    const deleteButton = document.createElement('button')
    deleteButton.className = 'btn btn-danger text-dark fs-5'
    deleteButton.textContent = 'Delete task'
    deleteButton.onclick = () => {
        const urlParam = new URLSearchParams({"id": id})
        fetch(`task/crud?${urlParam}`, {
            method: "DELETE"
        }).then(response => {
            if (response.ok) {
                tr.remove()
            }
        })
    }
    thDeleteTask.appendChild(deleteButton)
    tr.appendChild(thDeleteTask)
}

const updateButton = (tr, id, title, description) => {
    const thUpdateTask = document.createElement('td')
    const updateButton = document.createElement('button')
    updateButton.className = 'btn btn-primary text-dark fs-5'
    updateButton.textContent = 'Update task'
    updateButton.onclick = () => {

        const titleValue = title.textContent
        const descriptionValue = description.textContent

        const urlParam = new URLSearchParams({"id": id})

        fetch(`task/crud?${urlParam}`, {
            method: "PUT",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                "title": titleValue,
                "description": descriptionValue
            })
        }).then(response => {
            if (response.ok) {
                tr.remove()
                response.json()
                    .then(value => {
                        const status = value.status.toLowerCase()
                        const table = utilField(status)
                        const item = buildItem(value, status)
                        table.appendChild(item)
                    })
            }
        })

    }
    thUpdateTask.appendChild(updateButton)
    tr.appendChild(thUpdateTask)
}