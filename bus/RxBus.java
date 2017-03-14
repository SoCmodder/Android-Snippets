package info.trustify.pi.bus;

import rx.Subscription;
import rx.functions.Action1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;
import timber.log.Timber;

public class RxBus
{
    private static final RxBus INSTANCE = new RxBus();

    private final Subject<Object, Object> busSubject = new SerializedSubject<>(PublishSubject.create());

    public static RxBus getInstance()
    {
        return INSTANCE;
    }

    public <T> Subscription register(final Class<T> eventClass, Action1<T> onNext)
    {
        return busSubject
                .filter(event -> event.getClass().equals(eventClass))
                .ofType(eventClass)
                .subscribe(onNext, Timber::e);
    }

    public void post(Object event)
    {
        try
        {
            busSubject.onNext(event);
        }
        catch (Exception e)
        {
            Timber.e(e.getMessage());
            e.printStackTrace();
        }
    }
}
