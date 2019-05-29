package ecnu.dase.trie;

import java.util.HashMap;

/**
 * @author MartyPang
 * @version 1.0
 * @date 2019/5/29 17:47
 */
public class TrieNode {
    static final int CHAR_SIZE = 256;

    public String value;
    public HashMap<String, TrieNode> children;
    public boolean isEndOfWord;

    public TrieNode() {
        children = new HashMap<>(CHAR_SIZE);
        isEndOfWord = false;
    }
}
