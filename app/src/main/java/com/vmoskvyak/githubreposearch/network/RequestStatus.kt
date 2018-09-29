package com.vmoskvyak.githubreposearch.network

class RequestStatus(val status: Status, val message: String?) {

    enum class Status(val message: String) {
        SUCCESS(""),
        ERROR(""),
        LOADING("")
    }

}
