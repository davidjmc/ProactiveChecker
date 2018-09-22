package proactivechecker.checker;

import java.util.List;

import parser.Values;
import parser.ast.PropertiesFile;
import prism.PrismLangException;

public class CheckerProperty {

	public void configure(PropertiesFile propertiesFile) {
		List<String> consts = propertiesFile.getUndefinedConstantsUsedInProperty(propertiesFile.getPropertyObject(0));
		String constName = consts.get(0);
		Values vals = new Values();
		vals.addValue(constName, new Integer(3));
		try {
			propertiesFile.setUndefinedConstants(vals);
		} catch (PrismLangException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
