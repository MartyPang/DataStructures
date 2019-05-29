package ecnu.dase.trie;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author MartyPang
 * @version 1.0
 * @date 2019/5/29 17:49
 */
public class TrieTree {
    private TrieNode root;

    public TrieTree() {
        root = new TrieNode();
    }

    /**
     * insert a word to trie
     * Time complexity: O(n)
     * @param word the inserted word
     */
    public void insertWord(String word) {
        if(word.isEmpty()) return;
        TrieNode cur = root;
        for(char c : word.toCharArray()) {
            TrieNode next = cur.children.get(c+"");
            if(next == null) {
                next = new TrieNode();
                next.value = c+"";
                cur.children.put(c+"", next);
            }
            cur = next;
        }
        cur.isEndOfWord = true;
    }

    /**
     * if word exists in trie
     * @param word
     * @return true if exists
     */
    public boolean search(String word) {
        TrieNode cur = root;
        for(char c : word.toCharArray()) {
            if(!cur.children.containsKey(c+"")) return false;
            cur = cur.children.get(c+"");
        }
        return cur.isEndOfWord;
    }

    public void delete(String word) {
        delete(root, word, 0);
    }

    public boolean delete(TrieNode node, String word, int index) {
        if(index == word.length()) {
            // word does not exist in the trie
            if(!node.isEndOfWord) {
                return false;
            }
            // if node has no other child, it's safe to delete word
            node.isEndOfWord = false;
            return node.children.isEmpty();
        }
        char c = word.charAt(index);
        TrieNode next = node.children.get(c+"");
        if(next == null) {
            return false;
        }
        boolean shouldDelete = delete(next, word, index+1) && !node.isEndOfWord;

        if(shouldDelete) {
            node.children.remove(c+"");
            // make sure there is only one word to be deleted
            return node.children.isEmpty();
        }
        return false;
    }

    public void printTrie() {
        TrieNode cur = root;
        int level = 1;
        Queue<TrieNode> queue = new LinkedList<>();
        queue.offer(cur);
        while(!queue.isEmpty()) {
            System.out.print("Level " + level++ + ":");
            int size = queue.size();
            for(int i = 0; i < size; ++i) {
                TrieNode n = queue.poll();
                System.out.print(" " + n.value);
                for(Map.Entry<String, TrieNode> entry : n.children.entrySet()) {
                    queue.offer(entry.getValue());
                }
            }
            System.out.print("\n");
        }
    }
}
