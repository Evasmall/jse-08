package ru.evasmall.tm;

import ru.evasmall.tm.dao.ProjectDAO;
import ru.evasmall.tm.dao.TaskDAO;

import java.util.Scanner;

import static ru.evasmall.tm.constant.TerminalConst.*;

/**
 * Приложение вывода параметров к занятиям по JAVA
 */

public class App {

    private static final ProjectDAO projectDAO = new ProjectDAO();

    private static final TaskDAO taskDAO = new TaskDAO();

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(final String[] args) {
        run(args);
        displayWelcome();
        String command = "";
        while (!CMD_EXIT.equals(command)) {
            command = scanner.nextLine();
            run(command);
        }
    }

    private static void run(final String[] args) {
        if (args == null) return;
        if (args.length < 1) return;
        final String param = args[0];
        final int result = run(param);
        System.exit(result);
    }

    private static int run(final String param) {
        if (param == null || param.isEmpty()) return -1;
        switch (param) {
            case CMD_HELP: return displayHelp();
            case CMD_ABOUT: return displayAbout();
            case CMD_VERSION: return displayVersion();
            case CMD_EXIT: return displayExit();

            case CMD_PROJECT_CREATE: return createProject();
            case CMD_PROJECT_CLEAR: return clearProject();
            case CMD_PROJECT_LIST: return  listProject();

            case CMD_TASK_CREATE: return createTask();
            case CMD_TASK_CLEAR: return clearTask();
            case CMD_TASK_LIST: return  listTask();

            default: return displayError();
        }
    }

    private static int createProject() {
        System.out.println("[CREATE PROJECT]");
        System.out.println("[PLEASE ENTER PROJECT NAME:");
        final String name = scanner.nextLine();
        projectDAO.create(name);
        System.out.println("[OK]");
        return 0;
    }

    private static int clearProject() {
        System.out.println("[CLEAR PROJECT]");
        projectDAO.clear();
        System.out.println("[OK]");
        return 0;
    }

    private static int listProject() {
        System.out.println("[LIST PROJECT]");
        System.out.println(projectDAO.findAll());
        System.out.println("[OK]");
        return 0;
    }

    private static int createTask() {
        System.out.println("[CREATE TASK]");
        System.out.println("[PLEASE ENTER TASK NAME:");
        final String name = scanner.nextLine();
        taskDAO.create(name);
        System.out.println("[OK]");
        return 0;
    }

    private static int clearTask() {
        System.out.println("[CLEAR TASK]");
        taskDAO.clear();
        System.out.println("[OK]");
        return 0;
    }

    private static int listTask() {
        System.out.println("[LIST TASK]");
        System.out.println(taskDAO.findAll());
        System.out.println("[OK]");
        return 0;
    }

    private static int displayExit() {
        System.out.println("Terminate program. Goodbye!");
        return 0;
    }

    private static void displayWelcome() {
        System.out.println("*** WELCOME TO TASK MANAGER! ***");
    }

    private static int displayError() {
        System.out.println("ERROR! Unknown program argument.");
        return -1;
    }

    private static int displayHelp() {
        System.out.println("version - Display program version.");
        System.out.println("about - Display developer info.");
        System.out.println("help - Display list of terminal commands.");
        System.out.println("exit - Terminate console application.");
        System.out.println();
        System.out.println("project-list - Display list of projects.");
        System.out.println("project-create - Create new project by name.");
        System.out.println("project-clear - Remove all projects.");
        System.out.println();
        System.out.println("task-list - Display list of tasks.");
        System.out.println("task-create - Create new task by name.");
        System.out.println("task-clear - Remove all tasks.");

        return 0;
    }

    private static int  displayVersion() {
        System.out.println("1.0.0");
        return 0;
    }

    private static int  displayAbout() {
        System.out.println("Evgeniya Smolkina");
        System.out.println("smolkina_ev@nlmk.com");
        return 0;
    }

}