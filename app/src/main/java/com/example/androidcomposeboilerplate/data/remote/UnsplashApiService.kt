package com.example.androidcomposeboilerplate.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashApiService {

    companion object {
        const val BASE_URL = "https://api.unsplash.com/"
    }

    @Headers("Accept-Version: v1")
    @GET("photos")
    suspend fun listPhotos(
        @Query("page") page: Int? = 1,
        @Query("per_page") perPage: Int? = 10,
        @Query("order_by") orderBy: String? = "latest"
    ): Response<List<UnsplashPhoto>>

    @Headers("Accept-Version: v1")
    @GET("photos/{id}")
    suspend fun getPhoto(
        @Path("id") id: String
    ): Response<UnsplashPhoto>

    @Headers("Accept-Version: v1")
    @GET("photos/random")
    suspend fun getRandomPhotos(
        @Query("collections") collections: String? = null,
        @Query("topics") topics: String? = null,
        @Query("username") username: String? = null,
        @Query("query") query: String? = null,
        @Query("orientation") orientation: String? = null,
        @Query("content_filter") contentFilter: String? = "low",
        @Query("count") count: Int? = null
    ): Response<List<UnsplashPhoto>>


    @Headers("Accept-Version: v1")
    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int? = 1,
        @Query("per_page") perPage: Int? = 10,
        @Query("order_by") orderBy: String? = "relevant",
        @Query("collections") collections: String? = null,
        @Query("content_filter") contentFilter: String? = "low",
        @Query("color") color: String? = null,
        @Query("orientation") orientation: String? = null
    ): Response<UnsplashSearchResponse>



    @Headers("Accept-Version: v1")
    @GET("users/{username}")
    suspend fun getUserProfile(
        @Path("username") username: String
    ): Response<UnsplashUser>

    @Headers("Accept-Version: v1")
    @GET("users/{username}/photos")
    suspend fun getUserPhotos(
        @Path("username") username: String,
        @Query("page") page: Int? = 1,
        @Query("per_page") perPage: Int? = 10,
        @Query("order_by") orderBy: String? = "latest"
    ): Response<List<UnsplashPhoto>>



    @Headers("Accept-Version: v1")
    @GET("collections")
    suspend fun listCollections(
        @Query("page") page: Int? = 1,
        @Query("per_page") perPage: Int? = 10
    ): Response<List<UnsplashCollection>>

    @Headers("Accept-Version: v1")
    @GET("collections/{id}")
    suspend fun getCollection(
        @Path("id") id: String
    ): Response<UnsplashCollection>

    @Headers("Accept-Version: v1")
    @GET("collections/{id}/photos")
    suspend fun getCollectionPhotos(
        @Path("id") id: String,
        @Query("page") page: Int? = 1,
        @Query("per_page") perPage: Int? = 10,
        @Query("orientation") orientation: String? = null
    ): Response<List<UnsplashPhoto>>
}
