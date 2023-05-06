package cn.widdo.study.hanlp;

import cn.widdo.assistant.base.BaseController;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;

/**
 * HanLpController.
 *
 * @author XYL
 * @date 2023/04/25 23:55
 * @since 305.1.1.0
 */
public class HanLpController extends BaseController {

    public static void main(String[] args) {

        String text = "在我国，春季田间管理的重点是夏季粮油作物，主要是冬小麦和油菜，产量超过全年粮食产量的五分之一。";

        System.out.println(HanLP.segment("在我国，春季田间管理的重点是夏季粮油作物，主要是冬小麦和油菜，产量超过全年粮食产量的五分之一。"));

        // 不用词典照样分词。
        NLPTokenizer.ANALYZER.enableCustomDictionary(false);
        System.out.println(NLPTokenizer.segment(text));

        // 使用用词典分词。
        NLPTokenizer.ANALYZER.enableCustomDictionary(true);
        System.out.println(NLPTokenizer.segment(text));
        System.out.println(NLPTokenizer.analyze("我救的不是他，是多年前一个寒夜里，在篝火与烈酒中，想仗剑江湖的少年。").translateLabels());

        //简体转港体
        System.out.println(HanLP.s2hk(text));
    }
}
