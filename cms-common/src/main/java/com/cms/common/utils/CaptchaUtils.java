package com.cms.common.utils;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 验证码工具类
 */
public class CaptchaUtils {

    private static final Map<String, String> CODE_MAP = new ConcurrentHashMap<>();
    private static final int CAPTCHA_WIDTH = 130;
    private static final int CAPTCHA_HEIGHT = 48;

    public static class CaptchaResult {
        private String uuid;
        private String base64;

        public CaptchaResult(String uuid, String base64) {
            this.uuid = uuid;
            this.base64 = base64;
        }
        public String getUuid() { return uuid; }
        public String getBase64() { return base64; }
    }

    public static CaptchaResult generate() {
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(CAPTCHA_WIDTH, CAPTCHA_HEIGHT);
        String code = captcha.getCode();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        CODE_MAP.put(uuid, code.toLowerCase());
        return new CaptchaResult(uuid, captcha.getImageBase64Data());
    }

    public static boolean validate(String uuid, String code) {
        if (StringUtils.isBlank(uuid) || StringUtils.isBlank(code)) {
            return false;
        }
        String cachedCode = CODE_MAP.remove(uuid);
        return code.equalsIgnoreCase(cachedCode);
    }
}
