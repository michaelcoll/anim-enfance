package fr.animenfance.service;

import static fr.animenfance.utils.IndexUtils.createDocumentFromPartenaire;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.RAMDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.common.base.Stopwatch;
import fr.animenfance.bean.Partenaire;
import fr.animenfance.dao.PartenaireDao;
import fr.animenfance.utils.IndexUtils;

@Service
public class PartenaireIndexerService {

  private static final Logger LOGGER = LoggerFactory.getLogger(PartenaireIndexerService.class);

  private final Analyzer analyzer;

  @Autowired private RAMDirectory index;
  @Autowired private PartenaireDao dao;

  private final static Queue<String> REBUILD_ORDERS = new ConcurrentLinkedQueue<>();

  public PartenaireIndexerService() {
    analyzer = new StandardAnalyzer();
  }

  @PostConstruct
  public void postConstruct() {
    scheduleIndexRebuild();
  }

  public void scheduleIndexRebuild() {
    LOGGER.debug("Index rebuild scheduled.");
    REBUILD_ORDERS.add("rebuild");
  }

  @Scheduled(initialDelay = 1000, fixedDelay = 2000)
  public void rebuildIndex() throws IOException {

    String command = REBUILD_ORDERS.poll();

    if(command != null) {
      Stopwatch watch = Stopwatch.createStarted();
      LOGGER.debug("Starting rebuild index...");
      List<Partenaire> partenaires = dao.list();

      try(IndexWriter indexWriter = new IndexWriter(index, new IndexWriterConfig(analyzer))) {
        for (Partenaire partenaire : partenaires) {
          Document doc = createDocumentFromPartenaire(partenaire);
          indexWriter.addDocument(doc);
        }

        indexWriter.flush();
      }

      LOGGER.info("Index rebuild, " + partenaires.size() + " items in " +
        watch.toString());
    }
  }

  public long getIndexSize() {
    return index.ramBytesUsed();
  }

  public List<Partenaire> searchPartenaire(String search, int maxResultNumber) throws IOException, ParseException {
    try (DirectoryReader iReader = DirectoryReader.open(index)) {
      Query query = IndexUtils.getQueryForPartenaire(search, analyzer);

      IndexSearcher iSearcher = new IndexSearcher(iReader);
      ScoreDoc[] hits = iSearcher.search(query, maxResultNumber).scoreDocs;

      return Arrays.asList(hits).stream().map(scoreDoc -> getPartenaireFromSearcher(iSearcher, scoreDoc))
        .collect(Collectors.toList());
    }
  }

  private static Partenaire getPartenaireFromSearcher(IndexSearcher iSearcher, ScoreDoc scoreDoc) {
    try {
      return IndexUtils.createPartenaireFromDocument(iSearcher.doc(scoreDoc.doc));
    } catch (IOException e) {
      LOGGER.error(e.getLocalizedMessage(), e);
      return null;
    }
  }
}
