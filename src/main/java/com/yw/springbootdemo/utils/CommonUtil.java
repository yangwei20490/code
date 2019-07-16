package com.yw.springbootdemo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yangwei
 * @date 2019/6/18 19:14
 */
public enum CommonUtil {
    INSTANCE;

    private static final String EMAIL_REG = "^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])" +
            "+[A-Za-z\\d]{2,5}$";
    private static final String MOBILE_REG = "^[1][3,4,5,6,7,8,9][0-9]{9}$";
    private static final String PHONE_REG = "^(0\\d{2,3}-\\d{7,8})$";
    private static final String CERTIFICATE_NO_REG = "(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])" +
            "|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|" +
            "(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}[0-9Xx]$)";
    private static final String URL_REG = "^[A-Za-z0-9:.?+&@#%=~_*|\\/]+$";
    private static final String PERMISSION_CODE = "^[A-Za-z0-9_-]+$";
    private static final String DEPARTMENT_CODE = "^[A-Za-z0-9]{1,20}$";
    private static final String ACCOUNT_REG="^[A-Za-z0-9_-]{4,25}$";
    private static final String NAME_REG = "^[\\u4E00-\\u9FA5]{2,8}$";
    private static final String ORGANIZATION_NAME = "^[A-Za-z0-9\\u4e00-\\u9fa5（）()]{1,30}+$";

    public static boolean checkEmailFormat(String email){
        return checkFormat(email, EMAIL_REG);
    }

    public static boolean checkMobileFormat(String mobile) {
        return checkFormat(mobile, MOBILE_REG);
    }

    public static boolean checkPhoneFormat(String phone) {
        return checkFormat(phone, PHONE_REG);
    }

    public static boolean checkCertificateNoFormat(String certificateNo) {
        return checkFormat(certificateNo, CERTIFICATE_NO_REG);
    }

    public static boolean checkUrlFormat(String url) {
        return checkFormat(url, URL_REG);
    }

    public static boolean checkPermissionCodeFormat(String code) {
        return checkFormat(code, PERMISSION_CODE);
    }

    public static boolean checkDepartmentCodeFormat(String code) {
        return checkFormat(code, DEPARTMENT_CODE);
    }

    public static boolean checkAccountFormat(String account) {
        return checkFormat(account, ACCOUNT_REG);
    }

    public static boolean checkNameFormat(String name) {
        return checkFormat(name, NAME_REG);
    }

    public static boolean checkOrgNameFormat(String name) {
        return checkFormat(name, ORGANIZATION_NAME);
    }

    public static boolean checkFormat(String checkedStr, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(checkedStr);
        return matcher.matches();
    }
}
