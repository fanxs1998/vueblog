package com.code.util;

import com.code.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

public class ShiroUtil {

    public static AccountProfile getProfile() {
        return (AccountProfile)SecurityUtils.getSubject().getPrincipal();
    }
}
