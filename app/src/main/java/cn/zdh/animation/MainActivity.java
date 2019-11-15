package cn.zdh.animation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //帧动画
    public void animation1(View view) {
        startActivity(new Intent(this, Animation1Activity.class));
    }

    //补间动画
    public void animation2(View view) {
        startActivity(new Intent(this, Animation2Activity.class));
    }

    //属性动画
    public void animation3(View view) {
        startActivity(new Intent(this, Animation3Activity.class));
    }

}
