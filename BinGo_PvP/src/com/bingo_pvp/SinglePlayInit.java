package com.bingo_pvp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;


public class SinglePlayInit extends Activity {
	ImageView[] im;
	ArrayList<String> al= null;
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creatroom);     
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
    	im = new ImageView[25];
        for(int i = 0;i<25;i++){
        	String num = (i+26)+"";       	
        	String str = "imageView"+num;
        	int id = getResources().getIdentifier(str, "id", getPackageName());
        	im[i] = (ImageView)findViewById(id);
        	//長按棋盤 進入 設定 棋盤
        	im[i].setOnLongClickListener(new OnLongClickListener() {
    			@Override
    			public boolean onLongClick(View v) {
    				// 長按棋盤 進入 設定 棋盤
    				Intent intent = new Intent(SinglePlayInit.this, SinglePlayStart.class);
    				startActivity(intent);
    				return false;
    			}
    		});
        }
    	
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
}
