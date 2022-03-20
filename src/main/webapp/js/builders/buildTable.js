import {buildItem} from "./buildItem.js";

export const ACTIVE = 'ACTIVE'
export const DONE = 'DONE'
export const FROZEN = 'FROZEN'
export const FAILED = 'FAILED'

export const buildTable = (tasks, type) => {


    let table
    switch (type) {
        case ACTIVE:
            table = document.querySelector('#field_active')
            break
        case DONE:
            table = document.querySelector('#field_done')
            break
        case FROZEN:
            table = document.querySelector('#field_frozen')
            break
        case FAILED:
            table = document.querySelector('#field_failed')
            break
    }

    tasks.forEach(task => {
        const item = buildItem(task, type)
        table.appendChild(item)
    })

}