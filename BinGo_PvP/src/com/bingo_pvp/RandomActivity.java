package com.bingo_pvp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class RandomActivity extends Activity {
	Button[] bt;
	Button randbt,finishbt;
	ArrayList<String> al= null;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.randgame);
        if(Cheese.al != null)
        	al = Cheese.al;
        BT_Click();
    }
    
    public void BT_Click(){
    	//中間的棋盤
        bt = new Button[25];
        for(int i = 0;i<25;i++){
        	String num = (i+1)+"";       	
        	String str = "button"+num;
        	int id = getResources().getIdentifier(str, "id", getPackageName());
        	bt[i] = (Button)findViewById(id);
        	if(al != null)
        		bt[i].setText(al.get(i));
        	else
        		bt[i].setText(i+"");
        }
    	//rand鍵
        randbt = (Button)findViewById(R.id.randbt);
        randbt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ArrayList<String> temp = new ArrayList<String>();
				for(int i = 1;i<26;i++)
					temp.add(i+"");
				int t = new Random().nextInt(7);
				for(;t>=0;t--)
					Collections.shuffle(temp);
				for(int i = 0;i<25;i++){
					bt[i].setText(temp.get(i));
				}
				Cheese.al = al;
			}
		});
       
        //finish鍵
        finishbt = (Button)findViewById(R.id.finishbt);
        finishbt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Cheese.al = al;
				Cheese.boolean1 = true;
				SaveCheese();
			finish();
			}
		});
    }

    public void SaveCheese(){
    	ArrayList<String> al = Cheese.al;
    	String str = al.get(0);
    	for(int i = 1;i<al.size();i++)
    		str += ","+al.get(i);    	
    	SharedPreferences save = getSharedPreferences("Cheese",0);
    	save.edit().putString("al","").commit();
    	save.edit().putString("al", str).commit();
    	save.edit().putBoolean("check", Cheese.boolean1).commit();
    }
}
