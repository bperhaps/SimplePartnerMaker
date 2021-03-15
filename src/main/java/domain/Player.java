package domain;

import java.util.List;

public class Player {
    private final String nickName;
    private final List<String> answers;

    public Player(String nickName, List<String> answers) {
        this.nickName = nickName;
        this.answers = answers;
    }

    public int calculateScoreWith(Player player) {
        return (int) answers.stream()
                .filter(player.answers::contains)
                .count();
    }

    public String getNickName() {
        return nickName;
    }
}
