package com.example.kamisado;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import org.testng.annotations.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import static org.testng.Assert.assertTrue;

public class Continue_game extends AppCompatActivity {

    @Test
    public void fileExistTest(){
        File dir = new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)));
        File solo_game_Test = new File(dir, "game_data_solo.txt");
        File multi_game_Test = new File(dir, "game_data_multi.txt");
        assertTrue(solo_game_Test.exists(), "solo fájl létezik");
        assertTrue(multi_game_Test.exists(), "multi fájl létezik");
    }

    public String[][] map_black = new String[8][8];
    public String[][] map_white = new String[8][8];
    public String name, name_B, name_W, next_step;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continue_game);

        Button back = findViewById(R.id.button_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain();
            }
        });

        File dir = new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)));
        File solo_game = new File(dir, "game_data_solo.txt");
        File multi_game = new File(dir, "game_data_multi.txt");
        BufferedReader reader;

        Button solo = findViewById(R.id.button_Single);
        if(solo_game.exists()){
            try {
                reader = new BufferedReader(new FileReader("/storage/emulated/0/Documents/game_data_solo.txt"));
                name = reader.readLine();
                String[] akt;
                for(int i = 0; i < 8; i++){
                    akt = reader.readLine().split(" ");
                    for(int j = 0; j < 8; j++){
                        map_black[i][j] = akt[j];
                    }
                }
                next_step = reader.readLine();
                for(int i = 0; i < 8; i++){
                    akt = reader.readLine().split(" ");
                    for(int j = 0; j < 8; j++){
                        map_white[i][j] = akt[j];
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            solo.setText(name);
            solo.setClickable(true);
            solo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openSolo();
                }
            });
        }
        else{
            solo.setText("Nincsen mentett egyszemélyes játék");
            solo.setClickable(false);
        }

        Button multi = findViewById(R.id.button_Multi);
        if(multi_game.exists()){
            try {
                reader = new BufferedReader(new FileReader("/storage/emulated/0/Documents/game_data_multi.txt"));
                name = reader.readLine();
                String[] names = name.split(" ");
                name_B = names[0];
                name_W = names[2];
                String[] akt;
                for(int i = 0; i < 8; i++){
                    akt = reader.readLine().split(" ");
                    for(int j = 0; j < 8; j++){
                        map_black[i][j] = akt[j];
                    }
                }
                next_step = reader.readLine();
                for(int i = 0; i < 8; i++){
                    akt = reader.readLine().split(" ");
                    for(int j = 0; j < 8; j++){
                        map_white[i][j] = akt[j];
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            multi.setText(name);
            multi.setClickable(true);

            multi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openMulti();
                }
            });
        }
        else{
            multi.setText("Nincsen mentett kétszemélyes játék");
            multi.setClickable(false);
        }

    }

    public void openMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void openSolo(){
        Intent intent = new Intent(this, Game.class);
        intent.putExtra("loaded", true);
        intent.putExtra("name", name);
        intent.putExtra("map_B", map_black);
        intent.putExtra("next_step", next_step);
        intent.putExtra("map_W", map_white);
        startActivity(intent);
        finish();
    }

    public void openMulti(){
        Intent intent = new Intent(this, Multi_game.class);
        intent.putExtra("loaded", true);
        intent.putExtra("name_B", name_B);
        intent.putExtra("name_W", name_W);
        intent.putExtra("next_step", next_step);
        intent.putExtra("map_B", map_black);
        intent.putExtra("map_W", map_white);
        startActivity(intent);
        finish();
    }
}
