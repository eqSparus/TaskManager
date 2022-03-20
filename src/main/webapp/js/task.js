import {swapShow} from "./utilities/swapShow.js";
import {buildTable} from "./builders/buildTable.js";
import {ACTIVE, DONE, FAILED, FROZEN} from "./utilities/utilField.js";
import {buildItem} from "./builders/buildItem.js";

document.querySelector('#btn_task_done').onclick = () => {
    const taskDone = document.querySelector('#task_done')
    swapShow(taskDone)
}

document.querySelector('#btn_task_active').onclick = () => {
    const taskActive = document.querySelector('#task_active')
    swapShow(taskActive)
}

document.querySelector('#btn_task_frozen').onclick = () => {
    const taskFrozen = document.querySelector('#task_frozen')
    swapShow(taskFrozen)
}

document.querySelector('#btn_task_failed').onclick = () => {
    const taskFailed = document.querySelector('#task_failed')
    swapShow(taskFailed)
}

document.querySelector('#btn_add').onclick = () => {
    const title = document.querySelector('#title').value,
        description = document.querySelector('#description').value,
        createAt = Date.parse(document.querySelector('#create_at').value),
        completionAt = Date.parse(document.querySelector('#completion_at').value)

    if (title !== ""
        && description !== ""
        && !isNaN(createAt)
        && !isNaN(completionAt)) {

        if (completionAt > createAt) {

            fetch("task/crud", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    title: title,
                    description: description,
                    createAt: createAt,
                    completionAt: completionAt
                })
            }).then(response => {
                if (response.status === 201) {
                    response.json()
                        .then(value => {
                            const tableActive = document.querySelector('#field_active')
                            const itemActive = buildItem(value,ACTIVE)
                            tableActive.appendChild(itemActive)
                        })
                }
            })

        } else {
            const errorMessage = document.querySelector('#message_error')
            errorMessage.textContent = "The end date of the task must be greater than the start date"
        }

    } else {
        const errorMessage = document.querySelector('#message_error')
        errorMessage.textContent = "Fill in all the fields"
    }
}

document.querySelector('body').onload = () => {

    fetch('task/crud', {
        method: "GET"
    }).then(response => {
        if (response.ok) {
            response.json()
                .then(value => {

                    const activeTasks = Array.from(value).filter(task => task.status === 'ACTIVE')
                    const doneTasks = Array.from(value).filter(task => task.status === 'DONE')
                    const frozenTasks = Array.from(value).filter(task => task.status === 'FROZEN')
                    const failedTasks = Array.from(value).filter(task => task.status === 'FAILED')

                    buildTable(activeTasks, ACTIVE)
                    buildTable(doneTasks, DONE)
                    buildTable(frozenTasks, FROZEN)
                    buildTable(failedTasks, FAILED)
                })
        }
    })


}