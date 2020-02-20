package android.com.githubtask.di

import android.com.githubtask.data.network.Api
import android.com.githubtask.data.network.NetworkManager
import android.com.githubtask.data.network.NetworkManagerImpl
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkModule {

    companion object {

        private const val BASE_URL = "https://api.github.com/"
        private const val TOKEN = "token"
        private const val AUTH_TOKEN = "fc7adb04dfff85115f02701fb3fb9129e1051e8b"

        private const val READ_TIMEOUT = 100L
        private const val CONNECT_TIMEOUT = 120L

        /**HEADERS*/
        private const val AUTHORIZATION = "Authorization"

        /**POINTS*/
        const val SEARCH = "search/repositories"


        val networkModule = module {

            single {
                OkHttpClient.Builder()
                    .addInterceptor(get<Interceptor>(named(TOKEN)))
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .build()
            }

            single(named(TOKEN)) {
                Interceptor { chain ->
                    val newRequest = chain.request().newBuilder()
                        .addHeader(
                            AUTHORIZATION,
                            "$TOKEN $AUTH_TOKEN"
                        )
                        .build()
                    chain.proceed(newRequest)
                }
            }

            single {
                Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(get())
                    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))
                    .build()
            }

            single {
                (get() as Retrofit).create(Api::class.java)
            }

            single<NetworkManager> {
                NetworkManagerImpl(get())
            }
        }
    }
}