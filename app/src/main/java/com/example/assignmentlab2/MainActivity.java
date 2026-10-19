package com.example.assignmentlab2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

/**
 * Entry point of the Swipe Video app.
 * <p>
 * Builds the list of {@link VideoItem}s and wires them into a vertical
 * {@link ViewPager2}, so the user can swipe up and down between full-screen,
 * looping videos.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager = findViewById(R.id.viewPager);
        // Vertical swiping (up/down) instead of the default horizontal.
        viewPager.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        viewPager.setAdapter(new VideoAdapter(buildVideoList()));
    }

    /**
     * Creates the feed of videos. Each entry has a title, a description, a
     * unique id, and an online URL (hosted on GitHub via the jsDelivr CDN)
     * that the video streams from.
     *
     * @return the list of videos shown in the app
     */
    private List<VideoItem> buildVideoList() {
        List<VideoItem> videos = new ArrayList<>();
        String base = "https://cdn.jsdelivr.net/gh/fulya009/VideoLab/";

        videos.add(new VideoItem("Sweater Kitten",
                "A cozy Scottish Fold kitten lounging on the stairs in a tiny sweater.",
                "09876201", base + "video1.mp4"));

        videos.add(new VideoItem("Spa Day",
                "A relaxed tabby enjoys a gentle jade-roller facial on a pink backdrop.",
                "09876202", base + "video2.mp4"));

        videos.add(new VideoItem("The Street King",
                "A fluffy black cat rules the sidewalk from atop a golden crown.",
                "09876203", base + "video3.mp4"));

        videos.add(new VideoItem("Lazy Morning",
                "A fluffy cat grooms itself in soft morning light on a cozy bed.",
                "09876204", base + "video4.mp4"));

        videos.add(new VideoItem("Working From Home",
                "A curious grey-and-white cat paws at a laptop like it's on the clock.",
                "09876205", base + "video5.mp4"));

        return videos;
    }
}