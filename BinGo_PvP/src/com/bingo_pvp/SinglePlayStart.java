package com.bingo_pvp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SinglePlayStart extends Activity {

	// 判斷勝負
	Check playcheck, aicheck;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.singlevscomputer);
		AIInit(); // AI初始設定
		PlayInit(); // 玩家初始設定
	}

	// AI初始設定
	ImageView[][] ai;
	// 儲存ai的陣列
	int[][] aiArray = new int[5][5];
	// 儲存ai是否按下
	boolean [][] AIClick = new boolean[5][5];
	//AI設定
	private void AIInit() {
		// 設定AI初始棋盤
		ArrayList<Integer> temp = new ArrayList<Integer>();
		// 填滿1 - 25
		for (int i = 1; i <= 25; i++)
			temp.add(i);
		// 打亂1 - 25
		for (int i = new Random().nextInt(7); i > 0; i--)
			Collections.shuffle(temp);
		// 放入棋盤陣列
		for (int i = 0; i < 25; i++)
			aiArray[i / 5][i % 5] = temp.get(i);
		// imageview findviewbyid
		ai = new ImageView[5][5];
		for (int i = 0; i < 25; i++) {
			// findViewById
			int id = getResources().getIdentifier("imageView" + (i + 1), "id",
					getPackageName());
			ai[i / 5][i % 5] = (ImageView) findViewById(id);
			// setImageResource
			id = getResources().getIdentifier("p" + temp.get(i), "drawable",
					getPackageName());
			ai[i / 5][i % 5].setImageResource(id);
		}
	}

	// 玩家初始設定
	ImageView[][] Play;
	// 儲存Play的陣列
	int[][] PlayArray = new int[5][5];
	// 儲存Play是否按下
	boolean [][] PlayClick = new boolean[5][5];
	//玩家設定
	private void PlayInit() {
		//獲得玩家棋盤
		ArrayList<String> temp = Cheese.al;
		Play = new ImageView[5][5];
		// 放入棋盤陣列
		for (int i = 0; i < 25; i++)
			PlayArray[i / 5][i % 5] = Integer.parseInt(temp.get(i));
		// imageview findviewbyid
		for (int i = 0; i < 25; i++) {
			// findViewById
			int id = getResources().getIdentifier("imageView" + (i + 26), "id",
					getPackageName());
			Play[i / 5][i % 5] = (ImageView) findViewById(id);
			Play[i / 5][i % 5].setOnClickListener(click);
			// setImageResource
			id = getResources().getIdentifier("p" + temp.get(i), "drawable",
					getPackageName());
			Play[i / 5][i % 5].setImageResource(id);
		}
	}
	//偵測玩家按下棋盤
	OnClickListener click = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
	};
}
