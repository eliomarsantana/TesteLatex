import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jbibtex.ParseException;
import org.jbibtex.TokenMgrException;
import net.davidashen.text.Hyphenator; 

class Principal {

	
	public static void main(String[] args) throws TokenMgrException, ParseException, IOException {
		
		
		
		String dados = new String(Files.readAllBytes(new File("C:\\Users\\eliom\\Documents\\tcc\\main.tex").toPath()),Charset.forName("UTF-8").displayName());

		/*TESTAR ISSO
		Charset cs = Charset.forName("UTF-8"); 
		String dados = new String(Files.readAllBytes(new File("/home/eliomar/TCC-ADS-EliomarSantana/main.tex").toPath()), cs);
		OU ISSO
		Charset.forName("UTF-8").encode(dados);
		*/
		
		//BufferedReader arqIn = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\eliom\\Documents\\tcc\\main.tex"), "UTF-8"));
		
		int count = 0;
		String regex = "\\\\input\\{(?<conteudo>.*?)\\}";

		String title = dados.replace("\\", "\\\\");//escapando as barras no arquivo
		
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher comparator = pattern.matcher(title);
		
		while (comparator.find()) {
			title = comparator.group("conteudo");
			System.out.println(title);
			String conteudo = new String(Files.readAllBytes(new File("C:\\Users\\eliom\\Documents\\tcc\\"+title).toPath()));
			//System.out.println("conteudo: "+conteudo);
			
			
			String t = conteudo.replaceAll("%", "");
			String t2 = t.replaceAll("&", "");
			org.jbibtex.LaTeXParser latexParser = new org.jbibtex.LaTeXParser();

			List<org.jbibtex.LaTeXObject> latexObjects = latexParser.parse(t2);

			org.jbibtex.LaTeXPrinter latexPrinter = new org.jbibtex.LaTeXPrinter();

			String plainTextString = latexPrinter.print(latexObjects);
			
			System.out.println(plainTextString);
			
		}
		
		
		
		
		
		System.out.println("\n\n\n\n\n\n\n");
		/*String latexString = "% "
				+ "\\section{A solução: Corretor para Trabalhos de Conclusão de Curso}\\label{sec:a-solucao} "
				+ "Este trabalho tem como objetivo principal o desenvolvimento de um corretor automático de trabalhos de "
				+ "conclusão de curso. A ferramenta foi modelada de forma extensível para que desse suporte a diferentes tipos "
				+ "e formatos de arquivos de entrada. Entretanto, no escopo deste projeto, foi implementado a solução para correção "
				+ "de trabalhos escritos em formato .tex. \\vskip 1em "
				+ "e realizada a validação da mesma. Do ponto de vista da implementação, seguimos os seguintes passos: análise de requisitos, "
				+ "definição da arquitetura, modelagem de classes, implementação, e testes. Esses passos são descritos nas subseções seguintes.\\vskip 1em "
				+ "\\subsection{Análise de Requisitos}\\vskip 1em Segundo \\cite{KOTONYA:SOMMERVILLE}, a análise de requisitos tem por objetivo tratar do processo "
				+ "de definição dos requisitos de software. Para isso, todas as atividades de desenvolvimento precisam ser criteriosamente elaboradas e desenvolvidas. Com isso, foi de suma importância fazer uma análise de requisitos para saber o escopo mais próximo do real que a ferramenta irá atingir.\\vskip 1em ";

				*/
				
				
				
				
				//Hyphenator teste = new Hyphenator();
				
				//teste.setErrorHandler(new MyErrorHandler());
				//teste.loadTable(new java.io.BufferedInputStream(new java.io.FileInputStream("/home/eliomar/tcceliomar/main.tex")));
				//String hyphenated_word=teste.hyphenate("palavra");
				
				//System.out.println("------------->>> "+hyphenated_word);
		
	}
}
