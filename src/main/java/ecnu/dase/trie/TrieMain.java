package ecnu.dase.trie;

/**
 * @author MartyPang
 * @version 1.0
 * @date 2019/5/29 18:27
 */
public class TrieMain {
    public static void main(String[] args) {
        TrieTree trie = new TrieTree();

        trie.insertWord("add");
        trie.insertWord("egg");
        trie.insertWord("and");
        trie.insertWord("ethereum");
        trie.insertWord("nike");
        trie.printTrie();

        System.out.println(trie.search("egg"));
        System.out.println(trie.search("eqt"));

        trie.delete("nike");
        trie.printTrie();
        trie.delete("and");
        trie.printTrie();
    }
}
