package com.ashwin.example.videoview;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;

public class LingVisWebView extends WebView {
    public Double subLeft = 0.0;
    public Double subTop = 0.0;
    public Double subWidth = 0.0;
    public Double subHeight = 0.0;

    public LingVisWebView(Context context) {
        super(context);
    }

    public LingVisWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public LingVisWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public static int pxToDp(int px) {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        return Math.round(px / displayMetrics.density);
    }

    private Point getPoint(MotionEvent event) {
        return new Point(pxToDp((int)event.getX()), pxToDp((int)event.getY()));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Point pt = getPoint(event);
        boolean res = super.onTouchEvent(event);
        if (pt.x < subLeft || pt.x > subLeft + subWidth || pt.y < subTop || pt.y > subTop + subHeight) return false;
        return res;
    }

}