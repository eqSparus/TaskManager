export const ACTIVE = 'active'
export const DONE = 'done'
export const FROZEN = 'frozen'
export const FAILED = 'failed'

export const utilField = (type) => {

    switch (type) {
        case ACTIVE:
            return document.querySelector('#field_active')
        case DONE:
            return document.querySelector('#field_done')
        case FROZEN:
            return document.querySelector('#field_frozen')
        case FAILED:
            return document.querySelector('#field_failed')
    }

}