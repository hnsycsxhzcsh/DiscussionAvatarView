package com.discussionavatar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.discussionavatarview.DiscussionAvatarListener;
import com.discussionavatarview.DiscussionAvatarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> mDatas = new ArrayList<>();
    private DiscussionAvatarView mDiscussAva;
    private Button mBtAdd;
    private Button mBtReset;
    private Button mBtAdd2;
    private Button mBtMaxCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDiscussAva = (DiscussionAvatarView) findViewById(R.id.daview);
        mBtAdd = (Button) findViewById(R.id.bt_add);
        mBtAdd2 = (Button) findViewById(R.id.bt_add2);
        mBtReset = (Button) findViewById(R.id.bt_reset);
        mBtMaxCount = (Button) findViewById(R.id.bt_max_count);

        initTestDatas();

        mBtReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDiscussAva.initDatas(mDatas);
            }
        });

        mBtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://b-ssl.duitang.com/uploads/item/201807/11/20180711091152_FakCJ.thumb.700_0.jpeg";
                mDiscussAva.addData(url, new DiscussionAvatarListener() {
                    @Override
                    public void onAnimationStart() {

                    }

                    @Override
                    public void onAnimationEnd() {

                    }
                });
            }
        });

        mBtAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://b-ssl.duitang.com/uploads/item/201811/01/20181101093301_u2NKu.thumb.700_0.jpeg";
                mDiscussAva.addData(url);
            }
        });

        mBtMaxCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDiscussAva.setMaxCount(4);
            }
        });

    }

    private void initTestDatas() {
        mDatas.add("https://b-ssl.duitang.com/uploads/item/201811/04/20181104223950_vygmz.thumb.700_0.jpeg");
        mDatas.add("https://b-ssl.duitang.com/uploads/item/201807/11/20180711091152_FakCJ.thumb.700_0.jpeg");
        mDatas.add("https://b-ssl.duitang.com/uploads/item/201811/04/20181104223952_zfhli.thumb.700_0.jpeg");
        mDatas.add("https://b-ssl.duitang.com/uploads/item/201810/30/20181030153225_mixve.thumb.700_0.jpg");
        mDatas.add("https://b-ssl.duitang.com/uploads/item/201807/08/20180708095827_SYPL3.thumb.700_0.jpeg");
        mDatas.add("https://b-ssl.duitang.com/uploads/item/201811/01/20181101093301_u2NKu.thumb.700_0.jpeg");
    }
}
