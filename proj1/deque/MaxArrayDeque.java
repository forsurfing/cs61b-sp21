package deque;
import java.util.Comparator;

    public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> defaultcomparator;
    public MaxArrayDeque(Comparator<T> c){
        this.defaultcomparator = c;
    }

    public T max(){
        if (isEmpty()) {
            return null;
        }
        return max(defaultcomparator);
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        int maxIndex = 0;
        for (int i = 1; i < size(); i++) {
            if (c.compare(get(i), get(maxIndex)) > 0) {
                    maxIndex = i;
            }
        }
        return get(maxIndex);
    }

}
