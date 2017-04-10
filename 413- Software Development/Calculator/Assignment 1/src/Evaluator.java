import java.util.*;

public class Evaluator {
  private Stack<Operand> operandStack;
  private Stack<Operator> operatorStack;

  private StringTokenizer tokenizer;
  private static final String DELIMITERS = "+-*^/#!)( ";

  public Evaluator() {
    operandStack = new Stack<>();
    operatorStack = new Stack<>();
  }
  
  /* =====================================================
        void executeOnce()
        Undergoes one execution of the given operation.
  =======================================================*/
  private void executeOnce()
  {
      Operator oldOpr = operatorStack.pop();
      Operand operandRight = operandStack.pop();
      Operand operandLeft = operandStack.pop();
      operandStack.push( oldOpr.execute( operandLeft, operandRight ));
  }
  
  /* =====================================================
        int eval (String)
        Takes: A valid string for a mathamatical expression
        Returns: Solution to the expression 
  =======================================================*/ 
  
  public int eval( String expression ) 
  {
    String token;
    this.tokenizer = new StringTokenizer( expression, DELIMITERS, true );
    operatorStack.push( new Sharp() );

    while ( this.tokenizer.hasMoreTokens() ) 
    {
      if ( !( token = this.tokenizer.nextToken() ).equals( " " )) 
      {
        if ( Operand.check( token )) 
        {
          operandStack.push( new Operand( token ));
        } 
        else if ( token.equals( ")" ))
        {
          while ( operatorStack.peek().getClass() != Parentheses.class ) 
          {  
            executeOnce();
          }      
          operatorStack.pop();
        }
        
        else
        {
          if ( ! Operator.check( token )) 
          {
            System.out.println( "*****invalid token******" + token );
            System.exit( 1 );
          }

          Operator newOperator =  Operator.getOperator( token );

          while ( operatorStack.peek().priority() >= newOperator.priority() 
                        && newOperator.getClass() != Parentheses.class ) 
          {
            executeOnce();
          }
          operatorStack.push( newOperator );
        }
      }
    }
    
    while ( operatorStack.peek().getClass() != Sharp.class )
    {
        executeOnce();
    }
    return operandStack.pop().getValue();
  }
}
