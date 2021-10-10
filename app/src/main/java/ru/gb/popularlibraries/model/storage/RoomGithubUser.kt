package ru.gb.popularlibraries.model.storage

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["reposUrl"], unique = true)])
data class RoomGithubUser(
    @PrimaryKey val id: Int?,
    val login: String?,
    val avatarUrl: String?,
    val reposUrl: String?
)