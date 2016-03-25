package com.example.indicaterdemotwo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.widget.RadioButton;

public class Indicator extends RadioButton {

	private Paint mIndicatorPaint;
	private Paint mTextPaint;

	private String mText;
	private float mTextSize;
	private int mTextSelectedColor;
	private int mTextUnselectedColor;
	private int mIndicaterColor;
	private float mIndicatorHeight = 10.0f;

	public Indicator(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO 自动生成的构造函数存根
		TypedArray ta = context.obtainStyledAttributes(attrs,
				R.styleable.Indicater);
		mText = ta.getString(R.styleable.Indicater_text);
		mTextSize = ta.getDimension(R.styleable.Indicater_text_size, 18);
		mTextSelectedColor = ta.getColor(
				R.styleable.Indicater_text_selected_color, Color.GREEN);
		mTextUnselectedColor = ta.getColor(
				R.styleable.Indicater_text_unselected_color, Color.BLACK);
		mIndicaterColor = ta.getColor(R.styleable.Indicater_indicater_color,
				Color.GREEN);
		mIndicatorHeight = ta.getDimension(
				R.styleable.Indicater_indicater_height, 5);
		ta.recycle();

		initPaint();
	}

	private void initPaint() {
		mTextPaint = new Paint();
		mTextPaint.setTextSize(mTextSize);
		mTextPaint.setAntiAlias(true);

		mIndicatorPaint = new Paint();
		mIndicatorPaint.setColor(mIndicaterColor);
		mIndicatorPaint.setStyle(Style.FILL);
		mIndicatorPaint.setStrokeWidth(mIndicatorHeight);
		mIndicatorPaint.setAntiAlias(true);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO 自动生成的方法存根
		// super.onDraw(canvas);
		int width = getMeasuredWidth();
		int height = getMeasuredHeight();

		if (isChecked()) {
			mIndicatorPaint.setColor(mIndicaterColor);
			mTextPaint.setColor(mTextSelectedColor);
		} else {
			mIndicatorPaint.setColor(Color.TRANSPARENT);
			mTextPaint.setColor(mTextUnselectedColor);
		}
		FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
		// 计算文字高度
		float fontHeight = fontMetrics.bottom - fontMetrics.top;
		// 计算文字baseline
		float textBaseY = height - mIndicatorHeight - (height - mIndicatorHeight - fontHeight) / 2
				- fontMetrics.bottom;
		canvas.drawText(mText, width / 2, textBaseY, mTextPaint);
		canvas.drawRect(0, height - mIndicatorHeight, getMeasuredWidth(),
				height, mIndicatorPaint);
	}
}
