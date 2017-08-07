package com.oa.yk.ykoa.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huangchenggang on 2015/11/20.
 */
public class StringUtils {

    public static final String EMPTY_STR = "";

    private final static String APK_SUFFIX = "apk";

    private final static String ZIP_SUFFIX = "zip";

    private final static String RAR_SUFFIX = "rar";

    private final static String SECRET_KEY = "ead5de99e3dfe933ef56bd2ff6e08886";
    public final static String QQ_APP_ID = "1105926923";
    public final static String QQ_APP_KEY = "8HgeGN5xM09MzLEc";
//    public final static String QQ_APP_ID = "1105912510";
   // public final static String QQ_APP_KEY = "Sw7gyU8gmbplES0L";
    public final static String WEIXIN_APP_ID = "wxfc82a80b18654b47";
    public final static String WEIXIN_APP_KEY = "6a0d6f620b9a3eded09c33e0927ed564";
    public final static String SINA_APP_ID = "4289633184";
    public final static String SINA_APP_KEY = "38bb7a0009ce38df008b928ba586ad9d";


    public static boolean isLetter(char c) {
        int k = 0x80;
        return c / k == 0;
    }

    public static boolean isAllNull(String str) {
        if (str == null || str.trim().equals("") || str.equalsIgnoreCase("null")) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isNull(String str) {
        if (str == null || str.trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
     *
     * @param s 需要得到长度的字符串
     * @return int 得到的字符串长度
     */
    public static int length(String s) {
        if (s == null)
            return 0;
        char[] c = s.toCharArray();
        int len = 0;
        for (int i = 0; i < c.length; i++) {
            len++;
            if (!isLetter(c[i])) {
                len++;
            }
        }
        return len;
    }


    /**
     * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为1,英文字符长度为0.5
     *
     * @param s 需要得到长度的字符串
     * @return int 得到的字符串长度
     */
    public static double getLength(String s) {
        double valueLength = 0;
        String chinese = "[\u4e00-\u9fa5]";
        // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
        for (int i = 0; i < s.length(); i++) {
            // 获取一个字符
            String temp = s.substring(i, i + 1);
            // 判断是否为中文字符
            if (temp.matches(chinese)) {
                // 中文字符长度为1
                valueLength += 1;
            } else {
                // 其他字符长度为0.5
                valueLength += 0.5;
            }
        }
        //进位取整
        return Math.ceil(valueLength);
    }


    /**
     * 按照字符串长度切分字符串为两行
     *
     * @param content
     * @param size
     * @return
     */
    public static String[] splitByByteSize(String content, int size) {
        String[] arr = new String[2];
        for (int i = 0; i < size; i++) {
            if (StringUtils.length(content.substring(0, i)) == size || StringUtils.length(content.substring(0, i)) == size - 1) {
                arr[0] = content.substring(0, i);
                arr[1] = getDisplayStr(content.substring(i, content.length() - 1), size);
                break;
            }
        }
        return arr;

    }

    /**
     * 获取字符串的长度
     *
     * @param s
     * @return
     */
    public static int getWordCount(String s) {
        s = s.replaceAll("[^\\x00-\\xff]", "**");
        int length = s.length();
        return length;
    }

    /**
     * 获得字符串指定的长度的可显示的部分，末尾以...结束
     *
     * @param s
     * @param displayLength
     * @return
     */
    public static String getDisplayStr(String s, int displayLength) {
        if (StringUtils.isNull(s)) return "";
        int length = 0;
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            int ascii = Character.codePointAt(s, i);
            if (ascii >= 0 && ascii <= 255) {
                length++;
                if (length > displayLength) {
                    index = i;
                    break;
                }
            } else {
                length += 2;
                if (length > displayLength) {
                    index = i;
                    break;
                }
            }
        }
        if (StringUtils.getWordCount(s) > displayLength) return s.substring(0, index).concat("...");
        else return s;
    }

    /**
     * 判断传入的字符串是否全为数字
     */
    public static boolean isAllNum(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断传入的字符串是否全为中文汉字
     */
    public static boolean isAllChinese(String str) {
        char[] ch = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            Character.UnicodeBlock ub = Character.UnicodeBlock.of(ch[i]);
            if (!(ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                    || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                    || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                    || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B)) {
                return false;
            }
        }
        return true;
    }

    /**
     * String数组转ArrayList
     */
    public static ArrayList<String> array2List(String[] strArray) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, strArray);
        return list;
    }

    public static String formatString(String str) {
        if (str == null || "".equals(str)) {
            return str;
        }
        int index = str.indexOf("\n");
        if (index == -1) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        String[] arr = str.split("\n");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i == 0) {
                sb.append("\n");
            } else {
                sb.append(' ');
            }
        }
        return sb.toString();
    }


    /**
     * 判断当前字符是否含有中文的逗号，如果含有返回true
     *
     * @param str 所需要判断的字符串
     * @return true：有；false：无
     */
    public static boolean hasChinesComma(String str) {
        if (str == null || "".equals(str.trim())) {
            return false;
        }
        return str.indexOf("，") >= 0 ? true : false;
    }

    /**
     * 截取中文逗号之后的字符串，如果不包含逗号，则返回原来的字符串
     *
     * @param str 待处理的字符串
     * @return 已处理的字符串
     */
    public static String CutOffByComma(String str) {
        if (str == null || "".equals(str.trim())) {
            return str;
        }
        int index = str.indexOf("，");
        if (index >= 0) {
            return str.substring(index + 1);
        } else {
            return str;
        }
    }

    public static String getFileNameByUrl(String url) {
        if (isAllNull(url)) {
            return null;
        }
        int lastSeparator = url.lastIndexOf("/");
        if (lastSeparator > 0) {
            return url.substring(lastSeparator + 1);
        }
        return url;
    }


    /**
     * 获取文件名称，如果是zip结尾的话，转换为APK文件
     *
     * @param url
     * @return
     */
    public static String getAPKFileNameByUrl(String url) {
        if (isAllNull(url)) {
            return null;
        }
        String fileName = url.toLowerCase();
        int lastSeparator = url.lastIndexOf("/");
        if (lastSeparator > 0) {
            fileName = url.substring(lastSeparator + 1).toLowerCase();
        }

        if (fileName.endsWith(ZIP_SUFFIX) || fileName.endsWith(RAR_SUFFIX)) {
            return fileName.substring(0, fileName.length() - 3) + APK_SUFFIX;
        }
        return fileName;
    }

    /**
     * 签名算法sign
     *
     * @param parameters
     * @return
     */
    public static String createSign(SortedMap<String, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();// 所有参与传参的参数按照accsii排序（升序）
        Iterator it = es.iterator();
        while (it.hasNext()) {
            @SuppressWarnings("rawtypes")
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (!"sign".equals(k)
                    && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(SECRET_KEY); //KEY是商户秘钥
        String sign = MD5Util.encryptToSHA(sb.toString());
//        String sign=sb.toString();
        return sign;
    }

    public static String getImgStr(String str_filepath) {// 转码
        String imgStr = "";
        try {
            File file1 = new File(str_filepath);
            FileInputStream in = new FileInputStream(file1);
            byte[] buffer = new byte[(int) file1.length() + 100];
            int length = in.read(buffer);
            imgStr= Base64.encodeToString(buffer, 0, length,
                    Base64.DEFAULT);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imgStr;
    }

    public static String bitmapToBase64(Bitmap bitmap) {

        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static Bitmap stringtoBitmap(String string){
        //将字符串转换成Bitmap类型
        Bitmap bitmap=null;
        try {
            byte[]bitmapArray;
            bitmapArray= Base64.decode(string, Base64.DEFAULT);
            bitmap= BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }
    /**
     *说明：移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
     * 联通：130、131、132、152、155、156、185、186    * 电信：133、153、180、189
     * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
     **/
    public static boolean isPhoneNumberValid(String phoneNumber){
        boolean isValid = false;
        String expression = "[1][358]\\d{9}";
        CharSequence inputStr = phoneNumber;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches() ) {
            isValid = true;
        }
        return isValid;
    }
    public static boolean isPwdValid(String password){
        String telRegex = "^[0-9a-zA-Z]{6,20}$";
        if (TextUtils.isEmpty(password)) return false;
        else return password.matches(telRegex);
    }

    // 判断一个字符串是否含有数字
    public static boolean hasDigit(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }
    //判断是否是邮箱
    public static boolean isValidEmail(String mail) {
        Pattern pattern = Pattern
                .compile("^[A-Za-z0-9][\\w\\._]*[a-zA-Z0-9]+@[A-Za-z0-9-_]+\\.([A-Za-z]{2,4})");
        Matcher mc = pattern.matcher(mail);
        return mc.matches();
    }
}
