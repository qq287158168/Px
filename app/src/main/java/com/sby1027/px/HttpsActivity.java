package com.sby1027.px;

import static android.webkit.WebSettings.LayoutAlgorithm.SINGLE_COLUMN;

import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.sby1027.px.databinding.ActivityHttpsBinding;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.MemoryCookieStore;
import com.zhy.http.okhttp.https.HttpsUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.OkHttpClient;

public class HttpsActivity extends AppCompatActivity {

    private static final String TAG = "HttpsActivity";

    WebView web;

    private HttpsUtils.SSLParams sslParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_https);

        ActivityHttpsBinding inflate = ActivityHttpsBinding.inflate(getLayoutInflater());
        web = inflate.web;

        sslParams = HttpsUtils.getSslSocketFactory(null, null, null);


//        try {
//            sslParams = HttpsUtils.getSslSocketFactory(
//                    new InputStream[]{getResources().getAssets().open("srca.cer")}, null, null);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//支持javascript
        web.getSettings().setJavaScriptEnabled(true);
// 设置可以支持缩放
        web.getSettings().setSupportZoom(true);
// 设置出现缩放工具
        web.getSettings().setBuiltInZoomControls(true);
        web.getSettings().setDisplayZoomControls(false);
//扩大比例的缩放
        web.getSettings().setUseWideViewPort(true);
//自适应屏幕
        web.getSettings().setLayoutAlgorithm(SINGLE_COLUMN);
        web.getSettings().setLoadWithOverviewMode(true);


//        web.loadUrl("http:www.baidu.com");
        web.loadUrl("http://43.240.244.162//YUNTU.ASPX");
        web.setWebViewClient(new WebViewClient() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                view.loadUrl(request.getUrl().toString());
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {

                handler.proceed();

                super.onReceivedSslError(view, handler, error);
            }
        });


        CookieJarImpl cookieJar1 = new CookieJarImpl(new MemoryCookieStore());
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .addInterceptor(new LoggerInterceptor("TAG"))
                .cookieJar(cookieJar1)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        Log.i(TAG, hostname);
                        return true;
                    }
                })
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .build();
        OkHttpUtils.initClient(okHttpClient);

        String url = "https://kyfw.12306.cn/otn/";

        OkHttpUtils.get()//
                .url(url)//
                .id(101)//
                .build()//
                .execute(new MyStringCallback());


    }

    private class MyStringCallback extends StringCallback {
        @Override
        public void onError(Call call, Exception e, int id) {
            Log.e(TAG, "MyStringCallback错误");
        }

        @Override
        public void onResponse(String response, int id) {

            Log.i(TAG, response);
            //TODO: 2017/5/20 12306的响应
        }
    }

    @Override
    public void onBackPressed() {

        if (web.canGoBack()) {
            web.goBack();
            return;
        }

        super.onBackPressed();
    }
}
