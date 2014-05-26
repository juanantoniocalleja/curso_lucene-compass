package holamundo;

import java.io.File;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Reader {
	public static void main(String[] args) throws Exception {
		Version version = Version.LUCENE_48; // Versión de la librería
		
		// Creando directorio donde se almacenará el índice
		Directory dir = FSDirectory.open(new File("index"));
		
		// Creando analizador
		Analyzer analyzer = new StandardAnalyzer(version);
		
		// Creando el reader
		IndexReader reader = DirectoryReader.open(dir);
	
		
	}
}
