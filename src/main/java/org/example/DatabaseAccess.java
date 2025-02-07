package org.example;

import java.util.ArrayList;

public class DatabaseAccess {

    protected static DatabaseAccess instance;

    protected ArrayList<Task> tasks;
    protected ArrayList<User> users;

    private void fillTasks() {
        tasks.add(new Task("Titre 1", "Description 1", users.get(0)));
        tasks.add(new Task("Titre 2", "Description 2", users.get(1)));
        tasks.add(new Task("Titre 3", "Description 3", users.get(2)));
        tasks.add(new Task("Titre 4", "Description 4", users.get(2)));
        tasks.add(new Task("Titre 5", "Description 5", users.get(2)));
        tasks.add(new Task("Titre 6", "Description 6", users.get(1)));
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public Boolean findTask(Task task) {
        return tasks.contains(task);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public Boolean findUser(User user) {
        return users.contains(user);
    }

    public User getUser(Long id) {
        for(User user : users) {
            if(user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public User getUser(String name) {
        for(User user : users) {
            if(user.getFirstName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    public Task getTask(Long id) {
        for(Task task : tasks) {
            if(task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }

    public Task getTask(String title) {
        for(Task task : tasks) {
            if(task.getTitle().equals(title)) {
                return task;
            }
        }
        return null;
    }

    private void fillUsers() {
        users.add(new User("Bob"));
        users.add(new User("Jacob"));
        users.add(new User("Paul"));
    }

    private DatabaseAccess() {
        tasks = new ArrayList<>();
        users = new ArrayList<>();
        fillUsers();
        fillTasks();
    }

    public static DatabaseAccess getInstance() {
        if (instance == null) {
            instance = new DatabaseAccess();
        }
        return instance;
    }
}


