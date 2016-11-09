package com.example.editanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.editanimation.view.AnimEditText;

public class MainActivity extends AppCompatActivity implements AnimEditText.NotifyWhitch {


    private AnimEditText animEditText, anim_edit2;
    private AnimEditText animEditMiddle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {

        animEditText = (AnimEditText) findViewById(R.id.anim_edit);
        animEditText.setNotifyWhitch(this);
        animEditText.setTextText("填写身份证号");
//        animEditText.setEditText("1111111111111111111111111");

        anim_edit2 = (AnimEditText) findViewById(R.id.anim_edit2);
        anim_edit2.setNotifyWhitch(this);
        anim_edit2.setTextText("填写身份证号");
//        anim_edit2.setEditText("1111111111111111111111111");

        SoftKeyboardStateHelper softKeyboardStateHelper = new SoftKeyboardStateHelper(findViewById(R.id.layout_mainm));
        softKeyboardStateHelper.addSoftKeyboardStateListener(new SoftKeyboardStateHelper.SoftKeyboardStateListener() {
            @Override
            public void onSoftKeyboardOpened(int keyboardHeightInPx) {
                Log.w("HJ", "----------------------Keyboard----  " + keyboardHeightInPx);
            }

            @Override
            public void onSoftKeyboardClosed() {
                Log.w("HJ", "----------------------onSoftKeyboardClosed----  ");
                animEditMiddle.hideKeyBoard();
                if (animEditMiddle == animEditText) {
                    Log.w("HJ", "----------------------1111111111111111----  ");
                    anim_edit2.setEditText("2222222xxxxxxx");
//                    animEditText.hideKeyBoard();
                } else if (animEditMiddle == anim_edit2) {
                    Log.w("HJ", "----------------------222222222222222222----  ");
//                    anim_edit2.hideKeyBoard();
                }
            }
        });
    }

    @Override
    public void whitchEditText(AnimEditText AnimEdit) {

        animEditMiddle = AnimEdit;

    }
}
