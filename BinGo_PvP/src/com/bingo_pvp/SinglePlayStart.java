package com.bingo_pvp;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


public class SinglePlayStart extends Activity {
    Button[] bt;
    Button Startbt;
    ArrayList<String> al= null;
    LinearLayout Layout;
    int[][] aiArray = new int[5][5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singleplay);
        if(!Cheese.boolean1){
            al = new ArrayList<String>();
            for(int i=0;i<25;i++)
                al.add(((i+1)+""));
            Cheese.boolean1 = true;
            Cheese.al = al;
        }else
            al = Cheese.al;

        BT_Click();
    }

    public void BT_Click(){
        //中間的棋盤
        bt = new Button[25];
        for(int i = 0;i<25;i++){
            String num = (i+1)+"";
            String str = "button"+num;
            int id = getResources().getIdentifier(str, "id", getPackageName());
            bt[i] = (Button)findViewById(id);
            bt[i].setText(al.get(i));
            //長按棋盤 進入 設定 棋盤
            bt[i].setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    // 長按棋盤 進入 設定 棋盤
                    Intent intent = new Intent(SinglePlayStart.this, SetActivity.class);
                    startActivity(intent);
                    return false;
                }
            });
        }
        //開始按鈕
        Startbt = (Button)findViewById(R.id.Startbt);
        Startbt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //單人遊戲開始 昶崴負責

            }
        });
        //長按Layout
        Layout = (LinearLayout)findViewById(R.id.SingleLayout);
        Layout.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // 長按棋盤 進入 設定 棋盤
                Intent intent = new Intent(SinglePlayStart.this, SetActivity.class);
                startActivity(intent);
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.play, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();


    }

    //AI 設定
    public void AIcontrol(){

        Random rdm = new Random();
    }

    int aiCount = 0;
    boolean[] aiAllow = new boolean[12];
    //aiAllow[0]=[0,0],[1,1],[2,2][3,3],[4,4]
    //       [1]=[0,4],[1,3],[2,2][2,3],[1,4]
    //       [2-6]=橫線
    //       [7-11]=直線
    
    //ai 連線計數器
    public void aiCount(){
        if(aiAllow[0]&&(aiArray[0][0] == 1 && aiArray[1][1] == 1 && aiArray[2][2] == 1 && aiArray[3][3] == 1 && aiArray[4][4] == 1)){
            aiCount++;
        }
        if(aiAllow[1]&&(aiArray[0][4] == 1 && aiArray[1][3] == 1 && aiArray[2][2] == 1 && aiArray[3][1] == 1 && aiArray[4][0] == 1)){
            aiCount++;
        }
        for(int i = 0; i<aiArray.length; i++) {
            if ((aiArray[i][0] == 1 && aiArray[i][1] == 1 && aiArray[i][2] == 1 && aiArray[i][3] == 1 && aiArray[i][4] == 1)){
                aiCount++;
            }
            if ((aiArray[0][i] == 1 && aiArray[1][i] == 1 && aiArray[2][i] == 1 && aiArray[3][i] == 1 && aiArray[4][i] == 1)){
                aiCount++;
            }
        }
    }
}
