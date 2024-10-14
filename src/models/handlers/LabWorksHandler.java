package models.handlers;

import models.LabWork;
import models.comparators.LabWorkComparator;
import models.validators.*;

import java.time.Instant;
import java.util.Date;
import java.util.*;
import java.util.HashSet;

/**
 * Current implementation of CollectionsHandler for HashSet of LabWork.
 *
 * @since 1.0
 * @author Nikita
 */
public class LabWorksHandler implements CollectionHandler<HashSet<LabWork>, LabWork> {

    private static LabWorksHandler singletoneMoment;

    private HashSet<LabWork> labWorks;
    private final Date initDate;

    private LabWorksHandler() {
        labWorks = new HashSet<>();
        initDate = Date.from(Instant.now());
    }

    /**
     * Singletone moment.
     *
     * @return Single instance of handler.
     */
    public static LabWorksHandler getInstance() {
        if (singletoneMoment == null)
            singletoneMoment = new LabWorksHandler();
        return singletoneMoment;
    }

    /**
     * Returns actual collection reference.
     *
     * @return Current collection
     */
    @Override
    public HashSet<LabWork> getCollection()
    {
        return labWorks;
    }

    /**
     * Overrides current collection by provided value.
     *
     * @param labWorks Collection
     */
    @Override
    public void setCollection(HashSet<LabWork> labWorks) {
        this.labWorks = labWorks;
        validateElements();
        sort();
    }

    /**
     * Adds element to collection.
     *
     * @param e Element to add
     */
    @Override
    public void addElementToCollection(LabWork e)
    {
        labWorks.add(e);
        sort();
    }

    @Override
    public void clearCollection() {
        labWorks.clear();
    }

    /**
     * Sorts elements by ID Field in LabWork.
     */
    @Override
    public void sort() {
        HashSet<LabWork> sortLab = new HashSet<>();

        for (Iterator<LabWork> it = labWorks.stream().sorted(new LabWorkComparator()).iterator(); it.hasNext(); ) {
            LabWork sortedItem = it.next();

            sortLab.add(sortedItem);
        }

        this.labWorks = sortLab;
    }

    /**
     * Returns first element of collection.
     * @return First element of collection. If collection is empty, returns new object.
     */
    @Override
    public LabWork getFirstOrNew()
    {
        if (labWorks.iterator().hasNext())
            return labWorks.iterator().next();
        else
            return new LabWork();
    }

    @Override
    public Date getInitDate() {
        return initDate;
    }

    /**
     * Returns last element of collection.
     * @return Last element of collection of null if collection is empty
     */
    @Override
    public LabWork getLastElement()
    {
        LabWork result = null;
        for (LabWork labWork : labWorks) {
            result = labWork;
        }
        return result;
    }

    /**
     * Validates all elements in collection
     */
    @Override
    public void validateElements() {
        HashSet<Long> ids = new HashSet<>(getCollection().size());
        for (Iterator<LabWork> it = getCollection().iterator(); it.hasNext(); ) {
            LabWork toValid = it.next();
            Validator<LabWork> validator = new LabWorkValidator();

            if (!validator.validate(toValid) || !ids.add(toValid.getId()))
            {
                it.remove();
                System.out.println("Element removed from collection: " + toValid);
                System.out.println("This element violates the restriction of some fields. Check your file and fix it manually.");
            }
        }
    }


    /**
     * Gets max element by given comparator
     *
     * @param comparator Comparator to compare.
     * @return Max element or null if collection is empty
     */
    @Override
    public LabWork getMax(Comparator<LabWork> comparator) {
        return getCollection().stream().max(comparator).orElse(null);
    }
}
