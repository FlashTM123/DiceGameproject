package com.example.dicegame;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView imgDice;

    private Button btnRandom;

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imgDice = findViewById(R.id.imgDice);
        btnRandom = findViewById(R.id.btnRandom);

        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnRandom();
            }
        });





    }

    private void btnRandom() {
        Toast.makeText(this, "Đang quay.....", Toast.LENGTH_SHORT).show();

        btnRandom.setEnabled(false);

        if (mediaPlayer != null){
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(this, R.raw.dice_music);
        mediaPlayer.start();

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation);

        imgDice.startAnimation(animation);


        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int randomNumber = new Random().nextInt(6) + 1;

                int drawable;

                if (randomNumber == 1) {
                    drawable = R.drawable.dice_1;
                } else if (randomNumber == 2){
                    drawable = R.drawable.dice_2;
                } else if (randomNumber == 3) {
                    drawable = R.drawable.dice_3;

                } else if (randomNumber == 4) {
                    drawable = R.drawable.dice_4;

                } else if (randomNumber == 5) {
                    drawable = R.drawable.dice_5;
                } else {
                    drawable = R.drawable.dice_6;
                }

                imgDice.setImageResource(drawable);

                btnRandom.setEnabled(true);
            }
        }, 1000);

    }
}