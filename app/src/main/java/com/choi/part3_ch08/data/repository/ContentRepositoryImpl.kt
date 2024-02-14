package com.choi.part3_ch08.data.repository

import com.choi.part3_ch08.data.model.ContentMapper.toContent
import com.choi.part3_ch08.data.model.ContentMapper.toEntity
import com.choi.part3_ch08.data.model.ContentMapper.toRequest
import com.choi.part3_ch08.data.source.local.dao.ContentDao
import com.choi.part3_ch08.data.source.remote.api.ContentService
import com.choi.part3_ch08.domain.model.Content
import com.choi.part3_ch08.domain.repository.ContentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(
    private val contentService: ContentService,
    private val contentDao: ContentDao
) : ContentRepository {


    override suspend fun save(item: Content): Boolean {
        // 네트 워크가 없을 때 예외처리를 하기 위해
        return try {
            contentService.saveItem(item.toRequest())
            contentDao.insert(item.toEntity())
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun update(item: Content): Boolean {
        return try {
            contentService.updateItem(item.toRequest())
            contentDao.insert(item.toEntity())
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun loadList(): Flow<List<Content>> {
        return flow {
//            contentDao.selectAll().collect { list ->
//                emit(list.map { it.toContent() })
//            }
            emit(
                try {
                    contentService.getList().data.map { it.toContent() }
                } catch (e: Exception) {
                    emptyList()
                }
            )
        }
    }

    override suspend fun delete(item: Content): Boolean {
        return try {
            item?.id?.let {id->
                contentService.deleteItem(id)
            }
            contentDao.delete(item.toEntity())
            true
        } catch (e:Exception) {
            false
        }
    }
}