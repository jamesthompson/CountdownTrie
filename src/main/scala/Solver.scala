package countdown

import annotation.tailrec
import java.io.File
import io.Source
import collection.mutable.HashMap

object Solver extends App {

	// Load the words
	val dictSrc =Source.fromFile(new File("/Users/James/Desktop/CountdownTrie/src/main/resources/dictionary.txt"))
	val words = dictSrc.getLines().toList
	
	val hmap = new HashMap[Trie[Char], Trie[Char]]()
  for(c <- 'a' to 'z') hmap + (Trie(Some(c), new HashMap[Trie[Char], Trie[Char]]) -> Trie(None, new HashMap[Trie[Char], Trie[Char]]))
  val dict = Trie(None, hmap)

	println(dict.mapping.mkString("\n"))


}