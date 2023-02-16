import io.reactivex.Flowable;
import io.reactivex.Scheduler;

import java.util.concurrent.TimeUnit;

public class FlowableFlow {

    public Flowable<Long> intervalFlow(Scheduler scheduler) {
        return Flowable.interval(1, TimeUnit.SECONDS, scheduler);
    }
}
