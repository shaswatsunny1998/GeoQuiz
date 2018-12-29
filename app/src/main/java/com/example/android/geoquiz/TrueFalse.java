package com.example.android.geoquiz;

public class TrueFalse {
    private int question;
    private boolean truequestion;

    public TrueFalse(int question, boolean truequestion) {
        this.question = question;
        this.truequestion = truequestion;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public boolean isTruequestion() {
        return truequestion;
    }

    public void setTruequestion(boolean truequestion) {
        this.truequestion = truequestion;
    }
}
