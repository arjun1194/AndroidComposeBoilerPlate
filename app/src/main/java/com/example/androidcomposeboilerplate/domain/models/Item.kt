package com.example.androidcomposeboilerplate.domain.models

data class Photo(
    val id: String,
    val createdAt: String?,
    val updatedAt: String?,
    val width: Int,
    val height: Int,
    val color: String?,
    val blurHash: String?,
    val description: String?,
    val altDescription: String?,
    val urls: PhotoUrls,
    val links: PhotoLinks,
    val likes: Int,
    val likedByUser: Boolean,
    val user: User,
    val exif: ExifDetails?,
    val location: LocationDetails?
)

data class PhotoUrls(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String,
    val smallS3: String?
)

data class PhotoLinks(
    val self: String,
    val html: String,
    val download: String,
    val downloadLocation: String
)

data class User(
    val id: String,
    val username: String,
    val name: String,
    val portfolioUrl: String?,
    val bio: String?,
    val location: String?,
    val totalLikes: Int,
    val totalPhotos: Int,
    val totalCollections: Int,
    val profileImage: ProfileImage,
    val links: UserLinks
)

data class ProfileImage(
    val small: String,
    val medium: String,
    val large: String
)

data class UserLinks(
    val self: String,
    val html: String,
    val photos: String,
    val likes: String,
    val portfolio: String
)

data class ExifDetails(
    val make: String?,
    val model: String?,
    val exposureTime: String?,
    val aperture: String?,
    val focalLength: String?,
    val iso: Int?
)

data class LocationDetails(
    val city: String?,
    val country: String?,
    val position: PositionCoordinates?
)

data class PositionCoordinates(
    val latitude: Double?,
    val longitude: Double?
)

data class SearchResponse(
    val total: Int,
    val totalPages: Int,
    val results: List<Photo>
)

data class Collection(
    val id: String,
    val title: String,
    val description: String?,
    val publishedAt: String?,
    val lastCollectedAt: String?,
    val updatedAt: String?,
    val totalPhotos: Int,
    val isPrivate: Boolean,
    val shareKey: String?,
    val coverPhoto: Photo?,
    val user: User?
)
