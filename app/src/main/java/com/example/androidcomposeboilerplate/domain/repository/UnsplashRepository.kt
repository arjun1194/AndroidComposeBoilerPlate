package com.example.androidcomposeboilerplate.domain.repository

import com.example.androidcomposeboilerplate.domain.models.Photo
import com.example.androidcomposeboilerplate.domain.models.SearchResponse
import com.example.androidcomposeboilerplate.domain.models.User
import com.example.androidcomposeboilerplate.domain.models.Collection as Collection

/**
 * Repository interface defining data operations for interacting with the Unsplash API.
 * Encapsulates photo discovery, structural searches, user profiles, and curated collections.
 */
interface UnsplashRepository {

    /**
     * Get a single page from the editorial feed of photos.
     *
     * @param page Page number to retrieve. Default: 1.
     * @param perPage Number of items per page. Default: 10. Max: 30.
     * @param orderBy How to sort the photos. Valid values: "latest", "oldest", "popular". Default: "latest".
     * @return A [Result] containing a [List] of [Photo]s.
     */
    suspend fun getPhotos(
        page: Int,
        perPage: Int,
        orderBy: String
    ): Result<List<Photo>>

    /**
     * Retrieve a single photo by its unique identifier.
     *
     * @param id The unique identifier of the photo.
     * @return A [Result] containing the requested [Photo] object with complete metadata (exif, location, etc.).
     */
    suspend fun getPhotoById(
        id: String
    ): Result<Photo>

    /**
     * Retrieve a collection of random photos based on specified parameters.
     * Useful for placeholder content, ambient backdrops, or quick discovery features.
     *
     * @param collections Public collection ID(s) to narrow down the pool of random images (comma-separated).
     * @param topics Public topic ID(s) to narrow down the pool of random images (comma-separated).
     * @param username Limit selection to a specific photographer's profile.
     * @param query Limit selection to photos matching specific search keywords.
     * @param orientation Filter by image aspect ratio. Valid values: "landscape", "portrait", "squarish".
     * @param contentFilter Safety level filter for content. Valid values: "low", "high". Default: "low".
     * @param count The number of random photos to return. Valid values: 1 to 30.
     * @return A [Result] containing a [List] of randomly selected [Photo]s.
     */
    suspend fun getRandomPhotos(
        collections: String? = null,
        topics: String? = null,
        username: String? = null,
        query: String? = null,
        orientation: String? = null,
        contentFilter: String = "low",
        count: Int? = null
    ): Result<List<Photo>>

    /**
     * Search across the entire Unsplash photo catalog matching a specific search phrase.
     *
     * @param query The search keywords or phrases.
     * @param page Page number to retrieve. Default: 1.
     * @param perPage Number of items per page. Default: 10. Max: 30.
     * @param orderBy How to sort results. Valid values: "relevant", "latest". Default: "relevant".
     * @param collections Public collection ID(s) to narrow down the pool of results (comma-separated).
     * @param contentFilter Safety level filter for content. Valid values: "low", "high". Default: "low".
     * @param color Filter results by a dominant color tone. Valid values: "black_and_white", "black",
     * "white", "yellow", "orange", "red", "purple", "magenta", "green", "teal", "blue".
     * @param orientation Filter by aspect ratio. Valid values: "landscape", "portrait", "squarish".
     * @return A [Result] containing a [SearchResponse] with paginated totals and matching [Photo]s.
     */
    suspend fun searchPhotos(
        query: String,
        page: Int,
        perPage: Int,
        orderBy: String = "relevant",
        collections: String? = null,
        contentFilter: String = "low",
        color: String? = null,
        orientation: String? = null
    ): Result<SearchResponse>

    /**
     * Retrieve public profile metadata for a specific Unsplash user.
     *
     * @param username The unique username of the user.
     * @return A [Result] containing the [User]'s details, including biography, location, and social links.
     */
    suspend fun getUserProfile(
        username: String
    ): Result<User>

    /**
     * Get a paginated list of photos uploaded by a specific user.
     *
     * @param username The unique username of the photographer.
     * @param page Page number to retrieve. Default: 1.
     * @param perPage Number of items per page. Default: 10. Max: 30.
     * @param orderBy How to sort the photos. Valid values: "latest", "oldest", "popular", "views", "downloads". Default: "latest".
     * @return A [Result] containing a [List] of [Photo]s created by the user.
     */
    suspend fun getUserPhotos(
        username: String,
        page: Int,
        perPage: Int,
        orderBy: String
    ): Result<List<Photo>>

    /**
     * Get a single page from the global public collections registry on Unsplash.
     *
     * @param page Page number to retrieve. Default: 1.
     * @param perPage Number of items per page. Default: 10. Max: 30.
     * @return A [Result] containing a [List] of curated or featured [Collection1] structural modules.
     */
    suspend fun getCollections(
        page: Int,
        perPage: Int
    ): Result<List<Collection>>

    /**
     * Retrieve metadata information for a single collection.
     *
     * @param id The unique identifier of the collection.
     * @return A [Result] containing the requested [Collection1] data model.
     */
    suspend fun getCollectionById(
        id: String
    ): Result<Collection>

    /**
     * Get a paginated list of photos contained within a single specified collection.
     *
     * @param id The unique identifier of the collection.
     * @param page Page number to retrieve. Default: 1.
     * @param perPage Number of items per page. Default: 10. Max: 30.
     * @param orientation Filter photos by aspect ratio. Valid values: "landscape", "portrait", "squarish".
     * @return A [Result] containing a [List] of [Photo] mappings stored inside the collection.
     */
    suspend fun getCollectionPhotos(
        id: String,
        page: Int,
        perPage: Int,
        orientation: String? = null
    ): Result<List<Photo>>
}