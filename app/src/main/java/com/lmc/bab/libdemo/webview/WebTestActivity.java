package com.lmc.bab.libdemo.webview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.just.library.AgentWeb;
import com.just.library.ChromeClientCallbackManager;
import com.lmc.bab.lib.base.BaseActivity;
import com.lmc.bab.libdemo.R;

/**
 * Created by limin on 2017/10/12.
 */

public class WebTestActivity extends BaseActivity {

    //view
    private AgentWeb mAgentWeb;
    private TextView mTvTitle;
    private LinearLayout mContainer;
    //data
    private String mUrl;

    public static void enterActivity(Context context){
        Intent intent = new Intent(context, WebTestActivity.class);
        context.startActivity(intent);
    }

    public static void enterActivity(Context context, String url){
        Intent intent = new Intent(context, WebTestActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_test);
        getInParam();
        initView();
        initEvent();
        initData();
    }

    private void getInParam(){
        mUrl = getIntent().getStringExtra("url");
    }

    private void initView(){
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mContainer = (LinearLayout) findViewById(R.id.lay_container);
    }

    private void initEvent(){

    }

    private void initData(){
        if(mUrl == null || mUrl.length() <= 0){
            mUrl = "http://www.jd.com";
        }
        mAgentWeb = AgentWeb.with(WebTestActivity.this)//传入Activity or Fragment
                .setAgentWebParent(mContainer, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams ,第一个参数和第二个参数应该对应。
                .useDefaultIndicator()// 使用默认进度条
                .defaultProgressBarColor() // 使用默认进度条颜色
                .setReceivedTitleCallback(new ChromeClientCallbackManager.ReceivedTitleCallback() {
                    @Override
                    public void onReceivedTitle(WebView view, String title) {
                        if(title != null) {
                            mTvTitle.setText(title);
                        }
                    }
                }) //设置 Web 页面的 title 回调
                .createAgentWeb()//
                .ready()
                .go(mUrl);
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }
}
