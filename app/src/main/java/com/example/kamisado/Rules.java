package com.example.kamisado;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Rules extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        TextView goal = findViewById(R.id.text_Goal_full);
        goal.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);

        TextView step = findViewById(R.id.text_Step_full);
        step.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        TextView kick = findViewById(R.id.text_Step_full2);
        kick.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        TextView move = findViewById(R.id.text_Step_full3);
        move.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        TextView start = findViewById(R.id.text_Step_full4);
        start.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);

        Button back = findViewById(R.id.button_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
