package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class IssueManager {
    final private IssueRepository repository;

    public IssueManager(IssueRepository repository) {
        this.repository = repository;
    }

    public void add(Issue issue) {
        repository.add(issue);
    }

    public List<Issue> getOpenIssues() {
        return filterBy(new Predicate<Issue>() {
            @Override
            public boolean test(Issue issue) {
                return issue.isOpen();
            }
        });
    }

    public List<Issue> getClosedIssues() {
        return filterBy(new Predicate<Issue>() {
            @Override
            public boolean test(Issue issue) {
                return !issue.isOpen();
            }
        });
    }

    public List<Issue> filterByAuthor(String author) {
        return filterBy(new Predicate<Issue>() {
            @Override
            public boolean test(Issue issue) {
                return issue.getIssueAuthor().equals(author);
            }
        });
    }

    public List<Issue> filterByAssignee(String assignee) {
        return filterBy(new Predicate<Issue>() {
            @Override
            public boolean test(Issue issue) {
                return issue.getAssignee().equals(assignee);
            }
        });
    }

    public List<Issue> filterByLabel(String label) {
        return filterBy(new Predicate<Issue>() {
            @Override
            public boolean test(Issue issue) {
                return issue.getLabel().contains(label);
            }
        });
    }

    private List<Issue> filterBy(Predicate<Issue> predicate) {
        final List<Issue> result = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (predicate.test(issue)) {
                result.add(issue);
            }
        }
        return result;
    }

    public List<Issue> sortBy(Comparator<Issue> comparator){
        final List<Issue> result = new ArrayList<>(repository.findAll());
        result.sort(comparator);

        return result;
    }

    public void openIssue(int id){
        repository.updateStatus(id, true);
    }

    public void closeIssue(int id){
        repository.updateStatus(id, false);
    }

    public List<Issue> findAll(){
        return repository.findAll();
    }

}
