package ecnu.dase.patriciatrie;

/**
 * @author MartyPang
 * @version 1.0
 * @date 2019/5/31 15:56
 */
public class PatriciaMain {
    public static void main(String[] args) {
        PatriciaTrie pt = new PatriciaTrie();
        pt.insert("apple", 2);
        pt.insert("amend", 1);
        pt.insert("application", 4);
        pt.insert("car", 10);
        pt.insert("card", 11);
        pt.insert("bitcoin", 5);
        pt.insert("bitmap", 9);
        System.out.println("After inserting: ");
        pt.printTree();

        System.out.println("Search key: \"application\". " + pt.search("application"));

        pt.delete("application");
        System.out.println("After deleting \"application\": ");
        pt.printTree();

        System.out.println("Search key: \"application\". " + pt.search("application"));
    }
}
