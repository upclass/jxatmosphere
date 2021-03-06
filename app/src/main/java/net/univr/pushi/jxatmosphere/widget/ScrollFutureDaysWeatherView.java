package net.univr.pushi.jxatmosphere.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.univr.pushi.jxatmosphere.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tb on 2017/5/13.
 * 未来若干天天气滑动控件
 */

public class ScrollFutureDaysWeatherView extends ViewGroup {
    private static final String TAG = "ScrollFutureDaysWeatherView";
    /**
     * 未来若干天天气View的集合（每个item都是一样的）
     */
    private List<View> contents=new ArrayList<>();
    /**
     * 未来若干天天气温度的图表
     */
    private FutureDaysChart sevenDaysChart;
    /**
     * 未来具体的天数（包含昨天一个）
     */
    public  int days;
    /**
     * 每个item的宽度
     */
    private int futureDayItemWidth;
    /**
     * 温度图表的高度
     */
    private int futureDayChartHeight;
    /**
     * 未来若干天天气控件总宽度（viewgroup的宽度）
     */
    private int futureDayTotalWidth;
    /**
     * 具体的每个item的宽度（单位：dp）
     */
    public static final int ITEM_WIDTH=80;

    public ScrollFutureDaysWeatherView(Context context) {
        this(context,null);
    }



    public ScrollFutureDaysWeatherView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public ScrollFutureDaysWeatherView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public ScrollFutureDaysWeatherView(Context context, AttributeSet attrs, int defStyleAttr,int size) {
        super(context, attrs, defStyleAttr);
        days=size;
        init(context);
    }




    private void init(Context context){
        futureDayItemWidth= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,ITEM_WIDTH,context.getResources().getDisplayMetrics());
        futureDayChartHeight=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,FutureDaysChart.CHART_HEIGHT,context.getResources().getDisplayMetrics());
        futureDayTotalWidth=futureDayItemWidth*days;
        for (int i = 0; i < days; i++) {
            View view= LayoutInflater.from(context).inflate(R.layout.item_future_days_weather, null, false);
            view.findViewById(R.id.view).getLayoutParams().height=futureDayChartHeight;
            contents.add(view);
            addView(view,new LayoutParams(futureDayItemWidth, LayoutParams.WRAP_CONTENT));

        }
        sevenDaysChart=new FutureDaysChart(context);
        addView(sevenDaysChart,new LayoutParams(futureDayTotalWidth, LayoutParams.WRAP_CONTENT));
    }

    public List<View> getAllViews(){
        return contents;
    }

    public FutureDaysChart getSevenDaysChart(){
        return sevenDaysChart;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int totalHeight=0;
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            if(childView.getVisibility()!= View.GONE){
                measureChild(childView, widthMeasureSpec, heightMeasureSpec);
                totalHeight+=childView.getMeasuredHeight();
            }
        }
        //为ViewGroup设置宽高
        setMeasuredDimension(futureDayTotalWidth,totalHeight);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        int left=0;
        for (int j = 0; j < getChildCount()-1; j++) {
            View child=getChildAt(j);
            if(child.getVisibility()!= View.GONE){
                child.layout(left,0,left+child.getMeasuredWidth(),child.getMeasuredHeight());
                left+=futureDayItemWidth;
            }
        }
        View emptyView=contents.get(0).findViewById(R.id.view);
        int top=emptyView.getTop();
        View last=getChildAt(getChildCount()-1);
        last.layout(0,top,getMeasuredWidth(),top+futureDayChartHeight);
    }

}
