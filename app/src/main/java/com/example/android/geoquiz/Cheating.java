package com.example.android.geoquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Cheating extends AppCompatActivity {
    TextView manswer;
    Button cheat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheating);
        final boolean get=getIntent().getBooleanExtra("sending",false);
        manswer=(TextView) findViewById(R.id.Answer);
        setAnswerresult(false);
        cheat=(Button) findViewById(R.id.show);

        cheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(get)
                    manswer.setText("True");
                else
                    manswer.setText("False");
                setAnswerresult(true);
            }
        });
    }
    public void setAnswerresult(boolean isanswershown)
    {
        Intent data= new Intent();
        data.putExtra("return",isanswershown);
        setResult(RESULT_OK,data);
    }
}
