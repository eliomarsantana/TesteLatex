import java.io.IOException;
import java.util.List;

import org.jbibtex.ParseException;
import org.jbibtex.TokenMgrException;
import net.davidashen.text.Hyphenator; 

class Principal {

	
	public static void main(String[] args) throws TokenMgrException, ParseException, IOException {
		

		
		String latexString = "% "
				+ "\\section{A solução: Corretor para Trabalhos de Conclusão de Curso}\\label{sec:a-solucao} "
				+ "Este trabalho tem como objetivo principal o desenvolvimento de um corretor automático de trabalhos de "
				+ "conclusão de curso. A ferramenta foi modelada de forma extensível para que desse suporte a diferentes tipos "
				+ "e formatos de arquivos de entrada. Entretanto, no escopo deste projeto, foi implementado a solução para correção "
				+ "de trabalhos escritos em formato .tex. \\vskip 1em "
				+ "e realizada a validação da mesma. Do ponto de vista da implementação, seguimos os seguintes passos: análise de requisitos, "
				+ "definição da arquitetura, modelagem de classes, implementação, e testes. Esses passos são descritos nas subseções seguintes.\\vskip 1em "
				+ "\\subsection{Análise de Requisitos}\\vskip 1em Segundo \\cite{KOTONYA:SOMMERVILLE}, a análise de requisitos tem por objetivo tratar do processo "
				+ "de definição dos requisitos de software. Para isso, todas as atividades de desenvolvimento precisam ser criteriosamente elaboradas e desenvolvidas. Com isso, foi de suma importância fazer uma análise de requisitos para saber o escopo mais próximo do real que a ferramenta irá atingir.\\vskip 1em ";

				//org.jbibtex.LaTeXParser latexParser = new org.jbibtex.LaTeXParser();

				//List<org.jbibtex.LaTeXObject> latexObjects = latexParser.parse(latexString);

				//org.jbibtex.LaTeXPrinter latexPrinter = new org.jbibtex.LaTeXPrinter();

				//String plainTextString = latexPrinter.print(latexObjects);
				
				//System.out.println(plainTextString);
				
				
				
				Hyphenator teste = new Hyphenator();
				
				//teste.setErrorHandler(new MyErrorHandler());
				teste.loadTable(new java.io.BufferedInputStream(new java.io.FileInputStream("/home/eliomar/tcceliomar/main.tex")));
				String hyphenated_word=teste.hyphenate("palavra");
				
				System.out.println("------------->>> "+hyphenated_word);
		
	}
	
	/*
	 * public class CopiadorDeArquivos extends SimpleFileVisitor {
  private Path origem;
  private Path destino;
 
  public CopiadorDeArquivos(Path origem, Path destino) {
    this.origem = origem;
    this.destino = destino;
  }
 
  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
          throws IOException {
    copiaPath(dir);
    return FileVisitResult.CONTINUE;
  }
 
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
          throws IOException {
    copiaPath(file);
    return FileVisitResult.CONTINUE;
  }
 
  private void copiaPath(Path entrada) throws IOException {
    // encontra o caminho equivalente na árvore de cópia
    Path novoDiretorio = destino.resolve(origem.relativize(entrada));
    Files.copy(entrada, novoDiretorio);
  }
}
	 * */
	
	
}
