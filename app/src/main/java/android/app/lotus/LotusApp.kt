package android.app.lotus

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.AppConfiguration

lateinit var app: App

inline fun <reified T> T.tag(): String = T::class.java.simpleName

@HiltAndroidApp
class LotusApp : Application() {
    override fun onCreate() {
        
        super.onCreate()
        app = App.create(
            AppConfiguration.Builder(getString(R.string.realm_app_id))
                .baseUrl(getString(R.string.realm_base_url))
                .build()
        )
        Log.v(tag(), "Initialized the App configuration for: ${app.configuration.appId}")
    }
}