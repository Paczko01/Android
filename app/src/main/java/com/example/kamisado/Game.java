package com.example.kamisado;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.testng.Assert.assertTrue;

public class Game extends AppCompatActivity {

    private String [][] map_black = new String[8][8];
    private String [][] map_white = new String[8][8];
    private String [][] map_colors = new String[8][8];
    private Drawable move_pic, no_pic, black;
    private boolean moves;
    private int i_k, j_l, a, b;
    private Button[][] buttons = new Button[8][8];
    private String end, name, next;
    private boolean loaded_game = false;

    @Test
    public void winTest(){
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) map_black[x][y] = "none";
        }
        map_black[7][0] = "o";
        assertTrue(win_check_black(), "Nyert");
        map_black = new String[8][8];
    }

    @SuppressLint(value = "SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //játékos tér beállítása
        setMapDefault();
        //Megállítás gomb
        Button pause = findViewById(R.id.button_pause);
        pause.setClickable(true);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPause();
            }
        });
        // moves - éppen mozog-e a játékos, ha false, akkor még csak kiválasztotta, melyik bábuval lép
        moves = false;
        //játékostér gombjainak definiálása, tömbbe helyezése
        buttons[0][0] = findViewById(R.id.button_1_1);
        buttons[0][1] = findViewById(R.id.button_1_2);
        buttons[0][2] = findViewById(R.id.button_1_3);
        buttons[0][3] = findViewById(R.id.button_1_4);
        buttons[0][4] = findViewById(R.id.button_1_5);
        buttons[0][5] = findViewById(R.id.button_1_6);
        buttons[0][6] = findViewById(R.id.button_1_7);
        buttons[0][7] = findViewById(R.id.button_1_8);

        buttons[1][0] = findViewById(R.id.button_2_1);
        buttons[1][1] = findViewById(R.id.button_2_2);
        buttons[1][2] = findViewById(R.id.button_2_3);
        buttons[1][3] = findViewById(R.id.button_2_4);
        buttons[1][4] = findViewById(R.id.button_2_5);
        buttons[1][5] = findViewById(R.id.button_2_6);
        buttons[1][6] = findViewById(R.id.button_2_7);
        buttons[1][7] = findViewById(R.id.button_2_8);

        buttons[2][0] = findViewById(R.id.button_3_1);
        buttons[2][1] = findViewById(R.id.button_3_2);
        buttons[2][2] = findViewById(R.id.button_3_3);
        buttons[2][3] = findViewById(R.id.button_3_4);
        buttons[2][4] = findViewById(R.id.button_3_5);
        buttons[2][5] = findViewById(R.id.button_3_6);
        buttons[2][6] = findViewById(R.id.button_3_7);
        buttons[2][7] = findViewById(R.id.button_3_8);

        buttons[3][0] = findViewById(R.id.button_4_1);
        buttons[3][1] = findViewById(R.id.button_4_2);
        buttons[3][2] = findViewById(R.id.button_4_3);
        buttons[3][3] = findViewById(R.id.button_4_4);
        buttons[3][4] = findViewById(R.id.button_4_5);
        buttons[3][5] = findViewById(R.id.button_4_6);
        buttons[3][6] = findViewById(R.id.button_4_7);
        buttons[3][7] = findViewById(R.id.button_4_8);

        buttons[4][0] = findViewById(R.id.button_5_1);
        buttons[4][1] = findViewById(R.id.button_5_2);
        buttons[4][2] = findViewById(R.id.button_5_3);
        buttons[4][3] = findViewById(R.id.button_5_4);
        buttons[4][4] = findViewById(R.id.button_5_5);
        buttons[4][5] = findViewById(R.id.button_5_6);
        buttons[4][6] = findViewById(R.id.button_5_7);
        buttons[4][7] = findViewById(R.id.button_5_8);

        buttons[5][0] = findViewById(R.id.button_6_1);
        buttons[5][1] = findViewById(R.id.button_6_2);
        buttons[5][2] = findViewById(R.id.button_6_3);
        buttons[5][3] = findViewById(R.id.button_6_4);
        buttons[5][4] = findViewById(R.id.button_6_5);
        buttons[5][5] = findViewById(R.id.button_6_6);
        buttons[5][6] = findViewById(R.id.button_6_7);
        buttons[5][7] = findViewById(R.id.button_6_8);

        buttons[6][0] = findViewById(R.id.button_7_1);
        buttons[6][1] = findViewById(R.id.button_7_2);
        buttons[6][2] = findViewById(R.id.button_7_3);
        buttons[6][3] = findViewById(R.id.button_7_4);
        buttons[6][4] = findViewById(R.id.button_7_5);
        buttons[6][5] = findViewById(R.id.button_7_6);
        buttons[6][6] = findViewById(R.id.button_7_7);
        buttons[6][7] = findViewById(R.id.button_7_8);

        buttons[7][0] = findViewById(R.id.button_8_1);
        buttons[7][1] = findViewById(R.id.button_8_2);
        buttons[7][2] = findViewById(R.id.button_8_3);
        buttons[7][3] = findViewById(R.id.button_8_4);
        buttons[7][4] = findViewById(R.id.button_8_5);
        buttons[7][5] = findViewById(R.id.button_8_6);
        buttons[7][6] = findViewById(R.id.button_8_7);
        buttons[7][7] = findViewById(R.id.button_8_8);

        //üres kép meghatározása
        no_pic = buttons[5][5].getForeground();
        //fekete lépésének jelölésének meghatározása
        black = buttons[1][0].getForeground();
        buttons[1][0].setForeground(no_pic);
        //gombok beállítása, hogy kattintésra lépjenek (ha szükséges)
        buttons[0][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 0;
                    j_l = 0;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 0;
                    b = 0;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic,  i_k, j_l);
                }
            }
        });

        buttons[1][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 1;
                    j_l = 0;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 1;
                    b = 0;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[2][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 2;
                    j_l = 0;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 2;
                    b = 0;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[3][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 3;
                    j_l = 0;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 3;
                    b = 0;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic,  i_k, j_l);
                }
            }
        });

        buttons[4][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 4;
                    j_l = 0;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 4;
                    b = 0;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic,  i_k, j_l);
                }
            }
        });

        buttons[5][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 5;
                    j_l = 0;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 5;
                    b = 0;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic,  i_k, j_l);
                }
            }
        });

        buttons[6][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 6;
                    j_l = 0;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 6;
                    b = 0;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[7][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 7;
                    j_l = 0;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 7;
                    b = 0;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[0][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 0;
                    j_l = 1;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 0;
                    b = 1;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[1][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 1;
                    j_l = 1;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 1;
                    b = 1;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[2][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 2;
                    j_l = 1;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 2;
                    b = 1;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[3][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 3;
                    j_l = 1;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 3;
                    b = 1;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[4][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 4;
                    j_l = 1;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 4;
                    b = 1;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[5][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 5;
                    j_l = 1;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 5;
                    b = 1;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[6][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 6;
                    j_l = 1;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 6;
                    b = 1;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[7][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 7;
                    j_l = 1;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 7;
                    b = 1;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[0][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 0;
                    j_l = 2;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 0;
                    b = 2;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[1][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 1;
                    j_l = 2;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 1;
                    b = 2;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[2][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 2;
                    j_l = 2;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 2;
                    b = 2;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[3][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 3;
                    j_l = 2;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 3;
                    b = 2;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[4][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 4;
                    j_l = 2;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 4;
                    b = 2;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[5][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 5;
                    j_l = 2;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 5;
                    b = 2;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[6][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 6;
                    j_l = 2;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 6;
                    b = 2;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[7][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 7;
                    j_l = 2;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 7;
                    b = 2;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[0][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 0;
                    j_l = 3;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 0;
                    b = 3;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[1][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 1;
                    j_l = 3;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 1;
                    b = 3;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[2][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 2;
                    j_l = 3;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 2;
                    b = 3;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[3][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 3;
                    j_l = 3;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 3;
                    b = 3;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[4][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 4;
                    j_l = 3;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 4;
                    b = 3;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[5][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 5;
                    j_l = 3;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 5;
                    b = 3;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[6][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 6;
                    j_l = 3;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 6;
                    b = 3;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[7][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 7;
                    j_l = 3;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 7;
                    b = 3;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[0][4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 0;
                    j_l = 4;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 0;
                    b = 4;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[1][4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 1;
                    j_l = 4;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 1;
                    b = 4;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[2][4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 2;
                    j_l = 4;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 2;
                    b = 4;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[3][4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 3;
                    j_l = 4;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 3;
                    b = 4;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[4][4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 4;
                    j_l = 4;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 4;
                    b = 4;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[5][4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 5;
                    j_l = 4;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 5;
                    b = 4;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[6][4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 6;
                    j_l = 4;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 6;
                    b = 4;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[7][4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 7;
                    j_l = 4;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 7;
                    b = 4;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[0][5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 0;
                    j_l = 5;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 0;
                    b = 5;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[1][5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 1;
                    j_l = 5;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 1;
                    b = 5;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[2][5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 2;
                    j_l = 5;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 2;
                    b = 5;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[3][5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 3;
                    j_l = 5;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 3;
                    b = 5;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[4][5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 4;
                    j_l = 5;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 4;
                    b = 5;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[5][5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 5;
                    j_l = 5;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 5;
                    b = 5;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[6][5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 6;
                    j_l = 5;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 6;
                    b = 5;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[7][5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 7;
                    j_l = 5;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 7;
                    b = 5;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[0][6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 0;
                    j_l = 6;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 0;
                    b = 6;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[1][6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 1;
                    j_l = 6;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 1;
                    b = 6;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[2][6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 2;
                    j_l = 6;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 2;
                    b = 6;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[3][6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 3;
                    j_l = 6;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 3;
                    b = 6;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[4][6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 4;
                    j_l = 6;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 4;
                    b = 6;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[5][6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 5;
                    j_l = 6;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 5;
                    b = 6;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[6][6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 6;
                    j_l = 6;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 6;
                    b = 6;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[7][6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 7;
                    j_l = 6;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 7;
                    b = 6;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[0][7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 0;
                    j_l = 7;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 0;
                    b = 7;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[1][7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 1;
                    j_l = 7;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 1;
                    b = 7;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[2][7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 2;
                    j_l = 7;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 2;
                    b = 7;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[3][7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 3;
                    j_l = 7;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 3;
                    b = 7;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[4][7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 4;
                    j_l = 7;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 4;
                    b = 7;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[5][7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 5;
                    j_l = 7;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 5;
                    b = 7;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[6][7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 6;
                    j_l = 7;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 6;
                    b = 7;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });

        buttons[7][7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!moves){
                    i_k = 7;
                    j_l = 7;
                    moves = true;
                    move_pic = buttons[i_k][j_l].getForeground();
                    move_set(buttons, map_black, map_white, i_k, j_l);
                }
                else{
                    a = 7;
                    b = 7;
                    moves = false;
                    move(buttons, a, b, move_pic, no_pic, i_k, j_l);
                }
            }
        });
        //Mentett játék ellenörzése false -> új játék, true -> mentett
        Bundle bundle = getIntent().getExtras();
        try{
            assert bundle != null;
            loaded_game = bundle.getBoolean("loaded");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //mentett játék betöltése
        if (loaded_game){
            name = bundle.getString("name", "defValue");
            map_black = (String[][])bundle.getSerializable("map_B");
            map_white = (String[][])bundle.getSerializable("map_W");
            String[] next_step = bundle.getString("next_step","defValue").split(" ");
            next = next_step[0];
            setMapLoaded();
        }
        //új játék alaphelyzetbe állítása
        else{
            name = bundle.getString("name", "defValue");
            map_black = setBlackDefault();
            map_white = setWhiteDefault();
            for(int x = 0; x < 8; x++){
                for (int y = 0; y < 8; y++){
                    if(x == 0) buttons[x][y].setClickable(true);
                    else buttons[x][y].setClickable(false);
                }
            }
        }

        TextView edit_name = findViewById(R.id.Name_text);
        edit_name.setText(" Játékos: " + name + " ");
    }
    //pálya színezése
    public void setMapDefault(){
        map_colors[0][0] = "o";
        map_colors[0][1] = "db";
        map_colors[0][2] = "lb";
        map_colors[0][3] = "ro";
        map_colors[0][4] = "c";
        map_colors[0][5] = "re";
        map_colors[0][6] = "g";
        map_colors[0][7] = "b";

        map_colors[1][0] = "re";
        map_colors[1][1] = "o";
        map_colors[1][2] = "ro";
        map_colors[1][3] = "g";
        map_colors[1][4] = "db";
        map_colors[1][5] = "c";
        map_colors[1][6] = "b";
        map_colors[1][7] = "lb";

        map_colors[2][0] = "g";
        map_colors[2][1] = "ro";
        map_colors[2][2] = "o";
        map_colors[2][3] = "re";
        map_colors[2][4] = "lb";
        map_colors[2][5] = "b";
        map_colors[2][6] = "c";
        map_colors[2][7] = "db";

        map_colors[3][0] = "ro";
        map_colors[3][1] = "lb";
        map_colors[3][2] = "db";
        map_colors[3][3] = "o";
        map_colors[3][4] = "b";
        map_colors[3][5] = "g";
        map_colors[3][6] = "re";
        map_colors[3][7] = "c";

        map_colors[4][0] = "c";
        map_colors[4][1] = "re";
        map_colors[4][2] = "g";
        map_colors[4][3] = "b";
        map_colors[4][4] = "o";
        map_colors[4][5] = "db";
        map_colors[4][6] = "lb";
        map_colors[4][7] = "ro";

        map_colors[5][0] = "db";
        map_colors[5][1] = "c";
        map_colors[5][2] = "b";
        map_colors[5][3] = "lb";
        map_colors[5][4] = "re";
        map_colors[5][5] = "o";
        map_colors[5][6] = "ro";
        map_colors[5][7] = "g";

        map_colors[6][0] = "lb";
        map_colors[6][1] = "b";
        map_colors[6][2] = "c";
        map_colors[6][3] = "db";
        map_colors[6][4] = "g";
        map_colors[6][5] = "ro";
        map_colors[6][6] = "o";
        map_colors[6][7] = "re";

        map_colors[7][0] = "b";
        map_colors[7][1] = "g";
        map_colors[7][2] = "re";
        map_colors[7][3] = "c";
        map_colors[7][4] = "ro";
        map_colors[7][5] = "lb";
        map_colors[7][6] = "db";
        map_colors[7][7] = "o";
    }
    //játékos lépésnek ellenörzése
    public void move_set(Button[][] buttons, String[][] map_black, String[][] map_white, int x, int y){
        boolean player_steps = false;
        int a = x;
        int b = y;
        //minden mező kattintásának tiltása, majd a lehetséges lépések mezejének engedélyezése
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++ ) buttons[i][j].setClickable(false);
        while(true){
            a++;
            if(a == 8)
                break;
            if (map_black[a][b].contains("none") && map_white[a][b].contains("none"))
            {
                buttons[a][b].setClickable(true);
                buttons[a][b].setForeground(black);
                player_steps = true;
            }
            else
                break;
        }
        a = x;
        while(true){
            a++;
            if(a == 8)
                break;
            b++;
            if(b == 8)
                break;
            if (map_black[a][b].contains("none") && map_white[a][b].contains("none"))
            {
                buttons[a][b].setClickable(true);
                buttons[a][b].setForeground(black);
                player_steps = true;
            }
            else
                break;
        }
        a = x;
        b = y;
        while(true){
            a++;
            if(a == 8)
                break;
            b--;
            if(b < 0) {
                break;
            }
            if (map_black[a][b].contains("none") && map_white[a][b].contains("none"))
            {
                buttons[a][b].setClickable(true);
                buttons[a][b].setForeground(black);
                player_steps = true;
            }
            else
                break;
        }
        //Ha nem tud lépni veszített
        if(!player_steps){
            end = "Nem tudsz lépni, vesztettél!";
            openEnd();
        }
    }
    //fekete bábuk elhelyezkedésének alaphelyzete
    public String[][] setBlackDefault(){
        String[][] map = new String[8][8];
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) map[x][y] = "none";
        }
        map[0][0] = "o";
        map[0][1] = "db";
        map[0][2] = "lb";
        map[0][3] = "ro";
        map[0][4] = "c";
        map[0][5] = "re";
        map[0][6] = "g";
        map[0][7] = "b";
        return map;
    }
    //fehér bábuk elhelyezkedésének alaphelyzete
    public String[][] setWhiteDefault(){
        String[][] map = new String[8][8];
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) map[x][y] = "none";
        }
        map[7][0] = "b";
        map[7][1] = "g";
        map[7][2] = "re";
        map[7][3] = "c";
        map[7][4] = "ro";
        map[7][5] = "lb";
        map[7][6] = "db";
        map[7][7] = "o";
        return map;
    }

    public void steps_clear(){
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (buttons[x][y].getForeground() == black){
                    buttons[x][y].setForeground(no_pic);
                }
            }
        }
    }
    //lépés végrehajtása
    @SuppressLint("SetTextI18n")
    public void move(Button[][] buttons, int i, int j, Drawable move_pic, final Drawable no_pic, int a, int b){
        //fekete bábu lépése
        buttons[a][b].setForeground(no_pic);
        steps_clear();
        String act = map_black[a][b];
        map_black[a][b] = "none";
        map_black[i][j] = act;
        buttons[i][j].setForeground(move_pic);
        //győzelem ellenőrzése
        if(win_check_black()){
            end = "Gratulálok, nyertél!";
            openEnd();
            finish();
        }
        //ha nem nyert, a fehér lépése
        if(!win_check_black()){
            next = map_colors[i][j];
            int w_x = 0;
            int w_y = 8;
            search: for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    if (map_white[x][y].equals(next)){
                        w_x = x;
                        w_y = y;
                        break search;
                    }
                }
            }

            ArrayList<Button> next_step = new ArrayList<>();
            boolean enemi_step = false;

            a = w_x;
            b = w_y;
            while(true){
                a--;
                if(a < 0)
                    break;
                if (map_black[a][b].contains("none") && map_white[a][b].contains("none")){
                    next_step.add(buttons[a][b]);
                    enemi_step = true;
                }

                else
                    break;
            }
            a = w_x;
            while(true){
                a--;
                if(a < 0)
                    break;
                b--;
                if(b < 0)
                    break;
                if (map_black[a][b].contains("none") && map_white[a][b].contains("none")){
                    next_step.add(buttons[a][b]);
                    enemi_step = true;
                }
                else
                    break;
            }
            a = w_x;
            b = w_y;
            while(true){
                a--;
                if(a < 0)
                    break;
                b++;
                if(b == 8)
                    break;
                if (map_black[a][b].contains("none") && map_white[a][b].contains("none")){
                    next_step.add(buttons[a][b]);
                    enemi_step = true;
                }
                else
                    break;
            }
            //ha a fehér nem tud lépni
            if(!enemi_step){
                end = "Gratulálok, nyertél!";
                openEnd();
            }
            //Fehér lépése, feket számára lpéps meghatározása
            Collections.shuffle(next_step);
            Button n_s = next_step.get(0);
            move_pic = buttons[w_x][w_y].getForeground();
            buttons[w_x][w_y].setForeground(no_pic);
            n_s.setForeground(move_pic);
            int s_x = 0, s_y = 0;
            for(int f = 0; f < 8; f++){
                for (int g = 0; g < 8; g++){
                    Drawable test = buttons[f][g].getForeground();
                    if(test == move_pic){
                        s_x = f;
                        s_y = g;
                    }
                }
            }
            map_white[s_x][s_y] = map_white[w_x][w_y];
            map_white[w_x][w_y] = "none";
            next = map_colors[s_x][s_y];
            win_check_white();
            TextView last = findViewById(R.id.text_last_step);
            last.setText(" Utoljára lépett: ");
            ImageView last_pic = findViewById(R.id.imageView_last);
            last_pic.setForeground(move_pic);

            for (int x = 0; x < 8; x++){
                for (int y = 0; y < 8; y++ ) {
                    if(map_black[x][y].equals(next)){
                        buttons[x][y].setClickable(true);
                        move_pic = buttons[x][y].getForeground();
                    }
                    else
                        buttons[x][y].setClickable(false);
                }
            }
            TextView next = findViewById(R.id.text_next);
            next.setText(" Következő lépés: ");
            ImageView next_pic = findViewById(R.id.image_next);
            next_pic.setForeground(move_pic);
        }
    }
    //Játék végének megynitása
    public void openEnd(){
        Intent intent = new Intent(this, game_end.class);
        intent.putExtra("end_type", end);
        startActivity(intent);
        finish();
    }
    //fekete játékos nyerésének ellenőrzése
    public boolean win_check_black(){
        for (int i = 0; i < 8; i++){
            if (!map_black[7][i].equals("none")){
                end = "Gratulálok, győztél!";
                return true;
            }
        }
        return false;
    }
    //fehér játékos nyerésének ellenőrzése
    public void win_check_white(){
        for (int i = 0; i < 8; i++){
            if (!map_white[0][i].equals("none")){
                end = "Sajnos vesztettél!";
                openEnd();
            }
        }
    }
    //Megállítás megnyitása, adatok átadása esetleges mentéshez
    public void openPause(){
        Intent intent = new Intent(this, Pause.class);
        Bundle b  =new Bundle();
        Bundle w = new Bundle();
        b.putSerializable("B", map_black);
        w.putSerializable("W", map_white);
        intent.putExtra("next_step", next);
        intent.putExtra("multi", false);
        intent.putExtra("Name", name);
        intent.putExtras(b);
        intent.putExtras(w);
        startActivity(intent);
    }
    //mentett játék adatainak betöltése, pálya beállítása megfelelően
    @SuppressLint("SetTextI18n")
    public void setMapLoaded(){
        Drawable b_o = buttons[0][0].getForeground();
        Drawable b_db = buttons[0][1].getForeground();
        Drawable b_lb = buttons[0][2].getForeground();
        Drawable b_ro = buttons[0][3].getForeground();
        Drawable b_c = buttons[0][4].getForeground();
        Drawable b_re = buttons[0][5].getForeground();
        Drawable b_g = buttons[0][6].getForeground();
        Drawable b_b = buttons[0][7].getForeground();

        Drawable w_o = buttons[7][0].getForeground();
        Drawable w_db = buttons[7][1].getForeground();
        Drawable w_lb = buttons[7][2].getForeground();
        Drawable w_ro = buttons[7][3].getForeground();
        Drawable w_c = buttons[7][4].getForeground();
        Drawable w_re = buttons[7][5].getForeground();
        Drawable w_g = buttons[7][6].getForeground();
        Drawable w_b = buttons[7][7].getForeground();
        //fekete bábuk elhelyezése
        for (int x = 0; x < 8; x++){
            for (int y = 0; y < 8; y++ ) {
                switch (map_black[x][y]){
                    case "o":
                        buttons[x][y].setForeground(b_o);
                        break;
                    case "db":
                        buttons[x][y].setForeground(b_db);
                        break;
                    case "lb":
                        buttons[x][y].setForeground(b_lb);
                        break;
                    case "ro":
                        buttons[x][y].setForeground(b_ro);
                        break;
                    case "c":
                        buttons[x][y].setForeground(b_c);
                        break;
                    case "re":
                        buttons[x][y].setForeground(b_re);
                        break;
                    case "g":
                        buttons[x][y].setForeground(b_g);
                        break;
                    case "b":
                        buttons[x][y].setForeground(b_b);
                        break;
                    default:
                        buttons[x][y].setForeground(no_pic);
                        break;
                }
            }
        }
        //fehér bábuk elhelyezése
        for (int x = 0; x < 8; x++){
            for (int y = 0; y < 8; y++ ) {
                switch (map_white[x][y]){
                    case "o":
                        buttons[x][y].setForeground(w_o);
                        break;
                    case "db":
                        buttons[x][y].setForeground(w_db);
                        break;
                    case "lb":
                        buttons[x][y].setForeground(w_lb);
                        break;
                    case "ro":
                        buttons[x][y].setForeground(w_ro);
                        break;
                    case "c":
                        buttons[x][y].setForeground(w_c);
                        break;
                    case "re":
                        buttons[x][y].setForeground(w_re);
                        break;
                    case "g":
                        buttons[x][y].setForeground(w_g);
                        break;
                    case "b":
                        buttons[x][y].setForeground(w_b);
                        break;
                    default:
                        if(map_black[x][y].equals("none"))
                            buttons[x][y].setForeground(no_pic);
                        break;
                }
            }
        }
        //követkető lépés beállítása
        for (int x = 0; x < 8; x++){
            for (int y = 0; y < 8; y++ ) {
                if(map_black[x][y].equals(next)){
                    buttons[x][y].setClickable(true);
                    TextView next = findViewById(R.id.text_next);
                    next.setText(" Következő lépés: ");
                    ImageView next_pic = findViewById(R.id.image_next);
                    next_pic.setForeground(buttons[x][y].getForeground());
                }
                else
                    buttons[x][y].setClickable(false);
            }
        }
    }
}