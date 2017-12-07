package com.xiaolei.demo.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xiaolei.demo.http.HttpMethods;
import com.xiaolei.demo.model.entity.Subject;
import com.xiaolei.demo.subscribers.ProgressSubscriber;
import com.xiaolei.demo.subscribers.SubscriberOnNextListener;
import com.xiaolei.http.demo.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by ZhaoLei on 2017/12/7 11:28
 * E-Mail Address：17610230792@163.com
 */
public class MainActivity extends Activity {


    @BindView(R.id.tv_content)
    TextView mTvContent;


    private SubscriberOnNextListener mOnNextListener;                   //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        mOnNextListener = new SubscriberOnNextListener<List<Subject>>() {
            @Override
            public void onNext(List<Subject> subjects) {
                mTvContent.setText(subjects.toString());
            }
        };

    }


    //进行网络请求
    private void getMovie() {
        HttpMethods.getInstance().getTopMovie(new ProgressSubscriber(mOnNextListener, MainActivity.this), 0, 100);

    }

    @OnClick(R.id.btn_get)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_get:
                getMovie();
                break;
        }
    }
}
