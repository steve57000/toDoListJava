package org.example;

import java.time.LocalDate;
import java.util.Objects;

public class DatedTask extends Task {
    private LocalDate dueDate;

    public DatedTask(String title, String description, User user, LocalDate dueDate) {
        super(title, description, user);
        this.dueDate = dueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DatedTask)) return false;
        if (!super.equals(o)) return false;
        DatedTask that = (DatedTask) o;
        return Objects.equals(dueDate, that.dueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dueDate);
    }

    @Override
    public String toString() {
        return "DatedTask{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", done=" + isDone() +
                ", user=" + getUser() +
                ", dueDate=" + dueDate +
                '}';
    }
}
