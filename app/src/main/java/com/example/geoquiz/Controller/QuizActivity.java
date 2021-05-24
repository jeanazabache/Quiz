package com.example.geoquiz.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geoquiz.Model.Question;
import com.example.geoquiz.R;
import com.example.geoquiz.TrampaActivity;


public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mBackButton;
    private TextView mQuestionTextView;
    private Button mVerRespuesta;


    private static final int CODIGO_TRAMPA_REQUERIDO=0;
    private boolean mIsTramposo;

    private Question[] mBancopreguntas=new Question[]{
            new Question(R.string.question_text,true),
            new Question(R.string.question_text1,true),
            new Question(R.string.question_text2, false),
            new Question(R.string.question_text3, false),
            new Question(R.string.question_text4,true),
            new Question(R.string.question_text5,true),
    };
    private int mCurrentIndex=0;

    @Override
    protected void onActivityResult(int requestCode, int result_code, Intent data) {
        super.onActivityResult(requestCode, result_code, data);
        if (result_code != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == CODIGO_TRAMPA_REQUERIDO) {
            if (data == null) {
                return;
            }
        }
        mIsTramposo = TrampaActivity.seMostroRespuesta(data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);


        int question = mBancopreguntas[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener((view) -> {
            checkAnswer(true);
        });

        mFalseButton = findViewById(R.id.false_button);
        mFalseButton.setOnClickListener((view) -> {
            checkAnswer(false);


        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener((view) -> {
            mCurrentIndex = mCurrentIndex + 1;
            if (mCurrentIndex >= mBancopreguntas.length) {
                mNextButton.setEnabled(false);
            } else {
                int questionN = mBancopreguntas[mCurrentIndex].getTextResId();
                mQuestionTextView.setText(questionN);
                mBackButton.setEnabled(true);
            }
        });

        mBackButton = (Button) findViewById(R.id.back_button);
        mBackButton.setOnClickListener((view) -> {
            mCurrentIndex = mCurrentIndex - 1;
//            Log.i("info","indice b: "+mCurrentIndex);
            if (mCurrentIndex < 0) {
                mBackButton.setEnabled(false);
            } else {
                int questionN = mBancopreguntas[mCurrentIndex].getTextResId();
                mQuestionTextView.setText(questionN);
                mNextButton.setEnabled(true);
            }

        });

        mVerRespuesta=(Button)findViewById(R.id.button_ver_respuesta);
        mVerRespuesta.setOnClickListener((view)->{
            boolean asnwerIsTrue=mBancopreguntas[mCurrentIndex].isAnswerTrue();
            Intent intent = TrampaActivity.nuevoIntent(QuizActivity.this,asnwerIsTrue);
            startActivityForResult(intent,CODIGO_TRAMPA_REQUERIDO);
        });

    }

    private void checkAnswer(boolean userPressTrue) {
        boolean answerTrue=mBancopreguntas[mCurrentIndex].isAnswerTrue();
        int messageResId=0;
        String colors = "";
        int background = 0;
        int icon = 0;

        if (mIsTramposo){
            messageResId=R.string.toast_juicio;
        }else {
            if (userPressTrue == answerTrue) {
                messageResId = R.string.correct_toast;
                colors = "#00e676";
                background = R.drawable.background_button_true;
                icon = R.drawable.ic_correct;
            } else {
                messageResId = R.string.incorrect_toast;
                colors = "#e60300";
                background = R.drawable.background_button_false;
                icon = R.drawable.ic_incorrect;
            }
        }

        Toast toast = new Toast(getApplicationContext()); //Toast.makeText(QuizActivity.this, messageResId , Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP,0,190);
        LayoutInflater inflater = getLayoutInflater();
        View custom_toast = inflater.inflate(R.layout.toast_layout,(ViewGroup) findViewById(R.id.linear_layout_toast));
        custom_toast.setBackgroundResource(background);
        ImageView image = (ImageView) custom_toast.findViewById(R.id.icon_toast);
        image.setImageResource(icon);

        TextView textToast = (TextView) custom_toast.findViewById(R.id.toast_textView);
        textToast.setText(messageResId);

        toast.setView(custom_toast);
        toast.show();

    }

}
