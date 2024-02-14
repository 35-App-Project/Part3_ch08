package com.choi.part3_ch08.domain.repository

import com.choi.part3_ch08.domain.model.Content
import kotlinx.coroutines.flow.Flow

interface ContentRepository {

    suspend fun save(item: Content) : Boolean

    suspend fun update(item : Content) : Boolean

    fun loadList() : Flow<List<Content>>

    suspend fun delete(item :Content) : Boolean
}