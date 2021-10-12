package com.wangduwei.encode;

import java.io.UnsupportedEncodingException;

/**
 * Base64编码实现，二进制到字符的映射
 */
public class Base64Impl {

    private static final char[] CHARSET = new char[]{
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', '0', '1', '2', '3',
            '4', '5', '6', '7', '8', '9', '+', '/'
    };
    /*
     * charset used to decode.
     */
    private static final int[] DECODE_CHARSET = new int[128];
    static {
        for (int i=0; i<64; i++) {
            DECODE_CHARSET[CHARSET[i]] = i;
        }
    }

    /**
     * A convenient method for encoding Java String,
     * it uses encode(byte[], boolean) to encode byte array.
     *
     * @param inputStr a string to be encoded.
     * @param charset charset name ("GBK" for example) that is used to convert inputStr into byte array.
     * @param padding whether using padding characters "="
     * @return encoded string
     * @throws UnsupportedEncodingException if charset is unsupported
     */
    public String encode(String inputStr, String charset, boolean padding)
            throws UnsupportedEncodingException {
        String encodeStr = null;

        byte[] bytes = inputStr.getBytes(charset);
        encodeStr = encode(bytes, padding);

        return encodeStr;
    }

    /**
     * Using Base64 to encode bytes.
     *
     * @param bytes byte array to be encoded
     * @param padding whether using padding characters "="
     * @return encoded string
     */
    public String encode(byte[] bytes, boolean padding) {
        // 4 6-bit groups
        int group_6bit_1,
                group_6bit_2,
                group_6bit_3,
                group_6bit_4;

        // bytes of a group
        int byte_1,
                byte_2,
                byte_3;

        // number of 3-byte groups
        int groups = bytes.length / 3;
        // at last, there might be 0, 1, or 2 byte(s) remained,
        // which needs to be encoded individually.
        int tail = bytes.length % 3;

        StringBuilder sb = new StringBuilder(groups * 4 + 4);

        // handle each 3-byte group
        for (int i = 0; i < groups; i++) {
            byte_1 = bytes[3*i]   & 0xFF;
            byte_2 = bytes[3*i+1] & 0xFF;
            byte_3 = bytes[3*i+2] & 0xFF;

            group_6bit_1 =  byte_1 >>> 2;
            group_6bit_2 = (byte_1 &  0x03) << 4 | byte_2 >>> 4;
            group_6bit_3 = (byte_2 &  0x0F) << 2 | byte_3 >>> 6;
            group_6bit_4 =  byte_3 &  0x3F;

            sb.append(CHARSET[group_6bit_1])
                    .append(CHARSET[group_6bit_2])
                    .append(CHARSET[group_6bit_3])
                    .append(CHARSET[group_6bit_4]);
        }

        // handle last 1 or 2 byte(s)
        if (tail == 1) {
            byte_1 = bytes[bytes.length-1] & 0xFF;

            group_6bit_1 =  byte_1 >>> 2;
            group_6bit_2 = (byte_1 &   0x03) << 4;

            sb.append(CHARSET[group_6bit_1])
                    .append(CHARSET[group_6bit_2]);

            if (padding) {
                sb.append('=').append('=');
            }
        } else if (tail == 2) {
            byte_1 = bytes[bytes.length-2] & 0xFF;
            byte_2 = bytes[bytes.length-1] & 0xFF;

            group_6bit_1 =  byte_1 >>> 2;
            group_6bit_2 = (byte_1 &   0x03) << 4 | byte_2 >>> 4;
            group_6bit_3 = (byte_2 &   0x0F) << 2;

            sb.append(CHARSET[group_6bit_1])
                    .append(CHARSET[group_6bit_2])
                    .append(CHARSET[group_6bit_3]);

            if (padding) {
                sb.append('=');
            }
        }

        return sb.toString();
    }

    /**
     * Decode a Base64 string to bytes (byte array).
     *
     * @param code Base64 string to be decoded
     * @return byte array
     */
    public static byte[] decode(String code) {
        char[] chars = code.toCharArray();

        int group_6bit_1,
                group_6bit_2,
                group_6bit_3,
                group_6bit_4;

        int byte_1,
                byte_2,
                byte_3;

        int len = chars.length;
        // ignore last '='s
        if (chars[chars.length - 1] == '=') {
            len--;
        }
        if (chars[chars.length - 2] == '=') {
            len--;
        }

        int groups = len / 4;
        int tail = len % 4;

        // each group of characters (4 characters) will be converted into 3 bytes,
        // and last 2 or 3 characters will be converted into 1 or 2 byte(s).
        byte[] bytes = new byte[groups * 3 + (tail > 0 ? tail - 1 : 0)];

        int byteIdx = 0;

        // decode each group
        for (int i=0; i<groups; i++) {
            group_6bit_1 = DECODE_CHARSET[chars[4*i]];
            group_6bit_2 = DECODE_CHARSET[chars[4*i + 1]];
            group_6bit_3 = DECODE_CHARSET[chars[4*i + 2]];
            group_6bit_4 = DECODE_CHARSET[chars[4*i + 3]];

            byte_1 =  group_6bit_1         << 2 | group_6bit_2 >>> 4;
            byte_2 = (group_6bit_2 & 0x0F) << 4 | group_6bit_3 >>> 2;
            byte_3 = (group_6bit_3 & 0x03) << 6 | group_6bit_4;

            bytes[byteIdx++] = (byte) byte_1;
            bytes[byteIdx++] = (byte) byte_2;
            bytes[byteIdx++] = (byte) byte_3;
        }

        // decode last 2 or 3 characters
        if (tail == 2) {
            group_6bit_1 = DECODE_CHARSET[chars[len - 2]];
            group_6bit_2 = DECODE_CHARSET[chars[len - 1]];

            byte_1 = group_6bit_1 << 2 | group_6bit_2 >>> 4;
            bytes[byteIdx] = (byte) byte_1;
        } else if (tail == 3) {
            group_6bit_1 = DECODE_CHARSET[chars[len - 3]];
            group_6bit_2 = DECODE_CHARSET[chars[len - 2]];
            group_6bit_3 = DECODE_CHARSET[chars[len - 1]];

            byte_1 =  group_6bit_1         << 2 | group_6bit_2 >>> 4;
            byte_2 = (group_6bit_2 & 0x0F) << 4 | group_6bit_3 >>> 2;

            bytes[byteIdx++] = (byte) byte_1;
            bytes[byteIdx]   = (byte) byte_2;
        }

        return bytes;
    }

    /**
     * Test.
     * @param args
     */
    public static void main(String[] args) {
        Base64Impl base64 = new Base64Impl();
        String str = "Man is distinguished, not only by his reason, but by this singular passion from other animals, which is a lust of the mind, that by a perseverance of delight in the continued and indefatigable generation of knowledge, exceeds the short vehemence of any carnal pleasure.";
        System.out.println(str);
        try {
            String encodeStr = base64.encode(str, "GBK", false);
            System.out.println(encodeStr);
            byte[] decodeBytes = base64.decode(encodeStr);
            String decodeStr = new String(decodeBytes, "GBK");
            System.out.println(decodeStr);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
