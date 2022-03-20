export const swapShow = (task) => {
    const taskShow = document.querySelector('#block_task > div[class="mx-5 mt-3 fs-3 show"]')

    if (taskShow !== task) {
        task.classList.remove("none")
        task.classList.add("show")
        taskShow.classList.remove('show')
        taskShow.classList.add('none')
    }
}