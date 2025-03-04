package models.handlers;

import java.util.Date;
import java.util.AbstractCollection;
import java.util.Comparator;


/**
 * Base interface for handling Collection of Elements. You should implement it in your handler for correct command working.
 *
 * @param <T> Type of Collection
 * @param <E> Type of Elements
 *
 * @since 1.0
 * @author Nikita
 */
public interface CollectionHandler <T extends AbstractCollection<E>, E> {
    /**
     * Provides method for get collection.
     *
     * @return Actual collection reference
     */
    T getCollection();

    /**
     * Provides method for set collection.
     *
     * @param value Collection
     */
    void setCollection(T value);

    /**
     * Provides method for fast adding element in collection.
     *
     * @param value Element to add
     */
    void addElementToCollection(E value);

    /**
     * Provides method for fast clear element in collection.
     */
    void clearCollection();

    /**
     * Provides method for fast sorting collection. You can ignore this method.
     */
    void sort();

    /**
     * Provides method for fast getting first element.
     *
     * @return First element. If collection was empty, creates new Element and returns it.
     */
    E getFirstOrNew();

    /**
     * Provides method for get collection initDate.
     *
     * @see commandManager.commands.InfoCmd
     * @return Date instance -- Collection created time.
     */
    Date getInitDate();

    /**
     * Provides method for getting last element.
     *
     * @return Last element. If collection was empty, returns null.
     */
    E getLastElement();

    /**
     * Provides method for validate elements in collection. You can ignore this method
     */
    void validateElements();

    /**
     * Gets max element by given comparator
     *
     * @param comparator Comparator to compare.
     * @return Max element or null if collection is empty
     */
    E getMax(Comparator<E> comparator);
}
