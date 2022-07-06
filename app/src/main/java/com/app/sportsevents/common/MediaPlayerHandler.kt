package com.app.sportsevents.common

import android.content.Context
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import dagger.hilt.android.qualifiers.ApplicationContext

interface MediaPlayerHandler {
    fun createMediaPlayer(listener: Player.Listener): ExoPlayer
    fun addVideo()
    fun play()
    fun stop()
    fun getProgress(): Long
    fun progressTo(time: Long)
}

class MediaPlayerHandlerImpl(
    @ApplicationContext private val context: Context
) : MediaPlayerHandler {

    private lateinit var player: ExoPlayer

    override fun createMediaPlayer(listener: Player.Listener): ExoPlayer {
        player = ExoPlayer.Builder(context).build()
        player.addListener(listener)
        return player
    }

    override fun addVideo() {
        val url =
            "https://firebasestorage.googleapis.com/v0/b/dazn-recruitment/o/promo.mp4?alt=media"
        val video = MediaItem.fromUri(url)
        player.addMediaItems(listOf(video))
        player.prepare()
    }

    override fun play() = player.play()

    override fun stop() = player.release()

    override fun getProgress() = player.currentPosition

    override fun progressTo(time: Long) = player.seekTo(0, time)
}