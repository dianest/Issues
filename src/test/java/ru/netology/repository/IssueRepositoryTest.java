package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;

import java.util.*;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

class IssueRepositoryTest {

    Issue first = new Issue(
            "Креш при открытии страницы",
            1,
            "Pavel",
            newHashSet("a", "b", "c"),
            "Oleg",
            true);

    Issue second = new Issue(
            "Бесконечный лоадер",
            2,
            "Andrey",
            newHashSet("a", "d", "e"),
            "Oleg",
            true);

    Issue third = new Issue(
            "Кнопка задизейблена",
            3,
            "Irina",
            newHashSet(),
            "Andrey",
            false);

    Issue fourth = new Issue(
            "Опечатка",
            4,
            "Pavel",
            newHashSet("a"),
            "Sasha",
            true);

    @Test
    void add() {
        IssueRepository repository = new IssueRepository();
        repository.add(fourth);
        List<Issue> expected = Arrays.asList(fourth);
        assertThat(repository.findAll(), is(expected));
    }

    @Test
    void findAll() {
        IssueRepository repository = new IssueRepository();
        repository.add(first);
        repository.add(second);
        repository.add(fourth);
        List<Issue> expected = Arrays.asList(first, second, fourth);
        assertThat(repository.findAll(), is(expected));
    }

    @Test
    void updateStatus() {
        IssueRepository repository = new IssueRepository();
        repository.add(third);
        repository.updateStatus(3, true);
        assertTrue(third.isOpen());

        repository.updateStatus(10, false);
        assertTrue(third.isOpen());

    }

    private static Set<String> newHashSet(String... objs) {
        Set<String> set = new HashSet<String>();
        Collections.addAll(set, objs);
        return set;
    }
}