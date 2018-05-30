package net.univr.pushi.jxatmosphere.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import net.univr.pushi.jxatmosphere.R;
import net.univr.pushi.jxatmosphere.beens.DmcgjcmenuBeen;

import java.util.List;

/**
 * author : Administrator wl
 * e-mail : 389456264@qq.com
 * time   : 2018/05/05
 * desc   :
 * version: 1.0
 */


public class DmcgjcMenuAdapter extends BaseQuickAdapter<DmcgjcmenuBeen.DataBean, BaseViewHolder> {


    int lastposition = 0;

    public void setLastposition(int lastposition) {
        this.lastposition = lastposition;
    }

    public int getLastposition() {
        return lastposition;
    }

    public DmcgjcMenuAdapter(@Nullable List<DmcgjcmenuBeen.DataBean> data) {
        super(R.layout.item_recycle_horizontal_gkdmgc1_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DmcgjcmenuBeen.DataBean item) {
        TextView title = helper.getView(R.id.title);
        View view = helper.getView(R.id.tabline);
        title.setText(item.getZnName());
        if(item.isSelect()){
            view.setVisibility(View.VISIBLE);
            title.setTextSize(15);
            title.setTextColor(Color.parseColor("#0081e7"));
        }

        else{
            view.setVisibility(View.INVISIBLE);
            title.setTextColor(Color.parseColor("#121111"));
            title.setTextSize(13);
        }

        helper.addOnClickListener(R.id.title);
    }

}


