package com.nicetohave.songbook.core.exception

import kotlin.reflect.KClass

interface IFromException {
    fun getFactories(message: String): HashMap<KClass<Exception>, () -> Failure> {
        return hashMapOf()
    }

    fun fromException(exception: Exception): Failure {
        val message = exception.message.orEmpty()

        val factories = getFactories(message)

        val factory = factories[exception::class] ?: throw exception

        return factory()
    }
}
