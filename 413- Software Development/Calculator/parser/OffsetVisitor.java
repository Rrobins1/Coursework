

package visitor;

import ast.AST;
import java.util.*;


public class OffsetVisitor extends ASTVisitor
{
    private int numberOfNodes = 0;
    private int currentDepth = 0;
    private int maxDepth = 0;
    private int nextOffsetAtDepth[] = new int[100];
    private HashMap<Integer, Integer> nodeOffsets = new HashMap();
    
    
    private void offsets(AST t) 
    {   
        numberOfNodes++;
        
        if(currentDepth > maxDepth) {maxDepth = currentDepth;}
        
        initializeOffset(t);
        currentDepth++;
        visitKids(t);
        currentDepth--;
        recursivelyChangeOffsets(t);
    }
    
    private void initializeOffset(AST t)
    {
        nodeOffsets.put ( t.getNodeNum(), nextOffsetAtDepth[currentDepth] );
        nextOffsetAtDepth[currentDepth] += 2;
    }
    
    private void recursivelyChangeOffsets(AST t)
    {
        if(t.kidCount() != 0)
        {
            int currentOffset = nodeOffsets.get( t.getNodeNum() );
            int leftMostChild = t.getKid(1).getNodeNum();
            int rightMostChild = t.getKid( t.kidCount() ).getNodeNum();
            int calculatedOffsetPlacement = ( nodeOffsets.get(leftMostChild) + nodeOffsets.get(rightMostChild) ) /2;
            if ( calculatedOffsetPlacement > currentOffset ) 
            {
                nodeOffsets.replace(t.getNodeNum(), calculatedOffsetPlacement);
                nextOffsetAtDepth[currentDepth] = nodeOffsets.get( t.getNodeNum() ) + 2;
            }
            else if (calculatedOffsetPlacement < currentOffset)
            {
                int offsetDifference = currentOffset - calculatedOffsetPlacement;
                nodeOffsets.replace(leftMostChild, nodeOffsets.get(leftMostChild) + offsetDifference);
                if(t.kidCount() != 1)
                {
                    nodeOffsets.replace(rightMostChild, nodeOffsets.get(rightMostChild) + offsetDifference);
                    nextOffsetAtDepth[currentDepth + 1] = nodeOffsets.get(rightMostChild) + 2;
                }
                recursivelyChangeOffsets(t.getKid(1));
            }
        }
    }
    

    public HashMap getOffsetTable()
    {
        return nodeOffsets;
    }
    
    public int getNumberOfNodes()
    {
        return numberOfNodes;
    }
    
    public void printOffsets() 
    {
        for (int index = 1; index <= numberOfNodes; index++)
        {
            System.out.println("Node: " + index + "\t" +
                                "Offset: " + nodeOffsets.get(index)
            );
        }
    }
  
    public int getMaxDepth()
    {
        return maxDepth;
    }
    
    
    public Object visitProgramTree(AST t) {offsets(t); return null;}
    public Object visitBlockTree(AST t) {offsets(t); return null;}
    public Object visitFunctionDeclTree(AST t) {offsets(t); return null;    }
    public Object visitCallTree(AST t) {offsets(t); return null;    }
    public Object visitDeclTree(AST t) {offsets(t); return null;    }
    public Object visitIntTypeTree(AST t) {offsets(t); return null;    }
    public Object visitBoolTypeTree(AST t) {offsets(t); return null;    }
    public Object visitFormalsTree(AST t) {offsets(t); return null;    }
    public Object visitActualArgsTree(AST t) {offsets(t); return null;    }
    public Object visitIfTree(AST t) {offsets(t); return null;    }
    public Object visitWhileTree(AST t) {offsets(t); return null;    }
    public Object visitReturnTree(AST t) {offsets(t); return null;    }
    public Object visitAssignTree(AST t) {offsets(t); return null;    }
    public Object visitIntTree(AST t) {offsets(t); return null;    }
    public Object visitIdTree(AST t) {offsets(t); return null;    }
    public Object visitRelOpTree(AST t) {offsets(t); return null;    }
    public Object visitAddOpTree(AST t) {offsets(t); return null;    }
    public Object visitMultOpTree(AST t) {offsets(t); return null;    }

    //new methods here
    public Object visitFloatTree(AST t) {offsets(t); return null;    }
    public Object visitFloatTypeTree(AST t) {offsets(t); return null;    }
    public Object visitVoidTree(AST t) {offsets(t); return null;    }
    public Object visitVoidTypeTree(AST t) {offsets(t); return null;    }
    public Object visitRepeatTree(AST t) {offsets(t); return null;   }
}
