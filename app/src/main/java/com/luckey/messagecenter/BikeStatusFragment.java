package com.luckey.messagecenter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cq.esmoking.request.PhoneTypes;
import com.cq.esmoking.request.RegisterRequest;
import com.cq.esmoking.response.RegisterResponse;
import com.google.protobuf.InvalidProtocolBufferException;
import com.luckey.base.BaseOFragment;
import com.luckey.socket.App;
import com.luckey.socket.SocketAppPacket;
import com.luckey.socket.SocketClient;
import com.luckey.util.VerifyUtils;

import java.util.Timer;
import java.util.TimerTask;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by lenovo on 2016/12/22.
 */
public class BikeStatusFragment extends BaseOFragment implements View.OnClickListener {
    private EditText register_username_phone, input_register_password, et_verify_code; //input_password_again
    //    private Button submit_register_Btn; // tv_send_register_code
    private TextView tv_send_register_code,submit_register_tv;
    private EventHandler eh;
    private Timer mTimer;
    private static final int MSG_cannt_get_data_register = 20091;

    private int timerInterval = 0;
    private static final int MSG_handler_timer = 10081;
    private static final int MSG_verify_failed = 10082;
    private static final int MSG_get_verify_code_failed = 10083;
    private static final int MSG_VERIFY_SUCCESS = 10085;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                switch (msg.what) {
                    case MSG_handler_timer:
                        tv_send_register_code.setText(String.valueOf(timerInterval) + "秒");
                        if (timerInterval == 0) {
                            tv_send_register_code.setClickable(true);
                            tv_send_register_code.setText("发送验证码");
                            mTimer.cancel();
                        }

                        break;
                    case MSG_VERIFY_SUCCESS:
                        Toast.makeText(getContext(),"验证成功",Toast.LENGTH_SHORT);
                        break;
                    case MSG_cannt_get_data_register:
                        Toast.makeText(getContext(),"注册失败",Toast.LENGTH_SHORT);

                        break;
                    case MSG_get_verify_code_failed:
                        mTimer.cancel();
                        tv_send_register_code.setText("发送验证码");
                        tv_send_register_code.setClickable(true);
                        Toast.makeText(getContext(),"验证码发送失败",Toast.LENGTH_SHORT);
                        break;
                    case MSG_verify_failed:

                        mHandler.removeMessages(MSG_cannt_get_data_register);
                        mTimer.cancel();
                        tv_send_register_code.setText("发送验证码");
                        tv_send_register_code.setClickable(true);
                        Toast.makeText(getContext(),"验证失败",Toast.LENGTH_SHORT);
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public int getContentId() {
        return R.layout.fragment_message_bikestatus;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        register_username_phone = findViewByIds(R.id.register_username_phone);
        input_register_password = findViewByIds(R.id.input_register_password);
        et_verify_code = findViewByIds(R.id.input_verify_code);
//        input_password_again = findViewByIds(R.id.input_password_again);
        submit_register_tv = findViewByIds(R.id.submit_register_tv);
        tv_send_register_code = findViewByIds(R.id.tv_send_register_code);
        tv_send_register_code.setOnClickListener(this); //发送验证码
        submit_register_tv.setOnClickListener(this);//注册
        initSMSSDK();

    }

    private void initSMSSDK() {
        SMSSDK.initSDK(getContext(), App.SMS_APPKEY, App.SMS_APPSECRET, false);
        eh = new EventHandler() {

            @Override
            public void afterEvent(int event, int result, Object data) {

                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
//                        doESocket();
//                        dlgWaiting.show();
//                        mDlgWaitingHandler.sendEmptyMessageDelayed(MSG_CANNOT_GET_REQUEST_DATA, App.WAITTING_SECOND);
                        Looper.prepare();
                        Toast.makeText(getContext(),"验证成功",Toast.LENGTH_SHORT);

                        new Thread() {
                            public void run() {
                                SocketClient client;
                                client = SocketClient.getInstance();
                                RegisterRequest.RegisterRequestMessage.Builder requestMessage = RegisterRequest.RegisterRequestMessage.newBuilder();
                                requestMessage.setPhone(register_username_phone.getText().toString());
                                requestMessage.setPassword(input_register_password.getText().toString());
                                requestMessage.setPhoneType(PhoneTypes.PhoneType.AndroidPhone);
                                client.send(SocketAppPacket.COMMAND_ID_USER_REGISTER, requestMessage.build().toByteArray());
                            }
                        }.start();
                        Looper.loop();

                        //提交验证码成功
//                        mHandler.sendEmptyMessage(MSG_VERIFY_SUCCESS);

                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //获取验证码成功
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        //返回支持发送验证码的国家列表
                    }
                } else {
                    ((Throwable) data).printStackTrace();
                    if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        mHandler.sendEmptyMessage(MSG_get_verify_code_failed);

                    } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        mHandler.sendEmptyMessage(MSG_verify_failed);

                    }

                }
            }
        };
        SMSSDK.registerEventHandler(eh); //注册短信回调
    }



    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
        }
//        mHandler.removeMessages(MSG_cannt_get_data_login);
        mHandler.removeMessages(MSG_cannt_get_data_register);
        SMSSDK.unregisterEventHandler(eh); //注册短信回调
    }

    @Override
    public void onClick(View v) {
//        strPhoneName = register_username_phone.getText().toString().trim();
//        strPassWord = input_register_password.getText().toString().trim();
//        strVerifyCode = et_verify_code.getText().toString().trim();
//        strPassWordAgain = input_password_again.getText().toString().trim();
        switch (v.getId()) {
            case R.id.tv_send_register_code:
                //发送验证码
                if (TextUtils.isEmpty(register_username_phone.getText().toString())) {
                    Toast.makeText(getContext(),"电话号码为空",Toast.LENGTH_SHORT);
                    return;
                }
                if (VerifyUtils.verifyPhone(register_username_phone.getText().toString())) {
                    Toast.makeText(getContext(),"必须为电话号码",Toast.LENGTH_SHORT);
                    return;
                }

                timerInterval = 90;
                tv_send_register_code.setClickable(false);
                mTimer = new Timer();
                mTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub

                        timerInterval--;
                        mHandler.sendEmptyMessage(MSG_handler_timer);

                    }
                }, 1000, 1000);

                SMSSDK.getVerificationCode("86", register_username_phone.getText().toString());
                break;

            case R.id.submit_register_tv:
                //点击注册

                boolean cancel = false;
                View focusView = null;
                if (TextUtils.isEmpty(register_username_phone.getText().toString())) {
//                if (register_username_phone.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getContext(),"电话号码为空",Toast.LENGTH_SHORT);
                    focusView = register_username_phone;
                    cancel = true;
                    return;
                }
                if (VerifyUtils.verifyPhone(register_username_phone.getText().toString())) {
                    Toast.makeText(getContext(),"必须为电话号码",Toast.LENGTH_SHORT);
                    focusView = register_username_phone;
                    cancel = true;
                    return;
                }

                //判断密码是否为空
                if (TextUtils.isEmpty(input_register_password.getText().toString())) {
//                if (register_username_phone.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getContext(),"密码为空",Toast.LENGTH_SHORT);
                    focusView = input_register_password;
                    cancel = true;
                    return;
                }

                //判断输入的密码是否为6-12位
                if (input_register_password.getText().toString().length() < 6) {
                    Toast.makeText(getContext(),"密码位数不能少于6位",Toast.LENGTH_SHORT);
                    focusView = input_register_password;
                    cancel = true;
                    return;
                }


                if (cancel) {
                    focusView.requestFocus();
                } else {

                    //判断新密码与确认密码是否一致
//                    if (!strPassWord.equals(strPassWordAgain)) {
//                        Toast.makeText(context, "输入密码与确认密码不一致", Toast.LENGTH_SHORT).show();
//
//                    }

//                    dlgWaiting.show();
//                    mHandler.sendEmptyMessageDelayed(MSG_cannt_get_data_register, App.WAITTING_SECOND);
                    mTimer.cancel();
                    tv_send_register_code.setText("发送验证码");
                    tv_send_register_code.setClickable(true);
                    SMSSDK.submitVerificationCode("86", register_username_phone.getText().toString(),et_verify_code.getText().toString());
                }

//                if (input_register_password.getText().toString().trim().isEmpty()) {
//                    ToastUtils.showTextToast(this, getResources().getString(R.string.password_is_empty));
//                    return;
//                }
//
//                dlgWaiting.show();
//                mHandler.sendEmptyMessageDelayed(MSG_cannt_get_data_register, App.WAITTING_SECOND);
//                mTimer.cancel();
//                tv_send_register_code.setText(getResources().getString(R.string.send_code));
//                tv_send_register_code.setClickable(true);
//                SMSSDK.submitVerificationCode("86", register_username_phone.getText().toString(),etRegisterVerifyCode.getText().toString() );

                break;

        }
    }

    @Override
    public void onEventMainThread(SocketAppPacket eventPackage) {
        try {
            if (eventPackage.getCommandId() == SocketAppPacket.COMMAND_ID_USER_REGISTER) {
                RegisterResponse.RegisterResponseMessage responseMessage = RegisterResponse.RegisterResponseMessage.parseFrom(eventPackage.getCommandData());
//                if (dlgWaiting.isShowing()) {
//                    dlgWaiting.dismiss();
//                }
//                mDlgWaitingHandler.removeMessages(MSG_CANNOT_GET_REQUEST_DATA);

                if (responseMessage.getErrorMsg().getErrorCode() == 0) {
                    Toast.makeText(getContext(),"注册成功666",Toast.LENGTH_SHORT);

//                    PreferenceData.saveLoginAccount(context, responseMessage.getUserId());
//                    SysUser user = new SysUser();
//                    user.setUserName(register_username_phone.getText().toString());
//                    user.setPhone(input_register_password.getText().toString());
//                    user.setUserId(responseMessage.getUserId());
//                    user.setPlanSmokingMouth(2000);
//                    user.setPlanSmokingTime(120);
//                    FinalDb mDb = FinalDb.create(context, App.DB_NAME, true, App.DB_VERSION, this);
//                    mDb.save(user);
//                    PreferenceData.saveSession(context, responseMessage.getSession());
//                    Intent intent = new Intent();
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent.setClass(this, MainNewActivity.class);
//                    startActivity(intent);
//                    this.finish();
                } else {
                    Toast.makeText(getContext(),responseMessage.getErrorMsg().getErrorMsg(),Toast.LENGTH_SHORT);
                }
            }
        }catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }


}
