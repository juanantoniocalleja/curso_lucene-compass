package licencias;

import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Writer {
	public static void main(String[] args) throws Exception {
		Date inicio = new Date();
		
		Version version = Version.LUCENE_48;
		
		// Directorios del sistema
		File dirArchivos = new File("data");
		Directory dirIdx = FSDirectory.open(new File("index"));
		
		// Creando el analizador
		Analyzer analyzer = new StandardAnalyzer(version);
		
		// Configuración del índice
		IndexWriterConfig config = new IndexWriterConfig(version, analyzer);
	
		// Creando el writer
		IndexWriter writer = new IndexWriter(dirIdx, config);
		
		// Recorriendo archivos
		FieldType keyword = new FieldType();
		keyword.setIndexed(true);
		keyword.setStored(true);
		keyword.setTokenized(false);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		for (File archivo : dirArchivos.listFiles()) {
			Document doc = new Document();
			doc.add(new Field("archivo", archivo.getCanonicalPath(), 
					keyword));
			doc.add(new Field("contenido", 
					new FileReader(archivo), TextField.TYPE_NOT_STORED));
			doc.add(new Field("fechaIndexacion", sdf.format(new Date()), 
					keyword));
			doc.add(new Field("fechaCreacion", 
					sdf.format(new Date(archivo.lastModified())), 
					keyword));
			
			writer.addDocument(doc); // Añadiendo documento al writer
		}

		writer.close(); // Cerrando el writer
		
		Date fin = new Date();
		
		System.out.println("Directorio: " + dirArchivos + " indexado (" + (fin.getTime() - inicio.getTime()) + " mseg)");
	}
}
