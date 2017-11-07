package com.example.byz.studyandroid.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.UUID;

/**
 * @author MDP create at 2015-4-2下午1:28:35
 * @description:公用的工具类 com.qingfu.commons.CommonUtil
 */
public class CommonUtil {

    /**
     * 操作系统名称
     */
    private static String mOSVersion;

    /**
     * 设备名称
     */
    private static String mDeviceName;

    /**
     * @return String
     * @description:获取手机系统版本
     * @author yym create at 2015-1-12上午11:34:03
     */
    public static String getOSVersion() {
        if (TextUtils.isEmpty(mOSVersion)) {
            mOSVersion = Build.VERSION.RELEASE;
        }
        return mOSVersion;
    }

    /**
     * @return String
     * @description:获取设备名称
     * @author yym create at 2015-1-12上午11:37:04
     */
    public static String getDeviceName() {
        if (TextUtils.isEmpty(mDeviceName)) {
            mDeviceName = Build.MODEL;
        }
        return mDeviceName;
    }

    /**
     * 获取MAC地址
     *
     * @return
     */
    public static String getMacAddress() {
        String strMacAddr = null;
        try {
            //获得IpD地址
            InetAddress ip = getLocalInetAddress();
            byte[] b = NetworkInterface.getByInetAddress(ip).getHardwareAddress();
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < b.length; i++) {
                if (i != 0) {
                    buffer.append(':');
                }
                String str = Integer.toHexString(b[i] & 0xFF);
                buffer.append(str.length() == 1 ? 0 + str : str);
            }
            strMacAddr = buffer.toString().toUpperCase();
        } catch (Exception e) {

        }

        return strMacAddr;
    }

    private static InetAddress getLocalInetAddress() {
        InetAddress ip = null;
        try {
            //列举
            Enumeration<NetworkInterface> en_netInterface = NetworkInterface.getNetworkInterfaces();
            while (en_netInterface.hasMoreElements()) {//是否还有元素
                NetworkInterface ni = (NetworkInterface) en_netInterface.nextElement();//得到下一个元素
                Enumeration<InetAddress> en_ip = ni.getInetAddresses();//得到一个ip地址的列举
                while (en_ip.hasMoreElements()) {
                    ip = en_ip.nextElement();
                    if (!ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1)
                        break;
                    else
                        ip = null;
                }

                if (ip != null) {
                    break;
                }
            }
        } catch (SocketException e) {

            e.printStackTrace();
        }
        return ip;
    }

    /**
     * 获取device_id
     *
     * @param mContext
     * @return
     */
    public static String getDevice_id(Context mContext) {
        String android_id = Settings.Secure.getString(mContext.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        if(android_id==null){
            return "";
        }
        return android_id;
    }

    /**
     * 获取UUID
     *
     * @return
     */
    public static String getUuid(Context mContext) {

        try {
            return UUID.nameUUIDFromBytes(getDevice_id(mContext).getBytes("utf8")).toString();
        } catch (Exception e) {
            if (e != null) {
                Log.e("byz", e.getMessage().toString());
            } else {
                Log.e("byz", "--uuid--Exception---");
            }

        }

        return "";
    }

    public static String getIP(Context mContext) {
        //获取wifi服务
        WifiManager wifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        //判断wifi是否开启
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        String ip = intToIp(ipAddress);
        return ip;
    }

    private static String intToIp(int i) {

        return (i & 0xFF) + "." +
                ((i >> 8) & 0xFF) + "." +
                ((i >> 16) & 0xFF) + "." +
                (i >> 24 & 0xFF);
    }

    // 获取外网IP
    public static String GetNetIp() {
        URL infoUrl = null;
        InputStream inStream = null;
        try {
            // https://iframe.ip138.com/ic.asp
            // infoUrl = new URL(https://city.ip138.com/city0.asp);
            infoUrl = new URL("https://ip38.com");
            URLConnection connection = infoUrl.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inStream = httpConnection.getInputStream();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inStream, "utf-8"));
                StringBuilder strber = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null)
                    strber.append(line +""
                    );
                inStream.close();
                // 从反馈的结果中提取出IP地址
                // int start = strber.indexOf([);
                // Log.d(zph,  + start);
                // int end = strber.indexOf(], start + 1);
                // Log.d(zph,  + end);
//                line = strber.substring(378, 395);
//                line.replaceAll( " ","" );
                return line;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @param context
     * @return boolean true 网络可用  false 网络不可用
     * @description 判断是否有网络连接
     * <br>This method requires the call to hold the permission android.Manifest.permission.ACCESS_NETWORK_STATE.
     * @author MDP
     * create at 2015-4-7下午2:23:21
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()
                    && mNetworkInfo.isConnected()) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
}
