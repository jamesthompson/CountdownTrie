package countdown

import scala.collection.mutable.ArrayBuffer

case class TrieNode(val c: Option[Char], 
										val isWord: Boolean,
										val parent: Option[TrieNode],
										val children: Array[Option[TrieNode]] = Array.fill(26)(None)) {

	def hasChildren = !children.forall(_ == None)

	def addWord(word: String) : Unit = word.isEmpty match {
		case true => ()
		case false => {
			val char = word.head - 'a'
			if(children(char) == None) children(char) = Some(TrieNode(Some(word.head), false, Some(this)))
			if(word.length > 1) children(char).get.addWord(word.tail) else {
				children(char) = Some(TrieNode(Some(word.head), true, Some(this)))
			}
		}
	}

	def getNode(char: Char) : Option[TrieNode] = children(char - 'a')

	def getWords(l : List[String] = List[String]()) : List[String] = (isWord, hasChildren) match {
			case (true, true) => toString :: children.flatMap(cw => cw).toList.flatMap { (node: TrieNode) => node.getWords(node.toString :: l) }.reverse
			case (false, true) => children.flatMap(cw => cw).toList.flatMap { (node: TrieNode) => node.getWords(node.toString :: l) }
			case (true, false) => List(toString)
			case (false, false) => Nil
	}

	override def toString : String = 
		if(parent == None) "" else parent.get.toString + c.getOrElse("").toString
}

