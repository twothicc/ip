package duke.task;

import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public LocalDate getTime() {
        return null;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getTaskType() {
        return null;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            Task other = (Task) obj;
            return (this.isDone == other.getIsDone() &&
                    this.description.equals(other.getDescription()));
        }
        return false;
    }
}
