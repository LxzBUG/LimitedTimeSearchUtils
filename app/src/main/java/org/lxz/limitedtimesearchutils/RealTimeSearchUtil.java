package org.lxz.limitedtimesearchutils;

import android.os.Handler;
import android.os.Message;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by liuxiaozhong on 2016/9/6.
 */
public class RealTimeSearchUtil {
    private static ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(10);
    private static RealTimeSearchUtilLisetener mRealTimeSearchUtilLisetener;
    private static MyHandler handler = new MyHandler();

    /**
     * 实时搜索数据，通过RealTimeSearchUtilLisetener接口操作
     * @param newText 搜索的字符串
     * @param time  过多久搜索
     * @param realTimeSearchUtilLisetener
     */
    public static void sendRealTimeSearchMessage(String newText,int time, RealTimeSearchUtilLisetener realTimeSearchUtilLisetener){
        showSearchTip(newText,time);
        mRealTimeSearchUtilLisetener = realTimeSearchUtilLisetener;
    }

    public static void showSearchTip(String newText,int time) {
        // excute after 500ms, and when excute, judge current search tip and newText
        schedule(new SearchTipThread(newText), time);
    }

    public static ScheduledFuture<?> schedule(Runnable command, long delayTimeMills) {
        return scheduledExecutor.schedule(command, delayTimeMills, TimeUnit.MILLISECONDS);
    }

    private static class SearchTipThread implements Runnable {

        String newText;

        protected SearchTipThread(String newText) {
            this.newText = newText;
        }

        @Override
        public void run() {
            // keep only one thread to load current search tip, you can get data from network here
            boolean flag = newText != null && newText.length() > 0;
            if (flag) {
                handler.sendMessage(handler.obtainMessage(1));//不能在子线程中在开启一个子线程
            }
        }
    }

    private static class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    mRealTimeSearchUtilLisetener.handleUIMessage();
                    break;
            }
        }
    }

    public interface RealTimeSearchUtilLisetener{
        /**
         * 此方法在主线程中执行，可以发送网络请求数据
         */
        void handleUIMessage();
    }
}
