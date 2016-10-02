

abstract class Tree
case class Sum(l: Tree, r: Tree) extends Tree
case class Var(n: String) extends Tree
case class Const(v: Int) extends Tree


/**
scalac Tree.scala
scala -classpath . Treeclass
**/


object Treeclass {
	/**
	case "x" => 5    일종의 map 의 개념으로 이해를 시작. 
	String x 로 표현되면, Int 5 를 사용한다. 의 개념으로 시작. 
	**/
	type Environment = String => Int

	/**
	recursive 하게 표현형식에 따라서 호출.
	Tree t 에 대하여 매칭되는 case 로 분기되고, => 뒤의 표현식을 수행함.   
	**/
	def eval(t: Tree, env: Environment): Int = t match {
	  case Sum(l, r) => eval(l, env) + eval(r, env)
	  case Var(n)    => env(n)
	  case Const(v)  => v
	}

	/**
	eval 과 동일한 구조. 계산식이 어떻게 진행될 것인지, 변수가 어느구간에 사용되는지 확인할 수 있음. 
	**/
	def derive(t: Tree, v:String): Tree = t match {
	  case Sum(l, r) => Sum(derive(l, v), derive(r, v))
	  case Var(n) if (v == n) => Const(1)
	  case _ => Const(0)
	}

	def main(args: Array[String]) {
	  val exp: Tree = Sum(Sum(Var("x"), Var("x")), Sum(Const(7),Var("y")))
	  val env: Environment = { case "x" => 5 case "y" => 7 }
      
      println("env : " + env)
      println("env x : " + env("x"))
	  println("env y : " + env("y"))
//      println("env z : " + env("z"))     // scala.matchError 발생. 
	  println("expression: " + exp)


	  println("Evaluation with x=5, y=7: " + eval(exp, env))
      /**
	  Sum(Sum(Var("x"), Var("x")), Sum(Const(7),Var("y")))
	  l => Sum(Var("x"), Var("x")) 
	       l => Var("x")
		        n => "x" => 5 
		   r => Var("x")
	            n => "x" => 5
		   ::=> 5 + 5 = 10 
	  r => Sum(Const(7),Var("y"))
	       l => Const(7)
		        v => 7 
		   r => Var("y")
		        n => "y" = 7
	       ::=> 7 + 7 = 14
	  ::=> 10 + 14 = 24 	   
	  **/

      println("Derivative relative to x:\n " + derive(exp, "x"))
	  /**
	  Sum(Sum(Var("x"), Var("x")), Sum(Const(7),Var("y")))
	  l => Sum(Var("x"), Var("x"))
	       l => Var("x")
		        n="x" == "x"  => 1
		   r => Var("x")
		        n="x" == "x"  => 1
	  r => Sum(Const(7),Var("y"))
	       l => Const(7)
		        _ => Const(0) => 0
		   r => Var("y")
		        n="x" != "y"  => 0
	  **/

      println("Derivative relative to y:\n " + derive(exp, "y"))
	  /**
	  Sum(Sum(Var("x"), Var("x")), Sum(Const(7),Var("y")))
	  l => Sum(Var("x"), Var("x"))
	       l => Var("x")
		        n="x" != "y"  => 0
		   r => Var("x")
		        n="x" != "y"  => 0
	  r => Sum(Const(7),Var("y"))
	       l => Const(7)
		        _ => Const(0) => 0
		   r => Var("y")
		        n="y" == "y"  => 1 
	  **/



	}

}







