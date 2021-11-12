package com.example.ezcart.data.model

import java.io.Serializable

data class ScreenState (

    val headerLogoState: Int,
    val navButtonState: Int,
    val titleState: Int,
    val backButtonState: Int,
    val cartCountState: Int,
    val titleValue: String

): Serializable