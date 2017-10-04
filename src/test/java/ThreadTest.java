import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static java.util.stream.IntStream.range;

public class ThreadTest {

    @Test
    public void runsOneThread() throws Exception {
        final int loops = 100;

        AtomicInteger count = new AtomicInteger(0);

        Thread t1 = new Thread(()->range(0,loops)
            .forEach(i->count.incrementAndGet()));

        t1.start();

        t1.join();

        assertThat(count.get(), equalTo(loops));
    }
}
