package com.app.sportsevents.ui.playback

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.app.sportsevents.R
import com.app.sportsevents.common.MediaPlayerHandler
import com.app.sportsevents.databinding.FragmentPlaybackBinding
import com.app.sportsevents.ui.base.BaseSportsEventsFragment
import com.google.android.exoplayer2.Player
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlayBackFragment : BaseSportsEventsFragment<PlayBackViewModel, FragmentPlaybackBinding>(),
    Player.Listener {

    @Inject
    lateinit var mediaPlayerHandler: MediaPlayerHandler

    override fun createViewModel() = ViewModelProvider(this)[PlayBackViewModel::class.java]

    override fun getLayoutId() = R.layout.fragment_playback

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong("VideoProgress", mediaPlayerHandler.getProgress())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val videoView = binding.videoView
        videoView.player = mediaPlayerHandler.createMediaPlayer(this)
        mediaPlayerHandler.addVideo()
        if (savedInstanceState != null) {
            mediaPlayerHandler.progressTo(savedInstanceState.getLong("VideoProgress"))
        }
        mediaPlayerHandler.play()
    }

    override fun onPlaybackStateChanged(state: Int) {
        if (isAdded) {
            when (state) {
                Player.STATE_BUFFERING -> showProgressBar(true)
                else -> showProgressBar(false)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        mediaPlayerHandler.stop()
    }
}