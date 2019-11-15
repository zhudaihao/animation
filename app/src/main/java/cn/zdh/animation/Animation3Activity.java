package cn.zdh.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;

/**
 * 属性动画
 * 优点 ：
 * 1 不在局限于view 对象 ，无对象也看进行动画处理
 * 2 不在局限于4种基本变换 平移 旋转 缩放 透明度
 * 3可以灵活的操作任意对象属性，根据 自己业务来实现自己想要的结果
 * <p>
 * 核心
 * 1.ObjectAnimation 对象动画
 * 2.ValueAnimation 值动画
 * 3.PropertyValueHolder 多用于多个动画执行
 * 4.TypeEvaluator 估值器
 * 5.Interpolator 插值器
 * 6.AnimationSet 动画集合
 * <p>
 * <p>
 * <p>
 * 原理改变view属性值 -->注册同步信号--》系统每16ms发送一个同步信号--》接受到同步信号 移除上个属性值 注册新的属性值 垂直同步信号
 */
public class Animation3Activity extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        iv = (ImageView) findViewById(R.id.iv);
        final Button btn1 = (Button) findViewById(R.id.btn1);
        final Button btn2 = (Button) findViewById(R.id.btn2);
        final Button btn3 = (Button) findViewById(R.id.btn3);
        final Button btn4 = (Button) findViewById(R.id.btn4);
        final Button btn5 = (Button) findViewById(R.id.btn5);
        final Button btn6 = (Button) findViewById(R.id.btn6);
        final Button btn7 = (Button) findViewById(R.id.btn7);
        //使用objectAnimation 实现动画
        objectAnimation(btn1, btn2, btn3, btn4, btn5, btn7);

        //使用监听手动 实现动画
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(iv, "test", 0f, 1f, 0.5f, 1f);
                //监听手动设置动画
                objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        //手动设属  动画性值
                        float value = (float) animation.getAnimatedValue();
                        iv.setAlpha(value);
                    }
                });
                //设置时间
                objectAnimator.setDuration(500);
                //开始动画
                objectAnimator.start();


            }
        });


    }

    private void objectAnimation(Button btn1, Button btn2, Button btn3, Button btn4, Button btn5, Button btn7) {
        /**
         *
         * ObjectAnimation是ValueAnimation的子类
         * 参数 （需要执行动画的view对象，动画名称，动画值）
         * 、、
         * 注意动画名称： 透明度动画（alpha） 旋转动画（rotation） 缩放（scale）平移（translate）
         * 注意 如果忘记名称可以通过view.setxx获取对应名称  ,如果输入错误会报红
         * 、、
         * 动画值 ：第一个是开始值  最后一个是结束值  中间的是变化到结束值的过渡值
         * 注意 单位float
         *
         */

        //透明度动画
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //1创建属性对象
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(iv, "alpha", 1f, 0.2f, 1f);
                //2设置时间
                objectAnimator.setDuration(500);
                //设置动画次数 ObjectAnimator.INFINITE表示无限循环
                objectAnimator.setRepeatCount(ObjectAnimator.INFINITE);
                //设置延迟开始动画
                objectAnimator.setStartDelay(100);
                //设置重复播放动画的模式:正序 ValueAnimator.RESTART（默认）；倒叙 ValueAnimator.REVERSE
                objectAnimator.setRepeatMode(ValueAnimator.RESTART);

                //3启动动画
                objectAnimator.start();


            }
        });


        //旋转动画
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator.ofFloat(iv, "rotationX", 0f, 90f, 0f).setDuration(500).start();
            }
        });

        //缩放动画
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator.ofFloat(iv, "scaleX", 1f, 0.5f, 1f).setDuration(500).start();
                ObjectAnimator.ofFloat(iv, "scaleY", 1f, 0.5f, 1f).setDuration(500).start();
            }
        });


        //平移动画
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置动画到view上 启动动画
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(iv, "translationX", 0f, 100f, 500f).setDuration(500);

                //设置插值器
                setInterpolator(objectAnimator);
                objectAnimator.start();


            }
        });


        //组合动画
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //方法一 几个动画同时执行
//                ObjectAnimator.ofFloat(iv, "translationY", -100f, 100f, 0f).setDuration(500).start();
//                ObjectAnimator.ofFloat(iv, "scaleX", 1f, 0.5f, 1f).setDuration(500).start();
//                ObjectAnimator.ofFloat(iv, "scaleY", 1f, 0.5f, 1f).setDuration(500).start();

                //方法二 动画根据添加先后顺序一个个执行
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(iv, "translationY", -100f, 100f, 0f).setDuration(500);
                ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(iv, "scaleX", 1f, 0.5f, 1f).setDuration(500);
                ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(iv, "scaleY", 1f, 0.5f, 1f).setDuration(500);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playSequentially(objectAnimator, objectAnimator1, objectAnimator2);
                animatorSet.start();
            }
        });

        //估值器动画
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValueAnimator valueAnimator = new ValueAnimator();
                valueAnimator.setDuration(3000);


                //设置插值器
                setInterpolator(valueAnimator);

                //设置估值器
                setEvaluator(valueAnimator);

                //启动动画
                valueAnimator.start();


            }
        });
    }

    /**
     * 设置插值器
     */

    private void setInterpolator(ValueAnimator valueAnimator) {
//        //加速插值器 参数越大 起始速度越慢 但是速度越快
//        valueAnimator.setInterpolator(new AccelerateInterpolator(1f));
//        //减速插值器 参数越大 起始速度越快 但是速度越慢
//        valueAnimator.setInterpolator(new DecelerateInterpolator(0.1f));
//        //先加速后减速
//        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

//        //张力值   参数值越大  初始的偏移越大 而且速度越快
//        valueAnimator.setInterpolator(new AccelerateInterpolator(1f));

//        //张力值
//        valueAnimator.setInterpolator(new OvershootInterpolator(0.5f));
//
//        //张力值   参数值越大  初始和结束时的 偏移越大
//        valueAnimator.setInterpolator(new AnticipateOvershootInterpolator(0.5f));

//        //弹跳插值器
        valueAnimator.setInterpolator(new BounceInterpolator());

//        //周期插值器 2表示动画执行2次
//        valueAnimator.setInterpolator(new CycleInterpolator(2));

//        //线性插值器
//        valueAnimator.setInterpolator(new OvershootInterpolator(0.3f));


    }

    /**
     * 设置估值器
     */
    private void setEvaluator(ValueAnimator valueAnimator) {
        //设置估值对象
        valueAnimator.setObjectValues(new PointF(0, 0));
        final PointF point = new PointF();

        //计算估值指
        valueAnimator.setEvaluator(new TypeEvaluator() {
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {
                point.x = 100f * (fraction * 5);
                //使用重力公式计算Y =vt=1/2*g*t*t
                point.y = 1f * 98f * (fraction * 5) * (fraction * 5);

                return point;
            }
        });

        //监听动画顺序值变化
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();

                iv.setX(pointF.x);
                iv.setY(pointF.y);
            }
        });
    }

}