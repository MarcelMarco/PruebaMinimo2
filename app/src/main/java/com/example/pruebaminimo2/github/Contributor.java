package com.example.pruebaminimo2.github;

public class Contributor {
    public final String login;


    public Contributor(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public String toString() {
        return login;
    }
}
