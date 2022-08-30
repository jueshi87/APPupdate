package com.king.app.dialog.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.king.app.dialog.BaseDialogConfig;
import com.king.app.dialog.R;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class AppDialogFragment extends BaseDialogFragment {

    private BaseDialogConfig config;

    public static AppDialogFragment newInstance(BaseDialogConfig config) {

        Bundle args = new Bundle();
        AppDialogFragment fragment = new AppDialogFragment();
        fragment.config = config;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootLayoutId() {
        if(config != null){
            return config.getLayoutId();
        }
        return R.layout.app_dialog;
    }

    public void init(View rootView){
        if(config != null){
            TextView tvDialogTitle = rootView.findViewById(config.getTitleId());
            if(tvDialogTitle != null){
                setText(tvDialogTitle,config.getTitle());
                tvDialogTitle.setVisibility(config.isHideTitle() ? View.GONE : View.VISIBLE);
            }

            TextView tvDialogContent = rootView.findViewById(config.getContentId());
            if(tvDialogContent != null){
                setText(tvDialogContent,config.getContent());
            }

            Button btnDialogCancel = rootView.findViewById(config.getCancelId());
            if(btnDialogCancel != null){
                setText(btnDialogCancel,config.getCancel());
                btnDialogCancel.setOnClickListener(config.getOnClickCancel() != null ? config.getOnClickCancel() : getOnClickDismiss());
                btnDialogCancel.setVisibility(config.isHideCancel() ? View.GONE : View.VISIBLE);
            }

            View line = rootView.findViewById(config.getLineId());
            if(line != null){
                line.setVisibility(config.isHideCancel() ? View.GONE : View.VISIBLE);
            }

            Button btnDialogConfirm = rootView.findViewById(config.getConfirmId());
            if(btnDialogConfirm != null){
                setText(btnDialogConfirm,config.getConfirm());
                btnDialogConfirm.setOnClickListener(config.getOnClickConfirm() != null ? config.getOnClickConfirm() : getOnClickDismiss());
            }

        }
    }


    @Override
    protected void initDialogWindow(Context context, Dialog dialog, int gravity, float widthRatio, int animationStyleId) {
        if(config != null){
            super.initDialogWindow(context,dialog,config.getGravity(), config.getWidthRatio(), config.getAnimationStyleId());
        }else{
            super.initDialogWindow(context, dialog, gravity, widthRatio, animationStyleId);
        }
    }
}