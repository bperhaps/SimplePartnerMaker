package domain;

import domain.Player;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    void calculateScoreWith() {
        Player player = new Player("pobi", List.of("1", "2", "3"));
        Player player2 = new Player("jason", List.of("1", "4", "2"));

        assertThat(player.calculateScoreWith(player2)).isEqualTo(2);
    }
}