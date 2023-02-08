package cn.widdo.study.regex;

import cn.widdo.assistant.regex.RegexInterface;

/**
 * regex controller.
 *
 * @author XYL
 * @since 263.1.1.0
 * @date 2022/11/29 17:46
 */
public class RegexController {

    protected RegexController() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        final boolean mobile = RegexInterface.SIMCard.ALL.matched("+8618811599806");
        final boolean telephone = RegexInterface.SIMCard.TELEPHONE.matched("0394-5531120");
        RegexInterface.Email.EMAIL.matched("");
        System.out.println(mobile);
        System.out.println(telephone);

        final boolean chinaUnicom = RegexInterface.SIMCard.CHINA_UNICOM.matched("18811599806");
        final boolean chinaMobile = RegexInterface.SIMCard.CHINA_MOBILE.matched("18811599806");
        final boolean chinaTelecom = RegexInterface.SIMCard.CHINA_TELECOM.matched("18811599806");

        System.out.println(chinaUnicom);
        System.out.println(chinaMobile);
        System.out.println(chinaTelecom);
    }
}

