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

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class creatroom extends Activity implements OnClickListener, OnTouchListener {
	public static Handler mHandler = new Handler();
	String tmp; // 暫存文字訊息
	Socket clientSocket; // 客戶端socket
	int [][] self=new int[5][5];
	int temp;
	ImageView[][] im;
	int num=0;
	Button start;
	int choice;
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
    //
    //對手的棋盤
    ImageView[][] play2;
    public void BT_Click(){
    	int i=0;
    	im = new ImageView[5][5];
    	//對手棋盤宣告
    	play2 = new ImageView[5][5];
    	for(int x=0;x<5;x++)
            for(int y=0;y<5;y++){   	
        	String str = "imageView"+(x*5+y+1);
        	Log.d("line82",str);
        	int id = getResources().getIdentifier(str, "id", getPackageName());
        	play2[x][y] = (ImageView)findViewById(id);        	
        	play2[x][y].setImageResource(R.drawable.unknow); 	
       }
        for(int x=0;x<5;x++)
            for(int y=0;y<5;y++){   	
        	String str = "imageView"+(i+26);
        	i++;
        	int id = getResources().getIdentifier(str, "id", getPackageName());
        	im[x][y] = (ImageView)findViewById(id);
        	id = getResources().getIdentifier("p"+self[x][y], "drawable", getPackageName());
        	im[x][y].setImageResource(id);
        	im[x][y].setOnClickListener(this);
        	im[x][y].setOnTouchListener(this);        	
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
			case R.id.button1:
				
				break;
		
		}
		
	}
	// 顯示更新
	private Runnable updateText = new Runnable() {
		public void run() {
			int i=0;
			
			 for(int x=0;x<5;x++)
		            for(int y=0;y<5;y++){
		            	Log.d("123",tmp+"..."+String.valueOf(self[x][y]));
			        	String str = "imageView"+(i+26);
			        	i++;
			        	int id = getResources().getIdentifier(str, "id", getPackageName());
			        	if(tmp.equals(String.valueOf(self[x][y]))){
		            		 id = getResources().getIdentifier("g"+self[x][y], "drawable", getPackageName());
				        	im[x][y].setImageResource(id);
		            	}	

		            }
			System.out.print(tmp);
		}
	};
	private Runnable readData = new Runnable() {
 		public void run() {
// 			InetAddress serverIp;
 			String serverip;
 			try {
// 				serverIp = InetAddress.getByName("10.0.2.2");
 				int serverPort = 5050;
 				serverip = "172.16.94.16";
 				clientSocket = new Socket(serverip, serverPort);

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
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
	        for(int x=0;x<5;x++)
	            for(int y=0;y<5;y++){  
	            	if(v.getId()==im[x][y].getId()){
	            		if (clientSocket.isConnected()) {
	    					Log.d("test","WTF");
	    					BufferedWriter bw;
	    					try {
	    						// 取得網路輸出串流.
	    						bw = new BufferedWriter(new OutputStreamWriter(
	    								clientSocket.getOutputStream()));

	    						// 寫入訊息
	    						bw.write(String.valueOf(self[x][y])+"\n");
	    						Log.d("test","WTrrrr");
	    						// 立即發送
	    						bw.flush();
	    					} catch (IOException e) {

	    					}
	    				}
	            	}
	            }
			break;
	}

		
		return false;
	}

    
}
   
