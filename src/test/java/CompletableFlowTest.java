import io.reactivex.Completable;
import io.reactivex.observers.TestObserver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CompletableFlowTest {

    private CompletableFlow flow;

    @Mock
    Dependency dependencyMock;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        flow = new CompletableFlow(dependencyMock);
    }

    @Test
    public void simpleFlowShouldComplete() {
        TestObserver<Void> testObserver = flow.simpleFlow(1).test();

        testObserver.assertSubscribed();
        testObserver.assertComplete();
        testObserver.assertNoErrors();
    }

    @Test
    public void simpleFlowShouldSendError() {
        TestObserver<Void> testObserver = flow.simpleFlow(3).test();

        testObserver.assertSubscribed();
        testObserver.assertNotComplete();
        testObserver.assertError(RuntimeException.class);
        testObserver.assertErrorMessage("3 throws exception");
    }

    @Test
    public void dependencyFlowShouldComplete() {
        TestObserver<Void> dependencyObserver = TestObserver.create();
        Completable dependencyCompletable = Completable.complete()
                .doOnSubscribe(dependencyObserver::onSubscribe);

        Mockito.when(dependencyMock.dependencyCompletable()).thenReturn(dependencyCompletable);

        TestObserver<Void> testObserver = flow.dependencyFlow(1).test();

        dependencyObserver.assertSubscribed();
        testObserver.assertSubscribed();
    }
}