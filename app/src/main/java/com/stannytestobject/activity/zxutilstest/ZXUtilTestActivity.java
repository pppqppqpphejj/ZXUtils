package com.stannytestobject.activity.zxutilstest;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zx.zxutils.views.SwipeBack.ZXSwipeBackHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * zxutil测试类
 */

public class ZXUtilTestActivity extends ListActivity {

    List<Class<?>> classList = new ArrayList<>();
    List<String> titleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ZXSwipeBackHelper.onCreate(this)
                .setSwipeBackEnable(true)
                .setSwipeRelateEnable(true);
        addActivity(DialogTestActivity.class, "ZXDialogUtil-弹框");
        addActivity(ToastTestActivity.class, "ZXToastUtil-吐司");
        addActivity(NotifyTestActivity.class, "ZXNotifyUtil-提示");
        addActivity(HttpTestActivity.class, "ZXHttpApi-普通请求");
        addActivity(DownTestActivity.class, "ZXHttpApi-下载");
        addActivity(UploadTestActivity.class, "ZXHttpApi-上传");
        addActivity(BroadCastTestActivity.class, "ZXBroadCastUtil-广播");
        addActivity(UnZipOrRarTestActivity.class, "ZXUnZipOrRarUtil-解压");
        addActivity(PhotoPickerTestActivity.class, "ZXPhotoPickerView-图片选择器");
        addActivity(SpinnerTestActivity.class, "ZXSpinner-下拉框");
        addActivity(RecordTestActivity.class, "ZXRecordUtil-录音");
        addActivity(SeekBarTestActivity.class, "ZXSeekBar-刻度条");
        addActivity(BitmapTestActivity.class, "ZXBitmapUtil-bitmap");
        addActivity(AnimationTestActivity.class, "ZXAnimUtil-动画");
        addActivity(LogTestActivity.class, "ZXLogUtil-log");
        addActivity(BubbleTestActivity.class, "ZXBubbleView-气泡");
        addActivity(ImageLoaderTestActivity.class, "ZXImageLoaderUtil-图片加载器");
        addActivity(ThreadPoolTestActivity.class, "ZXThreadPool-线程池");
        addActivity(RecylerDeleteActivity.class, "ZXTabViewPager-TabView");
        addActivity(SlidingActivity.class, "ZXSlidingNav-侧边栏");
        addActivity(FloatScrollActivity.class, "ZXFloatScrollView-顶部悬浮");
        addActivity(TableActivity.class, "ZXTableView-表格");
        addActivity(SwipeRefreshRecylerActivity.class, "ZXSwipeRecyler-刷新列表");
        addActivity(ChartActivity.class, "ZXChart-统计");
        addActivity(TabLayoutActivity.class, "ZXTabViewPager-viewpager");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titleList);
        setListAdapter(adapter);
    }

    public void addActivity(Class<?> classFile, String className) {
        classList.add(classFile);
        titleList.add(className);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        startActivity(new Intent(this, classList.get(position)));
    }
}
