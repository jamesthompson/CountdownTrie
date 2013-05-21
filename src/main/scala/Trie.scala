package countdown

case class TrieNode(val c: Option[Char], 
										val isWord: Boolean,
										val parent: Option[TrieNode],
										val children: Array[Option[TrieNode]] = Array.fill(26)(None)) {

	def hasChildren = !children.forall(_ == None)

	def depth(i: Int = 0) : Int = parent match { 
		case Some(p) => p.depth(i + 1) 
		case None => i
	}

	def addWord(word: String) : Unit = 
		if(word.isEmpty) () else {
			val char = word.head - 'a'
			if(children(char) == None) 
				children(char) = Some(TrieNode(Some(word.head), false, Some(this)))
			if(word.length > 1) children(char).get.addWord(word.tail) else 
				children(char) = Some(TrieNode(Some(word.head), true, Some(this)))
	}

	def getNode(char: Char) : Option[TrieNode] = children(char - 'a')

	def getWords(l : List[String] = List[String]()) : List[String] = 
		(isWord, hasChildren) match {
			case   (true, true) =>	toString :: children.flatMap(cw => cw).toList.flatMap { 
																(node: TrieNode)  => node.getWords(node.toString :: l) 
															}.reverse
			case 	(false, true) => 	children.flatMap(cw => cw).toList.flatMap { 
																(node: TrieNode)  => node.getWords(node.toString :: l) }
			case  (true, false) => 	List(toString)
			case (false, false) =>	Nil
	}

	def wordsFromPrefix(s: String, node: TrieNode = this) = 
		nodeForPrefix(s, node) match {
			case Some(n) => n.getWords()
			case None => Nil
		}

	def nodeForPrefix(s: String, node: TrieNode = this) 
		: Option[TrieNode] = s.toList match {
			case Nil => Some(node)
			case h :: t => node.getNode(s.head).flatMap { nodeForPrefix(s.tail.toString, _) }
	}

	def graph : Unit = println(printTrie())

	def printTrie(level: Int = 0): String = {
    var trieString: String = ""
    val kids = this.children.flatMap { node => node } 
    kids.foreach { node => trieString +=  { 
    	if(node.isWord) {
    		"-" * level + node.toString + "\n"
    	} else "" } + node.printTrie(node.depth()) }
    trieString
  }

	override def toString : String = parent match {
		case Some(p) => p.toString + c.getOrElse("").toString
		case None 	 => ""
	}

}

