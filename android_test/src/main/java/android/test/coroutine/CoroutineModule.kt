package android.test.coroutine

import com.example.commonlibrary.fake_endpoint.FakeEndpoint
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule {
    @Provides
    @Singleton
    fun provideFakeEndpoint(): FakeEndpoint {
        return FakeEndpoint
    }
}