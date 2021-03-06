package net.univr.pushi.jxatmosphere.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;

import net.univr.pushi.jxatmosphere.R;
import net.univr.pushi.jxatmosphere.utils.PicassoTransformation;

import java.util.List;

/**
 * author : Administrator wl
 * e-mail : 389456264@qq.com
 * time   : 2018/05/12
 * desc   :
 * version: 1.0
 */


public class GeneforeAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    Context context;
    public GeneforeAdapter(@Nullable List<String> data, Context context) {
        super(R.layout.item_recycle_genefore_layout, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView image1 = helper.getView(R.id.image1);
        Picasso.with(mContext).load(item).placeholder(R.drawable.app_imageplacehold).transform(new PicassoTransformation(context)).into(image1);
    }


}
