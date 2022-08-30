package com.king.app.dialog.fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.king.app.dialog.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public abstract class BaseDialogFragment extends DialogFragment {

    protected static final float DEFAULT_WIDTH_RATIO = 0.85f;

    private View mRootView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getRootLayoutId(), container, false);
        init(mRootView);
        return mRootView;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable  Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setCanceledOnTouchOutside(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        initDialogWindow(getContext(),dialog,Gravity.NO_GRAVITY,DEFAULT_WIDTH_RATIO,R.style.app_dialog_scale_animation);
        return dialog;
    }

    protected void initDialogWindow(Context context,Dialog dialog,int gravity,float widthRatio,int animationStyleId){
        setDialogWindow(context, dialog, gravity, widthRatio, animationStyleId);
    }

    /**
     * 设置弹框窗口配置
     * @param context
     * @param dialog
     * @param gravity
     * @param widthRatio
     */
    private void setDialogWindow(Context context,Dialog dialog,int gravity,float widthRatio,int animationStyleId){
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.windowAnimations = animationStyleId;
        lp.width = (int)(context.getResources().getDisplayMetrics().widthPixels * widthRatio);
        lp.gravity = gravity;
        window.setAttributes(lp);
    }

    protected View getRootView(){
        return mRootView;
    }

    protected void setText(TextView tv, CharSequence text){
        if(text != null){
            tv.setText(text);
        }
    }

    protected View.OnClickListener getOnClickDismiss(){
        return mOnClickDismissDialog;
    }

    private View.OnClickListener mOnClickDismissDialog = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    };

    public abstract int getRootLayoutId();

    public abstract void init(View rootView);

}