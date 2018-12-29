package com.example.android.geoquiz;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class QuizActivity extends AppCompatActivity {
    private static final String TAG="QUIZ";
    private static final String SAVE="SAVING";
    private Button true_btn;
    private Button false_btn;
    private TextView tvquestion;
    private Button next_btn;
    private Button pre_btn;
    private Button cheat_btn;
    private TextView api;
    private boolean ischeater;
    private TrueFalse[] questionbank= new TrueFalse[]{new TrueFalse(R.string.question1,false),new TrueFalse(R.string.question2,true),
    new TrueFalse(R.string.question3,false),new TrueFalse(R.string.question4,false),new TrueFalse(R.string.question5,true),
    new TrueFalse(R.string.question6,true)};
    private int mcurrentindex=0;

    private void updatequestion()
    {
        int question=questionbank[mcurrentindex].getQuestion();
        tvquestion.setText(question);
    }

    private void checkanswer(boolean pressed)
    {
        if(ischeater)
        {
            Toast.makeText(this, R.string.cheat, Toast.LENGTH_SHORT).show();
        }
        else {
            if (questionbank[mcurrentindex].isTruequestion() == pressed)
                Toast.makeText(this, "Your answer is Correct", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Your answer is Wrong", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        if(savedInstanceState!=null)
            mcurrentindex = savedInstanceState.getInt(SAVE);
        true_btn = (Button) findViewById(R.id.True_btn);
        false_btn = (Button) findViewById(R.id.False_btn);
        tvquestion = (TextView) findViewById(R.id.tvquestion);
        next_btn = (Button) findViewById(R.id.next_btn);
        pre_btn=(Button) findViewById(R.id.btn_pre);
        api=(TextView) findViewById(R.id.Apilevel);

        true_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkanswer(true);
            }
        });

        cheat_btn=(Button) findViewById(R.id.cheat);
        false_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkanswer(false);
            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mcurrentindex = (mcurrentindex + 1) % questionbank.length;
                ischeater=false;
                updatequestion();
            }
        });

        api.setText(""+ Build.VERSION.SDK_INT);
        
        updatequestion();

        tvquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mcurrentindex=(mcurrentindex+1)% questionbank.length;
                updatequestion();
            }
        });

        pre_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mcurrentindex==0)
                {
                    Toast.makeText(QuizActivity.this, "You are at the beginning of the questions", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mcurrentindex=mcurrentindex-1;
                }
                updatequestion();
            }
        });

        cheat_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(QuizActivity.this,com.example.android.geoquiz.Cheating.class);
                intent.putExtra("sending",questionbank[mcurrentindex].isTruequestion());
                startActivityForResult(intent,0);
            }
        });


    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVE,mcurrentindex);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data==null)
            return;
        else
            ischeater=data.getBooleanExtra("return",false);
    }
}
