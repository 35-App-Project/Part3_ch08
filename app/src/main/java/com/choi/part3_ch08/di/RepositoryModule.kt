package com.choi.part3_ch08.di

import com.choi.part3_ch08.data.model.dto.ContentDto
import com.choi.part3_ch08.data.repository.ContentRepositoryImpl
import com.choi.part3_ch08.data.source.local.dao.ContentDao
import com.choi.part3_ch08.data.source.remote.api.ContentService
import com.choi.part3_ch08.domain.repository.ContentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun providesContentRepository(
        contentService: ContentService,
        contentDao: ContentDao
    ) : ContentRepository = ContentRepositoryImpl(contentService,contentDao)

}