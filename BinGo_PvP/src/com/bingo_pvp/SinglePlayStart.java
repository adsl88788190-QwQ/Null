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
	//判斷棋盤
	boolean[][] aiArrayAllow = new boolean[5][5];
	
	//儲存ai的棋盤
	String aiStr = "";
	//毫無反應就只是個button
	Button bt;
	//ai隨機點名的數字
	int aiGet = 0;
	//是否定義一個新的ai棋盤
	boolean aiSetUp = false;
		
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.textView1);
        bt = (Button)findViewById(R.id.button1);
        //定義新的棋盤
      	if(!aiSetUp)
      		aiSet();
		bt.setOnClickListener(new OnClickListener() {
			
			//按下button後，ai隨機點名一個不重複變數
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Random ran = new Random();
				//點擊button就隨機點名一個數字(1~25)
				aiGet = ran.nextInt(25)+1;
				//重複點名則重新點名
				while(aiArrayAllow[(aiGet-1)/5][(aiGet-1)%5])
					aiGet = ran.nextInt(25)+1;
				Log.d("aiGet", Integer.toString(aiGet));
				//數字(aiGet)存回ai之boolean陣列
				//(aiGet-1)/5=row
				//(aiGet-1)%5=column
				aiArrayAllow[(aiGet-1)/5][(aiGet-1)%5] = true;
				aiUpDate();
			}
		});
		//刷新棋盤
//		if(aiClick)
//			aiUpDate();
		tv.setText(aiStr);
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
    void aiSet(){
    	//重置ai的棋盤
    	aiStr = "";
    	for(int i=0; i<aiArray.length; i++){
        	for(int j=0; j<aiArray[i].length; j++){
        		aiArray[i][j] = i*5+j+1;
        		aiArrayAllow[i][j] = false;
        		//個位數前碼+0
        		if(aiArray[i][j] / 10 == 0)
        			aiStr += 0;
        		aiStr += aiArray[i][j]+" ";
        		//每5個換行
        		if(aiArray[i][j] % 5 == 0)
        			aiStr += "\n";
        	}
        }
    	//tv.setText(aiStr);
		aiSetUp = true;
    }
    
    //刷新ai棋盤
    void aiUpDate(){
    	//重置ai的棋盤
    	aiStr = "";
    	for(int i=0; i<aiArray.length; i++){
        	for(int j=0; j<aiArray[i].length; j++){
        		aiStr += " ";
        		//個位數前碼+0
        		if(aiArray[i][j] / 10 == 0)
        			aiStr += 0;
        		aiStr += aiArray[i][j];
        		//如果該數字被點名，就標記*
        		if(aiArrayAllow[i][j])
        			aiStr += "*";
        		else
        			aiStr += " ";
        		//每5個換行
        		if(aiArray[i][j] % 5 == 0)
        			aiStr += "\n";
        	}
        }
    	tv.setText(aiStr);
    }
}
