import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

/**
 *  The <code>StorageTable</code> class stores the <code>Storage</code> objects in a hash table to provide constant
 *  time insertion and deletion.
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */
public class StorageTable extends Hashtable<Integer,Storage> implements Serializable {
    /**
     * Serial UID used by the Serializable interface
     */
    private static int serialVersionUID;

    /**
     * Manually inserts a <code>Storage</code> object into the table using the specified key
     * @param storageID
     *  unique key for the <code>Storage</code> object
     * @param storage
     *  <code>Storage</code> object to insert into the table
     * @throws IllegalArgumentException
     *  if <code>storageID</code> is less than 0 or if <code>storage</code> is null
     */
    public void putStorage(int storageID, Storage storage) throws IllegalArgumentException {
        if (storageID<0||containsKey(storageID)||storage==null)
            throw new IllegalArgumentException();
        put(storageID,storage);
    }

    /**
     * Retrieve the <code>Storage</code> from the table having the indicated <code>storageID</code>.
     * If the requested <code>storageID</code> does not exist in the <code>StorageTable</code>, return null.
     * @param storageID
     *  key of the <code>Storage</code> to retrieve from the table
     * @return
     *  <code>Storage</code> object with the given key, null otherwise.
     */
    public Storage getStorage(int storageID) { return get(storageID); }

    /**
     * toString method for <code>StorageTable</code>
     * @return
     *  A printable representation of <code>StorageTable</code> containing a sorted list of all <code>Storage</code>
     *  objects in the HashTable with their id, client name, and description of contents
     */
    public String toString() {
        String out = "";
        Storage storage;
        out += String.format("%-10s%-38s%s%n%s","Box#","Contents","Owner","-".repeat(64));
        ArrayList<Integer> keys = new ArrayList<>(keySet());
        Collections.sort(keys);
        for (int key : keys) {
            storage = getStorage(key);
            out += String.format("%n%-10d%-38s%s", storage.getId(), storage.getContent(), storage.getClient());
        }
        return out;
    }
}
