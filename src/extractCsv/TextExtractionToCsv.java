package extractCsv;

import static java.nio.charset.StandardCharsets.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import utils.FileIO;

public class TextExtractionToCsv {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fpTrainingProjectList="G:\\gitAlon18Projects\\stars_analytics\\downloaded_train.txt";
		String fpTestProjectList="G:\\gitAlon18Projects\\stars_analytics\\downloaded_test.txt";
		
		String fopTrainingData="G:\\gitAlon18Projects\\starPredictCodeInfos\\";
		
		String fopCsvStar="G:\\gitAlon18Projects\\stars_analytics\\csvs\\";
		String fpOutTextTrain=fopCsvStar+"text_train.txt";
		String fpOutTextTest=fopCsvStar+"text_test.txt";
		
		
		String[] listNameTrain=FileIO.readStringFromFile(fpTrainingProjectList).split("\n");
		String[] listNameTest=FileIO.readStringFromFile(fpTestProjectList).split("\n");
		JSONParser parser = new JSONParser();
		
		FileIO.writeStringToFile("", fpOutTextTest);
		for(int i=0;i<listNameTest.length;i++) {
			System.out.println("begin "+i);
			try {
				String fpASTInfo=fopTrainingData+listNameTest[i]+"\\ast.txt";
				String strAST=FileIO.readStringFromFile(fpASTInfo).replaceAll("\r\n", " ").replaceAll("\n", " ").replaceAll(";", " ").replaceAll(",", " ").replaceAll("\\s+", " ").trim();
				String fpCodeInfo=fopTrainingData+listNameTest[i]+"\\code-abstract.txt";
				String strCode=FileIO.readStringFromFile(fpCodeInfo).replaceAll("\r\n", " ").replaceAll("\n", " ").replaceAll(";", " ").replaceAll(",", " ").replaceAll("\\s+", " ").trim();
				byte[] ptext = strCode.getBytes(ISO_8859_1); 
				strCode = new String(ptext, UTF_8); 
				String contentLine=listNameTest[i]+","+strAST+","+strCode+"\n";
		        FileIO.appendStringToFile(contentLine, fpOutTextTest);
			} catch(Exception ex) {
				System.out.println(i+" error: "+ex.getMessage());
				ex.printStackTrace();
			}
//			break;
		}
		
		FileIO.writeStringToFile("", fpOutTextTrain);
		for(int i=0;i<listNameTrain.length;i++) {
			System.out.println("begin "+i);
			try {
				String fpASTInfo=fopTrainingData+listNameTrain[i]+"\\ast.txt";
				String strAST=FileIO.readStringFromFile(fpASTInfo).replaceAll("\r\n", " ").replaceAll("\n", " ").replaceAll(";", " ").replaceAll(",", " ").replaceAll("\\s+", " ").trim();
				String fpCodeInfo=fopTrainingData+listNameTrain[i]+"\\code-abstract.txt";
				String strCode=FileIO.readStringFromFile(fpCodeInfo).replaceAll("\r\n", " ").replaceAll("\n", " ").replaceAll(";", " ").replaceAll(",", " ").replaceAll("\\s+", " ").trim();
				byte[] ptext = strCode.getBytes(ISO_8859_1); 
				strCode = new String(ptext, UTF_8); 
//				strCode.getBytes("UTF8");
				String contentLine=listNameTrain[i]+","+strAST+","+strCode+"\n";
		        FileIO.appendStringToFile(contentLine, fpOutTextTrain);
			} catch(Exception ex) {
				System.out.println(i+" error: "+ex.getMessage());
				ex.printStackTrace();
			}
//			break;
		}
		
		
		
		
		
	}

}
