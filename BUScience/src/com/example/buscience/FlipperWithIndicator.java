package com.example.buscience;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.widget.ViewFlipper;

public class FlipperWithIndicator extends ViewFlipper 
{
	Paint myPaint = new Paint();

	public FlipperWithIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void dispatchDraw(Canvas canvas)
	{
		super.dispatchDraw(canvas);
		
		int width = getWidth();
		float margin = 2;
		float radius = 5;
		
		float centerX = width / 2 - ((radius + margin) * getChildCount());
		float centerY = getHeight() - 15;

		canvas.save();
		for (int i = 0; i < getChildCount(); i++)
		{
			if (i == getDisplayedChild()) {
				myPaint.setColor(Color.BLUE);
				canvas.drawCircle(centerX, centerY, radius, myPaint);
			} else {
				myPaint.setColor(Color.GRAY);
				canvas.drawCircle(centerX, centerY, radius, myPaint);
			}
			
			centerX += 2 * (radius + margin);
		}
		canvas.restore();
	}
}
