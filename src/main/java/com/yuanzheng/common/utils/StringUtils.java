package com.yuanzheng.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author bootdo
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static String filterEmoji(String source) {
        if (source != null) {
            Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]|" +
                            "[\ud83e\udd00-\ud83e\uddff]|[\u2300-\u23ff]|[\u2500-\u25ff]|[\u2100-\u21ff]|[\u00a0-\u0fff]|[\u2b00-\u2bff]|[\u2d06]|[\u3030]"
                    , Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
            Matcher emojiMatcher = emoji.matcher(source);
            if (emojiMatcher.find()) {
                source = emojiMatcher.replaceAll("*");
                return source;
            }
            return source;
        }
        return source;
    }
}
