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
}
