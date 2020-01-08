package Pluto;


import java.security.MessageDigest;

public class function {
    public function() {

    }

    public static boolean isInvalid(String value) {
        return (value == null || value.length() == 0);
    }

    // MD5����
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin) {
        String resultString = null;

        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString
                    .getBytes()));
        } catch (Exception ex) {

        }
        return resultString;
    }

    /*javascript中的location.href有很多种用法，主要如下。
self.location.href="/url" 当前页面打开URL页面
location.href="/url" 当前页面打开URL页面
windows.location.href="/url" 当前页面打开URL页面，前面三个用法相同。
this.location.href="/url" 当前页面打开URL页面
parent.location.href="/url" 在父页面打开新页面
top.location.href="/url" 在顶层页面打开新页面
alert显示弹窗
*/
    public static String PlutoJump(String errorStr, String jumpTo) {
        String str = null;
        try {
            str = "<script language='javascript'>alert('" + errorStr
                    + "');location.href='" + jumpTo + "';</script>";
        } catch (Exception e) {
            str = "<script language='javascript'>alert('" + errorStr
                    + "');location.href='" + jumpTo + "';</script>";
        }
        return str;
    }

    public static String toJump(String jumpTo) {
        String str = null;
        try {
            str = "<script language='javascript'>location.href='" + jumpTo + "';</script>";
        } catch (Exception e) {
            str = "<script language='javascript'>location.href='" + jumpTo + "';</script>";
        }
        return str;
    }

    public static int strToInt(String str) {
        int a = 0;
        try {
            a = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            a = 0;
        }
        return a;
    }
}
