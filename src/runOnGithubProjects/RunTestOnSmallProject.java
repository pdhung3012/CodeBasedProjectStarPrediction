package runOnGithubProjects;

import constanct.PathConstanct;
import utils.StanfordLemmatizer;
import visitor.CodeInfoGenerator;

public class RunTestOnSmallProject {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		String inPath=PathConstanct.PATH_INPUT_PRO_LOCAL;
		String outPath=PathConstanct.PATH_OUTPUT_PRO_LOCAL;
//		StanfordLemmatizer lemm=new StanfordLemmatizer();
		CodeInfoGenerator mcsg=new CodeInfoGenerator(inPath,PathConstanct.NumberOfDocsExtracted);
		mcsg.generateSequences(outPath);
		mcsg.generateAlignment(true);
	}

}
