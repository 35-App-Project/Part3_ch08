package com.choi.part3_ch08.data.model

import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import com.choi.part3_ch08.data.model.dto.ContentDto
import com.choi.part3_ch08.data.model.entity.ContentEntity
import com.choi.part3_ch08.domain.model.Content
import java.util.Date

object ContentMapper {
    // ContentDto 로 반환
    fun Content.toRequest() = ContentDto(
        id = id,
        title = title,
        content = content,
        category = category,
        likeCount = likeCount,
        commentCount = commentCount,
        viewCount = viewCount,
    )

    fun ContentDto.toContent() = Content(
        id = id ?: -1,
        title = title,
        content = content,
        category = category,
        likeCount = likeCount ?: 0,
        viewCount = viewCount ?: 0,
        commentCount = commentCount ?: 0,
        createdDate = createdDate ?: Date()
    )

    fun Content.toEntity() = ContentEntity(
        id = id ?: -1,
        title = title,
        content = content,
        category = category,
        likeCount = likeCount,
        commentCount = commentCount,
        viewCount = viewCount,
        createdDate = createdDate ?: Date()
    )

    fun ContentEntity.toContent() = Content(
        id=id,
        title=title,
        content=content,
        category=category,
        viewCount = viewCount,
        commentCount = commentCount,
        likeCount = likeCount,
        createdDate = createdDate
    )
}