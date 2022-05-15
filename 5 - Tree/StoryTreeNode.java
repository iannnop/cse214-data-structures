/**
 *  The <code>StoryTreeNode</code> class represents a segment of the story in the <code>StoryTree</code>
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */

public class StoryTreeNode {
    /**
     * Special string sequence indicating a winning state
     */
    public static final String WIN_MESSAGE = "YOU WIN";
    /**
     * Special string sequence indicating a losing state
     */
    public static final String LOSE_MESSAGE = "YOU LOSE";
    /**
     * Position of the node relative to the root
     */
    private String position;
    /**
     * Option which will select the node
     */
    private String option;
    /**
     * Display message after this node has been selected
     */
    private String message;
    /**
     * Left child of the node
     */
    private StoryTreeNode leftChild;
    /**
     * Middle child of the node
     */
    private StoryTreeNode middleChild;
    /**
     * Right child of the node
     */
    private StoryTreeNode rightChild;

    /**
     * Getter method for the position of the node
     * @return
     *  position of the node
     */
    public String getPosition() { return position; }

    /**
     * Setter method for the node
     * @param position
     *  new position of the node relative to the root
     */
    public void setPosition(String position) { this.position = position; }

    /**
     * Getter method for the option of the node
     * @return
     *  option of the node
     */
    public String getOption() { return option; }

    /**
     * Setter method for the option of the node
     * @param option
     *  new option of the node
     */
    public void setOption(String option) { this.option = option; }

    /**
     * Getter method for the message of the node
     * @return
     *  message of the node
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter method for the message of the node
     * @param message
     *  new message of the node
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Getter method for the left child of the node
     * @return
     *  left child of this node
     */
    public StoryTreeNode getLeftChild() { return leftChild; }

    /**
     * Setter method for the left child of the node
     * @param leftChild
     *  new left child <code>StoryTreeNode</code> of this node
     */
    public void setLeftChild(StoryTreeNode leftChild) { this.leftChild = leftChild; }

    /**
     * Getter method for the middle child of the node
     * @return
     *  middle child of this node
     */
    public StoryTreeNode getMiddleChild() { return middleChild; }

    /**
     * Setter method for the middle child of the node
     * @param middleChild
     *  new middle child <code>StoryTreeNode</code> of this node
     */
    public void setMiddleChild(StoryTreeNode middleChild) { this.middleChild = middleChild; }

    /**
     * Getter method for the right child of the node
     * @return
     *  right child of this node
     */
    public StoryTreeNode getRightChild() { return rightChild; }

    /**
     * Setter method for the right child of the node
     * @param rightChild
     *  new right child <code>StoryTreeNode</code> of this node
     */
    public void setRightChild(StoryTreeNode rightChild) { this.rightChild = rightChild; }

    /**
     * Default constructor
     */
    public StoryTreeNode() { }

    /**
     * Constructor with <code>option</code> and <code>message</code> parameters
     * @param option
     *  Initial option of the node
     * @param message
     *  Initial message of the node
     */
    public StoryTreeNode(String option, String message) {
        this.option = option;
        this.message = message;
    }

    /**
     * Checks if the node is a leaf
     * @return
     *  True if this node has no children, false otherwise
     */
    public boolean isLeaf() { return leftChild==null&&middleChild==null&&rightChild==null; }

    /**
     * Checks of the node is a winning node
     * @return
     *  True if this node is a leaf and contains <code>WIN_MESSAGE</code>, false otherwise
     */
    public boolean isWinningNode() { return isLeaf()&&message.contains(WIN_MESSAGE); }

    /**
     * Checks if the node is a losing node
     * @return
     *  True if this node is a leaf and does not contain <code>WIN_MESSAGE</code>, false otherwise
     */
    public boolean isLosingNode() {
        return isLeaf()&&!message.contains(WIN_MESSAGE);
    }
}
