package com.app.sportsevents.network.entity

data class SportEvent(
    var id: String,
    var title: String,
    var subtitle: String,
    var date: String,
    var imageUrl: String,
    var videoUrl: String
)