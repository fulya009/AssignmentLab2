package com.example.assignmentlab2;

/**
 * Model class representing a single swipeable video in the feed.
 * <p>
 * Each {@code VideoItem} bundles the information shown on one page of the
 * {@link androidx.viewpager2.widget.ViewPager2}: an on-screen title, a short
 * description, a unique identifier, and the online URL the video streams from.
 */
public class VideoItem {

    /** Human-readable title shown as an overlay on top of the video. */
    private final String title;

    /** Short description shown beneath the title. */
    private final String description;

    /** Unique identifier for this video (the "unique video ID" requirement). */
    private final String id;

    /** Direct URL to the online-hosted video file (an .mp4 served over HTTPS). */
    private final String url;

    /**
     * Creates a new video entry.
     *
     * @param title       the title displayed over the video
     * @param description a brief description of the video
     * @param id          a unique id for the video
     * @param url         the online URL the video is streamed from
     */
    public VideoItem(String title, String description, String id, String url) {
        this.title = title;
        this.description = description;
        this.id = id;
        this.url = url;
    }

    /** @return the video title. */
    public String getTitle() { return title; }

    /** @return the short description. */
    public String getDescription() { return description; }

    /** @return the unique video id. */
    public String getId() { return id; }

    /** @return the online streaming URL. */
    public String getUrl() { return url; }
}