package domain;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.*;

public class GaleShapley {

    private static class Prefer {
        private final String name;
        private final List<String> prefers;

        public Prefer(String name, List<String> prefers) {
            this.name = name;
            this.prefers = prefers;
        }

        public String getFirst() {
            String first = prefers.get(0);
            prefers.remove(0);

            return first;
        }

        public String getName() {
            return name;
        }

        public int calculatePrefers(Prefer prefers) {
            return this.prefers.indexOf(prefers.getName());
        }

        public boolean isSameName(String name) {
            return this.name.equals(name);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Prefer prefer = (Prefer) o;
            return Objects.equals(name, prefer.name) && Objects.equals(prefers, prefer.prefers);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, prefers);
        }
    }

    private final List<Prefer> group1;
    private final List<Prefer> group2;

    public GaleShapley(List<Player> players) {
        this.group1 = players.subList(0, players.size() / 2).stream()
                .map(player -> new Prefer(player.getNickName(), getPrefers(player, players.subList(players.size() / 2, players.size()))))
                .collect(toList());
        this.group2 = players.subList(players.size() / 2, players.size()).stream()
                .map(player -> new Prefer(player.getNickName(), getPrefers(player, players.subList(0, players.size() / 2))))
                .collect(toList());
    }

    public Map<String, String> getMatches() {
        return start(new HashMap<>()).entrySet().stream().collect(toMap(Map.Entry::getKey, entry -> entry.getValue().getName()));
    }

    private Map<String, Prefer> start(Map<String, Prefer> matchList) {

        Map<String, List<Prefer>> collect = this.group1.stream()
                .filter(p -> !matchList.containsValue(p))
                .collect(groupingBy(Prefer::getFirst, toList()));

        Map<String, Prefer> collect1 = collect.entrySet().stream().collect(
                toMap(Map.Entry::getKey,
                        entry -> Stream.of(entry.getValue().stream(), Optional.ofNullable(matchList.get(entry.getKey())).stream())
                                .flatMap(Function.identity())
                                .min(comparing(p -> findByName(entry.getKey()).calculatePrefers(p)))
                                .get()
                )
        );

        if(collect.size() == 0) return matchList;

        matchList.putAll(collect1);

        return start(matchList);
    }

    private Prefer findByName(String name) {
        return Stream.of(group1.stream(), group2.stream())
                .flatMap(Function.identity())
                .filter(p -> p.isSameName(name))
                .findAny()
                .get();
    }

    private List<String> getPrefers(Player player, List<Player> players) {
        return players.stream()
                .filter(p -> !player.equals(p))
                .sorted(comparing(p -> p.calculateScoreWith(player), reverseOrder()))
                .map(Player::getNickName)
                .collect(toList());
    }


}
