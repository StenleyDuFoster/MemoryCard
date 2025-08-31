package memory.card.core.data

import android.content.Context
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.disk.DiskCache
import coil.memory.MemoryCache
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DiRemoteSingletonModule {

    companion object {
        private const val IMAGE_CACHE_DIR = "image_cache"
    }

    @Provides
    @Singleton
    fun provideChucker(
        @ApplicationContext context: Context,
    ): ChuckerInterceptor {
        val chuckerCollector = ChuckerCollector(
            context = context,
            showNotification = true,
            retentionPeriod = RetentionManager.Period.ONE_HOUR
        )

        return ChuckerInterceptor.Builder(context)
            .collector(chuckerCollector)
            .maxContentLength(250_000L)
            .alwaysReadResponseBody(true)
            .createShortcut(true)
            .build()
    }

    @Provides
    @Singleton
    fun imageLoader(
        @ApplicationContext application: Context,
        chuckerInterceptor: ChuckerInterceptor,
    ): ImageLoader =
        ImageLoader.Builder(application)
            .callFactory {
                okhttp3.OkHttpClient.Builder()
                    .addInterceptor(chuckerInterceptor)
                    .addInterceptor(Interceptor { chain ->
                        chain.proceed(
                            chain.request().newBuilder()
                                .build()
                        )
                    })
                    .build()
            }
            .memoryCache {
                MemoryCache.Builder(application)
                    .maxSizePercent(0.20)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(application.cacheDir.resolve(IMAGE_CACHE_DIR))
                    .maxSizeBytes(50 * 1024 * 1024)
                    .build()
            }
            .components { add(SvgDecoder.Factory()) }
            .respectCacheHeaders(false)
            .build()
}