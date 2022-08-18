package com.nicetohave.songbook.features.songbook.extra

import com.nicetohave.songbook.core.exception.Failure
import com.nicetohave.songbook.core.exception.IFromException


sealed class SongBookFeatureFailure(
    message: String = "SongBookFeature failure"
) : Failure.FeatureFailure(
    message = message
)   {
    companion object : IFromException {

    }
}


