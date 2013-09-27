package countdown

import java.io.File
import io.Source

object Solver extends App {

  // Load the words
  val dictSrc =Source.fromFile(new File("/Users/James/Desktop/CountdownTrie/src/main/resources/dictionary.txt"))
  val words = dictSrc.getLines().toList
  // Make a root node
  val dictTrie = TrieNode(None, false, None)
  // Load all the words
  words.map(dictTrie.addWord)

  dictTrie.graph

}