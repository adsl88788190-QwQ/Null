package com.bingo_pvp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class creatroom extends Activity implements OnClickListener {
	public static Handler mHandler = new Handler();
	String tmp; // 暫存文字訊息
	Socket clientSocket; // 客戶端socket
	int [][] self=new int[5][5];
	int temp;
	ImageView[][] im;
	int num=0;
	Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creatroom);
        /////
        // 以新的執行緒來讀取資料
		Thread t = new Thread(readData);

		// 啟動執行緒
		t.start();
        
        
        /////
		start=(Button)findViewById(R.id.button1);
        for(int x=0;x<5;x++)
            for(int y=0;y<5;y++){
        	self[x][y]=num+1;
        	num++;
            }
        for(int c=0;c<100;c++){
        	int x1=(int)(Math.random()*5),
        		y1=(int)(Math.random()*5),
        		x2=(int)(Math.random()*5),
        		y2=(int)(Math.random()*5);
        	temp=self[x1][y1];
        	self[x1][y1]=self[x2][y2];
        	self[x2][y2]=temp;
        }
        BT_Click();

    }
    public void BT_Click(){
    	int i=0;
    	im = new ImageView[5][5];
        for(int x=0;x<5;x++)
            for(int y=0;y<5;y++){   	
        	String str = "imageView"+(i+26);
        	i++;
        	int id = getResources().getIdentifier(str, "id", getPackageName());
        	im[x][y] = (ImageView)findViewById(id);
        	id = getResources().getIdentifier("p"+self[x][y], "drawable", getPackageName());
        	im[x][y].setImageResource(id);
        	im[x][y].setOnClickListener(this);
        	//長按棋盤 進入 設定 棋盤
        	/*im[x][y].setOnLongClickListener(new OnLongClickListener() {
    			@Override
    			public boolean onLongClick(View v) {
    				// 長按棋盤 進入 設定 棋盤
    				Intent intent = new Intent(creatroom.this, SetActivity.class);
    				startActivity(intent);
    				return false;
    			}
    		});*/
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
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
			case R.id.imageView26:
				// 如果已連接則
				if (clientSocket.isConnected()) {
					Log.d("test","WTF");
					BufferedWriter bw;
					try {
						// 取得網路輸出串流.
						bw = new BufferedWriter(new OutputStreamWriter(
								clientSocket.getOutputStream()));

						// 寫入訊息
						bw.write("123");
						Log.d("test","WTrrrr");
						// 立即發送
						bw.flush();
					} catch (IOException e) {

					}
				}
				break;
			case R.id.button1:
				// 如果已連接則
				if (clientSocket.isConnected()) {
					BufferedWriter bw;
					try {
						// 取得網路輸出串流
						bw = new BufferedWriter(new OutputStreamWriter(
								clientSocket.getOutputStream()));

						// 寫入訊息
						bw.write("123");

						// 立即發送
						bw.flush();
					} catch (IOException e) {

					}
				}
				break;
		
		}
		
	}
	// 顯示更新訊息
	private Runnable updateText = new Runnable() {
		public void run() {
			// 加入新訊息並換行
			start.setText(tmp);
			System.out.print(tmp);
		}
	};
	private Runnable readData = new Runnable() {
 		public void run() {
 			// server端的IP
 			InetAddress serverIp;

 			try {
 				// 以內定(本機電腦端)IP為Server端
 				serverIp = InetAddress.getByName("10.0.2.2");
 				int serverPort = 5050;
 				clientSocket = new Socket(serverIp, serverPort);

 				// 取得網路輸入串流
 				BufferedReader br = new BufferedReader(new InputStreamReader(
 						clientSocket.getInputStream()));

 				// 當連線後
 				while (clientSocket.isConnected()) {
 					// 取得網路訊息
 					tmp = br.readLine();

 					// 如果不是空訊息則
 					if (tmp != null)
 						// 顯示新的訊息
 						mHandler.post(updateText);
 				}

 			} catch (IOException e) {

 			}
 		}
 	};

    
}
   
