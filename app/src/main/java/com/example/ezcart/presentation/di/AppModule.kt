package com.example.ezcart.presentation.di

import dagger.Module

@Module(
    includes = [
        ViewModelModule::class,
        NetworkModule::class,
        DataBaseModule::class
    ]
)
class AppModule