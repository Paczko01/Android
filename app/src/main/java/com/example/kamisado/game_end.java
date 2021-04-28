package com.example.kamisado;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class game_end extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_end);
        //játék végeredményének kiírása
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String end = bundle.getString("end_type", "defValue");
        TextView edit_end = findViewById(R.id.textView_game_end);
        edit_end.setText(end);

        Button new_game = findViewById(R.id.button_new_game);
        new_game.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                openNewGame();
            }
        });

        Button exit = findViewById(R.id.button_exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAndRemoveTask();
            }
        });
    }

    public void openNewGame(){
        Intent intent = new Intent(this, New_game.class);
        startActivity(intent);
        finish();
    }
}
