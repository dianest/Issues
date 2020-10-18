package ru.netology.repository;

import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.List;

public class IssueRepository {
    final List<Issue> issues = new ArrayList<Issue>();

    public void add(Issue item) {
        issues.add(item);
    }

    public List<Issue> findAll() {
        return issues;
    }

    public void updateStatus(int id, boolean status) {
        for (Issue issue : issues) {
            if (issue.getId() == id) {
                issue.setOpen(status);
            }
        }
    }

}
