package org.virgil.jdk.sort;

import java.util.Map;

/**
 * Created by Virgil on 2017/8/29.
 */
public class EncodeResult {
    private String encode;
    // 字符编码对
    private Map<Character, String> letterCode;

    public EncodeResult(String encode, Map<Character, String> letterCode) {
        super();
        this.encode = encode;
        this.letterCode = letterCode;
    }

    public String getEncode() {
        return encode;
    }

    public Map<Character, String> getLetterCode() {
        return letterCode;
    }
}
