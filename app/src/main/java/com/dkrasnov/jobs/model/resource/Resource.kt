package com.dkrasnov.jobs.model.resource

class Resource<T>(val status: Status, val data: T?, val error: Throwable?) {

    companion object {
        fun <K> success(data: K) = Resource(Status.SUCCESS, data, null)

        fun <K> error(error: Throwable, data: K? = null) = Resource(Status.ERROR, data, error)

        fun <K> loading(data: K?) = Resource(Status.LOADING, data, null)
    }

    override fun toString(): String {
        return "Resource status: $status data: $data error: $error"
    }
}