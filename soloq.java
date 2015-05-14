package com.example.au.bingo_pvp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class soloq extends ActionBarActivity {
    TextView[] tv;
    Button randbt,finishbt,startbt;
    ArrayList<String> al= null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!Cheese.boolean1){
            al = new ArrayList<String>();
            for(int i = 0;i<25;i++)
                al.add((i+1)+"");
        }
        else{
            al = Cheese.al;
        }
        BT_Click();
    }
    public void BT_Click(){
        //中間的棋盤
        tv = new TextView[25];
        for(int i = 0;i<25;i++){
            String num = (i+1)+"";
            String str = "button"+num;
            int id = getResources().getIdentifier(str, "id", getPackageName());
            tv[i] = (TextView)findViewById(id);
            tv[i].setText(al.get(i));
        }
        //rand鍵
        randbt = (Button)findViewById(R.id.randbt);
        randbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                int t = new Random().nextInt(7);
                for(;t>=0;t--)
                    Collections.shuffle(al);
                for(int i = 0;i<25;i++)
                    tv[i].setText(al.get(i));
            }
        });

        //finish鍵
        finishbt = (Button)findViewById(R.id.finishbt);
        finishbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Cheese.al = al;
                Cheese.boolean1 = true;
                finish();
            }
        });

        startbt = (Button)findViewById(R.id.startbt);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
