package holamundo;

import java.io.File;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 * Este código depende de las siguientes librerías: 
 * - lucene-core-4.8.1.jar
 * - lucene-analyzers-common-4.8.1.jar
 * @author camposer
 *
 */
public class Writer {

	public static void main(String[] args) throws Exception {
		Version version = Version.LUCENE_48; // Versión de la librería
		
		// Creando directorio donde se almacenará el índice
		Directory dir = FSDirectory.open(new File("index"));
		
		// Creando analizador
		Analyzer analyzer = new StandardAnalyzer(version);
		
		// Definiendo configuraciones del índice (Versión y Analizador)
		IndexWriterConfig config = new IndexWriterConfig(version, analyzer);
		 
		// Creando escritor (writer)
		IndexWriter writer = new IndexWriter(dir, config);
		
		//Creando el docuemnto
		Document doc = new Document();
		doc.add(new Field("contenido", "Hola mundo", TextField.TYPE_STORED));

		writer.addDocument(doc);
		
		// Cerrando el índice (libera los recursos y escribe en disco)
		writer.close();
		
		System.out.println("Indexado finalizado");
	}

}
