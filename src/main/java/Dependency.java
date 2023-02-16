import io.reactivex.Completable;

class Dependency {
    public Completable dependencyCompletable() {
        return Completable.complete();
    }
}
