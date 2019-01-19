package com.discussionavatar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.discussionavatarview.DiscussionAvatarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> mDatas = new ArrayList<>();
    private DiscussionAvatarView mDiscussAva;
    private Button mBtAdd;
    private LayoutInflater mInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDiscussAva = (DiscussionAvatarView) findViewById(R.id.daview);
        mBtAdd = (Button) findViewById(R.id.bt_add);

        mInflater = LayoutInflater.from(this);

        setDatas();
        mDiscussAva.setDatas(mDatas);

//        for (int i = 0; i < mDatas.size(); i++) {
//            ImageView view = (ImageView) mInflater.inflate(R.layout.avatar, mDiscussAva, false);
//            mDiscussAva.addView(view);
//        }

    }

    private void setDatas() {
        mDatas.add("https://b-ssl.duitang.com/uploads/item/201811/04/20181104223950_vygmz.thumb.700_0.jpeg");
        mDatas.add("https://b-ssl.duitang.com/uploads/item/201807/11/20180711091152_FakCJ.thumb.700_0.jpeg");
        mDatas.add("https://b-ssl.duitang.com/uploads/item/201811/04/20181104223952_zfhli.thumb.700_0.jpeg");
        mDatas.add("https://b-ssl.duitang.com/uploads/item/201810/30/20181030153225_mixve.thumb.700_0.jpg");
        mDatas.add("https://b-ssl.duitang.com/uploads/item/201807/08/20180708095827_SYPL3.thumb.700_0.jpeg");
        mDatas.add("https://b-ssl.duitang.com/uploads/item/201811/01/20181101093301_u2NKu.thumb.700_0.jpeg");
    }
}
