package org.jnosql.artemis.demo.se.mongodb.project;

public class App {
    private static App ourInstance = new App();

    public static App getInstance() {
        return ourInstance;
    }

    private App() {
    }
}
