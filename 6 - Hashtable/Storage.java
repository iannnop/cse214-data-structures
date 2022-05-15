import java.io.Serializable;

/**
 *  The <code>Storage</code> class represents a storage box registered with the company.
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */
public class Storage implements Serializable {
    /**
     * Serial UID used by the Serializable interface
     */
    private static long serialVersionUID;
    /**
     * unique ID number of the box
     */
    private int id;
    /**
     * name of the client storing the box with the company
     */
    private String client;
    /**
     * description of the contents of the box
     */
    private String content;

    /**
     * Default constructor
     */
    public Storage() { }

    /**
     * Constructor with id, client, and content parameters
     * @param id
     *  unique ID number of the box
     * @param client
     *  name of the client
     * @param content
     *  description of the contents of the box
     */
    public Storage(int id, String client, String content) {
        this.id = id;
        this.client = client;
        this.content = content;
    }

    /**
     * Getter method for id
     * @return
     *  unique id number of the box
     */
    public int getId() {
        return id;
    }

    /**
     * Setter method for id
     * @param id
     *  new unique id number of the box
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter method for client
     * @return
     *  name of the client storing the box with the company
     */
    public String getClient() {
        return client;
    }

    /**
     * Setter method for client
     * @param client
     *  new name of the client storing the box with the company
     */
    public void setClient(String client) {
        this.client = client;
    }

    /**
     * Getter method for content
     * @return
     *  description of the contents of the box
     */
    public String getContent() {
        return content;
    }

    /**
     * Setter method for content
     * @param content
     *  new description of the contents of the box
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * toString method for the <code>Storage</code> object
     * @return
     *  A printable representation of the storage box with the box number, contents, and the name of the owner.
     */
    public String toString() {
        return String.format("""
                Box %d
                Contents: %s
                Owner: %s""",id,content,client);
    }
}
