package szoftechtutor;

import java.awt.Point;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SerialServer extends Network {

	private ServerSocket serverSocket = null;
	private Socket clientSocket = null;
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;

	SerialServer(Control c) {
		super(c);
	}

	private class ReceiverThread implements Runnable {

		public void run() {
			try {
				ctrl.setNetworkGame(true);
				System.out.println("Waiting for Client");
				ctrl.setNetworkMessage("Waiting for Client");
				clientSocket = serverSocket.accept();
				System.out.println("Client connected.");
				ctrl.setNetworkMessage("Client connected! \n Click to start game!");
				//super.wait(1000);
				ctrl.startGameEn();
			} catch (IOException e) {
				System.err.println("Accept failed.");
				disconnect();
				return;
			}

			try {
				out = new ObjectOutputStream(clientSocket.getOutputStream());
				in = new ObjectInputStream(clientSocket.getInputStream());
				out.flush();
			} catch (IOException e) {
				System.err.println("Error while getting streams.");
				disconnect();
				return;
			}

			try {
				while (true) {
					int received = (int) in.readObject();
					ctrl.statsReceived(received);
					//ctrl.startNetworkGame();
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				System.err.println("Client disconnected!");
			} finally {
				disconnect();
			}
		}
	}

	@Override
	void connect(String ip) {
		disconnect();
		try {
			serverSocket = new ServerSocket(10007);

			Thread rec = new Thread(new ReceiverThread());
			rec.start();
		} catch (IOException e) {
			System.err.println("Could not listen on port: 10007.");
		}
	}

	@Override
	void send(int p) {
		if(p==(-10)){
			System.out.println("Sleep!");
			return;
		}
		if (out == null)
			return;
		System.out.println("Sending score: " + p + " to Client");
		try {
			out.writeObject(p);
			out.flush();
		} catch (IOException ex) {
			System.err.println("Send error.");
		}
	}

	@Override
	void disconnect() {
		try {
			if (out != null)
				out.close();
			if (in != null)
				in.close();
			if (clientSocket != null)
				clientSocket.close();
			if (serverSocket != null)
				serverSocket.close();
		} catch (IOException ex) {
			Logger.getLogger(SerialServer.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}
}
