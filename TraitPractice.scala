

trait Ord {
	def < (that: Any): Boolean
	def <=(that: Any): Boolean = (this < that) || (this == that)
	def > (that: Any): Boolean = !(this <= that)
	def >=(that: Any): Boolean = !(this < that)
}


class Date(y : Int, m: Int, d: Int) extends Ord {
	def year = y
	def month = m
	def day = d
	override def toString(): String = year + "-" + month + "-" + day

	override def equals(that: Any): Boolean =
		that.isInstanceOf[Date] && {
			val o = that.asInstanceOf[Date]
			o.day == day && o.month == month && o.year == year
		}

	def < (that: Any): Boolean = {
		if( ! that.isInstanceOf[Date])
			sys.error("cannot compare " + that + " and a Date")

		val o = that.asInstanceOf[Date]
		(year < o.year) || 
		(year == o.year && (month < o.month ||
						   (month == o.month && day < o.day)))

	}

}


object TraitPractice {
	
	def main(args : Array[String] ) {
		val date1 = new Date(2016, 10, 1);
		val date2 = new Date(2016, 10, 2);

		println( "ret 1 <  : " + (date1 < date2) )
		println( "ret 2 >  : " + (date1 > date2) )
		println( "ret 3 <= : " + (date1 <= date2) )
		println( "ret 4 >= : " + (date1 >= date2) )
		println( "ret 5 == : " + (date1 == date2) )

/**
date1 = 2016,10,1
date2 = 2016,10,2 

ret 1 <  : true
ret 2 >  : false
ret 3 <= : true
ret 4 >= : false
ret 5 == : false

**/

/**
date1 = 2016,10,1
date2 = 2016,10,1

ret 1 <  : false
ret 2 >  : false
ret 3 <= : true
ret 4 >= : true
ret 5 == : true

**/

	}
}







