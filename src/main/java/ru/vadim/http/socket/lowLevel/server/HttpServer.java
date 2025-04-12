package ru.vadim.http.socket.lowLevel.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class HttpServer {
    private final int port;

    public HttpServer(int port) {
        this.port = port;
    }

    public void run() {
        try {
            var server = new ServerSocket(port);
            var socket = server.accept();
            processSocket(socket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void processSocket(Socket socket) {
        try (var inputStream = new DataInputStream(socket.getInputStream());
             var dataOutputStream = new DataOutputStream(socket.getOutputStream())) {

            // step 1 handle request
            System.out.println("Request: " + new String(inputStream.readNBytes(400)));

            // step 2 handle response
            byte[] body = Files.readAllBytes(Path.of("resources", "example.html"));
            var headers = """
                    HTTP/1.1 200 OK
                    content-type: text/html
                    content-length: %s
                    """.formatted(body.length).getBytes();
            dataOutputStream.write(headers);
            dataOutputStream.write(System.lineSeparator().getBytes());
            dataOutputStream.write(body);
        } catch (IOException e) {
            // TODO: 2/27/21 log error message
            e.printStackTrace();
        }
    }
}
