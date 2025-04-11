package ru.vadim.http.socket.lowLevel.client;

import java.io.IOException;
import java.net.URL;

// через класс URL также можно обращаться и к файлам
public class UrlExample {

    public static void main(String[] args) throws IOException {
        URL url = new URL("file:/Users/vadimzakirov/IdeaProjects/http-servlets/src/main/java/ru/vadim/http/socket/lowLevel/udp/DatagramRunner.java");
        var urlConnection = url.openConnection();

        System.out.println(new String(urlConnection.getInputStream().readAllBytes()));
    }

    private static void checkGoogle() throws IOException {
        var url = new URL("https://www.google.com");
        var urlConnection = url.openConnection();
        urlConnection.setDoOutput(true); // если хотим передать пут запрос
        System.out.println();
    }
}
