package com.bingo_pvp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class ChooseRoom extends Activity {
	Button CreateRoom,IntoRoom,RandRoom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_selectroom);
        //創建房間
        CreateRoom = (Button)findViewById(R.id.CreateRoom);
        CreateRoom.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ChooseRoom.this,creatroom.class);
				startActivity(intent);
			}
		});
        //進入房間
        IntoRoom = (Button)findViewById(R.id.IntoRoom);
        IntoRoom.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ChooseRoom.this,roomchoice.class);
				startActivity(intent);
			}
		});
        //隨機對戰
        RandRoom = (Button)findViewById(R.id.RandRoom);
        RandRoom.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ChooseRoom.this,creatroom.class);
				startActivity(intent);
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
