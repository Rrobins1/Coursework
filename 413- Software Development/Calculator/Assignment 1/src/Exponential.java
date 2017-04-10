/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Richard
 */
public class Exponential extends Parentheses
{
  public int priority()
  {
      return 4;
  };
  public Operand execute( Operand operandLeft, Operand operandRight )
  {
      return new Operand( (int)Math.pow ( operandLeft.getValue(), operandRight.getValue() ) );
  };
}
