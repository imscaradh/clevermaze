package com.example.balltest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

public class BackView extends View {
	Bitmap bottom;

	public BackView(Context context) {
		super(context);
		bottom = BitmapFactory
				.decodeResource(getResources(), R.drawable.bottom);
		// TODO Auto-generated constructor stub
	}

	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(bottom, 0, 0, null);

	}

}
