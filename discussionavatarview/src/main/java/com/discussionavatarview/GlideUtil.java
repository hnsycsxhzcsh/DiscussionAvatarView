package com.discussionavatarview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by HARRY on 2017/3/16 0016.
 */

public class GlideUtil {

    //Glide加载图片为圆形
    public static void loadCircleImageView(Context context, String url, ImageView iv, boolean isShowFrame, int color) {
        if (context != null) {
            if (context instanceof Activity) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    if (!((Activity) context).isDestroyed()) {
                        if (isShowFrame) {
                            Glide.with(context).load(url).
                                    diskCacheStrategy(DiskCacheStrategy.ALL).
                                    transform(new GlideCircleTransformWithBorder(context, 2, color)).
                                    into(iv);
                        } else {
                            Glide.with(context).load(url).transform(new GlideCircleTransform(context)).
                                    diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
                        }
                    }
                } else {
                    if (isShowFrame) {
                        Glide.with(context).load(url).
                                diskCacheStrategy(DiskCacheStrategy.ALL).
                                transform(new GlideCircleTransformWithBorder(context, 2, color)).
                                into(iv);
                    } else {
                        Glide.with(context).load(url).transform(new GlideCircleTransform(context)).
                                diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
                    }
                }
            } else {
                if (isShowFrame) {
                    Glide.with(context).load(url).
                            diskCacheStrategy(DiskCacheStrategy.ALL).
                            transform(new GlideCircleTransformWithBorder(context, 2, color)).
                            into(iv);
                } else {
                    Glide.with(context).load(url).transform(new GlideCircleTransform(context)).
                            diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
                }
            }
        }
    }
}
