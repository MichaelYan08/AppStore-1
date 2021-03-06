package com.seuic.app.store.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.seuic.app.store.AppStoreApplication;
import com.seuic.app.store.R;
import com.seuic.app.store.bean.AppInfo;
import com.seuic.app.store.bean.RecycleObject;
import com.seuic.app.store.view.MultifunctionalTextView;

import java.util.List;

/**
 * Created on 2017/9/20.
 *
 * @author dpuntu
 */

public class InstallAppAdapter extends BaseRecycleViewAdapter<InstallAppAdapter.InstallDataViewHolder, AppInfo> {

    public InstallAppAdapter(List<RecycleObject> mRecycleObjectList) {
        super(mRecycleObjectList, InstallAppAdapter.InstallDataViewHolder.class, R.layout.activity_install_item);
    }

    @Override
    protected void loadRecycleData(InstallDataViewHolder holder, final AppInfo info) {
        holder.titleText.setText(info.getAppName());
        holder.summaryText.setText(AppStoreApplication.getApp().getString(R.string.app_version, info.getAppVersion()));
        holder.appImage.setImageDrawable(info.getAppIcon());
        holder.unInstall.setTextState(MultifunctionalTextView.TextViewState.UNINSTALL);

//        Logger.d("-----------------------------------start--------------------------------------");
//        Logger.e("流量下载->" + TimesBytesUtils.getDownloadDataUsage4G());
//        Logger.e("流量上传->" + TimesBytesUtils.getUploadDataUsage4G());
//        Logger.e("WIFI下载->" + TimesBytesUtils.getDownloadDataUsageWifi());
//        Logger.e("WIFI上传->" + TimesBytesUtils.getUploadDataUsageWifi());
//        Logger.e("总流量下载->" + TimesBytesUtils.getDownloadDataUsage());
//        Logger.e("总流量上传->" + TimesBytesUtils.getUploadDataUsage());
//        String[] bytes = TimesBytesUtils.getAppDataUsage(info.getPackageName());
//        if (bytes != null) {
//            Logger.i(info.getAppName() + " 下载->" + bytes[0]);
//            Logger.i(info.getAppName() + " 上传->" + bytes[1]);
//        }
//        String msg = TimesBytesUtils.appRunTime(info.getPackageName());
//        if (msg != null) {
//            Logger.i(msg);
//        } else {
//            Logger.i("没有运行");
//        }
//        Logger.d("----------------------------------end---------------------------------------");

        holder.unInstall.setTextOnClickListener(new MultifunctionalTextView.TextOnClickListener() {
            @Override
            public void onTextClick(View view, int typeState) {
                if (typeState == MultifunctionalTextView.TextViewState.UNINSTALL) {
                    mTextOnClickListener.unInstall(info);
                }
            }
        });
    }

    public static class InstallDataViewHolder extends BaseRecycleViewAdapter.DataViewHolder {
        TextView titleText, summaryText;
        MultifunctionalTextView unInstall;
        ImageView appImage;

        public InstallDataViewHolder(View itemView) {
            super(itemView);
            titleText = (TextView) itemView.findViewById(R.id.install_app_item_title);
            summaryText = (TextView) itemView.findViewById(R.id.install_app_item_summary);
            appImage = (ImageView) itemView.findViewById(R.id.install_app_item_image);
            unInstall = (MultifunctionalTextView) itemView.findViewById(R.id.install_app_item_uninstall);
        }
    }

    private TextOnClickListener mTextOnClickListener;

    public void setTextOnClickListener(TextOnClickListener mTextOnClickListener) {
        this.mTextOnClickListener = mTextOnClickListener;
    }

    public interface TextOnClickListener {
        void unInstall(AppInfo info);
    }
}
