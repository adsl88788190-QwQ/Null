package com.bingo_pvp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
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
		show = (TextView)findViewById(R.id.textView2);//連線數
		show.setText("玩家   0 : 0   電腦");
	}

	// AI初始設定
	ImageView[][] ai;
	// 儲存ai的陣列
	int[][] aiArray = new int[5][5];
	// 儲存ai是否按下
	boolean[][] AIClick = new boolean[5][5];

	// AI設定
	private void AIInit() {
		// 設定AI初始棋盤
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for (boolean[] temp1 : AIClick)
			for (boolean temp2 : temp1)
				temp2 = false;
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
	boolean[][] PlayClick = new boolean[5][5];

	// 玩家設定
	private void PlayInit() {
		// 獲得玩家棋盤
		ArrayList<String> temp = Cheese.al;
		Play = new ImageView[5][5];
		for (boolean[] temp1 : PlayClick)
			for (boolean temp2 : temp1)
				temp2 = false;
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

	// 電腦等待時間
	private boolean wait = true;
	// 偵測玩家按下棋盤
	OnClickListener click = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int ClickId = v.getId();
			int id;
			for (int i = 0; i < 25; i++) {
				id = getResources().getIdentifier("imageView" + (i + 26), "id",
						getPackageName());
				// 比對看是哪個ImageView
				if (ClickId == id && !PlayClick[i / 5][i % 5]) {
					// setImageResource
					id = getResources().getIdentifier(
							"g" + PlayArray[i / 5][i % 5], "drawable",
							getPackageName());
					Play[i / 5][i % 5].setImageResource(id);
					PlayClick[i / 5][i % 5] = true;
					// 電腦顯示 同一個數字
					for (int j = 0; j < 5; j++) {
						for (int k = 0; k < 5; k++) {
							if (aiArray[j][k] == PlayArray[i / 5][i % 5]) {
								ai[j][k].setImageResource(id);
								AIClick[j][k] = true;
							}
						}
					}
					//判斷勝負
					if(!win()){// 電腦下一步
						AINext();
					}
					break;
				}
			}
		}
	};
	// 電腦選擇下一步
	AIChoose aiChoose = new AIChoose();

	public void AINext() {
		int choose = aiChoose.choose(AIClick);
		Log.d("line143", choose + "");
		// 電腦所選的數字
		int id = getResources().getIdentifier(
				"g" + (aiArray[choose / 10][choose % 10]), "drawable",
				getPackageName());
		ai[choose / 10][choose % 10].setImageResource(id);
		AIClick[choose / 10][choose % 10] = true;
		// 玩家顯示
		for (int k = 0; k < 5; k++) {
			for (int l = 0; l < 5; l++) {
				if (PlayArray[k][l] == aiArray[choose / 10][choose % 10]) {
					Play[k][l].setImageResource(id);
					PlayClick[k][l] = true;
				}
			}
		}
		//判斷勝負
		win();
	}
	//判斷勝負
	Check AICheck = new Check(AIClick);
	Check PlayCheck = new Check(PlayClick);
	TextView show;
	public boolean win(){
		//顯示行數比
		show.setText("玩家   "+PlayCheck.line()+" : "+AICheck.line()+"  電腦");
		//Dialog
		AlertDialog.Builder builder = new Builder(SinglePlayStart.this);
		builder.setTitle("賓果贏家");
		
		//判斷勝負
		if(AICheck.win() & PlayCheck.win()){//平手
			builder.setMessage("你與電腦打成了平手!!!");
		}else if(AICheck.win()){//AI win
			builder.setMessage("電腦碾碎你了!!!!!!!");
		}else if(PlayCheck.win()){//Play win
			builder.setMessage("你成功的擊敗了電腦!!!");
		}else return false;
		builder.setPositiveButton("確定。", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				//SinglePlayStart.this.finish();			
			}
		}).show();
		return true;
	}
}
