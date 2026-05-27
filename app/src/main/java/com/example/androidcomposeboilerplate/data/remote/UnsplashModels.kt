package com.example.androidcomposeboilerplate.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UnsplashPhoto(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "created_at") val createdAt: String?,
    @field:Json(name = "updated_at") val updatedAt: String?,
    @field:Json(name = "width") val width: Int,
    @field:Json(name = "height") val height: Int,
    @field:Json(name = "color") val color: String?,
    @field:Json(name = "blur_hash") val blurHash: String?,
    @field:Json(name = "description") val description: String?,
    @field:Json(name = "alt_description") val altDescription: String?,
    @field:Json(name = "urls") val urls: PhotoUrls,
    @field:Json(name = "links") val links: PhotoLinks,
    @field:Json(name = "likes") val likes: Int,
    @field:Json(name = "liked_by_user") val likedByUser: Boolean,
    @field:Json(name = "user") val user: UnsplashUser,
    @field:Json(name = "exif") val exif: ExifDetails?,
    @field:Json(name = "location") val location: LocationDetails?
)

@JsonClass(generateAdapter = true)
data class PhotoUrls(
    @field:Json(name = "raw") val raw: String,
    @field:Json(name = "full") val full: String,
    @field:Json(name = "regular") val regular: String,
    @field:Json(name = "small") val small: String,
    @field:Json(name = "thumb") val thumb: String,
    @field:Json(name = "small_s3") val smallS3: String?
)

@JsonClass(generateAdapter = true)
data class PhotoLinks(
    @field:Json(name = "self") val self: String,
    @field:Json(name = "html") val html: String,
    @field:Json(name = "download") val download: String,
    @field:Json(name = "download_location") val downloadLocation: String
)

@JsonClass(generateAdapter = true)
data class UnsplashUser(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "username") val username: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "portfolio_url") val portfolioUrl: String?,
    @field:Json(name = "bio") val bio: String?,
    @field:Json(name = "location") val location: String?,
    @field:Json(name = "total_likes") val totalLikes: Int,
    @field:Json(name = "total_photos") val totalPhotos: Int,
    @field:Json(name = "total_collections") val totalCollections: Int,
    @field:Json(name = "profile_image") val profileImage: ProfileImage,
    @field:Json(name = "links") val links: UserLinks
)

@JsonClass(generateAdapter = true)
data class ProfileImage(
    @field:Json(name = "small") val small: String,
    @field:Json(name = "medium") val medium: String,
    @field:Json(name = "large") val large: String
)

@JsonClass(generateAdapter = true)
data class UserLinks(
    @field:Json(name = "self") val self: String,
    @field:Json(name = "html") val html: String,
    @field:Json(name = "photos") val photos: String,
    @field:Json(name = "likes") val likes: String,
    @field:Json(name = "portfolio") val portfolio: String
)

@JsonClass(generateAdapter = true)
data class ExifDetails(
    @field:Json(name = "make") val make: String?,
    @field:Json(name = "model") val model: String?,
    @field:Json(name = "exposure_time") val exposureTime: String?,
    @field:Json(name = "aperture") val aperture: String?,
    @field:Json(name = "focal_length") val focalLength: String?,
    @field:Json(name = "iso") val iso: Int?
)

@JsonClass(generateAdapter = true)
data class LocationDetails(
    @field:Json(name = "city") val city: String?,
    @field:Json(name = "country") val country: String?,
    @field:Json(name = "position") val position: PositionCoordinates?
)

@JsonClass(generateAdapter = true)
data class PositionCoordinates(
    @field:Json(name = "latitude") val latitude: Double?,
    @field:Json(name = "longitude") val longitude: Double?
)

// Wrapper object returned from the search endpoints
@JsonClass(generateAdapter = true)
data class UnsplashSearchResponse(
    @field:Json(name = "total") val total: Int,
    @field:Json(name = "total_pages") val totalPages: Int,
    @field:Json(name = "results") val results: List<UnsplashPhoto>
)

@JsonClass(generateAdapter = true)
data class UnsplashCollection(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "description") val description: String?,
    @field:Json(name = "published_at") val publishedAt: String?,
    @field:Json(name = "last_collected_at") val lastCollectedAt: String?,
    @field:Json(name = "updated_at") val updatedAt: String?,
    @field:Json(name = "total_photos") val totalPhotos: Int,
    @field:Json(name = "private") val isPrivate: Boolean,
    @field:Json(name = "share_key") val shareKey: String?,
    @field:Json(name = "cover_photo") val coverPhoto: UnsplashPhoto?,
    @field:Json(name = "user") val user: UnsplashUser?
)

