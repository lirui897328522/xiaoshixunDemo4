package com.example.xiaoshixundemo4.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.xiaoshixundemo4.R;

public class MainActivity extends AppCompatActivity {

    private ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initAnimation();
    }

    private void initAnimation() {
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.alpha);
        imageview.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void initView() {
        imageview = (ImageView) findViewById(R.id.imageview);
    }
}
