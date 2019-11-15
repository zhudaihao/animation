package cn.zdh.animation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * 帧动画--》原理 像幻灯片样一张一张图片连续播放 达到一个动画效果
 */
public class Animation1Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);


        /**
         * xml代码添加动画
         */
        //1在drawable文件夹里面新建一个animation_list 类的文件
        //2xml添加动画图片--->在xml中添加（添加到src 或者background）
        //3获取动画对象
        ImageView iv1 = findViewById(R.id.iv1);
        AnimationDrawable animationDrawable1 = (AnimationDrawable) iv1.getBackground();
        //4启动动画
        animationDrawable1.start();


        /**
         * Java代码添加动画
         */
        //1在drawable文件夹里面新建一个animation_list 类的文件
        //2获取需要设置动画的view
        ImageView iv2 = findViewById(R.id.iv2);
        //3设置动画
        iv2.setBackgroundResource(R.drawable.animation1);
        //4获取动画对象
        AnimationDrawable animationDrawable2 = (AnimationDrawable) iv2.getBackground();
        //5启动动画
        animationDrawable2.start();


    }
}
