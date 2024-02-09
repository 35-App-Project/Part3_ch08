package com.choi.part3_ch08.domain.model

import java.io.Serializable
import java.util.Date

// Clean Architecture를 위해 domain 레이어에서 data 레이어를 알면 안된다
data class Content(
    val id: Int? = null,
    val title: String,
    val content: String,
    val category: String,
    val createdDate: Date = Date(),
    val likeCount: Int = 0,
    val commentCount: Int = 0,
    val viewCount: Int = 0,
    ) : Serializable
