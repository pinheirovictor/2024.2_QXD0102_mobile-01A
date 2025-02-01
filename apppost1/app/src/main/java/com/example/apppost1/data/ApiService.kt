package com.example.apppost1.data

import com.example.apppost1.data.models.CreatePostRequest
import com.example.apppost1.data.models.Post
import com.example.apppost1.data.models.User
import com.example.apppost1.data.models.UserCreateRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path




interface ApiService {

    @POST("users/")
    suspend fun createUser(
        @Body user: UserCreateRequest
    ): User

    @GET("users/")
    suspend fun getUsers(): List<User>

    @POST("users/{user_id}/posts/")
    suspend fun createPost(
        @Path("user_id") userId: Int,
        @Body post: CreatePostRequest
    ): Post

    @GET("users/{user_id}/posts")
    suspend fun getPosts(
        @Path("user_id") userId: Int
    ): List<Post>

    @PUT("posts/{post_id}")
    suspend fun updatePost(
        @Path("post_id") postId: Int,
        @Body post: CreatePostRequest
    ): Post

    @DELETE("posts/{post_id}")
    suspend fun deletePost(@Path("post_id") postId: Int): Unit


}