/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Richard
 */
public class Subtraction extends Operator
{
  public int priority()
  {
      return 2;
  };
  public Operand execute( Operand operandLeft, Operand operandRight )
  {
      return new Operand( operandLeft.getValue() - operandRight.getValue());
  }; 
}
