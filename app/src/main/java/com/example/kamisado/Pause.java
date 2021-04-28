package com.example.kamisado;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static android.view.View.INVISIBLE;

public class Pause extends AppCompatActivity {

    private String [][] map_black = new String[8][8];
    private String [][] map_white = new String[8][8];
    public boolean multi;
    private String next_step;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pause);

        TextView saved = findViewById(R.id.text_save);
        saved.setVisibility(INVISIBLE);
        //Adatok betöltése mentéshez
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        map_black = (String[][])bundle.getSerializable("B");
        map_white = (String[][])bundle.getSerializable("W");
        multi = bundle.getBoolean("multi");
        next_step = bundle.getString("next_step", "defValue");
        boolean black = true;
        try {
            black = bundle.getBoolean("black");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(black)
            next_step += " b";
        else
            next_step += " w";
        //gombok definiálása
        Button continue_game = findViewById(R.id.button_continue_game);
        continue_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button save = findViewById(R.id.button_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        Button rules = findViewById(R.id.button_rules);
        rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRules();
            }
        });
    }
    //Szabályok megnyitása
    public void openRules() {
        Intent intent = new Intent(this, Rules.class);
        startActivity(intent);
    }
    //játék mentése
    public void save(){
        try{
            //fájl meghatározása
            File dir = new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)));
            dir.mkdirs();
            File file;

            String player_name = "";
            if(multi){
                file = new File(dir, "game_data_multi.txt");
                if(file.exists()){
                    file.delete();
                }
                file.createNewFile();

                Bundle bundle = getIntent().getExtras();
                assert bundle != null;
                String name_B = bundle.getString("Name_B", "defValue");
                String name_W = bundle.getString("Name_W", "defValue");
                player_name = name_B + " és " + name_W;
            }
            else{
                file = new File(dir, "game_data_solo.txt");
                if(file.exists()){
                    file.delete();
                }
                file.createNewFile();

                Bundle bundle = getIntent().getExtras();
                assert bundle != null;
                player_name = bundle.getString("Name", "defValue");
            }
            //mentés végrehajtása, kiírás fájlba
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(player_name);
            writer.newLine();
            for (int i = 0; i < 8; i++){
                String akt_sor = "";
                for (int j = 0; j < 8; j++){
                    if(j != 7){
                        akt_sor += map_black[i][j] + " ";
                    }
                    else{
                        akt_sor += map_black[i][j];
                    }
                }
                writer.write(akt_sor);
                writer.newLine();
            }
            writer.write(next_step);
            writer.newLine();
            for (int i = 0; i < 8; i++){
                String akt_sor = "";
                for (int j = 0; j < 8; j++){
                    if(j != 7){
                        akt_sor += map_white[i][j] + " ";
                    }
                    else{
                        akt_sor += map_white[i][j];
                    }
                }
                writer.write(akt_sor);
                writer.newLine();
            }
            writer.close();
            TextView saved = findViewById(R.id.text_save);
            saved.setVisibility(View.VISIBLE);

        }
        catch(IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
