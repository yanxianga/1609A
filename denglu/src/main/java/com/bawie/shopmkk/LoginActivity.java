package com.bawie.shopmkk;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawie.shopmkk.di.presenter.LoginPresenter;
import com.bawie.shopmkk.utils.Constance;
import com.bawie.shopmkk.di.view.LoginView;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 步骤
 * 1.初始化控件
 * 2.设置输入的密码为点点129是点点  127是显示密码
 * 3.监听记住密码复选框和跳转注册页面 bindEvent();
 * a.
 * 4.记住密码 rememberThePassword();
 * a.
 * 5.调用P层
 * 6.点击登录按钮 获取输入密码  存入Map集合便于管理
 * 7.把输入的密码添加到Map集合 起个名字
 * 8.调用P层的方法  向P层传值
 */
public class LoginActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.login_phone)
    EditText loginPhone;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.login_eye_check)
    ImageView loginEyeCheck;
    @BindView(R.id.login_jizhu)
    CheckBox loginJizhu;
    @BindView(R.id.login_zhuce)
    TextView loginZhuce;
    @BindView(R.id.login_button)
    Button loginButton;
    @BindView(R.id.QQ)
    ImageView QQ;
    private EditText login_phone;
    private EditText login_password;
    private ImageView login_eye_check;
    private CheckBox login_jizhu;
    private TextView login_zhuce;
    private Button login_button;
    private LoginPresenter mLoginPresenter;
    private SharedPreferences mSharedPreferences;
    private String mPwd;
    private String mPhone;
    private Map<String, String> mLoginMap;

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            Toast.makeText(LoginActivity.this, "成功了", Toast.LENGTH_LONG).show();


        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(LoginActivity.this, "失败：" + t.getMessage(),                                     Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mSharedPreferences = getSharedPreferences("kk", MODE_PRIVATE);
        //初始化控件
        initView();
        //初始化数据
        initData();
    }

    private void initData() {
        //设置输入的为点点
        login_password.setInputType(129);
        //监听记住密码复选框和跳转注册页面
        bindEvent();
        //记住密码
        rememberThePassword();
        //调用P层
        mLoginPresenter = new LoginPresenter(this);
        //点击登录
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入的账号和密码
                mPhone = login_phone.getText().toString().trim();
                mPwd = login_password.getText().toString().trim();
                //存入HashMap集合便于管理
                // Map<String,String> loginMap = new HashMap<>();
                mLoginMap = new HashMap<>();
                mLoginMap.put("phone", mPhone);
                mLoginMap.put("pwd", mPwd);
                //向P层传值  上下文 路径  Map集合
                mLoginPresenter.onSuccess(Constance.USER_LOGIN_URL, mLoginMap);
            }
        });
    }

    /**
     * 记住密码
     */
    private void rememberThePassword() {
        //判断记住密码复选框是否选中
        if (mSharedPreferences.getBoolean("rememberPassword", false)) {
            login_jizhu.setChecked(true);
            login_phone.setText(mSharedPreferences.getString("phone", ""));
            login_password.setText(mSharedPreferences.getString("pwd", ""));
        }
    }

    /**
     * 监听记住密码复选框和跳转注册页面
     */
    private void bindEvent() {
        //记住密码是否选中
        login_jizhu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mSharedPreferences.edit().putBoolean("rememberPassword", true).commit();
                } else {
                    mSharedPreferences.edit().putBoolean("rememberPassword", false).commit();
                }
            }
        });
        //小眼睛
        login_eye_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断密码的类型  是否是129
                if (login_password.getInputType() == 129) {
                    login_password.setInputType(127);
                } else {
                    login_password.setInputType(129);
                }
            }
        });
        //注册
        login_zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

    }

    /**
     * 初始化控件
     */
    private void initView() {
        login_phone = (EditText) findViewById(R.id.login_phone);
        login_password = (EditText) findViewById(R.id.login_password);
        login_eye_check = (ImageView) findViewById(R.id.login_eye_check);
        login_jizhu = (CheckBox) findViewById(R.id.login_jizhu);
        login_zhuce = (TextView) findViewById(R.id.login_zhuce);
        login_button = (Button) findViewById(R.id.login_button);


    }

    /**
     * view层实现的方法
     *
     * @param data
     */
    @Override
    public void onSuccess(String data) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
        if (data.equals("登录成功")) {
            SharedPreferences.Editor edit = mSharedPreferences.edit();
            edit.putString("phone", mPhone);
            edit.putString("pwd", mPwd);
            edit.commit();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLoginPresenter != null) {
            mLoginPresenter = null;
        }
    }

    @OnClick(R.id.QQ)
    public void onViewClicked() {
        UMShareAPI mShareAPI=UMShareAPI.get(LoginActivity.this);
        mShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, authListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
