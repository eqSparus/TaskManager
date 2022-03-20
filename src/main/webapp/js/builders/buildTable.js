import {buildItem} from "./buildItem.js";
import {utilField} from "../utilities/utilField.js";



export const buildTable = (tasks, type) => {
    const table = utilField(type)

    tasks.forEach(task => {
        const item = buildItem(task, type)
        table.appendChild(item)
    })

}