package hello;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * Created by olmer on 08.08.17.
 */
public class FileNameWriter implements ItemWriter<String> {

    @Override
    public void write(List<? extends String> items) throws Exception {
        items.forEach(System.out::println);
    }
}
