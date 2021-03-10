package view;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

class InputViewTest {

    @Test
    void readToList() {
        String path = getClass().getClassLoader().getResource("data.csv").getPath();
        Assertions.assertThat(
                InputView.readToList(path).stream()
                        .flatMap(Collection::stream)
                        .collect(toList())
        ).containsExactly("4", "5", "6");
    }
}