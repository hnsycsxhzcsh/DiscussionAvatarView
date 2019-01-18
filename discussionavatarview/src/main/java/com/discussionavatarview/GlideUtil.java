package com.discussionavatarview;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by HARRY on 2017/3/16 0016.
 */

public class GlideUtil {

    //Glide加载图片为圆形
    public static void loadCircleImageView(Context context, String url, ImageView iv) {
        if (context != null) {
            if (context instanceof Activity) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    if (!((Activity) context).isDestroyed()) {
                        Glide.with(context).load(url).transform(new GlideCircleTransform(context)).
                                diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
                    }
                } else {
                    Glide.with(context).load(url).transform(new GlideCircleTransform(context)).
                            diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
                }
            } else {
                Glide.with(context).load(url).transform(new GlideCircleTransform(context)).
                        diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
            }
        }
    }

    public static void loadCircleImageView(Context context, int id, ImageView iv) {
        if (context != null) {
            if (context instanceof Activity) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    if (!((Activity) context).isDestroyed()) {
                        Glide.with(context).load(id).transform(new GlideCircleTransform(context)).
                                diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
                    }
                } else {
                    Glide.with(context).load(id).transform(new GlideCircleTransform(context)).
                            diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
                }
            } else {
                Glide.with(context).load(id).transform(new GlideCircleTransform(context)).
                        diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
            }
        }
    }

    public static void loadImageView(Context context, String url, ImageView iv) {
        if (context != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (context instanceof Activity) {
                    if (!((Activity) context).isDestroyed()) {
                        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.SOURCE).crossFade().into(iv);
                    }
                } else {
                    Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.SOURCE).crossFade().into(iv);
                }
            } else {
                Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.SOURCE).crossFade().into(iv);
            }
        }
    }
}
