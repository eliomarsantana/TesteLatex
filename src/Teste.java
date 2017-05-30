import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

import org.jbibtex.LaTeXParser;
import org.jbibtex.ParseException;
import org.jbibtex.TokenMgrException;
import org.jbibtex.LaTeXObject;

public class Teste {

	public static void main(String[] args) {
		try {
			Path startPath = Paths.get("/home/eliomar/tcceliomar");
			Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
					//System.out.println("Diretório: " + dir.toString());
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException{
					System.out.println("Arquivo: " + file.toString());
					
					if (file.toString().endsWith(".tex")) {
						String dados = new String(Files.readAllBytes(new File(file.toString()).toPath()));
						
						 LaTeXParser latexParser = null;
						try {
							latexParser = new LaTeXParser();
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
						}
						  
						 List<LaTeXObject> latexObjects = null;
						try {
							latexObjects = latexParser.parse(dados);
						} catch (TokenMgrException | ParseException e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
						}
						  
						 org.jbibtex.LaTeXPrinter latexPrinter = new org.jbibtex.LaTeXPrinter();
						  
						 String plainTextString = latexPrinter.print(latexObjects);
						  
						 System.out.println(plainTextString);
						
						System.out.println(dados);
					}
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFileFailed(Path file, IOException e) {
					return FileVisitResult.CONTINUE;
				}
			});
		} catch (IOException e) {
			//e.printStackTrace();
		}

	}
}

/*
 * String latexString =
 * "\\section{A solução: Corretor para Trabalhos de Conclusão de Curso}\\label{sec:a-solucao} "
 * +
 * "Este trabalho tem como objetivo principal o desenvolvimento de um corretor automático de trabalhos de "
 * +
 * "conclusão de curso. A ferramenta foi modelada de forma extensível para que desse suporte a diferentes tipos "
 * +
 * "e formatos de arquivos de entrada. Entretanto, no escopo deste projeto, foi implementado a solução para correção "
 * + "de trabalhos escritos em formato .tex. \\vskip 1em " +
 * "e realizada a validação da mesma. Do ponto de vista da implementação, seguimos os seguintes passos: análise de requisitos, "
 * +
 * "definição da arquitetura, modelagem de classes, implementação, e testes. Esses passos são descritos nas subseções seguintes.\\vskip 1em "
 * +
 * "\\subsection{Análise de Requisitos}\\vskip 1em Segundo \\cite{KOTONYA:SOMMERVILLE}, a análise de requisitos tem por objetivo tratar do processo "
 * +
 * "de definição dos requisitos de software. Para isso, todas as atividades de desenvolvimento precisam ser criteriosamente elaboradas e desenvolvidas. Com isso, foi de suma importância fazer uma análise de requisitos para saber o escopo mais próximo do real que a ferramenta irá atingir.\\vskip 1em "
 * ;
 * 
 * org.jbibtex.LaTeXParser latexParser = new org.jbibtex.LaTeXParser();
 * 
 * List<org.jbibtex.LaTeXObject> latexObjects = latexParser.parse(latexString);
 * 
 * org.jbibtex.LaTeXPrinter latexPrinter = new org.jbibtex.LaTeXPrinter();
 * 
 * String plainTextString = latexPrinter.print(latexObjects);
 * 
 * System.out.println(plainTextString);
 */