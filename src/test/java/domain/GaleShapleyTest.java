package domain;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GaleShapleyTest {

    @Test
    void getMatches() {
        GaleShapley galeShapley = new GaleShapley(List.of(
                new Player("발너못1", List.of("1", "1", "1")),
                new Player("손너잘2", List.of("2", "2", "2")),
                new Player("발너잘3", List.of("1", "1", "1")),
                new Player("손너못4", List.of("2", "2", "2"))
        ));

        Map<String, String> map = new HashMap<>();
        map.put("발너잘3", "발너못1");
        map.put("손너못4", "손너잘2");
        assertThat(galeShapley.getMatches()).containsAllEntriesOf(map);
    }
}