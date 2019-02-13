package com.bawie.shopmkk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawie.shopmkk.di.presenter.LoginPresenter;
import com.bawie.shopmkk.utils.Constance;
import com.bawie.shopmkk.di.view.LoginView;

import java.util.HashMap;
import java.util.Map;

/**
 * 步骤
 * 1.初始化控件
 * 2.
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, LoginView {

    private EditText register_phone;
    private EditText register_yanzheng;
    private EditText register_password;
    private ImageView register_eye_check;
    private TextView register_login;
    private Button register_zhuce;
    private Map<String, String> mLoginMap;
    private String mPhone;
    private String mPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //首先把输入的密码设置成不可见的
        //register_password.setInputType(129);
        //初始化控件
        initView();
        //监听小眼睛和点击登录
        bindEvent();
    }
    /**
     * 监听小眼睛和点击登录
     */
    private void bindEvent() {
        register_eye_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果是小圆点  设置成可见
                if (register_password.getInputType() == 129){
                    register_password.setInputType(127);
                }else {
                    //否则设置成不可见
                    register_password.setInputType(129);
                }
            }
        });
        //点击登录  跳转到登录的页面
        register_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
    }
    /**
     * 点击注册
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_zhuce:
                //点击注册的按钮
                //获取输入的账号密码
                mPhone = register_phone.getText().toString().trim();
                mPassword = register_password.getText().toString().trim();
                //存入HashMap集合便于管理
                // Map<String,String> loginMap = new HashMap<>();
                mLoginMap = new HashMap<>();
                mLoginMap.put("phone", mPhone);
                mLoginMap.put("pwd", mPassword);
                //创建P层
                LoginPresenter loginPresenter = new LoginPresenter(RegisterActivity.this);
                loginPresenter.onSuccess(Constance.USER_REGISTER_URL,mLoginMap);
                break;
        }
    }
    private void initView() {
        register_phone = (EditText) findViewById(R.id.register_phone);
        register_yanzheng = (EditText) findViewById(R.id.register_yanzheng);
        register_password = (EditText) findViewById(R.id.register_password);
        register_eye_check = (ImageView) findViewById(R.id.register_eye_check);
        register_login = (TextView) findViewById(R.id.register_login);
        register_zhuce = (Button) findViewById(R.id.register_zhuce);
        register_zhuce.setOnClickListener(this);
    }
    /**
     *成功
     * @param data
     */
    @Override
    public void onSuccess(String data) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }
}
