package ecnu.dase.patriciatrie;

import java.util.LinkedList;

/**
 * @author MartyPang
 * @version 1.0
 * @date 2019/5/31 10:15
 */
public class PatriciaTrie {
    PatriciaTrieNode root;

    public PatriciaTrie() {
        root = new PatriciaTrieNode();
    }

    public Integer search(String key) {
        //check parameters
        if(key == null || key.length() == 0) return null;

        return search(root, key);
    }

    private Integer search(PatriciaTrieNode node, String key) {
        for(PatriciaTrieNode child : node.children) {
            int index = child.getEqualIndex(key);
            if(index == 0) {
                if(key.compareTo(child.key) < 0) return null;
                continue;
            }
            // key and node.key are partially overlapped
            // child.key="abc", key="abd", index=2
            if(index < Math.min(key.length(), child.key.length())) return null;
            // child.key="abc", key="abc"
            if(key.length() == child.key.length()) {
                // find a match
                if(child.isTerminal) return child.value;
                return null;
               }
            // child.key="abc", key="ab", index=2
            else if (key.length() < child.key.length()) {
                return null;
            }
            //child.key="ab", key="abc", remains="c"
            else {
                String remains = key.substring(index);
                return search(child, remains);
            }
        }
        return null;
    }

    public void insert(String key, Integer value) {
        //check parameters
        if(key == null || key.length() == 0) return;

        insert(root, key, value);
    }

    public void insert(PatriciaTrieNode node, String key, Integer value) {
        int i = 0;
        for(; i < node.children.size(); ++i) {
            PatriciaTrieNode child = node.children.get(i);
            int index = child.getEqualIndex(key);
            if(index == 0) {
                //new branch. child.key="bc", key="a"
                if(key.compareTo(child.key) < 0) {
                    PatriciaTrieNode newNode = new PatriciaTrieNode(key, value);
                    newNode.isTerminal = true;
                    // maintain a lexi-order
                    node.children.add(i, newNode);
                    break;
                }
                // key is greater than all node's children
                if(i == node.children.size()-1) {
                    PatriciaTrieNode newNode = new PatriciaTrieNode(key, value);
                    newNode.isTerminal = true;
                    node.children.add(newNode);
                }
                continue;
            }
            if(index == Math.min(key.length(), child.key.length())) {
                if(key.length() == child.key.length()) {
                    if(child.isTerminal) {
                        //or throw exception, duplicate key
                        return;
                    }
                    child.isTerminal = true;
                    child.value = value;
                }
                // child.key="abc", key="ab", index=2
                else if(key.length() < child.key.length()) {
                    //split child
                    PatriciaTrieNode splitNode = new PatriciaTrieNode(child.key.substring(index), child.value);
                    splitNode.isTerminal = child.isTerminal;
                    splitNode.children = child.children;

                    child.key = child.key.substring(0, index);
                    // key ends here
                    child.isTerminal = true;
                    child.value = value;
                    child.children = new LinkedList<>();
                    child.children.add(splitNode);

                }
                // child.key="ab", key="abc"
                else {
                    // recursively insert the remaining key
                    String remains = key.substring(index);
                    insert(child, remains, value);
                }
            }
            // index < min length
            // child.key="abc", key="abde"
            // split both child.key and key
            else {
                //split child
                String subchildkey = child.key.substring(index);
                PatriciaTrieNode subchild = new PatriciaTrieNode(subchildkey, child.value);
                subchild.isTerminal = child.isTerminal;
                subchild.children = child.children;

                // split inserted key
                String subkey = key.substring(index);
                PatriciaTrieNode newChild = new PatriciaTrieNode(subkey, value);
                newChild.isTerminal = true;

                //update child
                child.key = child.key.substring(0, index);
                child.isTerminal = false;
                child.children = new LinkedList<>();
                // add children according to lexi-order
                if(subkey.compareTo(subchildkey) > 0) {
                    child.children.add(subchild);
                    child.children.add(newChild);
                }
                else {
                    child.children.add(newChild);
                    child.children.add(subchild);
                }
            }
        }
        // node has no child
        // directly insert a new child
        if(i == 0) {
            PatriciaTrieNode newNode = new PatriciaTrieNode(key, value);
            newNode.isTerminal = true;
            node.children.add(newNode);
        }
    }

    public void delete(String key) {
        if(key == null || key.length() == 0) return;

        delete(root, key);
    }

    private void delete(PatriciaTrieNode node, String key) {
        for(int i = 0; i < node.children.size(); ++i) {
            PatriciaTrieNode child = node.children.get(i);
            int index = child.getEqualIndex(key);
            if(index == 0) {
                if(key.compareTo(child.key) < 0) {
                    // or throw no such key exception
                    return;
                }
                if(i == node.children.size() - 1) {
                    // or throw no such key exception
                    return;
                }
                continue;
            }
            // index < min length
            if(index < Math.min(key.length(), child.key.length())) {
                // or throw no such key exception
                return;
            }
            // if(index == Math.min(key.length(), child.key.length()))
            if(key.length() == child.key.length()) {
                // key found
                // delete child, and merge nodes if necessary
                if(child.isTerminal) {
                    //e.g. child="ab#", key="ab", node="a"
                    //      a
                    //     / \
                    //    d  ab#
                    if(child.children.size() == 0) {
                        node.children.remove(i);
                        // merge nodes for node
                        if(!node.isTerminal && node.children.size() == 1) {
                            mergeNodes(node, node.children.get(0));
                        }
                    }
                    else {
                        child.isTerminal = false;
                        // merge nodes for child
                        if(child.children.size() == 1) {
                            mergeNodes(child, child.children.get(0));
                        }
                    }
                }
                else {
                    // or throw no such key exception
                    return;
                }
            }
            // child="abc#", key="ab", node="a"
            //     a
            //    / \
            //   a  abc#
            else if(key.length() < child.key.length()) {
                // or throw no such key exception
                return;
            }
            else {
                String remains = key.substring(index);
                delete(child, remains);
            }
        }
    }

private void mergeNodes(PatriciaTrieNode parent, PatriciaTrieNode onlyChild) {
    parent.key += onlyChild.key;
    parent.value = onlyChild.value;
    parent.isTerminal = onlyChild.isTerminal;
    parent.children = onlyChild.children;
}

    public void printTree() {
        this.print(0, this.root);
    }

    private void print(int level, PatriciaTrieNode node) {
        for (int i = 0; i < level; i++) {
            System.out.format(" ");
        }
        System.out.format("|");
        for (int i = 0; i < level; i++) {
            System.out.format("-");
        }
        if (node.isTerminal) System.out.format("%s - %s #%n", node.key, node.value);
        else System.out.format("%s%n", node.key);
        for (PatriciaTrieNode child : node.children) {
            print(level + 1, child);
        }
    }
}
