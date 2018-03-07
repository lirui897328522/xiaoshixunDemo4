package com.example.xiaoshixundemo4.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.xiaoshixundemo4.R;
import com.example.xiaoshixundemo4.adapter.MyNewsAdapter;
import com.example.xiaoshixundemo4.bean.NewsBean;
import com.google.gson.Gson;
import com.youth.banner.Banner;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Main2Activity extends AppCompatActivity {

    private Banner banner;
    private RecyclerView rv;
    private String path="http://172.16.54.19:8080/a/data1.json";
    private List<NewsBean.ResultBean.DataBean> listdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        initData();
    }

    private void initData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        OkHttpClient client = okHttpClient.newBuilder()
                .cache(new Cache(this.getCacheDir(), 1024 * 1024))
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder().url(path).cacheControl(new CacheControl.Builder().maxStale(20, TimeUnit.MINUTES).build()).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson=new Gson();
                        NewsBean newsBean = gson.fromJson(string, NewsBean.class);
                        listdata = newsBean.getResult().getData();
                        initAdapter();
                    }
                });

            }
        });
    }

    private void initAdapter() {
        MyNewsAdapter myNewsAdapter = new MyNewsAdapter(listdata, this);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(manager);
        rv.setAdapter(myNewsAdapter);
    }

    private void initView() {
        banner = (Banner) findViewById(R.id.banner);
        rv = (RecyclerView) findViewById(R.id.rv);
    }
}
