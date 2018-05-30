package net.univr.pushi.jxatmosphere.fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;

import net.univr.pushi.jxatmosphere.MyApplication;
import net.univr.pushi.jxatmosphere.R;
import net.univr.pushi.jxatmosphere.adapter.LdptWxAdapter;
import net.univr.pushi.jxatmosphere.adapter.MyPagerAdapter;
import net.univr.pushi.jxatmosphere.base.RxLazyFragment;
import net.univr.pushi.jxatmosphere.beens.GkdmClickBeen;
import net.univr.pushi.jxatmosphere.remote.RetrofitHelper;
import net.univr.pushi.jxatmosphere.utils.ExStaggeredGridLayoutManager;
import net.univr.pushi.jxatmosphere.widget.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class LdptRadarFragment extends RxLazyFragment {


    @BindView(R.id.viewpager)
    CustomViewPager mViewPager;

    @BindView(R.id.time_recycle)
    RecyclerView mRecyclerView3;

    @BindView(R.id.pic_ready)
    ImageView isStartPic;


    List<GkdmClickBeen> mData3 = new ArrayList<>();
    //播放的下一位置
    int recycle_skipto_position = 0;
    //是否播放
    Boolean isStart = false;
    //现在的位置
    int now_postion;
    ProgressDialog progressDialog = null;


    List<Fragment> fragmentList = new ArrayList<>();
    List<String> urls = new ArrayList<>();
    MyPagerAdapter viewPagerAdapter;
    String flag;


    public void setStart(Boolean start) {
        isStart = start;
        mViewPager.setScanScroll(true);
    }

    public static LdptRadarFragment newInstance(String flag) {
        LdptRadarFragment ldptRadarFragment = new LdptRadarFragment();
        Bundle bundle = new Bundle();
        bundle.putString("flag", flag);
        ldptRadarFragment.setArguments(bundle);
        return ldptRadarFragment;
    }


    public void setImage() {
        isStartPic.setImageResource(R.drawable.app_start);
    }

    private Context mcontext;
    private LdptWxAdapter mAdapter;


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_ldpt_radar;
    }

    @Override
    public void finishCreateView(Bundle state) {
        mcontext = getActivity();
        if (getArguments() != null) {
            flag = getArguments().getString("flag");
        }
        getTestdata();
        isStartPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mData3 == null || mData3.size() == 0) return;
                if (isStart == false) {
                    isStartPic.setImageResource(R.drawable.app_end);
                    Message message = uiHandler.obtainMessage();
                    message.what = 1;
                    uiHandler.sendMessageDelayed(message,MyApplication.getInstance().auto_time);
                    isStart = true;
                    mViewPager.setScanScroll(false);
                } else {
                    isStartPic.setImageResource(R.drawable.app_start);
                    isStart = false;
                    mViewPager.setScanScroll(true);
                    uiHandler.removeCallbacksAndMessages(null);
                }

            }
        });

    }


    private LdptWxAdapter getAdapter3() {
        if (mAdapter == null) {
            ExStaggeredGridLayoutManager layoutManager = new ExStaggeredGridLayoutManager(6,StaggeredGridLayoutManager.VERTICAL){
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            mAdapter = new LdptWxAdapter(mData3);
            mRecyclerView3.setLayoutManager(layoutManager);
            mRecyclerView3.setAdapter(mAdapter);
            mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
                switch (view.getId()) {
                    case R.id.time:
                        if (isStart == false) {
                            mViewPager.setCurrentItem(position);
                            //事件处理
                            List data = adapter.getData();
                            GkdmClickBeen clickBeenBefore = (GkdmClickBeen) (data.get(now_postion));
                            clickBeenBefore.setOnclick(false);
                            recycle_skipto_position = position + 1;
                            if (recycle_skipto_position > mData3.size() - 1)
                                recycle_skipto_position = 0;
                            GkdmClickBeen clickBeenNow = (GkdmClickBeen) (data.get(position));
                            clickBeenNow.setOnclick(true);
                            mData3.set(now_postion, clickBeenBefore);
                            mData3.set(position, clickBeenNow);
                            adapter.notifyItemChanged(now_postion);
                            adapter.notifyItemChanged(position);
                        }
                        break;
                }
            });
        }
        return mAdapter;
    }


    private void getTestdata() {
        progressDialog=ProgressDialog.show(getContext(),"请稍等...", "获取数据中...",true);
        progressDialog.setCancelable(true);
        if (flag.equals("1")) {
            getAdapter3();
            RetrofitHelper.getWeatherMonitorAPI()
                    .getLdpt()
                    .compose(bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(LdptBeen -> {
                        progressDialog.dismiss();
                        recycle_skipto_position = 1;
                        now_postion=0;

                        List<Fragment> huancunFragments = new ArrayList<>();
                        for (int i = 0; i < fragmentList.size(); i++) {
                            huancunFragments.add(fragmentList.get(i));
                        }
                        fragmentList.clear();

                        urls = LdptBeen.getData().getImageUrl();
                        for (int i = 0; i < urls.size(); i++) {
                            PicLoadFragment fragment = PicLoadFragment.newInstance(urls.get(i));
                            fragmentList.add(fragment);
                        }

                        viewPagerAdapter = new MyPagerAdapter(getChildFragmentManager(), fragmentList, huancunFragments);
                        mViewPager.setAdapter(viewPagerAdapter);
                        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                            }

                            @Override
                            public void onPageSelected(int position) {

                                GkdmClickBeen clickBeenStop = mData3.get(now_postion);
                                clickBeenStop.setOnclick(false);
                                GkdmClickBeen clickBeenNow = mData3.get(position);
                                clickBeenNow.setOnclick(true);
                                mData3.set(now_postion, clickBeenStop);
                                mData3.set(position, clickBeenNow);
                                mAdapter.notifyItemChanged(now_postion);
                                mAdapter.notifyItemChanged(position);
                                now_postion = position;
                                mRecyclerView3.smoothScrollToPosition(position);
                                recycle_skipto_position = position + 1;
                                if (recycle_skipto_position > mData3.size() - 1)
                                    recycle_skipto_position = 0;

                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });


                        List<String> time = LdptBeen.getData().getBtnName();
                        for (int i = 0; i < time.size(); i++) {
                            GkdmClickBeen clickBeen = new GkdmClickBeen();
                            if (i == 0) clickBeen.setOnclick(true);
                            else clickBeen.setOnclick(false);
                            clickBeen.setText(time.get(i));

                            mData3.add(clickBeen);
                        }

                        getAdapter3().setNewData(mData3);
                    }, throwable -> {
                        progressDialog.dismiss();
                        LogUtils.e(throwable);
                        ToastUtils.showShort(getString(R.string.getInfo_error_toast));
                    });
        } else {
            getAdapter3();
            RetrofitHelper.getWeatherMonitorAPI()
                    .getWxyt()
                    .compose(bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(wxytBeen -> {
                            progressDialog.dismiss();
                        recycle_skipto_position = 1;
                        now_postion=0;
                        List<Fragment> huancunFragments = new ArrayList<>();
                        for (int i = 0; i < fragmentList.size(); i++) {
                            huancunFragments.add(fragmentList.get(i));
                        }
                        fragmentList.clear();

                        urls = wxytBeen.getData().getImageUrl();
                        for (int i = 0; i < urls.size(); i++) {
                            PicLoadFragment fragment = PicLoadFragment.newInstance(urls.get(i));
                            fragmentList.add(fragment);
                        }

                        viewPagerAdapter = new MyPagerAdapter(getChildFragmentManager(), fragmentList, huancunFragments);
                        mViewPager.setAdapter(viewPagerAdapter);
                        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                            }

                            @Override
                            public void onPageSelected(int position) {

                                GkdmClickBeen clickBeenStop = mData3.get(now_postion);
                                clickBeenStop.setOnclick(false);
                                GkdmClickBeen clickBeenNow = mData3.get(position);
                                clickBeenNow.setOnclick(true);
                                mData3.set(now_postion, clickBeenStop);
                                mData3.set(position, clickBeenNow);
                                mAdapter.notifyItemChanged(now_postion);
                                mAdapter.notifyItemChanged(position);
                                now_postion = position;
                                mRecyclerView3.smoothScrollToPosition(position);
                                recycle_skipto_position = position + 1;
                                if (recycle_skipto_position > mData3.size() - 1)
                                    recycle_skipto_position = 0;

                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });


                        List<String> time = wxytBeen.getData().getBtnNameList();
                        for (int i = 0; i < time.size(); i++) {
                            GkdmClickBeen clickBeen = new GkdmClickBeen();
                            if (i == 0) clickBeen.setOnclick(true);
                            else clickBeen.setOnclick(false);
                            clickBeen.setText(time.get(i));

                            mData3.add(clickBeen);
                        }

                        getAdapter3().setNewData(mData3);
                    }, throwable -> {
                            progressDialog.dismiss();
                        LogUtils.e(throwable);
                        ToastUtils.showShort(getString(R.string.getInfo_error_toast));
                    });
        }

    }


    private Handler uiHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mRecyclerView3 != null) {
                switch (msg.what) {
                    case 1:
                        mViewPager.setCurrentItem(recycle_skipto_position);
                        if (recycle_skipto_position > mData3.size() - 1) {
                            recycle_skipto_position = 0;
                            Message message = uiHandler.obtainMessage();
                            message.what = 1;
                            if (isStart == false) {
                            } else {
                                uiHandler.sendMessageDelayed(message, MyApplication.getInstance().auto_time);
                            }
                        } else {
                            Message message = uiHandler.obtainMessage();
                            message.what = 1;
                            if (isStart == false) {

                            } else {
                                uiHandler.sendMessageDelayed(message, MyApplication.getInstance().auto_time);
                            }
                        }
                        break;
                    default:
                        break;
                }
            } else {

            }

        }

    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHandler.removeCallbacksAndMessages(null);
    }
}
