package Doctrina;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CollidableRepository implements Iterable<StaticEntity> {
    private static CollidableRepository instance;

    private final List<StaticEntity> registeredEntities;

    private CollidableRepository() {
        registeredEntities = new ArrayList<>();
    }

    public static CollidableRepository getInstance() {
        if (instance == null) {
            instance = new CollidableRepository();
        }
        return instance;
    }

    public void registerEntity(StaticEntity entity) {
        registeredEntities.add(entity);
    }

    public void unregisterEntity(StaticEntity entity) {
        registeredEntities.remove(entity);
    }

    public void registerEntities(Collection<StaticEntity> entities) {
        registeredEntities.addAll(entities);
    }

    public void unregisterEntities(Collection<StaticEntity> entities) {
        registeredEntities.removeAll(entities);
    }

    public int count() {
        return registeredEntities.size();
    }

    @Override
    public Iterator<StaticEntity> iterator() {
        return registeredEntities.iterator();
    }
}
