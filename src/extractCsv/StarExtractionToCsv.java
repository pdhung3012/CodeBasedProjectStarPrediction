package extractCsv;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import utils.FileIO;

public class StarExtractionToCsv {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fpTrainingProjectList="G:\\gitAlon18Projects\\stars_analytics\\downloaded_train.txt";
		String fpTestProjectList="G:\\gitAlon18Projects\\stars_analytics\\downloaded_test.txt";
		
		String fopTrainingData="G:\\gitAlon18Projects\\stars_analytics\\projectInfos_train\\";
		String fopTestData="G:\\gitAlon18Projects\\stars_analytics\\projectInfos_test\\";
		
		String fopCsvStar="G:\\gitAlon18Projects\\stars_analytics\\csvs\\";
		String fpOutStarTrain=fopCsvStar+"star_train.csv";
		String fpOutStarTest=fopCsvStar+"star_test.csv";
		
		
		String[] listNameTrain=FileIO.readStringFromFile(fpTrainingProjectList).split("\n");
		String[] listNameTest=FileIO.readStringFromFile(fpTestProjectList).split("\n");
		JSONParser parser = new JSONParser();
		
		FileIO.writeStringToFile("", fpOutStarTrain);
		for(int i=0;i<listNameTrain.length;i++) {
			try {
				String fpStarInfo=fopTrainingData+listNameTrain[i]+".txt";
				String strStarRawData=FileIO.readStringFromFile(fpStarInfo);
				Object obj = parser.parse(strStarRawData);
		        JSONObject root = (JSONObject)obj;
		        String created_at=(String)root.get("created_at");
		        long starCount=(long)root.get("stargazers_count");
		        String contentLine=listNameTrain[i]+","+created_at+","+starCount+"\n";
		        FileIO.appendStringToFile(contentLine, fpOutStarTrain);
			} catch(Exception ex) {
				System.out.println(i+" error: "+ex.getMessage());
				ex.printStackTrace();
			}
//			break;
		}
		
		FileIO.writeStringToFile("", fpOutStarTest);
		for(int i=0;i<listNameTest.length;i++) {
			try {
				String fpStarInfo=fopTestData+listNameTest[i]+".txt";
				String strStarRawData=FileIO.readStringFromFile(fpStarInfo);
				Object obj = parser.parse(strStarRawData);
		        JSONObject root = (JSONObject)obj;
		        String created_at=(String)root.get("created_at");
		        long starCount=(long)root.get("stargazers_count");
		        String contentLine=listNameTest[i]+","+created_at+","+starCount+"\n";
		        FileIO.appendStringToFile(contentLine, fpOutStarTrain);
			} catch(Exception ex) {
				System.out.println(i+" error: "+ex.getMessage());
				ex.printStackTrace();
			}
//			break;
		}
		
		
		
	}

}
