package net.univr.pushi.jxatmosphere.feature;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;

import net.univr.pushi.jxatmosphere.R;
import net.univr.pushi.jxatmosphere.base.BaseActivity;
import net.univr.pushi.jxatmosphere.remote.RetrofitHelper;

import butterknife.BindView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrievePasswordNextActivity extends BaseActivity {


    @BindView(R.id.re_pw)
    EditText rePw;
    @BindView(R.id.re_pw_1)
    EditText rePw1;


    @Override
    public int getLayoutId() {
        return R.layout.activity_retrieve_password_next;
    }



    @OnClick({R.id.retrieve_back, R.id.retrieve_password_complete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.retrieve_back:
                finish();
                break;
            case R.id.retrieve_password_complete:
                String rePwStr=rePw.getText().toString();
                String rePwStr1=rePw1.getText().toString();
                Intent intent = getIntent();
                String phone=intent.getStringExtra("phone");
                if(!rePwStr.isEmpty()&&!rePwStr1.isEmpty()){
                    if(rePwStr.equals(rePwStr1)){
                        RetrofitHelper.getUserAPI()
                                .userPSModify(phone,rePwStr)
                                .compose(bindToLifecycle())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(userBeen -> {
                                    String errcode = userBeen.getErrcode();
                                    String errmsg = userBeen.getErrmsg();
                                    if(errcode.equals("0")){
                                        Toast.makeText(context, errmsg, Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                    else  Toast.makeText(context, errmsg, Toast.LENGTH_SHORT).show();
                                }, throwable -> {
                                    LogUtils.e(throwable);
                                    ToastUtils.showShort(getString(R.string.getInfo_error_toast));
                                });
                    }
                    else Toast.makeText(context, "请输入一致的密码", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(context, "请填写两次密码", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
