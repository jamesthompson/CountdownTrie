package countdown

import annotation.tailrec
import java.io.File
import io.Source
import collection.mutable.HashMap

object Solver extends App {

	// Load the words
	val dictSrc =Source.fromFile(new File("/Users/James/Desktop/CountdownTrie/src/main/resources/dictionary.txt"))
	val words = dictSrc.getLines().toList
	
	val dictTrie = TrieNode(None, false, None)
	words.map(dictTrie.addWord)

	println(dictTrie.getWords().mkString("\n"))

}