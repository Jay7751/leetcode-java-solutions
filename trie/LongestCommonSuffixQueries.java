// Trie Node class
// Each node stores:
// 1. References to next characters
// 2. Minimum length word passing through this node
// 3. Index of that minimum length word
class TrieNode {
  
    // Stores child nodes for characters a-z
    TrieNode[] characters = new TrieNode[26];
    // Minimum word length passing through current node
    int minLen = Integer.MAX_VALUE;
    // Index of the word having minimum length
    int index = Integer.MAX_VALUE;
    // Constructor
    TrieNode() {
        // Initially no child nodes exist
        for (int i = 0; i < 26; i++) {
            characters[i] = null;
        }
    }
}

// Trie class
class Trie {
  
    // Root node of Trie
    TrieNode root = new TrieNode();
    // Inserts a reversed word into the Trie
    void insertWord(String word, int index) {
        int currLen = word.length();
        // Start traversal from root
        TrieNode node = root;
        // Update root with shortest word info
        if (currLen < node.minLen) {
            node.minLen = currLen;
            node.index = index;
        }
        // Traverse each character of the word
        for (char ch : word.toCharArray()) {

            // Convert character into array index
            int idx = ch - 'a';
            // Create new node if path does not exist
            if (node.characters[idx] == null) {
                node.characters[idx] = new TrieNode();
            }
            // Move to next node
            node = node.characters[idx];
            // Store shortest word information at every node
            if (currLen < node.minLen) {
                node.minLen = currLen;
                node.index = index;
            }
        }
    }
    // Searches for the best matching suffix
    int query(String word) {
        TrieNode node = root;
        // Traverse the Trie using reversed query word
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            // Continue traversal if matching character exists
            if (node.characters[idx] != null) {
                node = node.characters[idx];
            }
            // Stop when match breaks
            else {
                break;
            }
        }
        // Return stored best index
        return node.index;
    }
}
// Main Solution class
class Solution {
  
    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        // Create Trie
        Trie trie = new Trie();
        // Insert all reversed container words into Trie
        for (int i = 0; i < wordsContainer.length; i++) {
            String reversed = new StringBuilder(wordsContainer[i])
                    .reverse()
                    .toString();

            trie.insertWord(reversed, i);
        }
        // Result array
        int[] res = new int[wordsQuery.length];
        // Process each query word
        for (int i = 0; i < wordsQuery.length; i++) {
            String query = wordsQuery[i];
            // Reverse query string
            String reversed = new StringBuilder(query)
                    .reverse()
                    .toString();
            // Find best matching index
            res[i] = trie.query(reversed);
        }
        // Return final answers
        return res;
    }
}
