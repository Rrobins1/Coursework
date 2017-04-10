import java.util.*;

public abstract class Operator {
  public abstract int priority();  
  
  protected static final HashMap operators = new HashMap();
  static
  {
    operators.put( "+", new Addition() );
    operators.put( "-", new Subtraction() );
    operators.put( "*", new Multiplication() );
    operators.put( "/", new Division() );
    operators.put( "^", new Exponential() );
    operators.put( "(", new Parentheses() );
    operators.put( "#", new Sharp() );
  }
  
  public static Operator getOperator(String operatorToken )
  {
    return (Operator)operators.get(operatorToken);
  }
  

  
  public abstract Operand execute( Operand leftOpertor, Operand rightOperator );

  public static boolean check( String token ) 
  {
      return operators.containsKey(token);
  }
}
