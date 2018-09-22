package proactivechecker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import parser.ast.ModulesFile;
import parser.ast.PropertiesFile;
import prism.Prism;
import prism.PrismDevNullLog;
import prism.PrismException;
import prism.PrismLangException;
import prism.PrismLog;
import prism.Result;
import proactivechecker.auxiliary.Utility;
import proactivechecker.checker.CheckerModel;
import proactivechecker.checker.CheckerProperty;

public class ProactiveChecker {
	
	Prism prism;
	
	ModulesFile modulesFile;
	PropertiesFile propertiesFile;
	
	private String modelFileName;
	private String propertiesFileName;
	private String resultFileName;

	CheckerModel checkerModel;
	CheckerProperty checkerProperty;
	
	public static void main(String[] args) {
		
		ProactiveChecker proactiveChecker = new ProactiveChecker();
		
		proactiveChecker.modelFileName = Utility.getProperty("MODEL_FILE","models/marshaller.pm");
		proactiveChecker.propertiesFileName = Utility.getProperty("PROPERTIES_FILE","models/marshaller.pctl");
		proactiveChecker.resultFileName = Utility.getProperty("OUTPUT_FILE", "res/result");
		
		proactiveChecker.initialize();
		
		proactiveChecker.execute();
		
	}

	private void execute() {
		
		FileWriter file = null;
		try {
			file = new FileWriter(resultFileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter writerFile = new PrintWriter(file);
		
		System.out.println(propertiesFile.getPropertyObject(0));
		Result result = null;
		try {
			result = prism.modelCheck(propertiesFile, propertiesFile.getPropertyObject(0));
		} catch (PrismLangException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PrismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(result.getResult());
		
		writerFile.printf("%s = %s %n", propertiesFile.getPropertyObject(0), result.getResult());
		writerFile.close();
		prism.closeDown();
		
	}

	private void initialize() {
		
		checkerModel = new CheckerModel();
		checkerProperty = new CheckerProperty();
		PrismLog mainlog = new PrismDevNullLog();
		prism = new Prism(mainlog);
		
		try {
			prism.initialise();
			modulesFile = prism.parseModelFile(new File(modelFileName));
			
			if(!modulesFile.getUndefinedConstants().isEmpty()) {	
				this.checkerModel.configure(modulesFile);
			}
			
			prism.loadPRISMModel(modulesFile);
			
			propertiesFile = prism.parsePropertiesFile(modulesFile, new File(propertiesFileName));
			
			if(!propertiesFile.getUndefinedConstantsUsedInProperty(propertiesFile.getPropertyObject(0)).isEmpty()) {
				this.checkerProperty.configure(propertiesFile);
			}
			
		} catch (PrismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
