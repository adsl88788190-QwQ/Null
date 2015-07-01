package com.bingo_pvp;

public class AIChoose {
	public int choose(boolean AI[][]) {

		// 線數上已被選個數 * (個數+1) * 可連線數 = 選的權重
		// Reference:http://www.slidefinder.net/2/2010_3befbaeb/2010_6_3befbaeb/19830214
		// 存取權重的陣列
		int[][] Score = new int[AI.length][AI.length];
		// 清空陣列
		for (int[] temp1 : Score)
			for (int temp2 : temp1)
				temp2 = 0;
		// 每個橫線的選取數
		int[] RowCount = new int[5];
		// 每個直線的選取數
		int[] ColCount = new int[5];
		// 兩個斜線的選取數
		int[] Oblique = new int[2];
		// 計算選取數
		LineCount(AI, RowCount, ColCount, Oblique);
		// 計算權重
		WeightCount(AI, Score, RowCount, ColCount, Oblique);
		// 回傳權重最大的
		return Bigger(Score);
	}

	// 計算選取數
	public void LineCount(boolean[][] AI, int[] RowCount, int[] ColCount,
			int[] Oblique) {
		// 每個橫線所被選取個數

		for (int i = 0; i < 5; i++) {
			RowCount[i] = 0;// 歸零
			for (boolean temp : AI[i])
				// 取出每橫排的布林
				if (temp)// true為選過 Count++
					RowCount[i]++;
		}
		// 每個直線所被選取個數

		for (int i = 0; i < 5; i++) {
			ColCount[i] = 0;// 歸零
			for (int j = 0; j < 5; j++) {// 取出每直排的布林
				if (AI[j][i])// true為選過 Count++
					ColCount[i]++;
			}
		}
		// 斜的－左斜
		for (int i = 0; i < 5; i++)
			if (AI[i][i])
				Oblique[0]++;
		// 斜的－右斜
		for (int i = 0; i < 5; i++)
			if (AI[i][4 - i])
				Oblique[1]++;

	}

	// 計算權重
	public void WeightCount(boolean[][] AI, int[][] Score, int[] RowCount,
			int[] ColCount, int[] Oblique) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				// 沒被選過的 才需計算權重 ;選過為true
				if (!AI[i][j]) {
					// 已選取數 橫的 + 直的
					Score[i][j] = (RowCount[i] * (RowCount[i] + 1))
							+ (ColCount[j] * (ColCount[j] + 1));
					// 可連線數
					if (i == j) {// 左斜對角
						// 選取數 左斜的
						Score[i][j] += Oblique[0];
						Score[i][j] += 3;
					} else if (i + j == 4) {// 右斜對角
						// 選取數 右斜的
						Score[i][j] += Oblique[1];
						Score[i][j] += 3;
					} else
						// 其他為兩條
						Score[i][j] += 2;
				}
			}
		}
	}

	// 陣列中權重最大的
	public int Bigger(int[][] Score) {
		int x = 0, y = 0;
		int max = Score[x][y];
		for (int i = 0; i < 5; i++) {
			for (int j = 1; j < 5; j++) {
				if (Score[i][j] > max) {
					max = Score[i][j];
					x = i;
					y = j;
				}
			}
		}
		return x * 10 + y;
	}
}
