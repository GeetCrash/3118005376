package check;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLSpirit {
    public static String deleteHTML(String s) {//删除文本中的HTML部分
        String regEx_script = "<script[^>]*?[\\s\\s]*?<\\/script>";//定义script的正则表达式
        String regEx_style = "<style[^>]*?>[\\s\\s]*?<\\/style>";//定义style的正则表达式
        String regEx_html = "<[^>]+>";//定义HTML标签的正则表达式
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(s);
        s = m_script.replaceAll("");//删除script标签
        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(s);
        s = m_style.replaceAll("");//删除style标签
        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(s);
        s = m_html.replaceAll("");//删除html标签
        return s.trim();//将得到的文本返回
    }
}
