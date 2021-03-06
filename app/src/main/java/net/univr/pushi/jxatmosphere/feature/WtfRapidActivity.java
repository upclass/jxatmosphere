package net.univr.pushi.jxatmosphere.feature;

import android.animation.ValueAnimator;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;

import net.univr.pushi.jxatmosphere.MyApplication;
import net.univr.pushi.jxatmosphere.R;
import net.univr.pushi.jxatmosphere.adapter.DmcgjcMenuAdapter;
import net.univr.pushi.jxatmosphere.adapter.MultiGdybTxAdapterForGdybtx;
import net.univr.pushi.jxatmosphere.adapter.MyPagerAdapter;
import net.univr.pushi.jxatmosphere.base.BaseActivity;
import net.univr.pushi.jxatmosphere.beens.DmcgjcmenuBeen;
import net.univr.pushi.jxatmosphere.beens.EcOneMenu;
import net.univr.pushi.jxatmosphere.beens.GkdmClickBeen;
import net.univr.pushi.jxatmosphere.beens.MultiItemGdybTx;
import net.univr.pushi.jxatmosphere.fragments.PicLoadFragment;
import net.univr.pushi.jxatmosphere.interfaces.BrightnessActivity;
import net.univr.pushi.jxatmosphere.interfaces.CallBackUtil;
import net.univr.pushi.jxatmosphere.remote.RetrofitHelper;
import net.univr.pushi.jxatmosphere.utils.ExStaggeredGridLayoutManager;
import net.univr.pushi.jxatmosphere.utils.PicUtils;
import net.univr.pushi.jxatmosphere.widget.CustomViewPager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WtfRapidActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.main_tv)
    TextView main_tv;
    @BindView(R.id.vice_tv)
    TextView vice_tv;
    @BindView(R.id.vice_tv1)
    TextView vice_tv1;

    @BindView(R.id.tabline)
    ImageView tabline;
    @BindView(R.id.back)
    ImageView leave;
    @BindView(R.id.reload)
    ImageView reload;

    private int tabLineLength;// 1/2屏幕宽
    int marginleft;
    String selectTime = "";


    @BindView(R.id.recycler1)
    RecyclerView mRecyclerView1;
    @BindView(R.id.viepager)
    CustomViewPager mViewPager;
    @BindView(R.id.recycler3)
    RecyclerView mRecyclerView3;

    @BindView(R.id.time)
    TextView time;
    List<String> menuTime = new ArrayList<>();
    List<String> oneTime = new ArrayList<>();
    List<String> twoTime = new ArrayList<>();
    private ListPopupWindow popupWindow;
    private ArrayAdapter timeAdapter;
    Boolean oneMenu = true;

    private DmcgjcMenuAdapter mAdapter1;

    MyPagerAdapter viewPagerAdapter;
    List<Fragment> fragmentList = new ArrayList<>();
    List<String> urls;


    String type = "WRF-REFRESH";
    String ctype = "Precipitation_1h";
    //播放的下一位置
    int recycle_skipto_position = 2;
    //是否播放
    public Boolean isStart = false;

    //当前的位置
    int now_postion = 1;
    ProgressDialog progressDialog;


    private MultiGdybTxAdapterForGdybtx mAdapter3;
    public List<MultiItemGdybTx> multitemList = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.activity_wtf_rapid;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        // 初始化滑动条1/2
        initTabLine();
        // 初始化界面
        initView();
        initData();
    }

    private void initData() {
        getAdapter1();
        initSpinner();
        getOneTime();
        getTwoMenu();
        getTestdata();
    }


    private void getTwoMenu() {
        RetrofitHelper.getDataForecastAPI()
                .getOneMenu(type)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(wtfRapidBeen -> {
                    List<DmcgjcmenuBeen.DataBean> data = new ArrayList<>();
                    List<EcOneMenu.DataBean.MenuBean> menu = wtfRapidBeen.getData().getMenu();
                    for (int i = 0; i < menu.size(); i++) {
                        DmcgjcmenuBeen.DataBean temp = new DmcgjcmenuBeen.DataBean();
                        if (i == 0) {
                            temp.setSelect(true);
                        } else {
                            temp.setSelect(false);
                        }
                        temp.setZnName(menu.get(i).getZnName());
                        temp.setType(menu.get(i).getType());
                        temp.setPaname(menu.get(i).getPaname());
                        temp.setName(menu.get(i).getName());
                        temp.setId(menu.get(i).getId());
                        data.add(temp);
                    }
                    getAdapter1().setNewData(data);
                }, throwable -> {
                    LogUtils.e(throwable);
                    ToastUtils.showShort(getString(R.string.getInfo_error_toast));
                });
    }

    private void initTabLine() {
        Display display = getWindow().getWindowManager().getDefaultDisplay(); // 获取显示屏信息
        DisplayMetrics metrics = new DisplayMetrics(); // 得到显示屏宽度
        display.getMetrics(metrics);
        tabLineLength = metrics.widthPixels / 3; // 1/2屏幕宽度
        marginleft = tabLineLength / 8; // 1/16屏幕宽度
        LinearLayout.LayoutParams ps = (LinearLayout.LayoutParams) tabline.getLayoutParams();
        ps.width = tabLineLength * 3 / 4;
        tabline.setLayoutParams(ps);
    }

    private void initView() {
        main_tv.setOnClickListener(this);
        vice_tv.setOnClickListener(this);
        vice_tv1.setOnClickListener(this);
        leave.setOnClickListener(this);
        reload.setOnClickListener(this);
        LinearLayout.LayoutParams ll = (android.widget.LinearLayout.LayoutParams) tabline
                .getLayoutParams();
        ll.leftMargin = marginleft;
        tabline.setLayoutParams(ll);
        CallBackUtil.setBrightness(new BrightnessActivity() {
            @Override
            public void onDispatchDarken() {
                final Window window = getWindow();
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(1.0f, 0.5f);
                valueAnimator.setDuration(500);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        WindowManager.LayoutParams params = window.getAttributes();
                        params.alpha = (Float) animation.getAnimatedValue();
                        window.setAttributes(params);
                    }
                });

                valueAnimator.start();
            }

            @Override
            public void onDispatchLight() {
                final Window window = getWindow();
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(0.5f, 1.0f);
                valueAnimator.setDuration(500);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        WindowManager.LayoutParams params = window.getAttributes();
                        params.alpha = (Float) animation.getAnimatedValue();
                        window.setAttributes(params);
                    }
                });
                valueAnimator.start();
            }
        });
    }

    @Override
    public void onClick(View v) {
        LinearLayout.LayoutParams ll = (android.widget.LinearLayout.LayoutParams) tabline
                .getLayoutParams();
        switch (v.getId()) {
            case R.id.main_tv:

                changeSize(0);
                ll.leftMargin = marginleft;
                tabline.setLayoutParams(ll);
                type = "WRF-REFRESH";
                ctype = "Precipitation_1h";
                uiHandler.removeCallbacksAndMessages(null);
                getTwoMenu();
//                if (selectTime.equals("")) getTestdata();
//                else getTestDataBytime(selectTime);
                getTestdata();//取最新的数据
                getAdapter1().setLastposition(0);
                getOneTime();
//                fragment1.isStart = false;
//                MultiItemGdybTx multiItemGdybTx = new MultiItemGdybTx(MultiItemGdybTx.IMG, R.drawable.app_start);
//                fragment1.multitemList.set(0, multiItemGdybTx);
//                fragment1.getAdapter3().notifyItemChanged(0);
                break;
            case R.id.vice_tv:
                changeSize(1);
//                currentPage = 1;
//                viewPager.setCurrentItem(1);
                ll.leftMargin = tabLineLength + marginleft;
                tabline.setLayoutParams(ll);
                type = "WRF-RUC";
                ctype = "Precipitation_3h";
                uiHandler.removeCallbacksAndMessages(null);
                getTwoMenu();
//                if (selectTime.equals("")) getTestdata();
//                else getTestDataBytime(selectTime);
                getTestdata();
                getAdapter1().setLastposition(0);
                getOneTime();

//                fragment.isStart = false;
//                MultiItemGdybTx multiItemGdybTx1 = new MultiItemGdybTx(MultiItemGdybTx.IMG, R.drawable.app_start);
//                fragment.multitemList.set(0, multiItemGdybTx1);
//                fragment.getAdapter3().notifyItemChanged(0);
                break;

            case R.id.vice_tv1:
                changeSize(2);
                ll.leftMargin = tabLineLength*2 + marginleft;
                tabline.setLayoutParams(ll);
                type = "ENSEMBLE-NOWCAST";
                ctype = "Gale_ensemble";
                uiHandler.removeCallbacksAndMessages(null);
                getTwoMenu();
                getTestdata();
                getAdapter1().setLastposition(0);
                getOneTime();
                break;
            case R.id.back:
                finish();
                break;
            case R.id.reload:
                PicUtils.deleteDir(type + "/" + ctype);
                //selectTime减8小时，之前显示加了8小时
                getTestDataBytime(timeMinusHour(-8,selectTime));
                break;
        }

    }

    public void changeSize(int flag) {
        if (flag == 0) {
            vice_tv.setTextSize(13);
            vice_tv1.setTextSize(13);
            main_tv.setTextSize(15);
        }
        if (flag == 1) {
            main_tv.setTextSize(13);
            vice_tv1.setTextSize(13);
            vice_tv.setTextSize(15);
        }
        if (flag == 2) {
            main_tv.setTextSize(13);
            vice_tv.setTextSize(13);
            vice_tv1.setTextSize(15);
        }

    }

    private void initSpinner() {
        menuTime = new ArrayList<>();
        popupWindow = new ListPopupWindow(context);
        timeAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, menuTime);
        popupWindow.setAdapter(timeAdapter);
        popupWindow.setAnchorView(time);
        popupWindow.setWidth(370);   //WRAP_CONTENT会出错
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setDropDownGravity(Gravity.END);
        popupWindow.setModal(true);
        popupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (oneMenu) {
                    String s = menuTime.get(position);
                    getTwoTime1(s);
                    oneMenu = false;
                } else {
                    isStart = false;
                    uiHandler.removeCallbacksAndMessages(null);
                    popupWindow.dismiss();
                    getTestDataBytime( timeMinusHour(-8,menuTime.get(position)));
                    selectTime = menuTime.get(position);
                    time.setText(menuTime.get(position));
                    oneMenu = true;
                }
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                menuTime.clear();
                for (int i = 0; i < oneTime.size(); i++) {
                    menuTime.add(oneTime.get(i));
                }
                timeAdapter.notifyDataSetChanged();
                popupWindow.show();
                oneMenu = true;
            }
        });
    }


    public void getOneTime() {
        RetrofitHelper.getDataForecastAPI()
                .getDataForecastContentBytime(type)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(wtfOneMenu -> {
                    oneTime = wtfOneMenu.getData();
                    if (oneTime == null || oneTime.size() == 0) ;
//                        ToastUtils.showShort("没查询到时间菜单");
                    else {
                        getTwoTime(oneTime.get(0));
                    }
                }, throwable -> {
                    throwable.printStackTrace();
                    ToastUtils.showShort(getString(R.string.getInfo_error_toast));
                });
    }

    //默认选中二级时间
    private void getTwoTime(String param) {
        RetrofitHelper.getDataForecastAPI()
                .getDataForecastContentBytime1(type, param)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(timeTwoMenu -> {
                    //把返回时间设置为世界时间
                    List<String> data = timeTwoMenu.getData();
                    for (int i = 0; i < data.size(); i++) {
                        String worldTimeStr= timeMinusHour(8, data.get(i));
                        data.set(i,worldTimeStr);
                    }
                    twoTime = data;
                    if (twoTime == null || twoTime.size() == 0) {
//                        ToastUtils.showShort("没查询到二级时间菜单");
                    } else {
                        time.setText(twoTime.get(0));
                    }
                }, throwable -> {
                    LogUtils.e(throwable);
                    ToastUtils.showShort(getString(R.string.getInfo_error_toast));
                });
    }

    //供ListPopowindows使用
    private void getTwoTime1(String param) {
        RetrofitHelper.getDataForecastAPI()
                .getDataForecastContentBytime1(type, param)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(timeTwoMenu -> {
                    List<String> data = timeTwoMenu.getData();
                    for (int i = 0; i < data.size(); i++) {
                        String worldTimeStr= timeMinusHour(8, data.get(i));
                        data.set(i,worldTimeStr);
                    }
                    twoTime = data;
                    menuTime.clear();
                    for (int i = 0; i < twoTime.size(); i++) {
                        menuTime.add(twoTime.get(i));
                    }
                    timeAdapter.notifyDataSetChanged();
                }, throwable -> {
                    LogUtils.e(throwable);
                    ToastUtils.showShort(getString(R.string.getInfo_error_toast));
                });
    }


    private DmcgjcMenuAdapter getAdapter1() {
        if (mAdapter1 == null) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            List<DmcgjcmenuBeen.DataBean> mData1 = new ArrayList<>();

            mAdapter1 = new DmcgjcMenuAdapter(mData1);
            mRecyclerView1.setLayoutManager(layoutManager);
            mRecyclerView1.setAdapter(mAdapter1);
            mAdapter1.setOnItemChildClickListener((adapter, view, position) -> {
                isStart = false;
                mViewPager.setScanScroll(true);
//                if (isStartPic != null)
//                    isStartPic.setImageResource(R.drawable.app_start);
                List<DmcgjcmenuBeen.DataBean> data = adapter.getData();
                int lastclick = ((DmcgjcMenuAdapter) adapter).getLastposition();
                DmcgjcmenuBeen.DataBean dataBeanlasted = data.get(lastclick);
                DmcgjcmenuBeen.DataBean dataBean = data.get(position);
                dataBeanlasted.setSelect(false);
                dataBean.setSelect(true);
                adapter.notifyItemChanged(lastclick);
                adapter.notifyItemChanged(position);
                ((DmcgjcMenuAdapter) adapter).setLastposition(position);

                TextView title = ((TextView) view);
                String menu = title.getText().toString();
                if (menu.equals("1小时降水预报")) {
                    ctype = "Precipitation_1h";
                }
                if (menu.equals("3小时降水预报")) {
                    ctype = "Precipitation_3h";
                }


                if (menu.equals("6小时降水预报")) {
                    ctype = "Precipitation_6h";
                }
                if (menu.equals("12小时降水预报")) {
                    ctype = "Precipitation_12h";
                }
                if (menu.equals("24小时降水预报")) {
                    ctype = "Precipitation_24h";
                }
                if (menu.equals("对流有效位能")) {
                    ctype = "Cape";
                }
                if (menu.equals("对流抑制")) {
                    ctype = "Cin";
                }
                if (menu.equals("1小时冰雹直径")) {
                    ctype = "Hail_1h";
                }
                if (menu.equals("最大雷达回波反射率")) {
                    ctype = "MaxDBZ";
                }
                if (menu.equals("整层水汽含量")) {
                    ctype = "PW";
                }
                if (menu.equals("地面2米温度")) {
                    ctype = "SurfaceTemperature";
                }

                if (menu.equals("地面风")) {
                    ctype = "SurfaceWind";
                }
                if (menu.equals("850百帕温度")) {
                    ctype = "Temperature_850hPa";
                }
                if (menu.equals("925百帕温度")) {
                    ctype = "Temperature_925hPa";
                }
                if (menu.equals("850百帕风场")) {
                    ctype = "Wind_850hPa";
                }
                if (menu.equals("925百帕风场")) {
                    ctype = "Wind_925hPa";
                }
                if (menu.equals("大风")) {
                    ctype = "Gale_ensemble";
                }
                if (menu.equals("降水")) {
                    ctype = "Precipitation_ensemble";
                }
                if (menu.equals("反射率")) {
                    ctype = "Thunder_ensemble";
                }
                if (selectTime.equals(""))
                    getTestdata();
                else getTestDataBytime(timeMinusHour(-8,selectTime));

                layoutManager.scrollToPositionWithOffset(position,0);
                layoutManager.setStackFromEnd(false);
            });
        }
        return mAdapter1;
    }


    public MultiGdybTxAdapterForGdybtx getAdapter3() {
        if (mAdapter3 == null) {
//            ExStaggeredGridLayoutManager layoutManager = new ExStaggeredGridLayoutManager(6, StaggeredGridLayoutManager.VERTICAL) {
//                @Override
//                public boolean canScrollVertically() {
//                    return false;
//                }
//            };
            ExStaggeredGridLayoutManager layoutManager = new ExStaggeredGridLayoutManager(8, StaggeredGridLayoutManager.VERTICAL);
            mAdapter3 = new MultiGdybTxAdapterForGdybtx(multitemList);
            mRecyclerView3.setLayoutManager(layoutManager);
            mRecyclerView3.setAdapter(mAdapter3);
            mAdapter3.setOnItemChildClickListener((adapter, view, position) -> {
                switch (view.getId()) {
                    case R.id.time:
                        if (isStart == false) {
                            mViewPager.setCurrentItem(position - 1);
                        }
                        break;
                    case R.id.pic_ready:
                        if (isStart == false) {
//                            isStartPic = ((ImageView) view);
//                            isStartPic.setImageResource(R.drawable.app_end);
                            MultiItemGdybTx multiItemGdybTx = new MultiItemGdybTx(MultiItemGdybTx.IMG, R.drawable.app_end);
                            multitemList.set(0, multiItemGdybTx);
                            getAdapter3().notifyItemChanged(0);
                            Message message = uiHandler.obtainMessage();
                            message.what = 1;
                            uiHandler.sendMessageDelayed(message, MyApplication.getInstance().auto_time);
                            isStart = true;
                            mViewPager.setScanScroll(false);
                        } else {
                            uiHandler.removeCallbacksAndMessages(null);
//                            isStartPic.setImageResource(R.drawable.app_start);
                            MultiItemGdybTx multiItemGdybTx = new MultiItemGdybTx(MultiItemGdybTx.IMG, R.drawable.app_start);
                            multitemList.set(0, multiItemGdybTx);
                            getAdapter3().notifyItemChanged(0);
                            isStart = false;
                            mViewPager.setScanScroll(true);
                        }

                        break;

                }
            });
        }
        return mAdapter3;
    }


    public void getTestdata() {
        progressDialog = ProgressDialog.show(context, "请稍等...", "获取数据中...", true);
        progressDialog.setCancelable(true);
        getAdapter3();
        RetrofitHelper.getDataForecastAPI()
                .getEcContent1(type, ctype)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ecBeen -> {
                    progressDialog.dismiss();
                    recycle_skipto_position = 2;
                    now_postion = 1;
                    isStart = false;
//                    if (isStartPic != null) {
//                        isStartPic.setImageResource(R.drawable.app_start);
//                        mViewPager.setScanScroll(true);
//                    }
                    mViewPager.setScanScroll(true);
                    List<Fragment> HuancunfragmentList = new ArrayList<>();
                    for (int i = 0; i < fragmentList.size(); i++) {
                        Fragment fragment = fragmentList.get(i);
                        HuancunfragmentList.add(fragment);
                    }
                    fragmentList.clear();
                    urls = ecBeen.getData().getUrl();
                    for (int i = 0; i < urls.size(); i++) {
                        PicLoadFragment fragment = PicLoadFragment.newInstance(urls.get(i), urls, type + "/" + ctype);
                        fragmentList.add(fragment);
                    }
                    viewPagerAdapter = new MyPagerAdapter(
                            getSupportFragmentManager(), fragmentList, HuancunfragmentList);
                    // 绑定适配器
                    mViewPager.setAdapter(viewPagerAdapter);
                    mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                        }

                        @Override
                        public void onPageSelected(int position) {
                            if (CallBackUtil.picdispath != null) {
                                CallBackUtil.doDispathPic(position);
                            }
                            MultiItemGdybTx multiItemGdybTxStop = multitemList.get(now_postion);
                            GkdmClickBeen clickBeenStop = multiItemGdybTxStop.getContent();
                            clickBeenStop.setOnclick(false);
                            multiItemGdybTxStop.setContent(clickBeenStop);
                            MultiItemGdybTx multiItemGdybTxNow = multitemList.get(position + 1);
                            GkdmClickBeen clickBeenNow = multiItemGdybTxNow.getContent();
                            clickBeenNow.setOnclick(true);
                            multiItemGdybTxNow.setContent(clickBeenNow);
                            multitemList.set(now_postion, multiItemGdybTxStop);
                            multitemList.set(position + 1, multiItemGdybTxNow);
                            mAdapter3.notifyItemChanged(now_postion);
                            mAdapter3.notifyItemChanged(position + 1);
                            now_postion = position + 1;
                            mRecyclerView3.smoothScrollToPosition(position + 1);
                            recycle_skipto_position = position + 2;
                            if (recycle_skipto_position > multitemList.size() - 1)
                                recycle_skipto_position = 1;
                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {

                        }
                    });

                    //播放轮播
                    List<String> time = ecBeen.getData().getTime();
                    multitemList.clear();
                    MultiItemGdybTx multiItemGdybTx = new MultiItemGdybTx(MultiItemGdybTx.IMG, R.drawable.app_start);
                    multitemList.add(multiItemGdybTx);
                    for (int i = 0; i < time.size(); i++) {

                        GkdmClickBeen clickBeen = new GkdmClickBeen();
                        if (i == 0)
                            clickBeen.setOnclick(true);
                        else clickBeen.setOnclick(false);
                        clickBeen.setText(time.get(i));

                        multiItemGdybTx = new MultiItemGdybTx(MultiItemGdybTx.TIME_TEXT, clickBeen);
                        multitemList.add(multiItemGdybTx);
                    }
                    getAdapter3().setNewData(multitemList);
                    //播放轮播

                }, throwable -> {
                    progressDialog.dismiss();
                    LogUtils.e(throwable);
                    ToastUtils.showShort(getString(R.string.getInfo_error_toast));
                });
    }


    public void getTestDataBytime(String timePs) {
        progressDialog = ProgressDialog.show(context, "请稍等...", "获取数据中...", true);
        progressDialog.setCancelable(true);
        getAdapter3();
        RetrofitHelper.getDataForecastAPI()
                .getEcContent2(type, ctype, timePs)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ecBeen -> {
                    progressDialog.dismiss();
                    recycle_skipto_position = 2;
                    now_postion = 1;
                    isStart = false;
                    uiHandler.removeCallbacksAndMessages(null);
                    mViewPager.setScanScroll(true);
                    List<Fragment> HuancunfragmentList = new ArrayList<>();
                    for (int i = 0; i < fragmentList.size(); i++) {
                        Fragment fragment = fragmentList.get(i);
                        HuancunfragmentList.add(fragment);
                    }
                    fragmentList.clear();
                    urls = ecBeen.getData().getUrl();
                    for (int i = 0; i < urls.size(); i++) {
                        PicLoadFragment fragment = PicLoadFragment.newInstance(urls.get(i), urls, type + "/" + ctype);
                        fragmentList.add(fragment);
                    }
                    viewPagerAdapter = new MyPagerAdapter(
                            getSupportFragmentManager(), fragmentList, HuancunfragmentList);
                    // 绑定适配器
                    mViewPager.setAdapter(viewPagerAdapter);
                    mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                        }

                        @Override
                        public void onPageSelected(int position) {
                            if (CallBackUtil.picdispath != null) {
                                CallBackUtil.doDispathPic(position);
                            }
                            MultiItemGdybTx multiItemGdybTxStop = multitemList.get(now_postion);
                            GkdmClickBeen clickBeenStop = multiItemGdybTxStop.getContent();
                            clickBeenStop.setOnclick(false);
                            multiItemGdybTxStop.setContent(clickBeenStop);
                            MultiItemGdybTx multiItemGdybTxNow = multitemList.get(position + 1);
                            GkdmClickBeen clickBeenNow = multiItemGdybTxNow.getContent();
                            clickBeenNow.setOnclick(true);
                            multiItemGdybTxNow.setContent(clickBeenNow);
                            multitemList.set(now_postion, multiItemGdybTxStop);
                            multitemList.set(position + 1, multiItemGdybTxNow);
                            mAdapter3.notifyItemChanged(now_postion);
                            mAdapter3.notifyItemChanged(position + 1);
                            now_postion = position + 1;
                            mRecyclerView3.smoothScrollToPosition(position + 1);
                            recycle_skipto_position = position + 2;
                            if (recycle_skipto_position > multitemList.size() - 1)
                                recycle_skipto_position = 1;
                        }


                        @Override
                        public void onPageScrollStateChanged(int state) {

                        }
                    });


                    //播放轮播
                    List<String> time = ecBeen.getData().getTime();
                    multitemList.clear();
                    MultiItemGdybTx multiItemGdybTx = new MultiItemGdybTx(MultiItemGdybTx.IMG, R.drawable.app_start);
                    multitemList.add(multiItemGdybTx);
                    for (int i = 0; i < time.size(); i++) {

                        GkdmClickBeen clickBeen = new GkdmClickBeen();
                        if (i == 0)
                            clickBeen.setOnclick(true);
                        else clickBeen.setOnclick(false);
                        clickBeen.setText(time.get(i));

                        multiItemGdybTx = new MultiItemGdybTx(MultiItemGdybTx.TIME_TEXT, clickBeen);
                        multitemList.add(multiItemGdybTx);
                    }
                    getAdapter3().setNewData(multitemList);
                    //播放轮播


                }, throwable -> {
                    progressDialog.dismiss();
                    LogUtils.e(throwable);
                    ToastUtils.showShort(getString(R.string.getInfo_error_toast));
                });
    }


    private Handler uiHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mRecyclerView3 != null) {
                switch (msg.what) {
                    case 1:
                        mViewPager.setCurrentItem(recycle_skipto_position - 1);
                        if (recycle_skipto_position > multitemList.size() - 1) {
                            recycle_skipto_position = 1;
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


    public String timeMinusHour(int hour,String timeStr){
        String worldTimeStr = null;
        SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHH");
        try {
            Date parse = format.parse(timeStr);
            long l = parse.getTime() + hour* 3600 * 1000;
            Date worldTime=new Date(l);
            worldTimeStr= format.format(worldTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return worldTimeStr;
    }

}





