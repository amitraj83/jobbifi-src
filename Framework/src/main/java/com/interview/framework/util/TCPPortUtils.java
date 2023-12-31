package com.interview.framework.util;

import java.io.IOException;
import java.net.ServerSocket;


public class TCPPortUtils {

  public static int getFreePort() {
    ServerSocket socket = null;
    try {
      socket = new ServerSocket(0);
      socket.setReuseAddress(true);
      int port = socket.getLocalPort();
      try {
        socket.close();
      } catch (IOException e) {
        // Ignore IOException on close()
      }
      return port;
    } catch (IOException e) {
    } finally {
      if (socket != null) {
        try {
          socket.close();
        } catch (IOException e) {
        }
      }
    }
    throw new IllegalStateException(
        "Could not find a free TCP/IP port to start embedded Jetty HTTP Server on");
  }
}
