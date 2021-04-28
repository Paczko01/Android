package com.example.kamisado;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertFalse;

public class Single extends AppCompatActivity {
    //felhasználónév tesztelése
    @Test
    public void name_isCorrect(){
        String test_name_1 = "élet";
        String test_name_2 = "el";
        String test_name_3 = "";
        assertFalse("Hibás felhasználónév", Correct(test_name_1));
        assertFalse("Hibás felhasználónév", Correct(test_name_2));
        assertFalse("Hibás felhasználónév", Correct(test_name_3));
    }

    private String name, err = "";
    private EditText edit_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);

        //Játék indítása
        Button start = findViewById(R.id.button_Start);
        start.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View V){
                edit_name = findViewById(R.id.edit_Name);
                name = edit_name.getText().toString();
                TextView error = findViewById(R.id.text_error);
                if(Correct(name)){
                    openStart();
                }
                else{
                    error.setText(err);
                }
            }
        });
    }

    //Felhasználónév ellenörzése
    public boolean Correct(String name){
        if(name.isEmpty()){
        err = "Adjon meg felhasználónevet!";
        return false;
        }
        else if(name.length() < 3){
            err = "A felhasználónév legalább 3 karakterból álljon!";
            return false;
        }
        for(int i=0; i<name.length(); i++){
            char c = name.charAt(i);
            if(Character.UnicodeBlock.of(c) != Character.UnicodeBlock.BASIC_LATIN){
                err = "A felhasználónév nem tartalmazhat speciális vagy ékezetes karaktert!";
                return false;
            }
        }
        return true;
    }

    // Új ablak nyitása, felhasználónév átadásával
    public void openStart(){
        Intent intent = new Intent(this, Game.class);
        intent.putExtra("name", name);
        startActivity(intent);
        finish();
    }
}
