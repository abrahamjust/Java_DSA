public class Trie 
{
    private TrieNode root;

    public Trie(){
        //root references a trie node structure
        root = new TrieNode();//root is empty
    }
    private class TrieNode
    {
        private TrieNode[] children;
        private boolean isWord;

        public TrieNode(){
            //26 cause 26 alphabets
            this.children = new TrieNode[26];
            //false if word not ended, true if last alphabet of the word
            this.isWord = false;
        }
    }    

    //method to insert a word into Trie
    public void insert(String word){
        //edge case
        if(word == null || word.isEmpty()){
            throw new IllegalArgumentException("Invalid input");
        }
        //convert the word to lowercase (as if there is a mix of upper and lower case letters, it will cause issues when we do implicit indexing)
        word = word.toLowerCase();
        //current first points to root
        TrieNode current = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            //for implicit indexing, when we subract the char from 'a', we can get an index that represents the letter
            //we don't actually store the character
            int index = c - 'a';
            //if the index is null, there is no connection with that, so create a new node and connect it with it
            if(current.children[index] == null){
                TrieNode node = new TrieNode();
                current.children[index] = node;
                current = node;
            } 
            //if the index is connected, then we just move to the next node connected to this
            //this means that another word has the same letter as the word we are entering now
            else{
                current = current.children[index];
            }
        }
        //at the end of the word, make the boolean value false
        current.isWord = true;
    }

    //to search for a word in the trie
    public boolean search(String word){
        //edge case
        if(word == null || word.isEmpty()){
            throw new IllegalArgumentException("Invalid input");
        }
        //current first points to root
        TrieNode currentNode = root;
        for(int i = 0; i < word.length(); i++){
            //salami slicing the char from string
            char c = word.charAt(i);
            //getting the index where the character should be if the word exists in the trie
            int index = c - 'a';
            //if the index where the char should be is null, then return false as word is not there in trie
            if(currentNode.children[index] == null){
                return false;
            }
            //move to the next node connected to current node
            currentNode = currentNode.children[index];
        }
        //return the current boolean value ofcurrent node
        //reason why we don't return 'true' directly is cause:
        //eg let trie have the word 'hello' in it, if we search for word 'hell', the if condition is never satisfied as 'hell' is contained in
        //'hello', so if we just put 'true', it will return 'true', even if it is not the case
        return currentNode.isWord;
    }

    public static void main(String[] args) {
        //creating the trie
        Trie t = new Trie();

        //inserting values to the trie
        t.insert("hello");
        t.insert("world");

        //searching if a word is present or not
        System.out.println(t.search("helloded"));
        System.out.println(t.search("hell"));
        System.out.println(t.search("hello"));
    }
}
