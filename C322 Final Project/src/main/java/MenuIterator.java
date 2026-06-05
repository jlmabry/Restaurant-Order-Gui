import java.util.Iterator;
import java.util.List;

public class MenuIterator implements Iterator<MenuItem> {
    private final List<MenuItem> items;
    private int index = 0;

    public MenuIterator(List<MenuItem> items) {
        this.items = items;
    }

    @Override
    public boolean hasNext() {
        return index < items.size();
    }

    @Override
    public MenuItem next() {
        return items.get(index++);
    }
}
