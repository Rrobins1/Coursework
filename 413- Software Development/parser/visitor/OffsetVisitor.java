

package visitor;

import ast.AST;

import java.util.*;


public class OffsetVisitor extends ASTVisitor {
    private int numberOfNodes = 0;
    private int currentDepth = 0;
    private int maxDepth = 0;
    private int nextOffsetAtDepth[] = new int[100];
    private HashMap<Integer, Integer> nodeOffsets = new HashMap();

    private void offsets( AST t ) {
        numberOfNodes++;
        if ( currentDepth > maxDepth ) {
            maxDepth = currentDepth;
        }

        if ( initializeLeafOffsets( t ) ) return;
        currentDepth++;
        visitKids( t );
        currentDepth--;
        calculateOffsets( t );
    }

    private boolean initializeLeafOffsets( AST t ) {
        if ( t.kidCount() == 0 ) {
            nodeOffsets.put( t.getNodeNum(), nextOffsetAtDepth[currentDepth] );
            nextOffsetAtDepth[currentDepth] += 2;
            return true;
        }
        return false;
    }

    private void calculateOffsets( AST t ) {
        nodeOffsets.put( t.getNodeNum(), nextOffsetAtDepth[currentDepth] );
        nextOffsetAtDepth[currentDepth] = nodeOffsets.get( t.getNodeNum() ) + 2;

        int currentOffset = nodeOffsets.get( t.getNodeNum() );
        int leftMostChild = t.getKid( 1 ).getNodeNum();
        int rightMostChild = t.getKid( t.kidCount() ).getNodeNum();
        int calculatedOffsetPlacement = (nodeOffsets.get( leftMostChild ) + nodeOffsets.get( rightMostChild )) / 2;

        if ( calculatedOffsetPlacement > currentOffset ) {
            nodeOffsets.replace( t.getNodeNum(), calculatedOffsetPlacement );
            nextOffsetAtDepth[currentDepth] = nodeOffsets.get( t.getNodeNum() ) + 2;
        } else if ( calculatedOffsetPlacement < currentOffset ) {
            int offsetDifference = currentOffset - calculatedOffsetPlacement;
            shiftKids( t, offsetDifference );
        }
    }

    private void shiftKids( AST t, int shiftAmount ) {
        if ( t.kidCount() == 0 ) return;
        for ( int index = 1; index <= t.kidCount(); index++ ) {
            nodeOffsets.replace( t.getKid( index ).getNodeNum(), nodeOffsets.get( t.getKid( index ).getNodeNum() ) + shiftAmount );
        }
        nextOffsetAtDepth[currentDepth + 1] = nodeOffsets.get( t.getKid( t.kidCount() ).getNodeNum() ) + 2;

        for ( int index = 1; index <= t.kidCount(); index++ ) {
            currentDepth++;
            shiftKids( t.getKid( index ), shiftAmount );
            currentDepth--;
        }
    }

    public HashMap getOffsetTable() {
        return nodeOffsets;
    }

    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    public void printOffsets() {
        for ( int index = 1; index <= numberOfNodes; index++ ) {
            System.out.println( "Node: " + index + "\t" +
                    "Offset: " + nodeOffsets.get( index )
            );
        }
    }

    public int getMaxDepth() {
        return maxDepth;
    }


    @Override
    public Object visitProgramTree( AST t ) {
        offsets( t );
        return null;
    }

    @Override
    public Object visitBlockTree( AST t ) {
        offsets( t );
        return null;
    }

    @Override
    public Object visitFunctionDeclTree( AST t ) {
        offsets( t );
        return null;
    }

    @Override
    public Object visitCallTree( AST t ) {
        offsets( t );
        return null;
    }

    @Override
    public Object visitDeclTree( AST t ) {
        offsets( t );
        return null;
    }

    @Override
    public Object visitIntTypeTree( AST t ) {
        offsets( t );
        return null;
    }

    @Override
    public Object visitBoolTypeTree( AST t ) {
        offsets( t );
        return null;
    }

    @Override
    public Object visitFormalsTree( AST t ) {
        offsets( t );
        return null;
    }

    @Override
    public Object visitActualArgsTree( AST t ) {
        offsets( t );
        return null;
    }

    @Override
    public Object visitIfTree( AST t ) {
        offsets( t );
        return null;
    }

    @Override
    public Object visitWhileTree( AST t ) {
        offsets( t );
        return null;
    }

    @Override
    public Object visitReturnTree( AST t ) {
        offsets( t );
        return null;
    }

    @Override
    public Object visitAssignTree( AST t ) {
        offsets( t );
        return null;
    }

    @Override
    public Object visitIntTree( AST t ) {
        offsets( t );
        return null;
    }

    @Override
    public Object visitIdTree( AST t ) {
        offsets( t );
        return null;
    }

    @Override
    public Object visitRelOpTree( AST t ) {
        offsets( t );
        return null;
    }

    @Override
    public Object visitAddOpTree( AST t ) {
        offsets( t );
        return null;
    }

    @Override
    public Object visitMultOpTree( AST t ) {
        offsets( t );
        return null;
    }

    //new methods here
    public Object visitFloatTree( AST t ) {
        offsets( t );
        return null;
    }

    @Override
    public Object visitFloatTypeTree( AST t ) {
        offsets( t );
        return null;
    }

    public Object visitVoidTree( AST t ) {
        offsets( t );
        return null;
    }

    @Override
    public Object visitVoidTypeTree( AST t ) {
        offsets( t );
        return null;
    }

    public Object visitRepeatTree( AST t ) {
        offsets( t );
        return null;
    }
}
