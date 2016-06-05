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
			String qTitle = reader.readLine();
			String qContent = reader.readLine();
			while((qTitle != null) && (qContent != null)) {
				questions.put(qTitle, qContent);
				writer = new BufferedWriter(new FileWriter("resource/" + qTitle + ".txt", false));
				writer.write(qTitle + ": " + qContent + "\n");
				writer.close();
				qTitle = reader.readLine();
				qContent = reader.readLine();
			}
			reader.close();
		}
		catch (IOException e) {
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
}
