package com.bingo_pvp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class creatroom extends Activity {
	public static Handler mHandler = new Handler();
	String tmp; // 暫存文字訊息
	Socket clientSocket; // 客戶端socket
	ImageView im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creatroom);
    	// 以新的執行緒來讀取資料
    			Thread t = new Thread(readData);

    			// 啟動執行緒
    			t.start();

    			// 從資源檔裡取得位址後強制轉型成按鈕
    			//Button button1 = (Button) findViewById(R.id.Button01);

    			// 設定按鈕的事件
    		    im.setOnClickListener(new Button.OnClickListener() {
    				// 當按下按鈕的時候觸發以下的方法
    				public void onClick(View v) {
    					// 如果已連接則
    					if (clientSocket.isConnected()) {

    						BufferedWriter bw;

    						try {
    							// 取得網路輸出串流
    							bw = new BufferedWriter(new OutputStreamWriter(
    									clientSocket.getOutputStream()));

    							// 寫入訊息
    							//bw.write(EditText01.getText() + ":"
    									//+ EditText02.getText() + "\n");

    							// 立即發送
    							bw.flush();
    						} catch (IOException e) {

    						}
    						// 將文字方塊清空
    					//	EditText02.setText("");
    					}
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
    
 // 顯示更新訊息
 	private Runnable updateText = new Runnable() {
 		public void run() {
 			// 加入新訊息並換行
 			//TextView01.append(tmp + "\n");
 		}
 	};

 	// 取得網路資料
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
   
