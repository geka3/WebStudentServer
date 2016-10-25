package net.ukr.geka3;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class HttpProcessor {
	private String header = "<HTML><BODY>";
	private String bottom = "</BODY></HTML>";
	// private String request;
	// private String httpParametr;
	private Map<String, String> mapGet = null;
	// private boolean get = false;
	// private boolean post = false;
	private Init init;

	private String filePathAddress;

	public HttpProcessor() {

	}

	public HttpProcessor(Init init) {
		this.init = init;
	}

	public String getResponse(String request) {
		String getParam = getGetParams(request); // return subString between GET
													// / and HTTP
		setGetParam(getParam); // set global variable: path and map
		String result = null;
		// String reternedData = null;

		// change add or del or just show
		if (filePathAddress.equalsIgnoreCase("add")) {
			add();// add new student initial data is in mapGet
		} else if (filePathAddress.equalsIgnoreCase("del")) {
			del();// del student by hashCode
		} else {

		}
		// get table with students
		result = header + getHtmlTableGroup(init.getGr()) + bottom;
		return result;

	}

	private void del() {
		// TODO Auto-generated method stub

		int hashCodeToDel;
		try {
			System.out.println("test" + mapGet.get("hashCode") + "test");
			hashCodeToDel = Integer.parseInt(mapGet.get("hashCode"));
			init.getGr().getArrayStudents().remove(hashCodeToDel);

		} catch (java.lang.NumberFormatException e) {
			System.out.println("parseException");
			e.printStackTrace();
		}
		init.saveGroup();

	}

	private void add() {
		// TODO Auto-generated method stub
		Student st = new Student(mapGet.get("name"), mapGet.get("surName"),
				mapGet.get("age"));
		init.getGr().add(st);
		init.saveGroup();
	}

	public static String getHtmlTableGroup(Group gr) {
		StringBuilder sb = new StringBuilder();
		sb.append("Group Name " + gr.getName() + "<BR>");
		sb.append("<TABLE cellspacing=\"2\" border=\"1\" cellpadding=\"5\" width=\"600\">");
		sb.append("<TR><TH>Name</TH><TH>SurName</TH><TH>Age</TH><TH><form action = show><input type=\"submit\" value=\"Update\"></form></TH></TR>");
		HashMap<Integer, Student> arrayStudents = gr.getArrayStudents();
		for (Entry<Integer, Student> st : arrayStudents.entrySet()) {
			sb.append("<TR><TD>"
					+ st.getValue().name
					+ "</TD>"
					+ "<TD>"
					+ st.getValue().surName
					+ "</TD>"
					+ "<TD>"
					+ st.getValue().age
					+ "</TD>"
					+ "<TD><form action = del><input type=\"hidden\" name=\"hashCode\" value=\""
					+ st.getKey()
					+ "\"><input type=\"submit\" value=\"Del\"></form>"
					+ "</TD>" + "</TR>");
		}
		sb.append("</TABLE>");

		String formAdd = "<form action=\"add\">" + "Name: <br>"
				+ "<p><input type=\"text\" name=\"name\"></p>"
				+ "SurName: <br>"
				+ "<p><input type=\"text\" name=\"surName\"></p>" + "Age: <br>"
				+ "<p><input type=\"text\" name=\"age\">"
				+ "<p><input type=\"submit\" value=\"add\"></p>" + "</form>";

		sb.append(formAdd);

		return sb.toString();
	}

	// not use return get or post
	// public String GetOrPost(String request){
	// if(request.substring(0, 3).equalsIgnoreCase("GET")){
	// get = true;
	// post = false;
	// return "GET";
	// }
	// if(request.substring(0, 3).equalsIgnoreCase("POST")){
	// post = true;
	// get = false;
	//
	// return "POST";
	// }
	// return null;
	// }

	// set param to public variable
	// set String filePathAddress
	// set Map K V
	public void setGetParam(String request) {

		if (request.indexOf('?') <= 0) {
			filePathAddress = request;
			return;
		}

		String[] get = request.split("[?]");
		this.filePathAddress = get[0];
		if (get.length > 1) {
			this.mapGet = getGetKeyValue(get[1]);
		} else {
			this.mapGet = null;
		}

	}

	// get GET request from whole request between GET / and HTTP
	public String getGetParams(String request) {

		// System.out.println();

		Scanner sc = new Scanner(request);
		String temp;
		String get = null;
		for (; (temp = sc.nextLine()) != null;) {
			if (temp.indexOf("GET /") == 0) {
				int start = 5;
				int end = temp.indexOf(" HTTP/");
				get = temp.substring(start, end);
				break;
			}

		}
		return get;
	}

	// return Map from param
	public Map getGetKeyValue(String param) {
		Map<String, String> result = new HashMap<>();

		String[] arrayParam = param.split("[&]");
		for (String keyValue : arrayParam) {
			String[] arrayKV = keyValue.split("=");
			if (arrayKV.length == 0) {
				continue;
			}
			if (arrayKV.length == 1) {
				result.put(arrayKV[0], "noData");
				System.out.println("key " + arrayKV[0]);
				continue;

			}

			result.put(arrayKV[0], arrayKV[1]);
			System.out.println("key " + arrayKV[0] + " value " + arrayKV[1]);

		}

		return result;

	}

}
