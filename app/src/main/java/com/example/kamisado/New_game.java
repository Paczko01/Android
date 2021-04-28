package com.example.kamisado;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class New_game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        // Egyszemélyes játék indítása
        Button single = findViewById(R.id.single);
        single.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                openSingle();
            }
        });

        //Kétszemélyes játék indítása
        Button multi = findViewById(R.id.button_multi);
        multi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                openMulti();
            }
        });
    }

    public void openSingle(){
        Intent intent = new Intent(this, Single.class);
        startActivity(intent);
        finish();
    }

    public void openMulti(){
        Intent intent = new Intent(this, Multi.class);
        startActivity(intent);
        finish();
    }
}
