package com.example.kamisado;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

/**
 * A programba írt megjegyzésekhez a gyorsabb haladás érdekében nem
 * javadoc-ot alkalmazok, hanem sima kommenetet "//" alkalmazásával
 */

public class MainActivity extends AppCompatActivity {
    //Fájl írás/olvasás engedély ellenőrzése, hiány esetén kérése
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] permissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
            };
    public static void verifyPermissions(Activity activity){
        int wP = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int rP = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
        if(wP != PackageManager.PERMISSION_GRANTED || rP != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity, permissions, REQUEST_EXTERNAL_STORAGE);
        }
    }

    @Override // => parancs felülírása
    protected void onCreate(Bundle savedInstanceState) {
        //ablak megjelenésének beállításai
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verifyPermissions(this);

        //Új játék indításának gombja
        Button new_game = findViewById(R.id.new_game);
        new_game.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                openNew();
            }
        });

        //Játék folytatásána gombja
        Button continue_game = findViewById(R.id.continue_game);
        continue_game.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                openContinue();
            }
        });

        //Szabályok megnyitásának gombja
        Button settings = findViewById(R.id.rules);
        settings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                openRules();
            }
        });

        //Kilépés gomb
        Button exit = findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finishAndRemoveTask();
            }});
    }

    //Új Activity megnyitása, jelenlegi nyitva marad a háttérben
    public void openRules() {
        Intent intent = new Intent(this, Rules.class);
        startActivity(intent);
    }

    //Új Activity megnyitása a jelenlegi bezárása
    public void openContinue() {
        Intent intent = new Intent(this, Continue_game.class);
        startActivity(intent);
        finish();
    }

    public void openNew(){
        Intent intent = new Intent(this, New_game.class);
        startActivity(intent);
        finish();
    }


}
