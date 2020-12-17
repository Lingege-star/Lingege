李四李四李四李四李四李四李四李四李四李四李四李四
李四李四李四李四李四李四李四李四李四李四李四李四
李四李四李四李四


import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.factory.Phone;
import com.example.myapplication.factory.PhoneFactory;
import com.example.myapplication.factory2.HuaWeiPhoneFactory;
import com.example.myapplication.factory2.SunmPhoneFactory;
import com.example.myapplication.factory4.ThreadPoolFactory;
import com.example.myapplication.sginle.ApiService;
import com.example.myapplication.sginle.RetrofitMannager;
import com.example.myapplication.sginle.ThreadPoolManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_start;
    private Button btn_factory;
    private Button btn_factory2;
    private Button btn_factory3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this);
        btn_factory = (Button) findViewById(R.id.btn_factory);
        btn_factory.setOnClickListener(this);
        btn_factory2 = (Button) findViewById(R.id.btn_factory2);
        btn_factory2.setOnClickListener(this);
        btn_factory3 = (Button) findViewById(R.id.btn_factory3);
        btn_factory3.setOnClickListener(this);
    }


    //1.Retrfit         2.xUtils


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                ThreadPoolManager.getmManager().executeTask(runnable);
                ApiService apiSercice = RetrofitMannager.getmManager().getApiSercice();
                break;
            case R.id.btn_factory:
                Phone miPhone = PhoneFactory.createPhone("miPhone");
                miPhone.makePhone();

                Phone iPhone = PhoneFactory.createPhone("iPhone");
                iPhone.makePhone();
                break;
            case R.id.btn_factory2:
                HuaWeiPhoneFactory huaWeiPhoneFactory = new HuaWeiPhoneFactory();
                com.example.myapplication.factory2.Phone phone = huaWeiPhoneFactory.createPhone();
                phone.makePhone();

                SunmPhoneFactory sunmPhoneFactory = new SunmPhoneFactory();
                com.example.myapplication.factory2.Phone phone1 = sunmPhoneFactory.createPhone();
                phone1.makePhone();

                break;
            case R.id.btn_factory3:
                ThreadPoolFactory.getThreadPool(ThreadPoolFactory.CUSTOM_THREADPOOL).
                        executeTask(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });

                break;


            case R.id.btn_factory4:
                ThreadPoolFactory.getThreadPool(ThreadPoolFactory.CUSTOM_THREADPOOL).
                        executeTask(new Runnable() {
                            @Override
                            public void run() {

//TODO 下载文件

                            }
                        });

                break;


        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //TODO 执行耗时操作
             Log.e("TAG","哈哈哈哈哈哈");

        }
    };


    @Override
    protected void onDestroy() {
        ThreadPoolManager.getmManager().removeTask(runnable);
        super.onDestroy();
    }
}