import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TexRegex {

	public static String EXAMPLE_TEST = "Eliomar , Santana de . Jesus";
	
	public static void main(String[] args) {
		
		String pattern = "(\\w)(\\s+)([\\.,])";
		System.out.println(EXAMPLE_TEST.replaceAll(pattern, "$1$3"));
		
		System.out.println(countCitations(EXAMPLE_TEST));

	}
	
	public static Object countCitations(String texto) {
		int count = 0;
		String regex = "(\\w)(\\s+)([\\,.])";
	    

		Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(texto);
		System.out.println(matcher);
		while (matcher.find()) {
			count++;
		}
		return count;

	}

}
