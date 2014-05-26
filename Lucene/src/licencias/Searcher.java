package licencias;

import java.io.File;
import java.util.Scanner;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Searcher {

	public static void main(String[] args) throws Exception {
		Version version = Version.LUCENE_48;
		
		
		// Obteniendo IndexReader
		Directory dir = FSDirectory.open(new File("index"));
		IndexReader reader = DirectoryReader.open(dir);
		
		// Obteniendo IndexSearcher
		IndexSearcher searcher = new IndexSearcher(reader);

		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Introduca la búsqueda: ");
			String patron = scanner.nextLine();
			
			QueryParser qp = new QueryParser(version, "contenido", getAnalyzer());
			Query q = qp.parse(patron);
			
			TopDocs topDocs = searcher.search(q, 10);
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			
			for (ScoreDoc scoreDoc : scoreDocs) {
				Document doc = searcher.doc(scoreDoc.doc); 
				
				System.out.println("Archivo: " + doc.getField("archivo"));
				System.out.println("Contenido: " + doc.getField("contenido"));
				System.out.println("FechaIndexacion: " + doc.getField("fechaIndexacion"));
				System.out.println("FechaCreacion: " + doc.getField("fechaCreacion"));
				System.out.println();
			}
		}
	}

	public static Analyzer getAnalyzer() {
		//return new StandardAnalyzer(Version.LUCENE_48);
		return new KeywordAnalyzer();
	}
}
