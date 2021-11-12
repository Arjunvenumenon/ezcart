package com.example.ezcart.data.model

data class ApiResponseState (
    var apiStatus: ApiStatus
)

enum class ApiStatus{
    READY, FINISHED, PROGRESS, ERROR, DATA_SAVED
}