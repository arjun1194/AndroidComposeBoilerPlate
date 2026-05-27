package com.example.androidcomposeboilerplate.data.mappers

import com.example.androidcomposeboilerplate.domain.models.ExifDetails
import com.example.androidcomposeboilerplate.domain.models.LocationDetails
import com.example.androidcomposeboilerplate.domain.models.Photo
import com.example.androidcomposeboilerplate.domain.models.PhotoLinks
import com.example.androidcomposeboilerplate.domain.models.PhotoUrls
import com.example.androidcomposeboilerplate.domain.models.PositionCoordinates
import com.example.androidcomposeboilerplate.domain.models.ProfileImage
import com.example.androidcomposeboilerplate.domain.models.SearchResponse
import com.example.androidcomposeboilerplate.domain.models.User
import com.example.androidcomposeboilerplate.domain.models.UserLinks
import com.example.androidcomposeboilerplate.domain.models.Collection

import com.example.androidcomposeboilerplate.data.remote.ExifDetails as RemoteExifDetails
import com.example.androidcomposeboilerplate.data.remote.LocationDetails as RemoteLocationDetails
import com.example.androidcomposeboilerplate.data.remote.PhotoLinks as RemotePhotoLinks
import com.example.androidcomposeboilerplate.data.remote.PhotoUrls as RemotePhotoUrls
import com.example.androidcomposeboilerplate.data.remote.PositionCoordinates as RemotePositionCoordinates
import com.example.androidcomposeboilerplate.data.remote.ProfileImage as RemoteProfileImage
import com.example.androidcomposeboilerplate.data.remote.UnsplashCollection as RemoteUnsplashCollection
import com.example.androidcomposeboilerplate.data.remote.UnsplashPhoto as RemoteUnsplashPhoto
import com.example.androidcomposeboilerplate.data.remote.UnsplashSearchResponse as RemoteUnsplashSearchResponse
import com.example.androidcomposeboilerplate.data.remote.UnsplashUser as RemoteUnsplashUser
import com.example.androidcomposeboilerplate.data.remote.UserLinks as RemoteUserLinks


/**
 * Maps a remote photo data transfer object to its corresponding domain model.
 */
fun RemoteUnsplashPhoto.toDomain(): Photo = Photo(
    id = id,
    createdAt = createdAt,
    updatedAt = updatedAt,
    width = width,
    height = height,
    color = color,
    blurHash = blurHash,
    description = description,
    altDescription = altDescription,
    urls = urls.toDomain(),
    links = links.toDomain(),
    likes = likes,
    likedByUser = likedByUser,
    user = user.toDomain(),
    exif = exif?.toDomain(),
    location = location?.toDomain()
)

fun RemotePhotoUrls.toDomain(): PhotoUrls = PhotoUrls(
    raw = raw,
    full = full,
    regular = regular,
    small = small,
    thumb = thumb,
    smallS3 = smallS3
)

fun RemotePhotoLinks.toDomain(): PhotoLinks = PhotoLinks(
    self = self,
    html = html,
    download = download,
    downloadLocation = downloadLocation
)

fun RemoteUnsplashUser.toDomain(): User = User(
    id = id,
    username = username,
    name = name,
    portfolioUrl = portfolioUrl,
    bio = bio,
    location = location,
    totalLikes = totalLikes,
    totalPhotos = totalPhotos,
    totalCollections = totalCollections,
    profileImage = profileImage.toDomain(),
    links = links.toDomain()
)

fun RemoteProfileImage.toDomain(): ProfileImage = ProfileImage(
    small = small,
    medium = medium,
    large = large
)

fun RemoteUserLinks.toDomain(): UserLinks = UserLinks(
    self = self,
    html = html,
    photos = photos,
    likes = likes,
    portfolio = portfolio
)

fun RemoteExifDetails.toDomain(): ExifDetails = ExifDetails(
    make = make,
    model = model,
    exposureTime = exposureTime,
    aperture = aperture,
    focalLength = focalLength,
    iso = iso
)

fun RemoteLocationDetails.toDomain(): LocationDetails = LocationDetails(
    city = city,
    country = country,
    position = position?.toDomain()
)

fun RemotePositionCoordinates.toDomain(): PositionCoordinates = PositionCoordinates(
    latitude = latitude,
    longitude = longitude
)

/**
 * Maps a remote search payload structure to its domain equivalent.
 */
fun RemoteUnsplashSearchResponse.toDomain(): SearchResponse = SearchResponse(
    total = total,
    totalPages = totalPages,
    results = results.map { it.toDomain() }
)

/**
 * Maps a remote collection structural grouping model to its clean domain layer representation.
 */
fun RemoteUnsplashCollection.toDomain(): Collection = Collection(
    id = id,
    title = title,
    description = description,
    publishedAt = publishedAt,
    lastCollectedAt = lastCollectedAt,
    updatedAt = updatedAt,
    totalPhotos = totalPhotos,
    isPrivate = isPrivate,
    shareKey = shareKey,
    coverPhoto = coverPhoto?.toDomain(),
    user = user?.toDomain()
)