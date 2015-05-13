package com.bingo_pvp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class RandomActivity extends Activity {
	Button[] bt;
	Button randbt,finishbt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.randgame);
        //所有 棋子 的 位置
        final ArrayList<String> al = new ArrayList<String>();
        //中間的棋盤
        bt = new Button[25];
        for(int i = 0;i<25;i++){
        	String num = (i+1)+"";
        	al.add(num);
        	String str = "button"+num;
        	int id = getResources().getIdentifier(str, "id", getPackageName());
        	bt[i] = (Button)findViewById(id);
        }
        //rand鍵
        randbt = (Button)findViewById(R.id.button26);
        randbt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int t = new Random().nextInt(7);
				for(;t>=0;t--)
					Collections.shuffle(al);
			}
		});
       
        //finish鍵
        finishbt = (Button)findViewById(R.id.button27);
        finishbt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Cheese.al = al;
			finish();
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
}
