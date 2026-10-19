package com.example.assignmentlab2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

/** Entry point: vertical swipe feed of videos, with a button to the Saved screen. */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager = findViewById(R.id.viewPager);
        viewPager.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        viewPager.setAdapter(new VideoAdapter(VideoRepository.getVideos()));

        Button savedButton = findViewById(R.id.savedButton);
        savedButton.setOnClickListener(v ->
                startActivity(new Intent(this, SavedActivity.class)));
    }
}