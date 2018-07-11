package net.univr.pushi.jxatmosphere.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.univr.pushi.jxatmosphere.R;
import net.univr.pushi.jxatmosphere.base.RxLazyFragment;
import net.univr.pushi.jxatmosphere.feature.PicDealActivity;
import net.univr.pushi.jxatmosphere.utils.PicUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DmcgjcPicFragment extends RxLazyFragment {


    @BindView(R.id.pic)
    ImageView pic;
    @BindView(R.id.five_m)
    TextView five_m;
    @BindView(R.id.one_hour)
    TextView one_hour;
    private static ArrayList<String> temp;
    DMCGJCFragment dmcgjcFragment;
    @BindView(R.id.sjjg)
    LinearLayout sjjg_lay;
    int visibility;
    String interval;
    String pack;
    private Bitmap bitmap;
    private String url;

    public static DmcgjcPicFragment newInstance(String url, List<String> urls, DMCGJCFragment dmcgjcFragment, int visibility, String interval, String pack) {
        DmcgjcPicFragment picLoadFragment = new DmcgjcPicFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        bundle.putString("pack", pack);
        temp = new ArrayList<>();
        for (int i = 0; i < urls.size(); i++) {
            temp.add(urls.get(i));
        }
        bundle.putStringArrayList("urls", temp);
        picLoadFragment.setArguments(bundle);
        picLoadFragment.dmcgjcFragment = dmcgjcFragment;
        picLoadFragment.visibility = visibility;
        picLoadFragment.interval = interval;
        return picLoadFragment;
    }

    public DmcgjcPicFragment() {
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_dmcgjc_pic;
    }

    @Override
    public void finishCreateView(Bundle state) {


        if (getArguments() != null) {
            //取出保存的值
            url = getArguments().getString("url");
            pack = getArguments().getString("pack");
        }
//        Picasso.with(getActivity()).load(url).placeholder(R.drawable.app_imageplacehold).into(pic);
        String finalUrl = url;
        pic.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(getActivity(), PicDealActivity.class);
                intent.putExtra("url", finalUrl);
                intent.putExtra("pack", pack);
                intent.putStringArrayListExtra("urls", temp);
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        if (interval.equals("5")) {
            ;
        } else {
            five_m.setBackground(getResources().getDrawable(R.drawable.gd_text_bg1));
            five_m.setTextColor(getResources().getColor(R.color.toolbar_color));
            one_hour.setBackground(getResources().getDrawable(R.drawable.gd_text_bg3_select));
            one_hour.setTextColor(getResources().getColor(R.color.white));
        }
        if (visibility == 1) sjjg_lay.setVisibility(View.VISIBLE);
        five_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dmcgjcFragment.interval = "5";
                dmcgjcFragment.progressDialog = ProgressDialog.show(getContext(), "请稍等...", "获取数据中...", true);
                dmcgjcFragment.progressDialog.setCancelable(true);
                dmcgjcFragment.getTestdata();
            }
        });
        one_hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dmcgjcFragment.interval = "1";
                dmcgjcFragment.progressDialog = ProgressDialog.show(getContext(), "请稍等...", "获取数据中...", true);
                dmcgjcFragment.progressDialog.setCancelable(true);
                dmcgjcFragment.getTestdata();
            }
        });

            new Thread(new Runnable() {
                @Override
                public void run() {
                    PicUtils.decodeUriAsBitmapFromNet(url,pack);
                    uiHandler.sendEmptyMessage(0);
                }
            }).start();

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bitmap = PicUtils.readLocalImage(url, pack,getActivity());
        pic.setImageBitmap(bitmap);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        uiHandler.removeCallbacksAndMessages(null);
        if (bitmap != null) {
            bitmap.recycle();
            System.gc();
        }

    }

    private Handler uiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                bitmap = PicUtils.readLocalImage(url, pack,getActivity());
                if(pic!=null){
                    pic.setImageBitmap(bitmap);
                }
            }
        }
    };
}