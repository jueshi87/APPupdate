package com.king.app.updater.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.king.app.updater.util.AppUtils;

/**
 * @author: bytecodezz
 * @version: 1.0
 * @time: 2021/9/16 10:07
 * @package: com.king.app.updater.receiver
 * @description: 统一对下载的apk文件进行安装处理，必要参数：uri
 */
public class InstallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Uri uri = intent.getData();
        if (uri != null) {
            //执行安装操作
            context.startActivity(AppUtils.getInstallIntent(uri));
        }
    }
}
