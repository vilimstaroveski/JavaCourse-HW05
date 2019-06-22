package hr.fer.zemris.java.tecaj.hw5.filter;


import java.util.List;
import java.util.Optional;

import hr.fer.zamris.java.tecaj.hw5.operators.Equals;
import hr.fer.zemris.java.tecaj.hw5.db.ConditionalExpression;
import hr.fer.zemris.java.tecaj.hw5.db.StudentDatabase;
import hr.fer.zemris.java.tecaj.hw5.db.StudentRecord;
import hr.fer.zemris.java.tecaj.hw5.fields.Jmbag;
import hr.fer.zemris.java.tecaj.hw5.parser.Parser;
import hr.fer.zemris.java.tecaj.hw5.parser.ParserException;

/**
 * Class that executes users query input. 
 * 
 * @author Vilim Staroveški
 *
 */
public class QueryFilter implements IFilter {

	/**
	 * Private list of expressions that filter use for filtering database.
	 */
	private List<ConditionalExpression> expressions;
	
	/**
	 * String that can be null and is used for direct fetch of {@link StudentRecord} from {@link StudentDatabase}
 	 */
	private Optional<String> jmbag;
	
	/**
	 * Constructor that creates list of expressions from String that user has gave.
	 * @param userInput String entry given by user.
	 */
	public QueryFilter(String userInput) {

		Parser parser = new Parser(userInput);
		
		try {
			
			this.expressions = parser.parseLine();
			
		} catch(ParserException e) {
			
			System.err.println(e.getMessage());
			throw new IllegalArgumentException();
		}
		
		String jmbagIndex = null;
		if(parser.getNumberOfIndexing() == 1) {
			
			for(ConditionalExpression expression : expressions) {
				
				if(expression.getField() instanceof Jmbag && expression.getOperator() instanceof Equals && !expression.getLiteral().contains("*")) {
					
					jmbagIndex = expression.getLiteral();
				}
			}
		}
		this.jmbag = Optional.ofNullable(jmbagIndex);
		
	}
	
	@Override
	public boolean accepts(StudentRecord record) {

		for(ConditionalExpression expression : this.expressions) {
			
			if(expression.getOperator().satisfied(expression.getField().get(record), expression.getLiteral()) == false) {
				
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Returns String that can be null and is used for direct fetch of {@link StudentRecord} from {@link StudentDatabase}
	 * @return String that can be null and is used for direct fetch of {@link StudentRecord} from {@link StudentDatabase}
	 */
	public Optional<String> getJMBAG() {
		
		return jmbag;
	}
	
}
