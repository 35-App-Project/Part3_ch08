package com.choi.part3_ch08.data.source.remote.api

import com.choi.part3_ch08.data.model.dto.ContentDto
import com.choi.part3_ch08.data.model.dto.ContentResponse
import com.choi.part3_ch08.data.model.dto.ListResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ContentService {

    @GET("list")
    suspend fun getList() : ListResponse

    @POST("save")
    suspend fun saveItem(@Body contentDto: ContentDto) : ContentResponse

    @POST("update")
    suspend fun updateItem(@Body contentDto: ContentDto) : ContentResponse

    @DELETE("{id}")
    suspend fun deleteItem(@Path("id") id:Int) : ContentResponse

}