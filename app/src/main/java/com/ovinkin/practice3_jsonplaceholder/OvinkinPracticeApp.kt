package com.ovinkin.practice3_jsonplaceholder

import android.app.Application
import com.ovinkin.practice3_jsonplaceholder.di.networkModule
import com.ovinkin.practice3_jsonplaceholder.di.rootModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class OvinkinPracticeApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@OvinkinPracticeApp)
            modules(rootModule, networkModule)
        }
    }
}