package com.lmc.bab.libdemo.utils;

import android.os.Environment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by limin on 2017/10/12.
 */

public class FileHelper {
    public static String ReadHardwareConfig(){
        try {
            String sDir = Environment.getExternalStorageDirectory().getPath() + File.separator + "temp/test.txt";
            File fileName = new File(sDir);
            if (fileName.exists()) {
                FileInputStream input = new FileInputStream(sDir);
                Scanner scan = new Scanner(input);
                String str="";
                while (scan.hasNext()) {
                    str = new StringBuilder(String.valueOf(str)).append(scan.next()).append
                            ("\n").toString();
                }
                input.close();
                scan.close();
                return str;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //写入硬件参数配置
    public static void WriteHardwareConfig(String content){
        String sDir = Environment.getExternalStorageDirectory().getPath() + File.separator + "YFMedicineVendor/";
        File fDir = new File(sDir);
        String sFile = Environment.getExternalStorageDirectory().getPath() + File.separator + "YFMedicineVendor/HardwareConfig.txt";
        if (content == null || content.length() <= 0) {
            return;
        }

        FileWriter fileWriter = null;
        try {
            if(!fDir.exists() && fDir.isDirectory()){
                fDir.mkdirs();
            }
            fileWriter = new FileWriter(sFile, false);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
