package com.bignerdranch.android.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private View mCustomToastLayout;
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(view -> displayResponse(R.string.correct_toast,R.drawable.ic_happy));

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayResponse(R.string.incorrect_toast,R.drawable.ic_sad);
            }
        });
    }

    @Override
    protected void onStop() {
        if (mToast != null) {
            mToast.cancel();
        }
        super.onStop();
    }

    private void displayResponse(int responseStringResource, int responseDrawableResource) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = new Toast(getApplicationContext());
        mToast.setGravity(Gravity.BOTTOM, 0, 0);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setView(makeCustomToastView(responseStringResource,responseDrawableResource));
        mToast.show();
    }


    private View makeCustomToastView(int textResource, int imageResource) {
        View customToastView = getLayoutInflater().inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.custom_toast_container));
        TextView text = customToastView.findViewById(R.id.custom_toast_text);
        text.setText(textResource);
        ImageView imageView = customToastView.findViewById(R.id.custom_toast_image);
        imageView.setImageResource(imageResource);
        return customToastView;
    }
}
