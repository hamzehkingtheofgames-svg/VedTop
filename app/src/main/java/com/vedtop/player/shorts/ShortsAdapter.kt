package com.vedtop.player.shorts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.vedtop.player.R

class ShortsAdapter(
    private val items: List<ShortVideo>
) : RecyclerView.Adapter<ShortsAdapter.ShortViewHolder>() {

    inner class ShortViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val playerView: com.google.android.exoplayer2.ui.PlayerView =
            view.findViewById(R.id.shortPlayerView)
        val title: android.widget.TextView = view.findViewById(R.id.tvShortTitle)
        val btnLike: android.widget.ImageButton = view.findViewById(R.id.btnLike)
        val btnShare: android.widget.ImageButton = view.findViewById(R.id.btnShare)
        var player: ExoPlayer? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShortViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_short_video, parent, false)
        return ShortViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShortViewHolder, position: Int) {
        val item = items[position]
        holder.title.text = item.title

        val player = ExoPlayer.Builder(holder.itemView.context).build()
        player.setMediaItem(MediaItem.fromUri(item.videoUri))
        player.repeatMode = ExoPlayer.REPEAT_MODE_ONE
        player.prepare()
        player.playWhenReady = true
        holder.playerView.player = player
        holder.player = player

        var liked = false
        holder.btnLike.setOnClickListener {
            liked = !liked
            holder.btnLike.setImageResource(
                if (liked) android.R.drawable.btn_star_big_on
                else android.R.drawable.btn_star_big_off
            )
        }

        holder.btnShare.setOnClickListener {
            val shareIntent = android.content.Intent().apply {
                action = android.content.Intent.ACTION_SEND
                type = "text/plain"
                putExtra(android.content.Intent.EXTRA_TEXT, item.title)
            }
            holder.itemView.context.startActivity(
                android.content.Intent.createChooser(shareIntent, "مشاركة")
            )
        }
    }

    override fun onViewRecycled(holder: ShortViewHolder) {
        super.onViewRecycled(holder)
        holder.player?.release()
        holder.player = null
    }

    override fun getItemCount(): Int = items.size
}
