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
    private Iterator<Path> pathIterator;

    @Override
    protected String doRead() throws Exception {
        String pathString = null;
        if (pathIterator.hasNext())
            pathString = pathIterator.next().toString();
        return pathString;
    }

    @Override
    protected void doOpen() throws Exception {
        pathIterator = Files.walk(Paths.get("/opt/duckbill")).iterator();
    }

    @Override
    protected void doClose() throws Exception {
        StreamSupport
                .stream(((Iterable<Path>) () -> pathIterator).spliterator(), false)
                .close();
    }
}
