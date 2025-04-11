package ru.vadim.http.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;

public class DatagramRunner {
    public static void main(String[] args) throws IOException {

        var inetAddress = Inet4Address.getByName("localhost");

        try (var datagramSocket = new DatagramSocket()) {
            byte[] bytes = "Hello from udp client".getBytes();
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, inetAddress, 7777);
            datagramSocket.send(packet);
        }
    }
}
