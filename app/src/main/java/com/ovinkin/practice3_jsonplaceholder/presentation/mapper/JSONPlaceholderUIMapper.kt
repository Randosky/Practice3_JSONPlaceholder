package com.ovinkin.practice3_jsonplaceholder.presentation.mapper

import com.ovinkin.practice3_jsonplaceholder.domain.model.CommentEntity
import com.ovinkin.practice3_jsonplaceholder.domain.model.PostEntity
import com.ovinkin.practice3_jsonplaceholder.domain.model.user.UserEntity
import com.ovinkin.practice3_jsonplaceholder.presentation.model.CommentUiModel
import com.ovinkin.practice3_jsonplaceholder.presentation.model.PostUiModel
import com.ovinkin.practice3_jsonplaceholder.presentation.model.user.CompanyUiModel
import com.ovinkin.practice3_jsonplaceholder.presentation.model.user.UserUiModel
import com.ovinkin.practice3_jsonplaceholder.presentation.model.user.address.AddressUiModel
import com.ovinkin.practice3_jsonplaceholder.presentation.model.user.address.GeoUiModel

class JSONPlaceholderUIMapper {
    fun mapPosts(list: List<PostEntity>): List<PostUiModel> {
        return list.map {
            PostUiModel(
                userId = it.userId,
                id = it.id,
                title = it.title,
                body = it.body,
            )
        }
    }

    fun mapCommentsByPost(list: List<CommentEntity>): List<CommentUiModel> {
        return list.map {
            CommentUiModel(
                postId = it.postId,
                id = it.id,
                name = it.name,
                email = it.email,
                body = it.body,
            )
        }
    }

    fun mapUserById(user: UserEntity): UserUiModel {
        return UserUiModel(
            id = user.id,
            name = user.name,
            userName = user.userName,
            email = user.email,
            address = AddressUiModel(
                street = user.address.street,
                suite = user.address.suite,
                city = user.address.city,
                zipCode = user.address.zipCode,
                geo = GeoUiModel(
                    lat = user.address.geo.lat,
                    lng = user.address.geo.lng
                )
            ),
            phone = user.phone,
            website = user.website,
            company = CompanyUiModel(
                name = user.company.name,
                catchPhrase = user.company.catchPhrase,
                bs = user.company.bs,
            ),
        )
    }
}