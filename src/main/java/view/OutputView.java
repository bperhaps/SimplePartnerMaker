package view;

import java.util.Map;

public class OutputView {

    private static final String MATCH_RESULT_INTRO = "아래의 페어와 오늘 점심을 함께 해 주세요^^";
    private static final String MATCH_RESULT_FORMAT = "%s 는 오늘 %s 와 식사를 합니다.%n";
    private static final String BEST_MATCH_RESULT_INTRO = "당신의 최고의 짝은 아래와 같습니다.";
    private static final String BEST_MATCH_RESULT_FORMAT = "%s -> %s%n";


    public static void printMatchResult(Map<String, String> matches) {
        System.out.println(MATCH_RESULT_INTRO);
        System.out.println();
        matches.forEach((key, value) -> System.out.printf(MATCH_RESULT_FORMAT, key, value));
    }

    public static void printBestMatchResult(Map<String, String> bestMatches) {
        System.out.println();
        System.out.println(BEST_MATCH_RESULT_INTRO);
        System.out.println();
        bestMatches.forEach((key, value) -> System.out.printf(BEST_MATCH_RESULT_FORMAT, key, value));
    }
}
