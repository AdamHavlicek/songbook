package com.nicetohave.songbook.core.exception


sealed class Failure(
    val message: String
) {
    abstract class FeatureFailure(
        message: String = "Feature failure"
    ) : Failure(
        message = message
    )

    override fun toString(): String {
        return "${this::class}: $message"
    }

    companion object : IFromException
}

class NetworkConnectionFailure(
    message: String = "Network Connection Failed"
) : Failure(message)

class ServerFailure(
    message: String = "Unexpected server failure"
) : Failure(message)

class DeviceOfflineFailure(
    message: String = "Device is offline"
) : Failure(message)

class LocalStorageFailure(
    message: String
) : Failure(message)

class ActionCancelledFailure(
    message: String
) : Failure(message)