package com.example;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- To-Do List ---");
            System.out.println("1. Ajouter une tâche");
            System.out.println("2. Afficher les tâches");
            System.out.println("3. Marquer une tâche comme terminée");
            System.out.println("4. Quitter");
            System.out.print("Choisissez une option : ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Entrez le titre de la tâche : ");
                    String title = scanner.nextLine();
                    tasks.add(new Task(title));
                    System.out.println("Tâche ajoutée !");
                    break;
                case 2:
                    System.out.println("\n--- Liste des tâches ---");
                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        System.out.println((i + 1) + ". " + task);
                    }
                    break;
                case 3:
                    System.out.print("Entrez le numéro de la tâche à marquer comme terminée : ");
                    int taskNumber = scanner.nextInt();
                    if (taskNumber > 0 && taskNumber <= tasks.size()) {
                        tasks.get(taskNumber - 1).setCompleted(true);
                        System.out.println("Tâche marquée comme terminée !");
                    } else {
                        System.out.println("Numéro invalide !");
                    }
                    break;
                case 4:
                    System.out.println("Au revoir !");
                    scanner.close();
                    return;
                default:
                    System.out.println("Option invalide. Essayez encore.");
            }
        }
    }
}
