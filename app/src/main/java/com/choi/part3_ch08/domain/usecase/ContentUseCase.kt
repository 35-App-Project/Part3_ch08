package com.choi.part3_ch08.domain.usecase

import com.choi.part3_ch08.domain.model.Content
import com.choi.part3_ch08.domain.repository.ContentRepository
import javax.inject.Inject

class ContentUseCase @Inject constructor(
    private val contentRepository: ContentRepository
) {
    suspend fun save(item: Content) = contentRepository.save(item)

    fun loadList() = contentRepository.loadList()

    suspend fun delete(item :Content) = contentRepository.delete(item)
}