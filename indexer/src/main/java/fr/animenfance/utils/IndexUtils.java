package fr.animenfance.utils;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;

import fr.animenfance.bean.Partenaire;

public final class IndexUtils {

  private IndexUtils() {
  }

  /**
   * Create a {@link Document} from a {@link Partenaire} <br/>
   *  - 'partenaire.name' -> name of the partenaire
   */
  public static Document createDocumentFromPartenaire(Partenaire partenaire) {
    Document doc = new Document();

    doc.add(new IntField("partenaire.id", partenaire.getId(), Field.Store.YES));
    doc.add(new TextField("partenaire.name", partenaire.getName(), Field.Store.YES));

    return doc;
  }

  public static Partenaire createPartenaireFromDocument(Document document) {
    Partenaire partenaire = new Partenaire();

    partenaire.setName(document.getField("partenaire.name").stringValue());
    partenaire.setId(document.getField("partenaire.id").numericValue().intValue());

    return partenaire;
  }

  public static Query getQueryForPartenaire(String search, Analyzer analyzer) throws ParseException {
    QueryParser parser = new QueryParser("partenaire.name", analyzer);
    return parser.parse(search);
  }

}
