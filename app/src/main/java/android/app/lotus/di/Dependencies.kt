package android.app.lotus.di

import android.app.lotus.data.UserService
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserServiceModule {

    @Provides
    @Singleton
    fun provideUserService(): UserService {
        return UserService()
    }
}


@InstallIn(SingletonComponent::class)
@Module
object PreferencesModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext appContext: Context): SharedPreferences {
        return appContext.getSharedPreferences("lotus_app_preferences", Context.MODE_PRIVATE)
    }
}
