package com.deesastudio.android.flipanimation;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class FlipAnimation extends Animation{
	private final float m_FromDegrees;
	private final float m_ToDegrees;
	private final float m_CenterX;
	private final float m_CenterY;
	
	private Camera m_Camera;
	
	public FlipAnimation(float from_degrees, float to_degrees, float x_center, float y_center) {
		m_FromDegrees = from_degrees;
		m_ToDegrees = to_degrees;
		m_CenterX = x_center;
		m_CenterY = y_center;
	}
	
	@Override
	public void initialize(int width, int height, int parentWidth, int parentHeight) {
		super.initialize(width, height, parentWidth, parentHeight);
		m_Camera = new Camera();
	}
	
	//@Override
	protected void applyTransformation(float interpolatedTime, Transformation t) {
		//final float fromDegrees = mFromDegrees;
		//float degrees = fromDegrees + ((mToDegrees - fromDegrees) * interpolatedTime);
		float degrees = m_FromDegrees + ((m_ToDegrees - m_FromDegrees) * interpolatedTime);
		/*
		final float centerX = mCenterX;
		final float centerY = mCenterY;
		final Camera camera = mCamera;
		*/
		final Camera camera = m_Camera;
		final Matrix matrix = t.getMatrix();
		
		camera.save();
		
		camera.rotateY(degrees);
		
		camera.getMatrix(matrix);
		camera.restore();
		matrix.preTranslate(-m_CenterX, -m_CenterY);
		matrix.postTranslate(m_CenterX, m_CenterY);
		/*
		matrix.preTranslate(-centerX, -centerY);
		matrix.postTranslate(centerX, centerY);
		*/
	}
}
