package com.example.assignmentlab2;

/** One video in the feed, plus its creator and like/save state. */
public class VideoItem {
    private final String title, description, id, url;
    private final String creator, creatorName, creatorBio;
    private boolean liked;
    private boolean saved;

    public VideoItem(String title, String description, String id, String url,
                     String creator, String creatorName, String creatorBio) {
        this.title = title; this.description = description; this.id = id; this.url = url;
        this.creator = creator; this.creatorName = creatorName; this.creatorBio = creatorBio;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getId() { return id; }
    public String getUrl() { return url; }
    public String getCreator() { return creator; }
    public String getCreatorName() { return creatorName; }
    public String getCreatorBio() { return creatorBio; }

    public boolean isLiked() { return liked; }
    public void setLiked(boolean liked) { this.liked = liked; }
    public boolean isSaved() { return saved; }
    public void setSaved(boolean saved) { this.saved = saved; }
}