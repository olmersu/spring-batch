package hello;

import org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by olmer on 08.08.17.
 */
public class FileNameReader extends AbstractItemCountingItemStreamItemReader<String> {
    Stream<Path> pathStream;

    @Override
    protected String doRead() throws Exception {
        Iterator<Path> pathIterator = pathStream.iterator();
        String pathString = null;
        if (pathIterator.hasNext())
            pathString = pathIterator.next().toString();
        pathStream = StreamSupport.stream(((Iterable<Path>) () -> pathIterator).spliterator(), false);
        return pathString;
    }

    @Override
    protected void doOpen() throws Exception {
        pathStream = Files.walk(Paths.get("/Users/olmer/Documents"));
    }

    @Override
    protected void doClose() throws Exception {
        pathStream.close();
    }

}
