package com.example.lenovo_g50_70.newspad.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.lenovo_g50_70.newspad.R;
import com.example.lenovo_g50_70.newspad.adapter.FragmentAdapter;
import com.example.lenovo_g50_70.newspad.fragment.ContentFragment;

import java.util.ArrayList;
import java.util.List;

import static com.example.lenovo_g50_70.newspad.R.id.tableLayout;
import static com.example.lenovo_g50_70.newspad.R.id.viewPager;

/**
 * 主界面的显示
 * TabLayout结合ViewPager和Fragment使用
 */

public class MainActivity extends AppCompatActivity {
    private String NEWS_TYPE = "NEWSTYPE";
    private int GUO_NEI = 1;
    private int TOP = 2;
    private int SHI_SHANG = 3;
    private int YU_LE = 4;
    private int CAI_JING = 5;
    private int TI_YU = 6;
    private int SHE_HUI = 7;
    private int KE_JI = 8;
    private int JUN_SHI = 9;
    private int GUO_JI = 10;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private FragmentAdapter mAdapter;
    private List<Fragment> mFragmentList;
    private List<String> mTitleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatas();
        initViews();
    }

    private void initViews() {
        mTabLayout = (TabLayout) findViewById(tableLayout);
        mViewPager = (ViewPager) findViewById(viewPager);
        //添加Tab
        for (String str : mTitleList) {
            mTabLayout.addTab(mTabLayout.newTab().setText(str));
        }
        //绑定ViewPager
        mAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragmentList, mTitleList);
        mViewPager.setAdapter(mAdapter);
        //设置ViewPager页面预加载的数量,解决跨多个页面加载出现的白屏问题
        //白屏问题解析，假设当前页面为ViewPager的第一页，然后直接跳去第10页，由于先前没预加载，则出现白屏
        mViewPager.setOffscreenPageLimit(10);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initDatas() {
        //添加Fragment,通过Bundle传数据去fragment
        mFragmentList = new ArrayList<>();

        ContentFragment guonei = new ContentFragment();
        Bundle bundle_guonei = new Bundle();
        bundle_guonei.putInt(NEWS_TYPE, GUO_NEI);
        guonei.setArguments(bundle_guonei);
        mFragmentList.add(guonei);

        ContentFragment top = new ContentFragment();
        Bundle bundle_top = new Bundle();
        bundle_top.putInt(NEWS_TYPE, TOP);
        top.setArguments(bundle_top);
        mFragmentList.add(top);

        ContentFragment shishang = new ContentFragment();
        Bundle bundle_shishang = new Bundle();
        bundle_shishang.putInt(NEWS_TYPE, SHI_SHANG);
        shishang.setArguments(bundle_shishang);
        mFragmentList.add(shishang);

        ContentFragment yule = new ContentFragment();
        Bundle bundle_yule = new Bundle();
        bundle_yule.putInt(NEWS_TYPE, YU_LE);
        yule.setArguments(bundle_yule);
        mFragmentList.add(yule);

        ContentFragment caijing = new ContentFragment();
        Bundle bundle_caijing = new Bundle();
        bundle_caijing.putInt(NEWS_TYPE, CAI_JING);
        caijing.setArguments(bundle_caijing);
        mFragmentList.add(caijing);

        ContentFragment tiyu = new ContentFragment();
        Bundle bundle_tiyu = new Bundle();
        bundle_tiyu.putInt(NEWS_TYPE, TI_YU);
        tiyu.setArguments(bundle_tiyu);
        mFragmentList.add(tiyu);

        ContentFragment shehui = new ContentFragment();
        Bundle bundle_shehui = new Bundle();
        bundle_shehui.putInt(NEWS_TYPE, SHE_HUI);
        shehui.setArguments(bundle_shehui);
        mFragmentList.add(shehui);

        ContentFragment keji = new ContentFragment();
        Bundle bundle_keji = new Bundle();
        bundle_keji.putInt(NEWS_TYPE, KE_JI);
        keji.setArguments(bundle_keji);
        mFragmentList.add(keji);

        ContentFragment junshi = new ContentFragment();
        Bundle bundle_junshi = new Bundle();
        bundle_junshi.putInt(NEWS_TYPE, JUN_SHI);
        junshi.setArguments(bundle_junshi);
        mFragmentList.add(junshi);

        ContentFragment guoji = new ContentFragment();
        Bundle bundle_guoji = new Bundle();
        bundle_guoji.putInt(NEWS_TYPE, GUO_JI);
        guoji.setArguments(bundle_guoji);
        mFragmentList.add(guoji);

        //添加Tab文字
        mTitleList = new ArrayList<>();
        mTitleList.add("国内");
        mTitleList.add("头条");
        mTitleList.add("时尚");
        mTitleList.add("娱乐");
        mTitleList.add("财经");
        mTitleList.add("体育");
        mTitleList.add("社会");
        mTitleList.add("科技");
        mTitleList.add("军事");
        mTitleList.add("国际");
    }
}
