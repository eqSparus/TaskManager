import {ACTIVE, DONE, FROZEN} from "./buildTable.js";

export const buildItem = (task, type) => {

    const tr = document.createElement('tr')
    const thTitle = document.createElement('th')
    const thDescription = document.createElement('th')
    const thDateStart = document.createElement('th')
    const thDateStop = document.createElement('th')

    thTitle.className = 'text-dark fs-5'
    thDescription.className = 'text-dark fs-5'
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

        case 'ACTIVE':
            doneButton(tr, task.id)
            updateButton(tr)
            frozenButton(tr, task.id)
            deleteButton(tr, task.id)
            break

        case 'DONE':
            deleteButton(tr, task.id)
            break

        case 'FROZEN':
            activeButton(tr, task.id)
            updateButton(tr)
            deleteButton(tr, task.id)
            break

        case 'FAILED':
            deleteButton(tr, task.id)
            break
    }

    return tr;
}


const activeButton = (tr, id) => {
    const thActiveTask = document.createElement('th')
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
    const thDoneTask = document.createElement('th')
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
    const thFrozenTask = document.createElement('th')
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
    const thDeleteTask = document.createElement('th')
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

const updateButton = (tr) => {
    const thUpdateTask = document.createElement('th')
    const updateButton = document.createElement('button')
    updateButton.className = 'btn btn-primary text-dark fs-5'
    updateButton.textContent = 'Update task'
    updateButton.onclick = () => {

    }
    thUpdateTask.appendChild(updateButton)
    tr.appendChild(thUpdateTask)
}