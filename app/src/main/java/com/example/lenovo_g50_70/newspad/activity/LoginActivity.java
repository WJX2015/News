package com.example.lenovo_g50_70.newspad.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo_g50_70.newspad.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;

/**
 * 登陆界面
 * 手机+验证码
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtPhone; //手机号
    private EditText edtInputCode; //填写验证码
    private Button btnGetCode; //获取验证码
    private Button btnRegister; //用户登陆
    private String mPhone;  //输入的手机号
    private String mCode;   //输入的验证码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }

    private void initViews() {
        edtPhone = (EditText) findViewById(R.id.edt_phone);
        edtInputCode = (EditText) findViewById(R.id.edt_inputcode);
        btnGetCode = (Button) findViewById(R.id.btn_getcode);
        btnRegister = (Button) findViewById(R.id.btn_register);
        btnGetCode.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_getcode:
                mPhone = edtPhone.getText().toString().trim();
                //判断手机号是否正确
                if (isPhoneNumber(mPhone)) {
                    //获取短信验证码，这方法在用户管理的手机号相关功能
                    BmobSMS.requestSMSCode(mPhone, "wjx", new QueryListener<Integer>() {
                        @Override
                        public void done(Integer smsId, BmobException ex) {
                            if (ex == null) {//验证码发送成功
                                Log.i("smile", "短信id：" + smsId);//用于后续的查询本次短信发送状态
                            }
                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(),"不存在此手机号",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_register:
                //手机号+验证码登陆，这方法在用户管理的手机号相关功能
                mCode = edtInputCode.getText().toString().trim();
                BmobUser.loginBySMSCode(mPhone, mCode, new LogInListener<BmobUser>() {
                    @Override
                    public void done(BmobUser user, BmobException e) {
                        if (user != null) {
                            Toast.makeText(getApplicationContext(),
                                    BmobUser.getCurrentUser().getMobilePhoneNumber(),
                                    Toast.LENGTH_SHORT).show();
                            finish();
                            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                        }
                    }
                });
                break;
        }
    }

    public boolean isPhoneNumber(String str) {
        //电话格式表达式
        String regex = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        //设定查看模式
        Pattern pattern = Pattern.compile(regex);
        //判断str是否匹配，返回匹配结果
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }
}
