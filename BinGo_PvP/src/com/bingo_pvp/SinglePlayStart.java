package com.example.soloqchess;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

	//顯示ai的棋盤
	TextView tv;
	//儲存ai的陣列
	int[][] aiArray = new int[5][5];
	//判斷
	boolean[][] aiArrayAllow = new boolean[5][5];
	
	//儲存ai的棋盤
	String aiStr = "";
	//毫無反應就只是個button
	Button bt;
	//ai隨機到的數字
	int aiGet = 0;
	//是否定義一個新的ai棋盤
	boolean gamesetup = false;
		
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.textView1);
        bt = (Button)findViewById(R.id.button1);
        //定義新的棋盤
      		if(!gamesetup)
      			gameSet();
		bt.setOnClickListener(new OnClickListener() {
			
			//按下button就讓ai挑一個變數
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Random ran = new Random();
				//點擊button就隨機點名一個數字
				aiGet = ran.nextInt(25)+1;
				Log.d("aiGet", Integer.toString(aiGet));
				//數字(aiGet)存回ai之boolean陣列
				//(aiGet-1)/5=row
				//(aiGet-1)%5=column
				aiArrayAllow[(aiGet-1)/5][(aiGet-1)%5] = true;
				//檢測boolean是否有變動
				//Log.d("allow",String.valueOf((aiArrayAllow[(aiGet-1)/5][(aiGet-1)%5])?1:0));
				//Log.d("allow", Integer.toString((aiGet-1)/5)+" "+Integer.toString((aiGet-1)%5));				
			}
		});
		//刷新棋盤
		setupText();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    
    //定義aiStr內的棋盤
    void gameSet(){
    	aiStr = "";
    	//Log.d("83", "83");
    	for(int i=0; i<aiArray.length; i++){
        	for(int j=0; j<aiArray[i].length; j++){
        		aiArray[i][j] = i*5+j+1;
        		aiArrayAllow[i][j] = false;
        		//個位數+0
        		if(aiArray[i][j] / 10 == 0)
        			aiStr += 0;
        		aiStr += aiArray[i][j]+" ";
        		//每5個換行
        		if(aiArray[i][j] % 5 == 0)
        			aiStr += "\n";
        		//Log.d("95", "95");
        	}
        }
		gamesetup = true;
    }
    
    //刷新ai期盤
    void setupText(){
    	aiStr = "";
		//Log.d("96", "96");
    	for(int i=0; i<aiArray.length; i++){
        	for(int j=0; j<aiArray[i].length; j++){
        		aiStr += " ";
        		//個位數+0
        		if(aiArray[i][j] / 10 == 0)
        			aiStr += 0;
        		aiStr += aiArray[i][j];
        		//如果該數字被點名，就標記*
        		Log.d("r119-allow",String.valueOf((aiArrayAllow[i][j])?1:0));
        		if(aiArrayAllow[i][j])
        			aiStr += "*";
        		else
        			aiStr += " ";
        		//每5個換行
        		if(aiArray[i][j] % 5 == 0)
        			aiStr += "\n";
        		//Log.d("119", "119");
        	}
        }
    	Log.d("aiStr", aiStr);
    	tv.setText(aiStr);
    }
    
}
