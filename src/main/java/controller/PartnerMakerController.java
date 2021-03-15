package controller;

import domain.GaleShapley;
import domain.Player;
import view.InputView;
import view.OutputView;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class PartnerMakerController {

    public static void run(String path) {
        List<List<String>> lists = InputView.readToList(path);
        List<Player> players = createPlayersFromUserInput(lists);

        GaleShapley galeShapley = new GaleShapley(players);

        OutputView.printMatchResult(galeShapley.getMatches());
        OutputView.printBestMatchResult(galeShapley.getBestMatches());
    }

    private static List<Player> createPlayersFromUserInput(List<List<String>> inputs) {
        return inputs.stream()
                .map(input -> new Player(input.get(1), input.subList(2, input.size())))
                .collect(toList());
    }
}
