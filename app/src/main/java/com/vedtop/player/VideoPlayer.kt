package com.vedtop.player

import android.content.Context
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource

/**
 * مشغل فيديو عام: يشغّل ملفات محلية من الجهاز أو روابط فيديو مباشرة
 * يوفرها المستخدم بنفسه (مثل رابط ملف mp4 يملكه أو محتوى مرخّص له).
 * لا يقوم هذا الصف بأي استخراج أو تحميل من أي منصة خارجية.
 */
class VideoPlayer(context: Context) {

    private val exoPlayer: ExoPlayer = ExoPlayer.Builder(context).build()
    private val dataSourceFactory = DefaultHttpDataSource.Factory()

    fun playFromUrl(url: String) {
        val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(MediaItem.fromUri(url))

        exoPlayer.setMediaSource(mediaSource)
        exoPlayer.prepare()
        exoPlayer.play()
    }

    fun playFromLocalFile(uri: android.net.Uri) {
        val mediaItem = MediaItem.fromUri(uri)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.play()
    }

    fun pause() {
        exoPlayer.pause()
    }

    fun resume() {
        exoPlayer.play()
    }

    fun seekTo(positionMs: Long) {
        exoPlayer.seekTo(positionMs)
    }

    fun getPlayer(): ExoPlayer = exoPlayer

    fun release() {
        exoPlayer.release()
    }
}
