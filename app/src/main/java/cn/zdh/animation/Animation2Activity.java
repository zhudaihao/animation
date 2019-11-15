package cn.zdh.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

/**
 * 补间动画-->透明度 旋转 缩放 平移
 * 原理 改变canvas 坐标系上面的值 再进行重新绘制；
 * <p>
 * 缺点：
 * view本身的属性没有发生改变
 * 意味着类似平移后点击view没有反应，点击平移前位置有响应
 */
public class Animation2Activity extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        iv = (ImageView) findViewById(R.id.iv);
        final Button btn1 = (Button) findViewById(R.id.btn1);
        final Button btn2 = (Button) findViewById(R.id.btn2);
        final Button btn3 = (Button) findViewById(R.id.btn3);
        final Button btn4 = (Button) findViewById(R.id.btn4);
        final Button btn5 = (Button) findViewById(R.id.btn5);

        //获取 透明度动画
        final Animation animationAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
        //获取 旋转动画
        final Animation animationAlphaRotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        //获取 缩放动画
        final Animation animationAlphaScale = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
        //获取 平移动画
        final Animation animationAlphaTranslate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate);

        //透明度动画
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置动画到view上 启动动画
                iv.startAnimation(animationAlpha);

            }
        });


        //旋转动画
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置动画到view上 启动动画
                iv.startAnimation(animationAlphaRotate);


            }
        });

        //缩放动画
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置动画到view上 启动动画
                iv.startAnimation(animationAlphaScale);


            }
        });


        //平移动画
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置动画到view上 启动动画
                iv.startAnimation(animationAlphaTranslate);


            }
        });


        //组合动画动画
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //多个动画组合时
                AnimationSet set = new AnimationSet(true);
                //添加动画
                set.addAnimation(animationAlphaTranslate);
                set.addAnimation(animationAlphaScale);


                //哪个动画先添加就先执行 注意当一个动画还没有执行完 两个个动画开始时间到了，两个动画会同时执行
                //建议两个动画时间 第二个是第一个的两倍 ；或者第二个延迟第一个时间后执行(对应属性startOffset)

                //设置动画到view上
                iv.startAnimation(set);


            }
        });

    }

}
