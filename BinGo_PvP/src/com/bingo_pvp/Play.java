package com.bingo_pvp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class Play extends Activity {
	Button singlebt,multiplebt,sharebt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_start);
        //單人模式
        singlebt = (Button)findViewById(R.id.Singlebt);
        singlebt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//單人的intent 昶崴負責
				Intent intent = new Intent(Play.this,SinglePlayStart.class);
				startActivity(intent);			
			}
		});
        //多人模式
        multiplebt = (Button)findViewById(R.id.Multiplebt);
        multiplebt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//多人的intent 家豪負責
				
			}
		});
        //發表成績
        sharebt = (Button)findViewById(R.id.Sharebt);
        sharebt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//發表成績的intent 泰佑負責
				
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
