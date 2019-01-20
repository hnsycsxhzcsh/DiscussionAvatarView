package com.discussionavatarview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by HARRY on 2019/1/18 0018.
 */

public class DiscussionAvatarView extends ViewGroup {
    //    private ArrayList<String> mDatas = new ArrayList<>();
    private float mRadius;
    private float mDiameter;
    private float mSpace;
    private Context mContext;
    private LayoutInflater mInflater;
    private boolean isLastComplete;
    private int mMaxCount;
    private int mCurrentOffset;
    private boolean isAddView;
    private ValueAnimator animator;

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
            mMaxCount = array.getInteger(R.styleable.DiscussionAvatarView_maxCount, 6);
            isLastComplete = array.getBoolean(R.styleable.DiscussionAvatarView_isLastComplete, true);

            mRadius = DensityUtil.dip2px(context, radius);
            mSpace = DensityUtil.dip2px(context, space);

            mRadius = 4 * mRadius;
            mSpace = 4 * mSpace;
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
        int heiMeasure = MeasureSpec.getSize(heightMeasureSpec);
        int heiMode = MeasureSpec.getMode(heightMeasureSpec);
        int widMode = MeasureSpec.getMode(widthMeasureSpec);
        int wieMeasure = MeasureSpec.getSize(heightMeasureSpec);

        int wid = 0;
        int hei = 0;
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            // 测量子View的宽和高,系统提供的measureChild
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            // 得到LayoutParams
            LayoutParams lp = child.getLayoutParams();
            // 子View占据的宽度
            int childWidth = child.getMeasuredWidth();
            // 子View占据的高度
            int childHeight = child.getMeasuredHeight();

            if (i < mMaxCount) {
                if (i == 0) {
                    wid = wid + childWidth;
                } else {
                    wid = wid + childWidth / 2;
                }
            }
            hei = Math.max(hei, childHeight);
        }
        setMeasuredDimension((widMode == MeasureSpec.EXACTLY) ? wieMeasure : wid,
                (heiMode == MeasureSpec.EXACTLY) ? heiMeasure : hei);
        System.out.println("wid:" + wid + ",hei:" + hei);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        System.out.println("changed:" + changed + ",l:" + l + ",t:" + t + ",r:" + r + ",b:" + b);
        int count = getChildCount();

        int left = -mCurrentOffset;
        int top = 0;
        int right = -mCurrentOffset;
        for (int i = 0; i < count; i++) {
            View child;
            if (isLastComplete) {
                child = getChildAt(i);
            } else {
                child = getChildAt(count - i - 1);
            }
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

            if (i == 0) {
                right = right + childWidth;
            } else {
                right = right + childWidth / 2;
            }
            child.layout(left, top, right, childHeight);
            left = left + childWidth / 2;
        }

    }

    /**
     * 初始化数据
     *
     * @param list
     */
    public void initDatas(ArrayList<String> list) {
        if (list == null) {
            return;
        }
        removeAllViews();
        int size = list.size();
        mMaxCount = size;
        for (int i = 0; i < size; i++) {
            ImageView iv = (ImageView) mInflater.inflate(R.layout.avatar, this, false);

            ViewGroup.LayoutParams lp = iv.getLayoutParams();
            lp.width = (int) (2 * mRadius);
            lp.height = lp.width;
            iv.setLayoutParams(lp);

            if (isLastComplete) {
                GlideUtil.loadCircleImageView(mContext, list.get(i), iv);
            } else {
                GlideUtil.loadCircleImageView(mContext, list.get(size - i - 1), iv);
            }

            this.addView(iv);
        }
        invalidate();
    }

    /**
     * 添加一个头像
     *
     * @param ava
     */
    public void addData(String ava) {
        if (mMaxCount <= 0) {
            return;
        }
        if (TextUtils.isEmpty(ava)) {
            return;
        }

        if (animator != null) {
            animator.cancel();
        }
        int childCount = getChildCount();
        ImageView iv = (ImageView) mInflater.inflate(R.layout.avatar, this, false);
        ViewGroup.LayoutParams lp = iv.getLayoutParams();
        lp.width = (int) (2 * mRadius);
        lp.height = lp.width;
        iv.setLayoutParams(lp);
        GlideUtil.loadCircleImageView(mContext, ava, iv);
        if (isLastComplete) {
            this.addView(iv);
        } else {
            this.addView(iv, 0);
        }

        if (childCount >= mMaxCount) {
            int countAft = getChildCount();
            View child;
            if (isLastComplete) {
                child = getChildAt(0);
            } else {
                child = getChildAt(countAft - 1);
            }
            int childWid = child.getMeasuredWidth();
            animator = ValueAnimator.ofInt(0, childWid / 2);
            animator.setDuration(1000);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mCurrentOffset = (int) animation.getAnimatedValue();
                    System.out.println("mCurrentOffset:" + mCurrentOffset);
                    requestLayout();
                }
            });

            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    mCurrentOffset = 0;
                    if (isLastComplete) {
                        removeViewAt(0);
                    } else {
                        int count = getChildCount();
                        removeViewAt(count - 1);
                    }
                }

            });
            animator.start();
        } else {
            invalidate();
        }
    }

    /**
     * 设置最大头像数
     *
     * @param count
     */
    public void setMaxCount(int count) {
        this.mMaxCount = count;
        int childCount = getChildCount();
        if (childCount > mMaxCount) {
            for (int i = 0; i < childCount - mMaxCount; i++) {
                if (isLastComplete) {
                    removeViewAt(0);
                } else {
                    int currentCount = getChildCount();
                    removeViewAt(currentCount - 1);
                }
            }
        }
    }
}
