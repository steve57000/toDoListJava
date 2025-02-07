package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToDoListJava {

    private static final List<Task> tasks = new ArrayList<>();
    // Pour cet exemple, nous créons un utilisateur unique.
    private static final User currentUser = new User("Alice", "Saglisse");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            afficherMenu();
            System.out.print("Choix : ");
            String choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    listerTasks();
                    break;
                case "2":
                    creerTask(scanner);
                    break;
                case "3":
                    supprimerTask(scanner);
                    break;
                case "4":
                    modifierTask(scanner);
                    break;
                case "5":
                    exit = true;
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
        scanner.close();
    }

    private static void afficherMenu() {
        System.out.println("\n=== MENU TODO LIST ===");
        System.out.println("1. Lister les tâches");
        System.out.println("2. Créer une tâche");
        System.out.println("3. Supprimer une tâche");
        System.out.println("4. Modifier une tâche");
        System.out.println("5. Quitter");
    }

    private static void listerTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Aucune tâche à afficher.");
        } else {
            System.out.println("Liste des tâches :");
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }

    private static void creerTask(Scanner scanner) {
        System.out.print("Titre : ");
        String title = scanner.nextLine();
        System.out.print("Description : ");
        String description = scanner.nextLine();

        System.out.print("Voulez-vous ajouter une date d'échéance ? (o/n) : ");
        String reponse = scanner.nextLine();
        if (reponse.equalsIgnoreCase("o")) {
            System.out.print("Date d'échéance (format AAAA-MM-JJ) : ");
            String dateStr = scanner.nextLine();
            try {
                LocalDate dueDate = LocalDate.parse(dateStr);
                Task t = new DatedTask(title, description, currentUser, dueDate);
                tasks.add(t);
                System.out.println("Tâche avec échéance créée avec succès.");
            } catch (DateTimeParseException e) {
                System.out.println("Format de date invalide. Tâche non créée.");
            }
        } else {
            Task t = new Task(title, description, currentUser);
            tasks.add(t);
            System.out.println("Tâche créée avec succès.");
        }
    }

    private static void supprimerTask(Scanner scanner) {
        System.out.print("Entrez l'id de la tâche à supprimer : ");
        String idStr = scanner.nextLine();
        try {
            long id = Long.parseLong(idStr);
            Task taskASupprimer = null;
            for (Task task : tasks) {
                if (task.getId() == id) {
                    taskASupprimer = task;
                    break;
                }
            }
            if (taskASupprimer != null) {
                tasks.remove(taskASupprimer);
                System.out.println("Tâche supprimée avec succès.");
            } else {
                System.out.println("Aucune tâche trouvée avec cet id.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Format d'id invalide.");
        }
    }

    private static void modifierTask(Scanner scanner) {
        System.out.print("Entrez l'id de la tâche à modifier : ");
        String idStr = scanner.nextLine();
        try {
            long id = Long.parseLong(idStr);
            Task taskAModifier = null;
            for (Task task : tasks) {
                if (task.getId() == id) {
                    taskAModifier = task;
                    break;
                }
            }
            if (taskAModifier == null) {
                System.out.println("Aucune tâche trouvée avec cet id.");
                return;
            }
            System.out.println("Modification de la tâche : " + taskAModifier);

            System.out.print("Nouveau titre (laisser vide pour ne pas modifier) : ");
            String nouveauTitre = scanner.nextLine();
            if (!nouveauTitre.trim().isEmpty()) {
                taskAModifier.setTitle(nouveauTitre);
            }

            System.out.print("Nouvelle description (laisser vide pour ne pas modifier) : ");
            String nouvelleDesc = scanner.nextLine();
            if (!nouvelleDesc.trim().isEmpty()) {
                taskAModifier.setDescription(nouvelleDesc);
            }

            System.out.print("Marquer comme terminée ? (o/n, laisser vide pour ne rien modifier) : ");
            String doneStr = scanner.nextLine();
            if (doneStr.equalsIgnoreCase("o")) {
                taskAModifier.setDone(true);
            } else if (doneStr.equalsIgnoreCase("n")) {
                taskAModifier.setDone(false);
            }

            // Si la tâche est une DatedTask, on peut modifier la date d'échéance
            if (taskAModifier instanceof DatedTask) {
                System.out.print("Nouvelle date d'échéance (format AAAA-MM-JJ, laisser vide pour ne pas modifier) : ");
                String dateStr = scanner.nextLine();
                if (!dateStr.trim().isEmpty()) {
                    try {
                        LocalDate newDueDate = LocalDate.parse(dateStr);
                        ((DatedTask) taskAModifier).setDueDate(newDueDate);
                    } catch (DateTimeParseException e) {
                        System.out.println("Format de date invalide. Date non modifiée.");
                    }
                }
            }

            System.out.println("Tâche modifiée : " + taskAModifier);

        } catch (NumberFormatException e) {
            System.out.println("Format d'id invalide.");
        }
    }
}
