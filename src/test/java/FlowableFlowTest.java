import io.reactivex.schedulers.TestScheduler;
import io.reactivex.subscribers.TestSubscriber;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class FlowableFlowTest {

    private FlowableFlow flow;

    @Before
    public void setUp() {
        flow = new FlowableFlow();
    }

    @Test
    public void shouldEmit1Value() {
        TestScheduler testScheduler = new TestScheduler();
        TestSubscriber<Long> longTestSubscriber = new TestSubscriber<>();

        flow.intervalFlow(testScheduler)
                .subscribe(longTestSubscriber);

        longTestSubscriber.assertSubscribed();
        longTestSubscriber.assertNoValues();
        longTestSubscriber.assertNotComplete();

        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS);

        longTestSubscriber.assertNoErrors();
        longTestSubscriber.assertValueCount(1);
        longTestSubscriber.assertValue(0L);
    }

    @Test
    public void shouldEmit2Value() {
        TestScheduler testScheduler = new TestScheduler();
        TestSubscriber<Long> longTestSubscriber = new TestSubscriber<>();

        flow.intervalFlow(testScheduler)
                .subscribe(longTestSubscriber);

        longTestSubscriber.assertSubscribed();
        longTestSubscriber.assertNoValues();
        longTestSubscriber.assertNotComplete();

        testScheduler.advanceTimeBy(2, TimeUnit.SECONDS);

        longTestSubscriber.assertNoErrors();
        longTestSubscriber.assertValueCount(2);
        longTestSubscriber.assertValueAt(1, 1L);
    }
}