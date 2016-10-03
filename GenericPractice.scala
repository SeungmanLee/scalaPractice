

class Reference[T] {
	private var contents: T = _
	def set(value: T) { contents = value }
	def get: T = contents
}


object IntegerReference {

	def main(args: Array[String]) {
	
		val cell = new Reference[Int]
		cell.set(30)
		println( "cell.get is : " + cell.get )

				

		val anyCell = new Reference[Any]
		anyCell.set("asdf")
		println( "anyCell.get is : " + anyCell.get )
	}
}


