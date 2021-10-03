package com.guyaogg;

import static com.guyaogg.HuffmanZip.unZipFile;

/**
 * @author 顾遥
 */
public class HuffmanUnZip {
    public static void main(String[] args) {
        unZipFile(args[0],args[0].substring(0,args[0].lastIndexOf("."))+ ".back");

    }
}
