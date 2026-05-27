package com.example.androidcomposeboilerplate.data.repository

import com.example.androidcomposeboilerplate.data.mappers.toDomain
import com.example.androidcomposeboilerplate.data.remote.UnsplashApiService
import com.example.androidcomposeboilerplate.domain.models.Photo
import com.example.androidcomposeboilerplate.domain.models.Collection
import com.example.androidcomposeboilerplate.domain.models.SearchResponse
import com.example.androidcomposeboilerplate.domain.models.User
import com.example.androidcomposeboilerplate.domain.repository.UnsplashRepository
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class UnsplashRepositoryImpl @Inject constructor(
    private val apiService: UnsplashApiService,
) : UnsplashRepository {

    override suspend fun getPhotos(
        page: Int, perPage: Int, orderBy: String
    ): Result<List<Photo>> = safeApiCall(
        apiCall = { apiService.listPhotos(page, perPage, orderBy) },
        transform = { dtoList -> dtoList.map { it.toDomain() } })

    override suspend fun getPhotoById(id: String): Result<Photo> =
        safeApiCall(apiCall = { apiService.getPhoto(id) }, transform = { it.toDomain() })

    override suspend fun getRandomPhotos(
        collections: String?,
        topics: String?,
        username: String?,
        query: String?,
        orientation: String?,
        contentFilter: String,
        count: Int?
    ): Result<List<Photo>> = safeApiCall(apiCall = {
        apiService.getRandomPhotos(
            collections = collections,
            topics = topics,
            username = username,
            query = query,
            orientation = orientation,
            contentFilter = contentFilter,
            count = count
        )
    }, transform = { dtoList -> dtoList.map { it.toDomain() } })

    override suspend fun searchPhotos(
        query: String,
        page: Int,
        perPage: Int,
        orderBy: String,
        collections: String?,
        contentFilter: String,
        color: String?,
        orientation: String?
    ): Result<SearchResponse> = safeApiCall(apiCall = {
        apiService.searchPhotos(
            query = query,
            page = page,
            perPage = perPage,
            orderBy = orderBy,
            collections = collections,
            contentFilter = contentFilter,
            color = color,
            orientation = orientation
        )
    }, transform = { it.toDomain() })

    override suspend fun getUserProfile(username: String): Result<User> =
        safeApiCall(
            apiCall = { apiService.getUserProfile(username) },
            transform = { it.toDomain() })

    override suspend fun getUserPhotos(
        username: String, page: Int, perPage: Int, orderBy: String
    ): Result<List<Photo>> = safeApiCall(
        apiCall = { apiService.getUserPhotos(username, page, perPage, orderBy) },
        transform = { dtoList -> dtoList.map { it.toDomain() } })

    override suspend fun getCollections(
        page: Int, perPage: Int
    ): Result<List<Collection>> = safeApiCall(
        apiCall = { apiService.listCollections(page, perPage) },
        transform = { dtoList -> dtoList.map { it.toDomain() } })

    override suspend fun getCollectionById(id: String): Result<Collection> =
        safeApiCall(apiCall = { apiService.getCollection(id) }, transform = { it.toDomain() })

    override suspend fun getCollectionPhotos(
        id: String, page: Int, perPage: Int, orientation: String?
    ): Result<List<Photo>> = safeApiCall(
        apiCall = { apiService.getCollectionPhotos(id, page, perPage, orientation) },
        transform = { dtoList -> dtoList.map { it.toDomain() } })

    /**
     * Inline helper function to wrap Retrofit network calls safely, map the remote DTO [T]
     * to a target Domain module [R], and wrap it into a Kotlin Result framework wrapper.
     */
    private inline fun <T, R> safeApiCall(
        apiCall: () -> Response<T>, transform: (T) -> R
    ): Result<R> {
        return try {
            val response = apiCall()
            val body = response.body()

            if (response.isSuccessful && body != null) {
                // Map the remote network object to the pure domain variant before returning success
                Result.success(transform(body))
            } else {
                Result.failure(Exception("API Error: ${response.code()} - ${response.message()}"))
            }
        } catch (e: IOException) {
            Result.failure(Exception("Network failure. Please check your connection.", e))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}