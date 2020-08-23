package work.garaku.code.example.spring_batch_restart;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.*;

import java.util.List;

@Slf4j
public class Reader<T> implements ItemStreamReader<T> {
  private static final String KEY = "Reader.";
  private int index;
  private int listSize;
  private List<T> list;
  private boolean isFirst;

  public Reader(List<T> list) {
    this.list = list;
    listSize = list.size();
    index = 0;
  }

  @Override
  public void open(ExecutionContext executionContext) throws ItemStreamException {
    log.info("Reader.open");
    index = executionContext.getInt(KEY + "index", 0);
    isFirst = index == 0;
  }

  @Override
  public T read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
    log.info("Reader.read");
    if (index + 1 > listSize) {
      return null;
    }
    if (index == 2 && isFirst) {
      throw new RuntimeException();
    }
    return list.get(index++);
  }

  @Override
  public void update(ExecutionContext executionContext) throws ItemStreamException {
    log.info("Reader.update");
    executionContext.putInt(KEY + "index", index);
  }

  @Override
  public void close() throws ItemStreamException {
    log.info("Reader.close");
  }
}
