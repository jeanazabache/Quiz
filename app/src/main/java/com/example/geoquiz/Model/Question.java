package com.example.geoquiz.Model;

public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;


    public Question(int textResID, boolean answertrue){
        mTextResId=textResID;
        mAnswerTrue=answertrue;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }


}
