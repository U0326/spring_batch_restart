package work.garaku.code.example.spring_batch_restart;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Writer<T> implements ItemWriter<T> {
  @Override
  public void write(List<? extends T> list) throws Exception {
    list.forEach(System.out::println);
  }
}
