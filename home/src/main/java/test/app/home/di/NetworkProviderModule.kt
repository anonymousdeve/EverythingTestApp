package test.app.home.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import test.app.home.model.service.ArticleApiService
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkProviderModule {


    @Provides
    @Singleton
    fun provideApiService(@Named("normalRetrofit") retrofit: Retrofit): ArticleApiService =
        retrofit.create(ArticleApiService::class.java)


}