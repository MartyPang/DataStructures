package ecnu.dase.patriciatrie;

import java.util.LinkedList;
import java.util.List;

/**
 * @author MartyPang
 * @version 1.0
 * @date 2019/5/31 9:53
 */
public class PatriciaTrieNode {
    public String key;
    public Integer value;
    List<PatriciaTrieNode> children;
    boolean isTerminal;

    public PatriciaTrieNode() {
        key = "";
        value = null;
        children = new LinkedList<>();
    }

    public PatriciaTrieNode(String key) {
        this.key = key;
        this.value = null;
        children = new LinkedList<>();
    }

    public PatriciaTrieNode(String key, Integer value) {
        this.key = key;
        this.value = value;
        children = new LinkedList<>();
    }

    public int getEqualIndex(String searchKey) {
        int len = Math.min(searchKey.length(), key.length());
        int j = 0;
        for(; j < len; ++j) {
            if(searchKey.charAt(j) != key.charAt(j)) break;
        }
        return j;
    }
}
