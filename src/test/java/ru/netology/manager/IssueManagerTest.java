package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class IssueManagerTest {
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

    Issue fifth = new Issue(
            "Кот в мешке",
            5,
            "Gavrila",
            newHashSet("m"),
            "Olga",
            false);

    Issue six = new Issue(
            "Собака на сене",
            6,
            "Gennadij",
            newHashSet("k"),
            "Maksim",
            true);

    @Test
    void add() {
        IssueRepository repository = new IssueRepository();
        repository.add(fourth);
        IssueManager manager = new IssueManager(repository);
        List<Issue> expected = Arrays.asList(fourth);
        assertThat(manager.findAll(), is(expected));
    }

    @Test
    void getOpenIssues() {
        IssueRepository repository = new IssueRepository();
        repository.add(first);
        repository.add(second);
        repository.add(third);
        repository.add(fourth);
        IssueManager manager = new IssueManager(repository);
        List<Issue> expected = Arrays.asList(first, second, fourth);
        assertThat(manager.getOpenIssues(), is(expected));
    }

    @Test
    void getClosedIssues() {
        IssueRepository repository = new IssueRepository();
        repository.add(first);
        repository.add(second);
        repository.add(third);
        repository.add(fourth);
        IssueManager manager = new IssueManager(repository);
        List<Issue> expected = Arrays.asList(third);
        assertThat(manager.getClosedIssues(), is(expected));
    }

    @Test
    void filterByAuthor() {
        IssueRepository repository = new IssueRepository();
        repository.add(first);
        repository.add(second);
        repository.add(third);
        repository.add(fourth);
        IssueManager manager = new IssueManager(repository);
        List<Issue> expected = Arrays.asList(first, fourth);
        assertThat(manager.filterByAuthor("Pavel"), is(expected));
    }

    @Test
    void filterByAssignee() {
        IssueRepository repository = new IssueRepository();
        repository.add(first);
        repository.add(second);
        repository.add(third);
        repository.add(fourth);
        IssueManager manager = new IssueManager(repository);
        List<Issue> expected = Arrays.asList(first, second);
        assertThat(manager.filterByAssignee("Oleg"), is(expected));
    }

    @Test
    void filterByLabel() {
        IssueRepository repository = new IssueRepository();
        repository.add(first);
        repository.add(second);
        repository.add(third);
        repository.add(fourth);
        IssueManager manager = new IssueManager(repository);
        List<Issue> expected = Arrays.asList(first, second, fourth);
        assertThat(manager.filterByLabel("a"), is(expected));
    }

    @Test
    void sortById() {
        IssueRepository repository = new IssueRepository();
        repository.add(third);
        repository.add(first);
        repository.add(fourth);
        repository.add(second);
        IssueManager manager = new IssueManager(repository);
        List<Issue> expected = Arrays.asList(first, second, third, fourth);
        assertThat(manager.sortBy(Comparator.comparingInt(Issue::getId)), is(expected));
    }

    @Test
    void openIssue() {
        IssueRepository repository = new IssueRepository();
        repository.add(fifth);
        IssueManager manager = new IssueManager(repository);
        manager.openIssue(5);
        assertTrue(fifth.isOpen());
    }

    @Test
    void closeIssue() {
        IssueRepository repository = new IssueRepository();
        repository.add(six);
        IssueManager manager = new IssueManager(repository);
        manager.closeIssue(6);
        assertFalse(six.isOpen());
    }

    @Test
    void findAll() {
        IssueRepository repository = new IssueRepository();
        repository.add(first);
        repository.add(second);
        repository.add(fourth);
        IssueManager manager = new IssueManager(repository);
        List<Issue> expected = Arrays.asList(first, second, fourth);
        assertThat(manager.findAll(), is(expected));

    }

    private static Set<String> newHashSet(String... objs) {
        Set<String> set = new HashSet<String>();
        Collections.addAll(set, objs);
        return set;
    }


}