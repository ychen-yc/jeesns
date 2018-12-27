package com.lxinet.jeesns.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlUtil {
    private static final String REGEX_SCRIPT = "<script[^>]*?>[\\s\\S]*?<\\/script>";
    private static final String REGEX_STYLE = "<style[^>]*?>[\\s\\S]*?<\\/style>";
    private static final String REGEX_HTML = "<[^>]+>";
    private static final String REGEX_SPACE = "\\s*|\t|\r|\n";
    private static final String REGEX_SPAN = "&lt;span[^>]*?&gt;[\\s\\S]*?&lt;\\/span&gt;";
    private static final String REGEX_P = "&lt;p[^>]*?&gt;[\\s\\S]*?&lt;\\/p&gt;";
    private static final String REGEX_A = "&lt;a[^>]*?&gt;[\\s\\S]*?&lt;\\/a&gt;";
    private static final String REGEX_B = "&lt;b[^>]*?&gt;[\\s\\S]*?&lt;\\/b&gt;";
    private static final String REGEX_BLOCKQUOTE = "&lt;blockquote[^>]*?&gt;[\\s\\S]*?&lt;\\/blockquote&gt;";
    private static final String REGEX_BR = "&lt;br\\/&gt;";
    private static final String REGEX_CODE = "<code[^>]*?>[\\s\\S]*?<\\/code>";
    private static final String REGEX_PRE = "&lt;pre[^>]*?&gt;[\\s\\S]*?&lt;\\/pre&gt;";
    private static final String REGEX_DIV = "&lt;div[^>]*?&gt;[\\s\\S]*?&lt;\\/div&gt;";
    private static final String REGEX_COL = "&lt;col[^>]*?&gt;[\\s\\S]*?&lt;\\/col&gt;";
    private static final String REGEX_COLGROUP = "&lt;colgroup[^>]*?&gt;[\\s\\S]*?&lt;\\/colgroup&gt;";
    private static final String REGEX_DD = "&lt;dd[^>]*?&gt;[\\s\\S]*?&lt;\\/dd&gt;";
    private static final String REGEX_DL = "&lt;dl[^>]*?&gt;[\\s\\S]*?&lt;\\/dl&gt;";
    private static final String REGEX_DT = "&lt;dt[^>]*?&gt;[\\s\\S]*?&lt;\\/dt&gt;";
    private static final String REGEX_EM = "&lt;dt[^>]*?&gt;[\\s\\S]*?&lt;\\/em&gt;";
    private static final String REGEX_H1 = "&lt;h1[^>]*?&gt;[\\s\\S]*?&lt;\\/h1&gt;";
    private static final String REGEX_H2 = "&lt;h2[^>]*?&gt;[\\s\\S]*?&lt;\\/h2&gt;";
    private static final String REGEX_H3 = "&lt;h3[^>]*?&gt;[\\s\\S]*?&lt;\\/h3&gt;";
    private static final String REGEX_H4 = "&lt;h4[^>]*?&gt;[\\s\\S]*?&lt;\\/h4&gt;";
    private static final String REGEX_H5 = "&lt;h5[^>]*?&gt;[\\s\\S]*?&lt;\\/h5&gt;";
    private static final String REGEX_H6 = "&lt;h6[^>]*?&gt;[\\s\\S]*?&lt;\\/h6&gt;";
    private static final String REGEX_I = "&lt;i[^>]*?&gt;[\\s\\S]*?&lt;\\/i&gt;";
    private static final String REGEX_IMG = "&lt;img[^>]*?&gt;[\\s\\S]*?&lt;\\/img&gt;";
    private static final String REGEX_UL = "&lt;ul[^>]*?&gt;[\\s\\S]*?&lt;\\/ul&gt;";
    private static final String REGEX_LI = "&lt;li[^>]*?&gt;[\\s\\S]*?&lt;\\/li&gt;";
    private static final String REGEX_OL = "&lt;ol[^>]*?&gt;[\\s\\S]*?&lt;\\/ol&gt;";
    private static final String REGEX_SMALL = "&lt;small[^>]*?&gt;[\\s\\S]*?&lt;\\/small&gt;";
    private static final String REGEX_STRONG = "&lt;strong[^>]*?&gt;[\\s\\S]*?&lt;\\/strong&gt;";
    private static final String REGEX_TABLE = "&lt;table[^>]*?&gt;[\\s\\S]*?&lt;\\/table&gt;";
    private static final String REGEX_TBODY = "&lt;tbody[^>]*?&gt;[\\s\\S]*?&lt;\\/tbody&gt;";
    private static final String REGEX_TD = "&lt;td[^>]*?&gt;[\\s\\S]*?&lt;\\/td&gt;";
    private static final String REGEX_TH = "&lt;th[^>]*?&gt;[\\s\\S]*?&lt;\\/th&gt;";
    private static final String REGEX_TR = "&lt;tr[^>]*?&gt;[\\s\\S]*?&lt;\\/tr&gt;";
    private static final String REGEX_TFOOT = "&lt;tfoot[^>]*?&gt;[\\s\\S]*?&lt;\\/tfoot&gt;";
    private static final String REGEX_THEAD = "&lt;thead[^>]*?&gt;[\\s\\S]*?&lt;\\/thead&gt;";
    private static final String REGEX_U = "&lt;u[^>]*?&gt;[\\s\\S]*?&lt;\\/u&gt;";

    public static String delHTMLTag(String htmlStr) {
        if (StringUtils.isNotEmpty(htmlStr)) {
            Pattern pScript = Pattern.compile("<script[^>]*?>[\\s\\S]*?<\\/script>", 2);
            Matcher mScript = pScript.matcher(htmlStr);
            htmlStr = mScript.replaceAll("");

            Pattern pStyle = Pattern.compile("<style[^>]*?>[\\s\\S]*?<\\/style>", 2);
            Matcher mStyle = pStyle.matcher(htmlStr);
            htmlStr = mStyle.replaceAll("");

            Pattern pHtml = Pattern.compile("<[^>]+>", 2);
            Matcher mHtml = pHtml.matcher(htmlStr);
            htmlStr = mHtml.replaceAll("");

            Pattern pSpace = Pattern.compile("\\s*|\t|\r|\n", 2);
            Matcher mSpace = pSpace.matcher(htmlStr);
            htmlStr = mSpace.replaceAll("");
            return htmlStr.trim();
        }
        return htmlStr;
    }

    public static String partHtmlUnescape(String html) {
        if (StringUtils.isNotEmpty(html)) {
            html = html.replaceAll("(?i)&lt;p ", "<p ").replaceAll("(?i)&lt;p&gt;", "<p>").replaceAll("(?i)&lt;/p&gt;", "</p>").replaceAll("(?i)&lt;span ", "<span ").replaceAll("(?i)&lt;span&gt;", "<span>").replaceAll("(?i)&lt;/span&gt;", "</span>").replaceAll("(?i)&lt;a ", "<a ").replaceAll("(?i)&lt;a&gt;", "<a>").replaceAll("(?i)&lt;/a&gt;", "</a>").replaceAll("(?i)&lt;b ", "<b ").replaceAll("(?i)&lt;b&gt;", "<b>").replaceAll("(?i)&lt;/b&gt;", "</b>").replaceAll("(?i)&lt;blockquote ", "<blockquote ").replaceAll("(?i)&lt;blockquote&gt;", "<blockquote>").replaceAll("(?i)&lt;/blockquote&gt;", "</blockquote>").replaceAll("(?i)&lt;div ", "<div ").replaceAll("(?i)&lt;div&gt;", "<div>").replaceAll("(?i)&lt;/div&gt;", "</div>").replaceAll("(?i)&lt;div ", "<div ").replaceAll("(?i)&lt;div&gt;", "<div>").replaceAll("(?i)&lt;br&gt;", "<br>").replaceAll("(?i)&lt;br/&gt;", "<br/>").replaceAll("(?i)&lt;code ", "<code ").replaceAll("(?i)&lt;code&gt;", "<code>").replaceAll("(?i)&lt;/code&gt;", "</code>").replaceAll("(?i)&lt;ul&gt;", "<ul>").replaceAll("(?i)&lt;/ul&gt;", "</ul>").replaceAll("(?i)&lt;ol&gt;", "<ol>").replaceAll("(?i)&lt;/ol&gt;", "</ol>").replaceAll("(?i)&lt;li&gt;", "<li>").replaceAll("(?i)&lt;/li&gt;", "</li>").replaceAll("(?i)&lt;h1&gt;", "<h1>").replaceAll("(?i)&lt;/h1&gt;", "</h1>").replaceAll("(?i)&lt;h2&gt;", "<h2>").replaceAll("(?i)&lt;/h2&gt;", "</h2>").replaceAll("(?i)&lt;h3&gt;", "<h3>").replaceAll("(?i)&lt;/h3&gt;", "</h3>").replaceAll("(?i)&lt;h4&gt;", "<h4>").replaceAll("(?i)&lt;/h4&gt;", "</h4>").replaceAll("(?i)&lt;h5&gt;", "<h5>").replaceAll("(?i)&lt;/h5&gt;", "</h5>").replaceAll("(?i)&lt;h6&gt;", "<h6>").replaceAll("(?i)&lt;/h6&gt;", "</h6>").replaceAll("(?i)&lt;pre&gt;", "<pre>").replaceAll("(?i)&lt;/pre&gt;", "</pre>").replaceAll("(?i)&lt;strong&gt;", "<strong>").replaceAll("(?i)&lt;/strong&gt;", "</strong>").replaceAll("(?i)&lt;u&gt;", "<u>").replaceAll("(?i)&lt;/u&gt;", "</u>").replaceAll("(?i)&lt;em&gt;", "<em>").replaceAll("(?i)&lt;/em&gt;", "</em>").replaceAll("(?i)&lt;s&gt;", "<s>").replaceAll("(?i)&lt;/s&gt;", "</s>").replaceAll("(?i)&lt;img ", "<img ").replaceAll("(?i)&lt;table ", "<table ").replaceAll("(?i)&lt;/table&gt;", "</table>").replaceAll("(?i)&lt;tbody&gt;", "<tbody>").replaceAll("(?i)&lt;/tbody&gt;", "</tbody>").replaceAll("(?i)&lt;tr&gt;", "<tr>").replaceAll("(?i)&lt;/tr&gt;", "</tr>").replaceAll("(?i)&lt;td&gt;", "<td>").replaceAll("(?i)&lt;/td&gt;", "</td>").replaceAll("(?i)&lt;th&gt;", "<th>").replaceAll("(?i)&lt;/th&gt;", "</th>").replaceAll("(?i)&lt;thead&gt;", "<thead>").replaceAll("(?i)&lt;/thead&gt;", "</thead>").replaceAll("(?i)&lt;tfoot&gt;", "<tfoot>").replaceAll("(?i)&lt;/tfoot&gt;", "</tfoot>").replaceAll("(?i)&lt;small&gt;", "<small>").replaceAll("(?i)&lt;/small&gt;", "</small>").replace("&quot;", "\"").replace("/&gt;", "/>").replace("\"&gt;", "\">").replace("&amp;", "&");
            Pattern p = Pattern.compile("<code[^>]*?>[\\s\\S]*?<\\/code>");
            Matcher m = p.matcher(html);
            while (m.find()) {
                String s = m.group(0);
                String news = s + "";
                news = news.replace("<", "&lt;");
                news = news.replace(">", "&gt;");
                html = html.replace(s, news);
            }
            html = html.replaceAll("(?i)&lt;code&gt;", "<code>").replaceAll("(?i)&lt;/code&gt;", "</code>");
        }
        return html;
    }
}
