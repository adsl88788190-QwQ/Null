package com.bingo_pvp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
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
        //開始遊戲
        Button bt = (Button)findViewById(R.id.button1);
        bt.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SinglePlayInit.this, SinglePlayStart.class);
				startActivity(intent);
			}
		});
    }
    
    public void BT_Click(){
    	im = new ImageView[25];
        for(int i = 0;i<25;i++){     	
        	String str = "imageView"+(i+26);
        	int id = getResources().getIdentifier(str, "id", getPackageName());
        	im[i] = (ImageView)findViewById(id);
        	id = getResources().getIdentifier("p"+al.get(i), "drawable", getPackageName());
        	im[i].setImageResource(id);
        	//長按棋盤 進入 設定 棋盤
        	im[i].setOnLongClickListener(new OnLongClickListener() {
    			@Override
    			public boolean onLongClick(View v) {
    				// 長按棋盤 進入 設定 棋盤
    				Intent intent = new Intent(SinglePlayInit.this, SetActivity.class);
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
    	//設定棋盤
    	BT_Click();    	
    }
}
