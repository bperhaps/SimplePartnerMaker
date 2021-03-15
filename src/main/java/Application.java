
import controller.PartnerMakerController;

import java.util.Objects;

public class Application {
    public static void main(String[] args) {
        String path = Objects.requireNonNull(
                Application.class
                        .getClassLoader()
                        .getResource("file2.csv")
        ).getPath();

        PartnerMakerController.run(path);
    }
}
