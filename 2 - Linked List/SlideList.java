/**
 *  The <code>SlideListNode</code> class implements a double linked-list data structure that maintains a list of
 *  <code>Slides</code> by chaining a series of <code>SlideListNodes</code> between a <code>head</code> and a
 *  <code>tail</code> reference. In addition, a <code>cursor</code> is provided to allow the user to select and
 *  manipulate individual <code>SlideListNodes</code>.
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */

public class SlideList {

    /**
     * The reference to the <code>head</code> of the double linked-list
     */
    private SlideListNode head;

    /**
     * The reference to the <code>tail</code> of the double linked-list
     */
    private SlideListNode tail;

    /**
     * The reference to the location of the <code>cursor</code>. If <code>cursor</code> is <code>null</code>, then
     * nothing is currently selected.
     */
    private SlideListNode cursor;

    /**
     * Counter for the number of slides in the list
     */
    private int numSlides;

    /**
     * Blank dummy node that is set as the head of the list when a list is initialized
     */
    private final SlideListNode INIT_NODE;

    /**
     * Default constructor which initializes this object to an empty list of <code>Slides</code>
     * <p>Postconditions:</p>
     * <p>This <code>SlideList</code> has been initialized with <code>head</code> set to a dummy node,
     * <code>tail</code> and <code>cursor</code> both set to <code>null</code></p>
     */
    public SlideList() {
        INIT_NODE = new SlideListNode(new Slide());
        head = INIT_NODE;
        tail = null;
        cursor = null;
    }

    /**
     * Gets the total number of <code>Slides</code> in the slideshow
     *
     * @return
     *  Returns the count of all <code>Slides</code> in the slideshow
     */
    public int size() { return numSlides; }

    /**
     * Gets the total duration of the slideshow
     *
     * @return
     *  Returns the sum of all individual <code>Slide</code> durations in the slideshow
     */
    public double duration() {
        double sum = 0;
        SlideListNode current = head.getNext();
        while (current != null) {
            if (current.getData().getDuration()>0)
                sum += current.getData().getDuration();
            current = current.getNext();
        }
        return sum;
    }

    /**
     * Gets the total number of bullet points in the slideshow
     *
     * @return
     *  Returns the sum of all bullet points of all individual <code>Slides</code> in the slideshow
     */
    public int numBullets() {
        int numBullets = 0;
        SlideListNode current = head.getNext();
        while (current != null) {
            numBullets += current.getData().getNumBullets();
            current = current.getNext();
        }
        return numBullets;
    }

    /**
     * Gets the reference to the <code>Slide</code> wrapped by the <code>SlideListNode</code> currently referenced
     * by <code>cursor</code>
     *
     * @return
     *  Returns the reference of the <code>Slide</code> currently referenced by the <code>cursor</code>
     *  If the <code>cursor</code> is <code>null</code>, then this method also returns <code>null</code>.
     */
    public Slide getCursorSlide() {
        if (cursor == null)
            return null;
        return cursor.getData();
    }

    /**
     * Returns the <code>cursor</code> to the start of the list
     * <p>Postconditions:</p>
     * <p>If <code>head</code> is not <code>null</code>, the <code>cursor</code> now references the first
     * <code>SlideListNode</code> in this list</p>
     * <p>If <code>head</code> is <code>null</code>, the <code>cursor</code> is also set to <code>null</code></p>
     */
    public void resetCursorToHead() { cursor = head.getNext(); }

    /**
     * Moves the <code>cursor</code> to select the next <code>SlideListNode</code> in the list.
     * If the <code>cursor</code> is at the <code>tail</code> of the list, this method throws an exception.
     *
     * @throws EndOfListException
     *  Thrown if the <code>cursor</code> is at the <code>tail</code> of the list
     */
    public void cursorForward() throws EndOfListException {
        if (cursor == tail)
            throw new EndOfListException("Cursor has reached the end of the list");
        cursor = cursor.getNext();
    }

    /**
     * Moves the <code>cursor</code> to select the previous <code>SlideListNode</code> in the list.
     * If the <code>cursor</code> is at the <code>head</code> of the list, this method throws an exception.
     *
     * @throws EndOfListException
     *  Thrown if the <code>cursor</code> is at the <code>head</code> of the list
     */
    public void cursorBackward() throws EndOfListException {
        if (cursor == head.getNext())
            throw new EndOfListException("Cursor has reached the beginning of the list");
        cursor = cursor.getPrev();
    }



    /**
     * Inserts the indicated <code>Slide</code> before the <code>cursor</code>
     * <p>Preconditions:</p>
     * <p><code>newSlide</code> is not <code>null</code></p>
     * <p>Postconditions:</p>
     * <p>newSlide has been wrapped in a new <code>SlideListNode</code> object</p>
     * <p>If <code>cursor</code> was not <code>null</code>, the new <code>SlideListNode</code> has been inserted
     * into the list before the cursor</p>
     * <p>If <code>cursor</code> was <code>null</code>, the new <code>SlideListNode</code> has been set as the new
     * <code>head</code> of the list</p>
     * <p>The <code>cursor</code> now references the new <code>SlideListNode</code></p>
     *
     * @param newSlide
     * The <code>Slide</code> object to be wrapped and inserted into the list before the <code>cursor</code>
     *
     * @throws IllegalArgumentException
     *  Thrown if <code>newSlide</code> is <code>null</code>
     */
    public void insertBeforeCursor(Slide newSlide) throws IllegalArgumentException {
        if (newSlide == null)
            throw new IllegalArgumentException("Slide data cannot be null");
        SlideListNode newNode = new SlideListNode(newSlide);
        if (cursor == null) {
            newNode.setPrev(head);
            head.setNext(newNode);
            tail = newNode;
        }
        else {
            newNode.setNext(cursor);
            newNode.setPrev(cursor.getPrev());
            cursor.getPrev().setNext(newNode);
            cursor.setPrev(newNode);
        }
        numSlides++;
        cursor = newNode;
    }

    /**
     * Inserts the indicated <code>Slide</code> after the <code>tail</code> of the list
     * <p>Preconditions:</p>
     * <p><code>newSlide</code> is not <code>null</code></p>
     * <p>Postconditions:</p>
     * <p><code>newSlide</code> has been wrapped in a new <code>SlideListNode</code> object</p>
     * <p>If <code>tail</code> was not <code>null</code>, the new <code>SlideListNode</code> has been inserted
     * into the list after the <code>tail</code></p>
     * <p>If <code>tail</code> was <code>null</code>, the new <code>SlideListNode</code> has been set to the new
     * <code>head</code> (and <code>tail</code>) of the list</p>
     * <p>The <code>tail</code> now references the new <code>SlideListNode</code></p>
     *
     * @param newSlide
     *  The <code>Slide</code> object to be wrapped and inserted into the list after the <code>tail</code> of the list
     *
     * @throws IllegalArgumentException
     *  Thrown if <code>newSlide</code> is <code>null</code>
     */
    public void appendToTail(Slide newSlide) throws IllegalArgumentException {
        if (newSlide == null)
            throw new IllegalArgumentException("Slide data cannot be null");
        SlideListNode newNode = new SlideListNode(newSlide);
        if (tail == null) {
            newNode.setPrev(head);
            head.setNext(newNode);
            cursor = newNode;
        }
        else {
            newNode.setPrev(tail);
            tail.setNext(newNode);
        }
        numSlides++;
        tail = newNode;
    }

    /**
     * Removes the <code>SlideListNode</code> referenced by <code>cursor</code> and returns the <code>Slide</code>
     * inside
     * <p>Preconditions:</p>
     * <p><code>cursor</code> is not null</p>
     * <p>Postconditions:</p>
     * <p>The <code>SlideListNode</code> referenced by <code>cursor</code> has been removed from the list</p>
     * <p>All other <code>SlideListNodes</code> in the list exist in the same order as before</p>
     * <p>The <code>cursor</code> now references the previous <code>SlideListNode</code>. If the previous
     * <code>SlideListNode</code> is the dummy node, the cursor is set to the next node. If there are no other nodes,
     * the cursor is set to <code>null</code></p>
     *
     * @return
     *  Returns the reference to the <code>Slide</code> contained within the <code>SlideListNode</code> which was just
     *  removed from the list
     *
     * @throws EndOfListException
     *  Thrown if <code>cursor</code> is <code>null</code>
     */
    public Slide removeCursor() throws EndOfListException {
        if (cursor == null)
            throw new EndOfListException("Cannot perform operation on an empty list");
        Slide removed = cursor.getData();
        cursor.getPrev().setNext(cursor.getNext());
        if (cursor != tail)
            cursor.getNext().setPrev(cursor.getPrev());
        else
            tail = tail.getPrev()==head ? null : tail.getPrev();
        cursor = cursor.getPrev()==head ? cursor.getNext() : cursor.getPrev();

        numSlides--;
        return removed;
    }

    /**
     * Gets the String representation for the <code>SlideList</code> object
     *
     * @return
     *  Returns a printable String representation of the slideshow summary
     */
    public String toString() {
        String summary="Slideshow Summary:\n"+"=".repeat(47)+"\n";
        summary+=String.format("%7s%9s%17s%10s%n","Slide","Title","Duration","Bullets");
        summary+="-".repeat(47)+"\n";
        SlideListNode current = head.getNext();
        int i=1;
        while (current != null) {
            String title = current.getData().getTitle();
            double duration = current.getData().getDuration();
            summary+=String.format("%-3s%-8d%-14s%-11.1f%-3d%n",
              (cursor==current ? "->" : ""), i++,(title.length()>13 ? title.substring(0,13) : title),
              (duration==-1 ? null : duration), current.getData().getNumBullets());
            current = current.getNext();
        }
        summary+="=".repeat(47)+"\n";
        summary+=String.format("Total: %d slides(s), %.1f minute(s), %d bullet(s)%n",size(),duration(),numBullets());
        summary+="=".repeat(47)+"\n";
        return summary;
    }
}
