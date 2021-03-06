package net.univr.pushi.jxatmosphere.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import net.univr.pushi.jxatmosphere.R;
import net.univr.pushi.jxatmosphere.beens.GkdmClickBeen;

import java.util.List;

/**
 * author : Administrator wl
 * e-mail : 389456264@qq.com
 * time   : 2018/05/03
 * desc   :
 * version: 1.0
 */


public class GkdmgcAdapter3 extends BaseQuickAdapter<GkdmClickBeen, BaseViewHolder> {


    public int lastClickPosition=0;

    public void setLastClickPosition(int lastClickPosition) {
        this.lastClickPosition = lastClickPosition;
    }

    public GkdmgcAdapter3(@Nullable List<GkdmClickBeen> data) {
        super(R.layout.item_recycle_horizontal_gkdmgc3_layout, data);
    }

    public int getLayout() {
        return R.layout.item_recycle_horizontal_gkdmgc3_layout;
    }

    @Override
    protected void convert(BaseViewHolder helper, GkdmClickBeen item) {
        TextView TextView = ((TextView) helper.getView(R.id.time));
        TextView.setText(item.getText());
        if (item.getOnclick() == true) {
            TextView.setBackgroundColor(Color.parseColor("#0081e7"));
//            TextView.setTextColor(Color.parseColor("#537ed7"));
        } else {
            TextView.setBackgroundColor(Color.parseColor("#FFFFFF"));
//            TextView.setTextColor(Color.parseColor("#FFFFFF"));
        }
        helper.addOnClickListener(R.id.time);

    }
}