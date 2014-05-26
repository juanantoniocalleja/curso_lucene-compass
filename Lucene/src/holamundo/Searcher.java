package holamundo;

import java.io.File;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Searcher {

	public static void main(String[] args) throws Exception {
		Version version = Version.LUCENE_48; // Versión de la librería
		
		// Creando directorio donde se almacenará el índice
		Directory dir = FSDirectory.open(new File("index"));
		
		// Creando analizador
		Analyzer analyzer = new StandardAnalyzer(version);
		
		// Creando el reader
		IndexReader reader = DirectoryReader.open(dir);

		// Creando el searcher
		IndexSearcher searcher = new IndexSearcher(reader);
		
		QueryParser parser = new QueryParser(version, "contenido", analyzer);

		Query q = parser.parse("hola~");
		TopDocs topDocs = searcher.search(q, 10);
		
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		
		for (int i = 0; i < scoreDocs.length; i++) {
			System.out.println("Documento: " + scoreDocs[i].doc);
			System.out.println("Score: " + scoreDocs[i].score);
			System.out.println(
					searcher
						.doc(scoreDocs[i].doc)
						.getField("contenido")
					);
		}
		
	}

}
