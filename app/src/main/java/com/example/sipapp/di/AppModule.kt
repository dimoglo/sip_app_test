package com.example.sipapp.di

import com.example.sipapp.data.UsersRepositoryImpl
import com.example.sipapp.data.local.AddressEntity
import com.example.sipapp.data.local.CompanyEntity
import com.example.sipapp.data.local.GeoEntity
import com.example.sipapp.data.local.UserEntity
import com.example.sipapp.data.remote.ApiService
import com.example.sipapp.domain.UsersRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.types.TypedRealmObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.reflect.KClass

@Module()
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())

    @Provides
    fun provideRetrofit(): ApiService {
        val baseURL = "https://jsonplaceholder.typicode.com/"
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor()
                            .apply {
                                level = HttpLoggingInterceptor.Level.BODY
                            }
                    )
                    .build()
            )
            .build()

        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRealm(
        config: RealmConfiguration
    ): Realm {
        val realm = Realm.open(config)
        return realm
    }

    @Provides
    @Singleton
    fun provideConfig(): RealmConfiguration {
        val rawConfig = RealmConfiguration.Builder(
            schema = setOf(
                UserEntity::class,
                AddressEntity::class,
                GeoEntity::class,
                CompanyEntity::class
            ) as Set<KClass<out TypedRealmObject>>
        )
        rawConfig.name("SipApp")
        rawConfig.deleteRealmIfMigrationNeeded()
        val config = rawConfig.build()
        return config
    }

    @Module
    @InstallIn(ViewModelComponent::class)
    internal abstract class MappingModule {

        @Binds
        abstract fun bindUsersRepository(impl: UsersRepositoryImpl): UsersRepository
    }
}
