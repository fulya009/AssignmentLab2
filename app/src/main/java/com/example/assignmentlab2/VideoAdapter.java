package com.example.assignmentlab2;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/** Supplies one full-screen page per {@link VideoItem}: handles playback,
 *  tap-to-pause, and the like, save, and creator-profile buttons. */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private final List<VideoItem> videos;

    public VideoAdapter(List<VideoItem> videos) { this.videos = videos; }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_video, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.bind(videos.get(position));
    }

    @Override
    public int getItemCount() { return videos.size(); }

    @Override
    public void onViewAttachedToWindow(@NonNull VideoViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.videoView.start();
        holder.pauseIcon.setVisibility(View.GONE);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull VideoViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.videoView.pause();
    }

    /** Holds one page's views and binds a video to them. */
    public static class VideoViewHolder extends RecyclerView.ViewHolder {

        private final VideoView videoView;
        private final ProgressBar progressBar;
        private final View tapOverlay;
        private final ImageView pauseIcon;
        private final TextView creatorText, titleText, idText, descriptionText;
        private final ImageButton likeButton, profileButton, saveButton;

        VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoView);
            progressBar = itemView.findViewById(R.id.progressBar);
            tapOverlay = itemView.findViewById(R.id.tapOverlay);
            pauseIcon = itemView.findViewById(R.id.pauseIcon);
            creatorText = itemView.findViewById(R.id.creatorText);
            titleText = itemView.findViewById(R.id.titleText);
            idText = itemView.findViewById(R.id.idText);
            descriptionText = itemView.findViewById(R.id.descriptionText);
            likeButton = itemView.findViewById(R.id.likeButton);
            profileButton = itemView.findViewById(R.id.profileButton);
            saveButton = itemView.findViewById(R.id.saveButton);
        }

        void bind(VideoItem item) {
            creatorText.setText(item.getCreator());
            titleText.setText(item.getTitle());
            idText.setText(itemView.getContext().getString(R.string.video_id, item.getId()));
            descriptionText.setText(item.getDescription());

            videoView.setVideoURI(Uri.parse(item.getUrl()));
            progressBar.setVisibility(View.VISIBLE);
            pauseIcon.setVisibility(View.GONE);
            videoView.setOnPreparedListener(mp -> {
                mp.setLooping(true);
                progressBar.setVisibility(View.GONE);
            });

            // Tap anywhere on the video to pause / resume.
            tapOverlay.setOnClickListener(v -> {
                if (videoView.isPlaying()) {
                    videoView.pause();
                    pauseIcon.setVisibility(View.VISIBLE);
                } else {
                    videoView.start();
                    pauseIcon.setVisibility(View.GONE);
                }
            });

            refreshLike(item);
            refreshSave(item);

            likeButton.setOnClickListener(v -> {
                item.setLiked(!item.isLiked());
                refreshLike(item);
            });

            saveButton.setOnClickListener(v -> {
                item.setSaved(!item.isSaved());
                refreshSave(item);
                Toast.makeText(v.getContext(),
                        item.isSaved() ? "Saved" : "Removed from saved",
                        Toast.LENGTH_SHORT).show();
            });

            profileButton.setOnClickListener(v -> showProfile(item));
        }

        private void refreshLike(VideoItem item) {
            likeButton.setImageResource(item.isLiked()
                    ? R.drawable.ic_heart_filled : R.drawable.ic_heart);
        }

        private void refreshSave(VideoItem item) {
            saveButton.setImageResource(item.isSaved()
                    ? R.drawable.ic_bookmark_filled : R.drawable.ic_bookmark);
        }

        private void showProfile(VideoItem item) {
            Context c = itemView.getContext();
            int count = 0;
            for (VideoItem v : VideoRepository.getVideos())
                if (v.getCreator().equals(item.getCreator())) count++;

            new AlertDialog.Builder(c)
                    .setTitle(item.getCreator())
                    .setMessage(item.getCreatorName() + "\n\n"
                            + item.getCreatorBio() + "\n\n"
                            + count + " video(s) on SwipeVideo")
                    .setPositiveButton("Close", null)
                    .show();
        }
    }
}