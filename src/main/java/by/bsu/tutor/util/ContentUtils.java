package by.bsu.tutor.util;

import javax.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;

/**
 * The class <code>ContentUtil</code>.
 */
public final class ContentUtils {

    private ContentUtils() {
    }

    public static String encodeContentDisposition(final String agent, final String filename, final boolean inline)
        throws UnsupportedEncodingException {

        if (filename == null) {
            throw new IllegalArgumentException("Value of the \"filename\" parameter cannot be null!");
        }

        String contentDisposition = inline ? "inline; " : "attachment; ";

        String str = agent.toLowerCase();

        if (str != null && str.indexOf("opera") == -1 && str.indexOf("msie") != -1) {
            // IE
            contentDisposition += "filename=\"" + ContentUtils.toUTF8String(filename) + "\"";
        } else if (str.indexOf("opera") != -1) {
            // Opera
            contentDisposition += "filename=" + ContentUtils.toUTF8String(filename);
        } else {
            // Firefox and others
            contentDisposition += "filename=\"" + MimeUtility.encodeText(filename, "UTF8", "B") + "\"";
        }

        return contentDisposition;
    }

    public static String toUTF8String(final String s) throws UnsupportedEncodingException {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c >= 0 && c <= 255 && !Character.isWhitespace(c)) {
                sb.append(c);
            } else {
                byte[] b;
                b = Character.toString(c).getBytes("utf-8");
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0) {
                        k += 256;
                    }
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }

        return sb.toString();
    }
}
