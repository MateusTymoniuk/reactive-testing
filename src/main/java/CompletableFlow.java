import io.reactivex.Completable;

public class CompletableFlow {

    private final Dependency dependency;

    public CompletableFlow(Dependency dependency) {
        this.dependency = dependency;
    }

    public Completable simpleFlow(int i) {
        return Completable.fromAction(() -> {
            if (i == 3) {
                throw new RuntimeException("3 throws exception");
            }

            System.out.println("gets here");
        });
    }


    public Completable nestedFlow(int i) {
        return Completable.fromCallable(() -> {
            if (i == 3) {
                throw new RuntimeException("3 throws exception");
            }

            System.out.println("gets here");

            return innerNestedFlow();
        });
    }

    private Completable innerNestedFlow() {
        return Completable.complete();
    }

    public Completable dependencyFlow(int i) {
        return dependency.dependencyCompletable();
    }
}
