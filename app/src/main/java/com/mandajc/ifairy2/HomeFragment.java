package com.mandajc.ifairy2;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Adapter.MyFragmentPagerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import model.Article;
import View.PagerFragment;

/**
 * Created by lyzwj on 2018/5/6.
 */

public class HomeFragment extends Fragment{
    @BindView(R.id.search)
    Button search;
    Resources resources;
    private ViewPager mPager;
    private ArrayList<PagerFragment> fragmentsList;
    private ImageView ivBottomLine;
    private TextView tvTabNew, tvTabHot, tvTabSpecial;
    RelativeLayout layout1;

    private int currIndex = 0;
    private ViewGroup.LayoutParams bottomLineWidth;
    private int offset = 0;
    private int position_one, position_two, avg;
    public final static int num = 3 ;
    PagerFragment home1;
    PagerFragment home2;
    PagerFragment home3;

    Bundle bundle;
    public List<Article> mainItemsList = new ArrayList<>();
    String search_tag;
    public String username;
    int start_mode;//0-自启动，1-LoginActivity启动，2-SearchActivity启动，

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_home, null);
        bundle = getArguments();
        start_mode = bundle.getInt("start_mode");
        Log.e("HomeCreate", String.valueOf(start_mode));
        ButterKnife.bind(this, view);
        resources = getResources();
        InitTextView(view);
        InitWidth(view);
        InitViewPager(view);
        tvTabHot.setTextColor(resources.getColor(R.color.white));
        TranslateAnimation animation = new TranslateAnimation(position_one, position_one, 0, 0);
        animation.setFillAfter(true);
        animation.setDuration(300);
        ivBottomLine.startAnimation(animation);
        username = bundle.getString("username");
//        search_tag = bundle.getString("tag");
//        mainItemsList = (List<Article>) bundle.getSerializable("data");

//        Toast.makeText(getContext(), username, Toast.LENGTH_SHORT).show();
        Log.e("HomeFragment", username );
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
        return view;
    }



    private void InitTextView(View parentView) {
        tvTabNew = (TextView) parentView.findViewById(R.id.tv_tab_1);//关注
        tvTabHot = (TextView) parentView.findViewById(R.id.tv_tab_2);//首页
        tvTabSpecial = (TextView)parentView.findViewById(R.id.tv_tab_3);//专题

        tvTabNew.setOnClickListener(new MyOnClickListener(0));
        tvTabHot.setOnClickListener(new MyOnClickListener(1));
        tvTabSpecial.setOnClickListener(new MyOnClickListener(2));
    }

    private void InitViewPager(View parentView) {
        mPager = (ViewPager) parentView.findViewById(R.id.vPager);
        fragmentsList = new ArrayList<PagerFragment>();

        home1 = new HomeFragment_1();
        home2 = new HomeFragment_2();
        home3 = new HomeFragment_3();

        fragmentsList.add(home1);
        fragmentsList.add(home2);
        fragmentsList.add(home3);

        mPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(), fragmentsList));
        mPager.addOnPageChangeListener(new MyOnPageChangeListener());
        mPager.setCurrentItem(1);
        bundleRe(bundle, false, true, false);
        bundleRe(bundle, false, false, true);
        ///////////////////////////////////
        mPager.setOffscreenPageLimit(3);
    }

    private void InitWidth(View parentView) {
        ivBottomLine = (ImageView) parentView.findViewById(R.id.iv_bottom_line);
        layout1 = (RelativeLayout)parentView.findViewById(R.id.layout1);
        bottomLineWidth = ivBottomLine.getLayoutParams();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;
        layout1.measure(0, 0);
        int layoutW = layout1.getMeasuredWidth();
        offset = (int) ((screenW - layoutW) / 2)+5;//=position_zero
        avg = (int) (layoutW / num);
        bottomLineWidth.width = avg/2;
        ivBottomLine.setLayoutParams(bottomLineWidth);
        position_one = avg+5 + offset;
        position_two = (avg+5) * 2 + offset;
    }

    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            mPager.setCurrentItem(index);
        }
    };

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int arg0) {
            Animation animation = null;
            switch (arg0) {
                case 0:
                    if (currIndex == 1) {
                        animation = new TranslateAnimation(position_one, offset, 0, 0);
                        tvTabHot.setTextColor(resources.getColor(R.color.lightwhite));
                    }else if(currIndex == 2){
                        animation = new TranslateAnimation(position_two, offset, 0, 0);
                        tvTabSpecial.setTextColor(resources.getColor(R.color.lightwhite));
                    }
                    tvTabNew.setTextColor(resources.getColor(R.color.white));
                    break;
                case 1:
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(offset, position_one, 0, 0);
                        tvTabNew.setTextColor(resources.getColor(R.color.lightwhite));
                    }else if(currIndex == 2){
                        animation = new TranslateAnimation(position_two, position_one, 0, 0);
                        tvTabSpecial.setTextColor(resources.getColor(R.color.lightwhite));
                    }
                    tvTabHot.setTextColor(resources.getColor(R.color.white));
                    bundleRe(bundle, false, true, false);
                    break;
                case 2:
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(offset, position_two, 0, 0);
                        tvTabNew.setTextColor(resources.getColor(R.color.lightwhite));
                    }
                    if (currIndex == 1) {
                        animation = new TranslateAnimation(position_one, position_two, 0, 0);
                        tvTabHot.setTextColor(resources.getColor(R.color.lightwhite));
                    }
                    tvTabSpecial.setTextColor(resources.getColor(R.color.white));
                    bundleRe(bundle, false, false, true);
                    break;
            }
            currIndex = arg0;
            animation.setFillAfter(true);
            animation.setDuration(300);
            ivBottomLine.startAnimation(animation);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }

    private void bundleRe(Bundle bundle, boolean isFirst, boolean isSecond, boolean isThird) {
        if (isFirst){}
        if (isSecond){
            fragmentsList.get(currIndex).reBundle(bundle);
            fragmentsList.get(currIndex).reTag(bundle.getString("tag", ""));
            fragmentsList.get(currIndex).reMode(bundle.getInt("start_mode", 0));
            Log.e("Home", String.valueOf(bundle.getInt("start_mode", 0)));
            fragmentsList.get(currIndex).reUser(bundle.getString("username"));
            fragmentsList.get(currIndex).reData((List<Article>) bundle.getSerializable("data"));
        }
        if (isThird){
            Log.e("bundleRe: ", bundle.getString("username"));
            fragmentsList.get(2).reUser(bundle.getString("username"));
        }
    }
}
