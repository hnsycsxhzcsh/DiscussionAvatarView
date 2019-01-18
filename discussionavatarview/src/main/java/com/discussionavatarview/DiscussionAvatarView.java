package com.discussionavatarview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by HARRY on 2019/1/18 0018.
 */

public class DiscussionAvatarView extends RelativeLayout {
    private ArrayList<String> mDatas = new ArrayList<>();
    private ArrayList<ImageView> mAvaLists = new ArrayList<>();
    private float mRadius;
    private float mDiameter;
    private float mSpace;
    private Context mContext;
    private LayoutInflater mInflater;

    public DiscussionAvatarView(Context context) {
        this(context, null);
    }

    public DiscussionAvatarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DiscussionAvatarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.DiscussionAvatarView);
        if (array != null) {
            int radius = array.getInteger(R.styleable.DiscussionAvatarView_radius, 10);
            int space = array.getInteger(R.styleable.DiscussionAvatarView_space, -10);

            mRadius = DensityUtil.dip2px(context, radius);
            mSpace = DensityUtil.dip2px(context, space);

            mRadius = 6 * mRadius;
            mSpace = 6 * mSpace;
            mDiameter = 2 * mRadius;
            array.recycle();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wid = 0;
        if (mAvaLists.size() > 0) {
            wid = (int) (2 * mRadius + (mAvaLists.size() - 1) * mRadius);
        }
        int hei = MeasureSpec.getSize(heightMeasureSpec);
        int widMode = MeasureSpec.getMode(widthMeasureSpec);
        if (widMode != MeasureSpec.EXACTLY) {
            setMeasuredDimension(wid, hei);
        }
        System.out.println("wid:" + wid + ",hei:" + hei);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        TODO https://blog.csdn.net/zuo_er_lyf/article/details/81629384
        super.onLayout(changed, l, t, r, b);
        System.out.println("changed:" + changed + ",l:" + l + ",t:" + t + ",r:" + r + ",b:" + b);
        removeAllViews();
        for (int i = 0; i < mAvaLists.size(); i++) {
            ImageView iv = mAvaLists.get(i);
            addView(iv, (int) (2 * mRadius), (int) (2 * mRadius));
//            RelativeLayout.LayoutParams st =
//                    (RelativeLayout.LayoutParams) iv.getLayoutParams();
//            iv.layout((int)(st.width + mSpace * i), t, (int) (st.width * (i + 1) + mSpace * i), st.width);
            iv.layout((int) (l + 2 * mRadius * i + mSpace * i), t, (int) (l + (2 * mRadius * (i + 1) + mSpace * i)), (int) (2 * mRadius));
        }
    }

    public void setDatas(ArrayList<String> list) {
        mDatas.clear();
        mDatas.addAll(list);
        for (int i = 0; i < mDatas.size(); i++) {
            ImageView iv = (ImageView) mInflater.inflate(R.layout.avatar, null, true);
            GlideUtil.loadCircleImageView(mContext, mDatas.get(i), iv);
            mAvaLists.add(iv);
        }
        invalidate();
    }

    public void addData(String ava) {
        mDatas.add(ava);
        ImageView iv = (ImageView) mInflater.inflate(R.layout.avatar, this, true);
        GlideUtil.loadCircleImageView(mContext, ava, iv);
        mAvaLists.add(iv);
        invalidate();
    }
}
