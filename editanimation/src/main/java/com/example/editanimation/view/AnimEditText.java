package com.example.editanimation.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by HJ on 2016/11/8.
 */
public class AnimEditText extends LinearLayout implements View.OnClickListener{

    private TextView textView;
    private int originalHeight;
    private EditText editText;

    private ScaleAnimation animation;

    private NotifyWhitch notifyWhitch;

    public AnimEditText(Context context) {
        super(context);
    }

    public AnimEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void setNotifyWhitch(NotifyWhitch notifyWhitch) {
        this.notifyWhitch = notifyWhitch;
    }

    public void setTextText(String textString) {
        textView.setText(textString);
    }
    public void setEditText(String editString) {

        if(editText.getVisibility()==GONE){
            ScaleAnimation animation =new ScaleAnimation(1.0f, 0.75f, 1.0f, 0.75f);
            animation.setDuration(10);//设置动画持续时间
            animation.setFillAfter(true);//动画执行完后是否停留在执行完的状态
            textView.startAnimation(animation);
        }


        editText.setText(editString);
        editText.setVisibility(VISIBLE);
        editText.setFocusable(false);


        this.setGravity(Gravity.CENTER_VERTICAL);
    }

    public String getEditString(){
        return editText.getText().toString().trim();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        if(originalHeight == 0){

            if(editText.getVisibility()==VISIBLE){
                originalHeight = editText.getMeasuredHeight();
            }else {
                originalHeight = textView.getMeasuredHeight();
            }


        }

        Log.e("HJ","-------------onMeasure-----------textView.getMeasuredHeight()--   "+textView.getMeasuredHeight());
        Log.e("HJ","-------------onMeasure-----------editText.getMeasuredHeight()--   "+editText.getMeasuredHeight());
        this.setMinimumHeight(2*originalHeight);
        this.setGravity(Gravity.CENTER_VERTICAL);


        if(animation==null){
            initAnim();
        }

    }



    private void initAnim() {

        animation =new ScaleAnimation(1.0f, 0.75f, 1.0f, 0.75f);
        animation.setDuration(500);//设置动画持续时间
        animation.setFillAfter(true);//动画执行完后是否停留在执行完的状态
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                requestEditText();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        Log.i("HJ","-------------onFinishInflate-------------");

        textView = (TextView) getChildAt(0);
        editText = (EditText) getChildAt(1);
        editText.setVisibility(GONE);

        editText.setOnClickListener(this);
        textView.setOnClickListener(this);
        this.setOnClickListener(this);

    }

    private void setNoGravity() {
        this.setGravity(Gravity.TOP);
    }

    public void hideKeyBoard() {

        editText.setFocusable(false);

//        if(!TextUtils.isEmpty(editText.getText().toString().trim()) && !TextUtils.equals(editText.getText().toString().trim(),"111111111")  ){
        if(!TextUtils.isEmpty(editText.getText().toString().trim()) ){
//            isOnEdit = true;
            return;
        }

        ScaleAnimation animation2 =new ScaleAnimation(0.75f, 1.0f, 0.75f, 1.0f);
        animation2.setDuration(500);//设置动画持续时间
        animation2.setFillAfter(true);//动画执行完后是否停留在执行完的状态
        textView.startAnimation(animation2);
//        isOnEdit = false;

        editText.setVisibility(GONE);

    }

    private void requestEditText() {

        if(TextUtils.isEmpty(editText.getText().toString().trim())){
            editText.setText(" ");
        }

        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        InputMethodManager inputManager = (InputMethodManager)editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(editText, 0);

        if(notifyWhitch!=null){
            notifyWhitch.whitchEditText(this);
        }
    }

    @Override
    public void onClick(View v) {
        if( editText.getVisibility()==GONE){
            editText.setVisibility(VISIBLE);
            setNoGravity();
            textView.startAnimation(animation);
        }else{
            requestEditText();
        }
    }


    public interface NotifyWhitch{
        void whitchEditText(AnimEditText AnimEdit);
    }
}
