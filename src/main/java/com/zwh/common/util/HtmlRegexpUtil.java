package com.zwh.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA. User: Cougar Date: 15-4-14 Time: 下午7:03 To change this template use File | Settings | File Templates.
 */
public class HtmlRegexpUtil {

    private final static String regxpForHtml = "<([^>]*)>"; // 过滤所有以<开头以>结尾的标签

    private final static String regxpForImgTag = "<\\s*img\\s+([^>]*)\\s*>"; // 找出IMG标签

    private final static String regxpForImaTagSrcAttrib = "src=\"([^\"]+)\""; // 找出IMG标签的SRC属性

    private static String[] tags = StringUtils.split("div,b,big,code,del,dfn,em,font,i,ins,kbd,q,samp,small,span,strike,strong,sub,sup,tt,u,var,script", ",");

    /**
     * 基本功能：替换标记以正常显示 <p>
     *
     * @return String
     */
    public static String replaceTag(String input) {
        if (!hasSpecialChars(input)) {
            return input;
        }
        StringBuffer filtered = new StringBuffer(input.length());
        char c;
        for (int i = 0; i <= input.length() - 1; i++) {
            c = input.charAt(i);
            switch (c) {
                case '<':
                    filtered.append("&lt;");
                    break;
                case '>':
                    filtered.append("&gt;");
                    break;
                case '"':
                    filtered.append("&quot;");
                    break;
                case '&':
                    filtered.append("&amp;");
                    break;
                default:
                    filtered.append(c);
            }

        }
        return (filtered.toString());
    }

    /**
     * 基本功能：判断标记是否存在 <p>
     *
     * @return boolean
     */
    public static boolean hasSpecialChars(String input) {
        boolean flag = false;
        if ((input != null) && (input.length() > 0)) {
            char c;
            for (int i = 0; i <= input.length() - 1; i++) {
                c = input.charAt(i);
                switch (c) {
                    case '>':
                        flag = true;
                        break;
                    case '<':
                        flag = true;
                        break;
                    case '"':
                        flag = true;
                        break;
                    case '&':
                        flag = true;
                        break;
                }
            }
        }
        return flag;
    }

    /**
     * 基本功能：过滤所有以"<"开头以">"结尾的标签 <p>
     *
     * @return String
     */
    public static String filterHtml(String str) {
        Pattern pattern = Pattern.compile(regxpForHtml);
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        boolean result1 = matcher.find();
        while (result1) {
            matcher.appendReplacement(sb, "");
            result1 = matcher.find();
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 基本功能：过滤指定标签 <p>
     *
     * @param tag 指定标签
     * @return String
     */
    public static String fiterHtmlTag(String str, String tag) {
        String regxp = "<\\s*" + tag + "\\s+([^>]*)\\s*>";
        Pattern pattern = Pattern.compile(regxp);
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        boolean result1 = matcher.find();
        while (result1) {
            matcher.appendReplacement(sb, "");
            result1 = matcher.find();
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 过滤指定的一些标签
     */
    public static String fiterHtmlTag(String str) {
        for (String c : tags) {
            str = fiterHtmlTag(str, c);
        }
        return str;
    }


    /**
     * 基本功能：替换指定的标签 <p>
     *
     * @param beforeTag 要替换的标签
     * @param tagAttrib 要替换的标签属性值
     * @param startTag  新标签开始标记
     * @param endTag    新标签结束标记
     * @return String
     * @如：替换img标签的src属性值为[img]属性值[/img]
     */
    public static String replaceHtmlTag(String str, String beforeTag, String tagAttrib, String startTag, String endTag) {
        String regxpForTag = "<\\s*" + beforeTag + "\\s+([^>]*)\\s*>";
        String regxpForTagAttrib = tagAttrib + "=\"([^\"]+)\"";
        Pattern patternForTag = Pattern.compile(regxpForTag);
        Pattern patternForAttrib = Pattern.compile(regxpForTagAttrib);
        Matcher matcherForTag = patternForTag.matcher(str);
        StringBuffer sb = new StringBuffer();
        boolean result = matcherForTag.find();
        while (result) {
            StringBuffer sbreplace = new StringBuffer();
            Matcher matcherForAttrib = patternForAttrib.matcher(matcherForTag.group(1));
            if (matcherForAttrib.find()) {
                matcherForAttrib.appendReplacement(sbreplace, startTag + matcherForAttrib.group(1) + endTag);
            }
            matcherForTag.appendReplacement(sb, sbreplace.toString());
            result = matcherForTag.find();
        }
        matcherForTag.appendTail(sb);
        return sb.toString();
    }

    /**
     * 替换/n 和空格
     *
     * @param str
     * @return
     */
    public static String replaceRow(String str) {
        str = str.replaceAll("\\n", "<br/>").replaceAll(" ", "&nbsp;");
        return str;
    }


//    public static void main(String[] args) {
//        String
//                c =
//                "<div id=\"artical_real\" class=\"js_img_share_area\">\n" + "    <div class=\"dy_box ss_none\" >\n" + "        <div class=\"bg_top\"><span></span></div>\n"
//                        + "        <p>中新社北京3月28日电 (记者 李晓喻)中国官方28日发布推动“一带一路”的愿景和行动，提出政策、设施、贸易、金融、民心五大合作重点。此间分析人士接受中新社记者采访时认为，合作重点中亮点频现，将为沿线国家带来诸多利好，助力“一带一路”建设稳步推进。</p>\n"
//                        + "        <div class=\"bg_bottom\"><span></span></div>\n" + "    </div>\n" + "    <div id=\"main_content\" class=\"js_selection_area\">\n" + "       <!--mainContent begin-->\n"
//                        + "        <p>原标题：&ldquo;一带一路&rdquo;合作重点公布六大亮点引关注</p> <p>中新社北京3月28日电(记者 李晓喻)中国官方28日发布推动&ldquo;一带一路&rdquo;的愿景和行动，提出政策、设施、贸易、金融、民心五大合作重点。此间分析人士接受中新社记者采访时认为，合作重点中亮点频现，将为沿线国家带来诸多利好，助力&ldquo;一带一路&rdquo;建设稳步推进。</p> <p>&mdash;&mdash;共同制定推进区域合作的规划和措施</p> <p>&ldquo;愿景和行动&rdquo;提出，沿线各国可以就经济发展战略和对策进行充分交流对接，共同制定推进区域合作的规划和措施。</p> <p>中国国务院发展研究中心研究员王辉表示，各国可协商解决合作中的问题，这为下一步深化务实合作夯实了基础。</p> <p>&mdash;&mdash;推进建立统一的全程运输协调机制</p> <p>中国官方建议，抓住交通基础设施的关键通道、关键节点和重点工程，优先打通缺失路段，畅通瓶颈路段，配套完善道路安全防护设施和交通管理设施设备；推进建立统一的全程运输协调机制，实现国际运输便利化。</p> <p>王辉表示，这些领域都是目前&ldquo;一带一路&rdquo;沿线国家需要着力突破的地方，合作紧迫性强。在这些方面合作，既能使沿线国家受益，也为中国相关产业发展提供了更广阔的空间。</p> <p>&mdash;&mdash;共同商建自由贸易区</p> <p><strong>官方提出，积极同沿线国家和地区共同商建自由贸易区，激发释放合作潜力，做大做好合作&ldquo;蛋糕&rdquo;。</strong></p> <p>商务部研究院副院长李光辉指出，高标准的自贸区网络是推进&ldquo;一带一路&rdquo;的重要平台。提出共同商建自贸区，将极大地促进中国和沿线国家双向投资，带来巨大商机。&ldquo;这是和沿线国家合作共赢，融合发展的体现，能使之更好分享中国改革开放的红利。&rdquo;</p> <p>王辉认为，一带一路和自贸区建设相辅相成，共同商建自贸区将使&ldquo;一带一路&rdquo;战略由点到面更好地落实。</p> <p>&mdash;&mdash;推动新兴产业合作</p> <p>官方提出，推动新兴产业合作，促进沿线国家加强在新一代信息技术、生物、新能源、新材料等新兴产业领域的深入合作，推动建立创业投资合作机制。</p> <p>&ldquo;新兴产业是当前全球在经济结构调整中的共同方向&rdquo;，国家发改委宏观经济研究院科研部副部长史育龙指出，加快新兴产业合作是一举多得：短期来看能成为沿线国家新的经济增长点，长远来看有利于培养持续竞争能力。</p> <p>在王辉看来，提出在新兴产业方面加强合作，表明中国并非借&ldquo;一带一路&rdquo;搞低水平产业转移，体现了中国互利共赢的理念，彰显了负责任大国的地位。</p> <p>&mdash;&mdash;深化中国-东盟银行联合体合作</p> <p>官方首次明确提出，要深化中国-东盟银行联合体、上合组织银行联合体务实合作。</p> <p>王辉认为，这将助力中国和东盟更好合作打造&ldquo;钻石十年&rdquo;，同时表明上海合作组织正从政治领域向经济金融领域深化。</p> <p>此外，官方提出要加强金融监管合作，逐步在区域内建立高效监管协调机制。</p> <p>&ldquo;如果没有监管标准，对外投资风险会很大&rdquo;，王辉指出，加强金融监管合作是规避对外投资风险的有力举措，为企业顺利&ldquo;出海&rdquo;提供了保障，也有助于完善经济治理体系。</p> <p>&mdash;&mdash;建设绿色丝绸之路</p> <p>&ldquo;愿景和行动&rdquo;建议，在投资贸易中突出生态文明理念，加强生态环境、生物多样性和应对气候变化合作，共建绿色丝绸之路。</p> <p>王辉指出，绿色发展理念关系到&ldquo;一带一路&rdquo;建设全局。如果不注重生态环境保护，不仅具体项目合作将受阻，国与国之间的相互信任也会遭到损害。</p> <p>李光辉认为，明确提出建设绿色丝绸之路，体现出中国力避沿线国家重走自己过去&ldquo;先污染再治理&rdquo;弯路的苦心，将使沿线国家经济发展更加健康可持续，也有利于&ldquo;一带一路&rdquo;战略获得更多支持。<span class=\"ifengLogo\"><a href=\"http://www.ifeng.com/\" target=\"_blank\"><img src=\"http://img.ifeng.com/page/Logo.gif\" width=\"15\" height=\"17\" /></a></span></p>\n"
//                        + "      <!--mainContent end-->\n" + "    </div>\n" + "</div>";
//
//        if (HtmlRegexpUtil.hasSpecialChars(c)) {
//            String a = HtmlRegexpUtil.filterHtml(c);
//            a = HtmlRegexpUtil.replaceRow(a);
//            System.out.println(a);
//        }
//    }
}
