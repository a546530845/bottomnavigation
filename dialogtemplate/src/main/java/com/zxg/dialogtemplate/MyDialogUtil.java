package com.zxg.dialogtemplate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by zxg on 2016/9/27.
 */
public class MyDialogUtil {

    public static interface OnButtonEventListener {
        public void onConfirm();

        public void onCancel();
    }

    /**
     * 旋转加载dialog
     *
     * @param mContext
     * @param mMsg
     * @param cancancelable
     * @return
     * 方法中属于堆栈，在方法结束后就回收内存
     */
    public static CustomDialog showRotateDialog(Context mContext, String mMsg, boolean cancancelable) {
        CustomDialog rotateDialog = null;
        if (rotateDialog == null) {
            rotateDialog = new CustomDialog(mContext, R.layout.dialog_rotate_layout, R.style.Theme_dialog);
        }
        WindowManager.LayoutParams lp = rotateDialog.getWindow().getAttributes();
        lp.dimAmount = 0.3f;
        rotateDialog.getWindow().setAttributes(lp);
        rotateDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        TextView tv_Msg = (TextView) rotateDialog.findViewById(R.id.tv_Msg);
        tv_Msg.setText(mMsg);

        rotateDialog.setCancelable(false);//把外部的设置关闭了
        rotateDialog.setCanceledOnTouchOutside(false);
        rotateDialog.show();
        return rotateDialog;
    }

    /**
     * 两个按钮的dialog
     *
     * @param mContext
     * @param mMsg
     * @param cancancelable
     * @param btnConfirmText
     * @param btnCancelText
     * @param buttonEventListener
     * @return
     */
    public static CustomDialog showTwoButtonDialog(Context mContext, String mMsg, boolean cancancelable, String btnConfirmText, String btnCancelText, final OnButtonEventListener buttonEventListener) {
        final CustomDialog twoButtonDialog = new CustomDialog(mContext, R.layout.dialog_two_bt_layout, R.style.Theme_dialog);
        WindowManager.LayoutParams lp = twoButtonDialog.getWindow().getAttributes();
        lp.dimAmount = 0.3f;
        twoButtonDialog.getWindow().setAttributes(lp);
        twoButtonDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        TextView msgText = (TextView) twoButtonDialog.findViewById(R.id.tv_Msg);
        msgText.setText(mMsg);

        Button btCommit = (Button) twoButtonDialog.findViewById(R.id.btn_Confirm);
        btCommit.setText(btnConfirmText);

        btCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buttonEventListener != null) {
                    buttonEventListener.onConfirm();
                }
                twoButtonDialog.dismiss();
            }
        });

        Button btCancel = (Button) twoButtonDialog.findViewById(R.id.btn_Cancel);
        btCancel.setText(btnCancelText);
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buttonEventListener != null) {
                    buttonEventListener.onCancel();
                }
                twoButtonDialog.dismiss();
            }
        });

        twoButtonDialog.setCancelable(cancancelable);
        twoButtonDialog.setCanceledOnTouchOutside(false);
        twoButtonDialog.show();
        return twoButtonDialog;
    }

    /**
     * 一个按钮的dialog
     *
     * @param mContext
     * @param mMsg
     * @param cancancelable
     * @param btnConfirmText
     * @param buttonEventListener
     * @return
     */
    public static CustomDialog showOneButtonDialog(Context mContext, String mMsg, boolean cancancelable, String btnConfirmText, final OnButtonEventListener buttonEventListener, int layout) {
        final CustomDialog oneButtonDialog = new CustomDialog(mContext, R.layout.dialog_confirm_layout, R.style.Theme_dialog);
        WindowManager.LayoutParams lp = oneButtonDialog.getWindow().getAttributes();
        lp.dimAmount = 0.3f;
        oneButtonDialog.getWindow().setAttributes(lp);
        oneButtonDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);


        TextView msgText = (TextView) oneButtonDialog.findViewById(R.id.tv_Msg);
//        msgText.setText(mMsg);
        msgText.setText(Html.fromHtml(mMsg));
//        msgText.setGravity(Gravity.CENTER);
        msgText.setGravity(layout);

        Button btCommit = (Button) oneButtonDialog.findViewById(R.id.btn_Confirm);
        btCommit.setText(btnConfirmText);
        btCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buttonEventListener != null) {
                    buttonEventListener.onConfirm();
                }
                oneButtonDialog.dismiss();
            }
        });

        oneButtonDialog.setCancelable(cancancelable);
        oneButtonDialog.setCanceledOnTouchOutside(true);
        oneButtonDialog.show();
        return oneButtonDialog;
    }

    /**
     * 自动消失的dialog
     *
     * @param mContext
     * @param mMsg
     * @param btnText
     * @param cancancelable
     * @param buttonEventListener
     * @param delayMillis
     * @param intent
     * @param isfinish
     */
    public final static void showDialogAutoDissmiss(final Context mContext, String mMsg, String btnText, boolean cancancelable, final OnButtonEventListener buttonEventListener,
                                                    long delayMillis, final Intent intent, final Boolean isfinish) {
        Handler handler = new Handler();


        final CustomDialog autoDismissButtonDialog = new CustomDialog(mContext, R.layout.dialog_auto_layout, R.style.Theme_dialog);
        WindowManager.LayoutParams lp = autoDismissButtonDialog.getWindow().getAttributes();
        lp.dimAmount = 0.3f;
        autoDismissButtonDialog.getWindow().setAttributes(lp);
        autoDismissButtonDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        TextView msgText = (TextView) autoDismissButtonDialog.findViewById(R.id.tv_Msg);
        msgText.setText(mMsg);
        msgText.setGravity(Gravity.CENTER);

        Button btCommit = (Button) autoDismissButtonDialog.findViewById(R.id.btn_Confirm);
        btCommit.setText(btnText);
        btCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buttonEventListener != null) {
                    buttonEventListener.onConfirm();
                }
                autoDismissButtonDialog.dismiss();
            }
        });
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                autoDismissButtonDialog.dismiss();
                // context.startActivity(intent);
                if (isfinish) {
                    ((Activity) mContext).finish();
                }

            }
        }, delayMillis);
        autoDismissButtonDialog.setCancelable(cancancelable);
        autoDismissButtonDialog.setCanceledOnTouchOutside(false);
        autoDismissButtonDialog.show();

    }


}
