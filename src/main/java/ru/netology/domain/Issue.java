package ru.netology.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor

public class Issue {
    String name;
    int id;
    String issueAuthor;
    Set<String> label;
    String creationDate;
    String modificationDate;
    String milestone;
    String assignee;
    boolean open;
    String project;
    List<String> comment;
    List<String> linkedPullRequest;
    String notificationStatus;
    boolean locked;
    boolean pinned;

    public Issue(String name, int id, String issueAuthor, Set<String> label, String assignee, boolean open) {
        this.name = name;
        this.id = id;
        this.issueAuthor = issueAuthor;
        this.label = label;
        this.assignee = assignee;
        this.open = open;
    }
}
