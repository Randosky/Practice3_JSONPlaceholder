package com.ovinkin.practice3_jsonplaceholder.data.mapper

import com.ovinkin.practice3_jsonplaceholder.data.model.CommentResponse
import com.ovinkin.practice3_jsonplaceholder.data.model.PostResponse
import com.ovinkin.practice3_jsonplaceholder.data.model.user.UserResponse
import com.ovinkin.practice3_jsonplaceholder.domain.model.CommentEntity
import com.ovinkin.practice3_jsonplaceholder.domain.model.PostEntity
import com.ovinkin.practice3_jsonplaceholder.domain.model.user.CompanyEntity
import com.ovinkin.practice3_jsonplaceholder.domain.model.user.UserEntity
import com.ovinkin.practice3_jsonplaceholder.domain.model.user.address.AddressEntity
import com.ovinkin.practice3_jsonplaceholder.domain.model.user.address.GeoEntity

class JSONPlaceholderResponseToEntityMapper {
    fun mapPosts(response: List<PostResponse>): List<PostEntity> {
        return response.map {
            PostEntity(
                userId = it.userId ?: 0,
                id = it.id ?: 0,
                title = it.title.orEmpty(),
                body = it.body.orEmpty(),
            )
        }
    }

    fun mapCommentsByPost(response: List<CommentResponse>): List<CommentEntity> {
        return response.map {
            CommentEntity(
                postId = it.postId ?: 0,
                id = it.id ?: 0,
                name = it.name.orEmpty(),
                email = it.email.orEmpty(),
                body = it.body.orEmpty(),
            )
        }
    }

    fun mapUsers(response: List<UserResponse>): List<UserEntity> {
        return response.map { mapUser(it) }
    }

    fun mapUserById(response: UserResponse): UserEntity {
        return mapUser(response)
    }

    private fun mapUser(response: UserResponse): UserEntity {
        return UserEntity(
            id = response.id ?: 0,
            name = response.name.orEmpty(),
            userName = response.userName.orEmpty(),
            email = response.email.orEmpty(),
            address = AddressEntity(
                street = response.address?.street.orEmpty(),
                suite = response.address?.suite.orEmpty(),
                city = response.address?.city.orEmpty(),
                zipCode = response.address?.zipCode.orEmpty(),
                geo = GeoEntity(
                    lat = response.address?.geo?.lat.orEmpty(),
                    lng = response.address?.geo?.lng.orEmpty()
                )
            ),
            phone = response.phone.orEmpty(),
            website = response.website.orEmpty(),
            company = CompanyEntity(
                name = response.company?.name.orEmpty(),
                catchPhrase = response.company?.catchPhrase.orEmpty(),
                bs = response.company?.bs.orEmpty(),
            ),
        )
    }

}