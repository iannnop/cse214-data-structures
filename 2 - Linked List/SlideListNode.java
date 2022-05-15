/**
 *  The <code>SlideListNode</code> class wraps a <code>Slide</code> object to allow it to be inserted
 *  into a doubly linked-list data structure.
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */

public class SlideListNode {

    /**
     * Stores the <code>Slide</code> object reference
     */
    private Slide data;

    /**
     * Link to the next <code>SlideListNode</code> on the list
     */
    private SlideListNode next;

    /**
     * Link to the previous <code>SlideListNode</code> on the list
     */
    private SlideListNode prev;

    /**
     * Default constructor
     * <p>Preconditions:</p>
     * <p><code>initData</code> is not <code>null</code></p>
     * <p>Postconditions:</p>
     * <p>Initializes this <code>SlideListNode</code> to wrap the indicated <code>Slide</code></p>
     *
     * @param initData
     *  The <code>data</code> to be wrapped by this <code>SlideListNode</code>
     * @throws IllegalArgumentException
     *  Thrown if <code>initData</code> is <code>null</code>
     */
    public SlideListNode(Slide initData) throws IllegalArgumentException {
        if (initData == null)
            throw new IllegalArgumentException("Data cannot be null");
        data = initData;
        prev = null;
        next = null;
    }

    /**
     * Gets the reference to the <code>data</code> member variable of the list node
     *
     * @return
     *  Returns the reference of the <code>data</code> member variable
     */
    public Slide getData() { return data; }

    /**
     * Updates the <code>data</code> member variable with a reference to a new <code>Slide</code>
     * <p>Preconditions:</p>
     * <p><code>newData</code> is not <code>null</code></p>
     *
     * @param newData
     *  Reference to a new <code>Slide</code> object to update the <code>data</code> variable
     *
     * @throws IllegalArgumentException
     *  Thrown if <code>newData</code> is <code>null</code>
     */
    public void setData(Slide newData) throws IllegalArgumentException {
        if (newData == null)
            throw new IllegalArgumentException("Data cannot be null");
        data = newData;
    }

    /**
     * Gets the reference to the <code>next</code> member variable of the list node
     *
     * @return
     *  Returns the reference of the <code>next</code> member variable
     */
    public SlideListNode getNext() { return next; }

    /**
     * Updates the <code>next</code> member variable with a new <code>SlideListNode</code> reference
     *
     * @param newNext
     *  Reference to a new <code>SlideListNode</code> object to update the <code>next</code> member variable
     */
    public void setNext(SlideListNode newNext) { next = newNext; }

    /**
     * Gets the reference to the <code>prev</code> member variable of the list node
     *
     * @return
     *  Returns the reference of the <code>prev</code> member variable
     */
    public SlideListNode getPrev() { return prev; }

    /**
     * Updates the <code>prev</code> member variable with a new <code>SlideListNode</code> reference
     *
     * @param newPrev
     *  Reference to a new <code>SlideListNode</code> object to update the <code>prev</code> member variable
     */
    public void setPrev(SlideListNode newPrev) { prev = newPrev; }
}
