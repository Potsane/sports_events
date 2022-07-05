package com.app.sportsevents.ui.playback

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.app.sportsevents.R
import com.app.sportsevents.common.MediaPlayerFactory
import com.app.sportsevents.databinding.FragmentPlaybackBinding
import com.app.sportsevents.ui.base.BaseSportsEventsFragment
import com.google.android.exoplayer2.Player
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlayBackFragment : BaseSportsEventsFragment<PlayBackViewModel, FragmentPlaybackBinding>(),
    Player.Listener {

    @Inject
    lateinit var mediaPlayerFactory: MediaPlayerFactory

    override fun createViewModel() = ViewModelProvider(this)[PlayBackViewModel::class.java]

    override fun getLayoutId() = R.layout.fragment_playback

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val videoView = binding.videoView
        videoView.player = mediaPlayerFactory.createMediaPlayer(this)
        mediaPlayerFactory.addVideo()
        mediaPlayerFactory.play()
    }

    override fun onPlaybackStateChanged(state: Int) {
        when (state) {
            Player.STATE_BUFFERING -> showProgressBar(true)
            else -> showProgressBar(false)
        }
    }

    override fun onPause() {
        super.onPause()
        mediaPlayerFactory.stop()
    }
}