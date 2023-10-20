package android.app.lotus.di

import android.app.lotus.data.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AuthServiceModule {

    @Provides
    fun provideAuthService(): AuthService {
        return AuthService()
    }
}
