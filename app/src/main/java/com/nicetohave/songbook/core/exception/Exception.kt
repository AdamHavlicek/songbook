package com.nicetohave.songbook.core.exception

sealed class BaseException(
    override val message: String
) : Exception(
    message
)

class ServerException(
    message: String = "Unexpected server exception"
) : BaseException(
    message = message
)

class NetworkException(
    message: String = "Unexpected network exception"
) : BaseException(
    message = message
)

class CancelledException(
    message: String
) : BaseException (
    message = message
)

class LocalStorageException(
    message: String
) : BaseException (
    message = message
)