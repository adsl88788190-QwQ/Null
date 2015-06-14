package com.bingo_pvp;

//API EX
//	boolean[][] array = new boolean[SIZE][SIZE]; : 棋盤 
//	Check check = new Check(array); 參數放入要檢查的棋盤
//	check.win() 回傳true 則連線達五條 獲勝;回傳false 則否
//	check.line() 回傳目前棋盤連線數
//


public class Check {
	int SIZE = 5;
	boolean[][] array;
	public Check(boolean[][] array){
		if(array.length != 5)//若棋盤大小不為5 則更改SIZE與棋盤大小符合
			SIZE = array.length;
		this.array = array;//棋盤放入
	}
	public boolean win(){		
		if(line() >= 5)
			return true;
		
		return false;
	}
	public int line(){
		int count = 0;//連線數
		//橫的連線
		for(int i = 0;i<SIZE;i++){
			int temp = 0;
			for(int j = 0;j<SIZE;j++)
				if(array[i][j])
					temp++;
			if(temp == SIZE)
				count++;
		}
		//直的連線
		for(int j = 0;j<SIZE;j++){
			int temp = 0;
			for(int i = 0;i<SIZE;i++)
				if(array[i][j])
					temp++;
			if(temp == SIZE)
				count++;
		}
		//斜的連線
		if(array[0][0] &array[1][1]&array[2][2]&array[3][3]&array[4][4])
			count++;
		if(array[0][4] &array[1][3]&array[2][2]&array[3][1]&array[4][0])
			count++;
		return count; 
	}
	
}
