package com.example.kamisado;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertFalse;

public class Multi extends AppCompatActivity {

    @Test
    public void name_isCorrect(){
        String test_name_1 = "élet";
        String test_name_2 = "el";
        String test_name_3 = "";
        assertFalse("Hibás felhasználónév", Correct(test_name_1));
        assertFalse("Hibás felhasználónév", Correct(test_name_2));
        assertFalse("Hibás felhasználónév", Correct(test_name_3));
    }

    public String name_B, name_W, err = "";
    private EditText Text_name_B, Text_name_W;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);

        Button start = findViewById(R.id.button_Start);
        start.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                Text_name_B = findViewById(R.id.edit_Name_B);
                Text_name_W = findViewById(R.id.edit_Name_W);
                name_B = Text_name_B.getText().toString();
                name_W = Text_name_W.getText().toString();
                TextView error = findViewById(R.id.text_error);

                if(Correct(name_B) && Correct(name_W)){
                    openStart(name_B, name_W);
                }
                else{
                    error.setText(err);
                }

            }
        });
    }

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

    public void openStart(String B, String W){
        Intent intent = new Intent(this, Multi_game.class);
        intent.putExtra("name_B", B);
        intent.putExtra("name_W", W);
        startActivity(intent);
        finish();
    }
}
