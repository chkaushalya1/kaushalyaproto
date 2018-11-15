package com.example.pratik.kaushalyaproto;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {


    ImageView playButton;
    VideoView videoView;
    TextView currenttimerText, durationtimerText;
    ProgressBar progressBar, progressBar2;

    int current, duration;

    boolean isPlaying;

    Uri videouri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        isPlaying = false;

        playButton = findViewById(R.id.btn_play);
        videoView = findViewById(R.id.videoView);
        currenttimerText = findViewById(R.id.currentProgresstext);
        durationtimerText = findViewById(R.id.durationText);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(100);
        progressBar2 = findViewById(R.id.progressBar2);

        videouri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/kaushalyaproto.appspot.com/o/SoilTesting%2FSoil%20Testing_%20Laboratory%20Process.mp4?alt=media&token=b2fbac94-40da-4efb-9106-46f917673ef3");

        videoView.setVideoURI(videouri);
        videoView.requestFocus();
        videoView.start();
        isPlaying = true;
        playButton.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);

        new VideoProgress().execute();

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPlaying) {
                    videoView.pause();
                    playButton.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);
                    isPlaying = false;

                } else {
                    videoView.start();
                    playButton.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);
                    isPlaying = true;
                }
            }
        });

        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mediaPlayer, int i, int i1) {
                if (i == mediaPlayer.MEDIA_INFO_BUFFERING_START)
                    progressBar2.setVisibility(View.VISIBLE);
                else if (i == mediaPlayer.MEDIA_INFO_BUFFERING_END)
                    progressBar2.setVisibility(View.INVISIBLE);

                return false;
            }
        });

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {

                duration = mediaPlayer.getDuration() / 1000;
                String durationString = String.format("%02d:%02d", duration / 60, duration % 60);

                durationtimerText.setText(durationString);

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        isPlaying = false;
    }

    public class VideoProgress extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            do {

                if (isPlaying) {
                    current = videoView.getCurrentPosition() / 1000;
                    publishProgress(current);
                }


            } while (progressBar.getProgress() <= 100);
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            try {

                int currentpercent = values[0] * 100 / duration;
                progressBar.setProgress(currentpercent);

                String currentString = String.format("%02d:%02d", duration / 60, duration % 60);

                currenttimerText.setText(currentString);

            } catch (Exception e) {

            }
        }
    }
}
