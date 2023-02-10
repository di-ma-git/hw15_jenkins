package org.example;

import java.util.ArrayList;
import java.util.List;

public class PathFinder {

    // метод для определения кандидатов на следующий шаг
    // слово должно отличаться ровно на 1 букву
    private static boolean isMorph(String source, String target) {
        if (source.length() != target.length()) {
            return false;
        }

        int morphCounter = 0;
        for (int i = 0; i < source.length(); i++) {
            if (source.toCharArray()[i] != target.toCharArray()[i]) {
                morphCounter++;
            }
        }
        return morphCounter == 1 ? true : false;
    }

    public static boolean hasPath(List<String> words, String head, String tail) {
        // Если голова и хвост равны, то все готово
        if (head.equals(tail)) {
            return true;
        }
        // Если слов нет или в словах нет хвоста, то ничего не найдем
        if (words.size() == 0 || !words.contains(tail)) {
            return false;
        }

        System.out.println("All words: " + words.toString());

        List<String> morphs = new ArrayList<>();
        // Собираем из всех слов список тех, которые отличаются 1 буквой
        // Нужно их перебрать как следующие шаги в пути
        for (String word : words) {
            if (isMorph(head, word)) {
                morphs.add(word);
            }
        }
        System.out.println("Words to go: " + morphs.toString());

        boolean result = false;
        // Перебираем кандидатов:
        // - Удаляем кандидата из основного списка
        // - Запускаем метод рекурсивно с укороченным списком и с кандидатом в роли головы
        for (String morph : morphs) {
            System.out.println("Using: " + morph);
            List<String> newWords = new ArrayList<>(words);
            newWords.remove(morph);
            result = hasPath(newWords, morph, tail);

            // Если дошли до тру, то дальше не ищем и останавливаемся.
            if (result) break;
        }
        return result;
    }
}
