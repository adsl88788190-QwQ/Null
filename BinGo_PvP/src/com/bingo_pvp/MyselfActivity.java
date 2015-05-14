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


public class MyselfActivity extends Activity {
	Button[] bt;
	Button finishbt,clearbt,returnbt;
	ArrayList<String> al= null;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myselflayout);
        al = new ArrayList<String>();
    }
    
    public void BT_Click(){
    	//中間的棋盤
        bt = new Button[25];
        for(int i = 0;i<25;i++){
        	String num = (i+1)+"";       	
        	String str = "button"+num;
        	int id = getResources().getIdentifier(str, "id", getPackageName());
        	bt[i] = (Button)findViewById(id);
        }
    	finishbt = (Button)findViewById(R.id.finishbt);
    	finishbt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Cheese.al = al;
				finish();
			}
		});
    	//清除所有
    	clearbt = (Button)findViewById(R.id.clearbt);
    	clearbt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				for(Button button:bt)
					button.setText("");
			}
		});
    	//回上一步
    	returnbt = (Button)findViewById(R.id.returnbt);
    }
    public OnClickListener click = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Button bt = (Button)v;
		}
	};
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
