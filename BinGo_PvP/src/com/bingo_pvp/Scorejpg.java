package com.bingo_pvp;




import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class Scorejpg {
	public Bitmap createDieImage(Bitmap bitmap,int width, int height,String score) {
		int x = width;
        int y = height;
        Bitmap newbit = Bitmap.createBitmap(x, y, Bitmap.Config.ARGB_8888);		          
        Canvas canvas = new Canvas(newbit);		          
        Paint paint = new Paint();
        canvas.drawBitmap(bitmap, 0, 0, paint);
        paint.setColor(Color.GREEN);
        paint.setTextSize(20);
        canvas.drawText("Score", 25 , 20, paint);
        paint.setColor(Color.RED);
        paint.setTextSize(20);
        canvas.drawText(score, 25 , 40, paint);
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        return newbit;
	}
}