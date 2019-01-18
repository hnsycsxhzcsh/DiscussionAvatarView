package com.discussionavatar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.discussionavatarview.DiscussionAvatarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> mDatas = new ArrayList<>();
    private DiscussionAvatarView mDiscussAva;
    private Button mBtAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDiscussAva = (DiscussionAvatarView) findViewById(R.id.daview);
        mBtAdd = (Button) findViewById(R.id.bt_add);

        setDatas();

        mDiscussAva.setDatas(mDatas);
    }

    private void setDatas() {
        mDatas.add("https://a-ssl.duitang.com/uploads/item/201811/06/20181106012156_GvrKR.thumb.700_0.jpeg");
        mDatas.add("https://b-ssl.duitang.com/uploads/item/201811/04/20181104223950_vygmz.thumb.700_0.jpeg");
        mDatas.add("https://b-ssl.duitang.com/uploads/item/201807/11/20180711091152_FakCJ.thumb.700_0.jpeg");
        mDatas.add("https://b-ssl.duitang.com/uploads/item/201811/04/20181104223952_zfhli.thumb.700_0.jpeg");
        mDatas.add("https://b-ssl.duitang.com/uploads/item/201810/30/20181030153225_mixve.thumb.700_0.jpg");
        mDatas.add("https://b-ssl.duitang.com/uploads/item/201806/01/20180601003856_iCSrm.thumb.700_0.jpeg");
        mDatas.add("https://b-ssl.duitang.com/uploads/item/20181/30/2018130133422_sGNuW.thumb.700_0.jpeg");
        mDatas.add("https://b-ssl.duitang.com/uploads/item/201807/08/20180708095827_SYPL3.thumb.700_0.jpeg");
        mDatas.add("https://b-ssl.duitang.com/uploads/item/201811/01/20181101093301_u2NKu.thumb.700_0.jpeg");
    }
}
