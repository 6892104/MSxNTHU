package questionSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Random;

public class QuestionOperator {
	private BufferedReader reader;
	private BufferedWriter writer;
	public HashMap<String, String> questions;
	public QuestionOperator() {
		try {
			reader = new BufferedReader(new FileReader(new File("resource/question_list.txt")));
			questions = new HashMap<String, String>();
			String qTitle = "";
			String qContent = "";
			String tmp = "";
			
			qTitle = reader.readLine();
			while((qTitle != null)) {
				writer = new BufferedWriter(new FileWriter("resource/" + qTitle + ".txt", false));
				writer.write(qTitle + ": " + qContent + "\n");
				writer.close();
				tmp = reader.readLine();
				while ((tmp != null) && (!tmp.substring(0, 8).equals("question"))) {
					qContent += tmp;
					tmp = reader.readLine();
				}
				questions.put(qTitle, qContent);
				qTitle = tmp;
				qContent = "";
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getQuestion(String fileName) {
		return questions.get(fileName);
	}

	public void writeAnswer(String fileName, String inputAns) {
		String result;
		switch(inputAns) {
			case "1":
				result = "非常不同意";
				break;
			case "2":
				result = "不同意";
				break;
case "3":
				result = "同意";
				break;
			case "4":
				result = "非常同意";
				break;
			default:
				return;
		}
		try {
			writer = new BufferedWriter(new FileWriter("resource/" + fileName + ".txt", true));
			writer.append(result + "\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void writeQuestion(String qContent) {
		int qNum = questions.size()+1;
		try {
			writer = new BufferedWriter(new FileWriter("resource/quetion_list.txt", true));
			writer.append("quetion" + qNum + "\n");
			writer.append(qContent + "\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
