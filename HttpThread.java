package net.ukr.geka3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HttpThread implements Runnable {

	private Socket soc = null;
	Init init;

	public HttpThread(Socket soc, Init init) {
		this.soc = soc;
		this.init = init;
	}

	@Override
	//start one Thread
	public void run() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(
				soc.getInputStream()))) {
			//read all lines from Browser
			//while no  get two empty \n\r
			String in;
			int numFreeStrings = 0;
			for (; (in = br.readLine()) != null;) {
				sb.append(in + System.lineSeparator());
				//System.out.println(in);
				if (in.equals("")) {
					numFreeStrings++;
				} else {
					numFreeStrings = 0;
				}

				if (numFreeStrings == 1) {
					break;
				}

			}

			HttpProcessor hp = new HttpProcessor(init);
			String answer = hp.getResponse(sb.toString());
			//System.out.println(answer);
			sendToBrowser(answer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void sendToBrowser(String toBrowser) {
		try (PrintWriter pw = new PrintWriter(soc.getOutputStream())) {
			System.out.println("i send browser " + System.lineSeparator()
					+ toBrowser);
			pw.write(toBrowser);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
