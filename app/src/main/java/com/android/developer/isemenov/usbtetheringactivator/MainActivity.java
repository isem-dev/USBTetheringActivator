package com.android.developer.isemenov.usbtetheringactivator;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTetheringEnabled();
    }

    private void setTetheringEnabled() {
        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        Log.d(TAG,"test enable usb tethering");
        String[] available = null;
        int code=-1;
        Method[] wmMethods = cm.getClass().getDeclaredMethods();

        for(Method method: wmMethods){
            if(method.getName().equals("getTetherableIfaces")){
                try {
                    available = (String[]) method.invoke(cm);
                    break;
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return;
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return;
                } catch (InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return;
                }
            }
        }

        for(Method method: wmMethods){
            if(method.getName().equals("tether")){
                try {
                    code = (Integer) method.invoke(cm, available[0]);


                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return;
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return;
                } catch (InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return;
                }
                break;
            }
        }

        if (code==0)
            Log.d(TAG,"Enable usb tethering successfully!");
        else
            Log.d(TAG,"Enable usb tethering failed!");
    }
}
