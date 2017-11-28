package dk.stork.entities;

/**
 * @author Johannes Ernstsen
 */
public class EntityObject {
    public void save() {
        EntityFactory.save(this);
    }
}
