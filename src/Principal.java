import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Files;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jbibtex.ParseException;
import org.jbibtex.TokenMgrException;
import net.davidashen.text.Hyphenator; 

class Principal {

	
	public static void main(String[] args) throws TokenMgrException, ParseException, IOException {
		
		
		
		//String dados = new String(Files.readAllBytes(new File("C:\\Users\\eliom\\Documents\\tcc\\main.tex").toPath()),Charset.forName("UTF-8").displayName());

		//TESTAR ISSO
		//Charset cs = Charset.forName("ISO-8859-1"); 
		String dados = new String(Files.readAllBytes(new File("C:\\Users\\eliom\\Documents\\tcc\\main.tex").toPath()));
		//System.out.println(dados);
		//OU ISSO
		//Charset.forName("ISO-8859-1").decode(dados);
		
		Principal principal = new Principal();
		
		//BufferedReader arqIn = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\eliom\\Documents\\tcc\\main.tex"), "UTF-8"));
		
		int count = 0;
		String regex = "\\\\input\\{(?<conteudo>.*?)\\}";

		String title = principal.charset(dados).replace("\\", "\\\\");//escapando as barras no arquivo
		
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher comparator = pattern.matcher(title);
		
		while (comparator.find()) {
			title = comparator.group("conteudo");
			System.out.println(title);
			String conteudo = new String(Files.readAllBytes(new File("C:\\Users\\eliom\\Documents\\tcc\\"+title).toPath()));

			
			String t = principal.charset(conteudo).replaceAll("%", "");
			String t2 = t.replaceAll("&", "");
			org.jbibtex.LaTeXParser latexParser = new org.jbibtex.LaTeXParser();

			List<org.jbibtex.LaTeXObject> latexObjects = latexParser.parse(t2);

			org.jbibtex.LaTeXPrinter latexPrinter = new org.jbibtex.LaTeXPrinter();

			String plainTextString = latexPrinter.print(latexObjects);
			
			System.out.println(plainTextString);
			
		}
		
	
	}
	public String charset(String texto) throws CharacterCodingException{
		
		Charset charsetE = Charset.forName("ISO-8859-1");
		CharsetEncoder encoder = charsetE.newEncoder();

		//i believe from here to the end will probably stay the same, as per your posted example.
		Charset charsetD = Charset.forName("UTF-8");
		CharsetDecoder decoder = charsetD.newDecoder();

		ByteBuffer bbuf = encoder.encode(CharBuffer.wrap(texto));
		CharBuffer cbuf = decoder.decode(bbuf);
		//final String result = cbuf.toString();
		
		
		
		return cbuf.toString();
	}
}
