package com.example.assignmentlab2;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adapter that supplies one full-screen video page to the
 * {@link androidx.viewpager2.widget.ViewPager2} for every {@link VideoItem}
 * in the feed. It also starts and pauses playback as pages scroll in and out
 * of view so that only the visible video plays.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    /** The videos to display, one per swipeable page. */
    private final List<VideoItem> videos;

    /**
     * @param videos the list of videos backing the feed
     */
    public VideoAdapter(List<VideoItem> videos) {
        this.videos = videos;
    }

    /**
     * Inflates the {@code item_video} layout for a new page.
     */
    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_video, parent, false);
        return new VideoViewHolder(view);
    }

    /**
     * Binds the video at {@code position} to the page.
     */
    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.bind(videos.get(position));
    }

    /**
     * @return the number of videos in the feed
     */
    @Override
    public int getItemCount() {
        return videos.size();
    }

    /** Starts playback when a page scrolls into view. */
    @Override
    public void onViewAttachedToWindow(@NonNull VideoViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.videoView.start();
    }

    /** Pauses playback when a page scrolls out of view. */
    @Override
    public void onViewDetachedFromWindow(@NonNull VideoViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.videoView.pause();
    }

    /**
     * Holds the views for one page and binds a {@link VideoItem} to them.
     */
    static class VideoViewHolder extends RecyclerView.ViewHolder {

        private final VideoView videoView;
        private final ProgressBar progressBar;
        private final TextView titleText;
        private final TextView idText;
        private final TextView descriptionText;

        /**
         * Caches the child views for fast binding.
         *
         * @param itemView the inflated {@code item_video} layout
         */
        VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoView);
            progressBar = itemView.findViewById(R.id.progressBar);
            titleText = itemView.findViewById(R.id.titleText);
            idText = itemView.findViewById(R.id.idText);
            descriptionText = itemView.findViewById(R.id.descriptionText);
        }

        /**
         * Fills this page with a video's details and loads it for streaming.
         *
         * @param item the video to display
         */
        void bind(VideoItem item) {
            titleText.setText(item.getTitle());
            idText.setText("ID: " + item.getId());
            descriptionText.setText(item.getDescription());

            videoView.setVideoURI(Uri.parse(item.getUrl()));
            progressBar.setVisibility(View.VISIBLE);

            // Called once the stream is ready: loop it and hide the spinner.
            videoView.setOnPreparedListener(mediaPlayer -> {
                mediaPlayer.setLooping(true);
                progressBar.setVisibility(View.GONE);
            });
        }
    }
}