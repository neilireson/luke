package org.getopt.luke;

import java.io.IOException;

import org.apache.lucene.index.LeafReaderContext;
import org.apache.lucene.search.LeafCollector;
import org.apache.lucene.search.Scorer;
import org.apache.lucene.search.TimeLimitingCollector;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.search.TimeLimitingCollector.TimeExceededException;

public class IntervalLimitedCollector extends LimitedHitCollector {
  private final long maxTime;
  private long lastDoc = 0;
  private TopScoreDocCollector tdc;
  private LeafCollector leafCollector = null;
  private TopDocs topDocs = null;
  private TimeLimitingCollector thc;

  public IntervalLimitedCollector(int maxTime, boolean shouldScore) {
    this.maxTime = maxTime;
    this.shouldScore = shouldScore;
    tdc = TopScoreDocCollector.create(1000);
    thc = new TimeLimitingCollector(tdc, TimeLimitingCollector.getGlobalCounter(), maxTime);
  }

  @Override
  public long limitSize() {
    return maxTime;
  }

  @Override
  public int limitType() {
    return TYPE_TIME;
  }

  @Override
  public int getDocId(int pos) {
    if (topDocs == null) {
      topDocs = tdc.topDocs();
    }
    return topDocs.scoreDocs[pos].doc;
  }

  /* (non-Javadoc)
   * @see org.getopt.luke.AccessibleHitCollector#getScore(int)
   */
  @Override
  public float getScore(int pos) {
    if (topDocs == null) {
      topDocs = tdc.topDocs();
    }
    return topDocs.scoreDocs[pos].score;
  }

  @Override
  public int getTotalHits() {
    return tdc.getTotalHits();
  }

  @Override
  public void collect(int docNum) throws IOException {
    try {
      leafCollector.collect(docNum);
    } catch (TimeExceededException tee) {
      // re-throw
      throw new LimitedException(TYPE_TIME, maxTime, tee.getTimeElapsed(), tee.getLastDocCollected());
    }
  }

  @Override
  public void doSetNextReader(LeafReaderContext context) throws IOException {
    this.docBase = context.docBase;
    leafCollector = tdc.getLeafCollector(context);
  }

  @Override
  public void setScorer(Scorer scorer) throws IOException {
    this.scorer = scorer;
      if (shouldScore) {
          leafCollector.setScorer(scorer);
      } else {
          leafCollector.setScorer(NoScoringScorer.INSTANCE);
      }
  }

  @Override
  public void reset() {
    lastDoc = 0;
    tdc = TopScoreDocCollector.create(1000);
    thc = new TimeLimitingCollector(tdc, TimeLimitingCollector.getGlobalCounter(), maxTime);
  }
}
