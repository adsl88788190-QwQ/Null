package com.bingo_pvp;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class Play extends Activity {
	Button singlebt,multiplebt,sharebt;
	ImageView imageView;
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
				Intent intent = new Intent(Play.this,ChooseRoom.class);
				startActivity(intent);	
			}
		});
        //發表成績
        sharebt = (Button)findViewById(R.id.Sharebt);
        sharebt.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				//發表成績的intent 泰佑負責
				Scorejpg scorejpg = new Scorejpg();
				Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.black);
				bitmap = bitmap.createScaledBitmap(bitmap, 100, 100,true);
				bitmap = scorejpg.createDieImage(bitmap, 100, 100, (int)(Math.random()*1000)+"");
				
				
				
				Intent intent=new Intent(Intent.ACTION_SEND);
				intent.setType("image/jpg");
				Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, null,null));
				intent.putExtra(Intent.EXTRA_STREAM, uri);
				intent.putExtra(Intent.EXTRA_SUBJECT, "「分享」 Bingo_PvP");
				intent.putExtra(Intent.EXTRA_TEXT, "真的超好玩的啦!!。");
				startActivity(Intent.createChooser(intent, getTitle()));
				imageView.setImageBitmap(bitmap);
			}
		});
        //測試用ImageView
        imageView = (ImageView)findViewById(R.id.imageView);
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
        	Toast.makeText(this, "去問泰佑啦!!", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
