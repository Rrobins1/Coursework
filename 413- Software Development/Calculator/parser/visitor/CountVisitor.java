package visitor;

import ast.AST;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lowell Milliken
 */
public class CountVisitor extends ASTVisitor {

    private int[] nCount = new int[100];
    private int depth = 0;
    private int maxDepth = 0;

    private void count( AST t ) {
        nCount[depth]++;

        if ( depth > maxDepth ) {
            maxDepth = depth;
        }

        depth++;
        visitKids( t );
        depth--;
    }

    public int[] getCount() {
        int[] count = new int[maxDepth + 1];

        for ( int i = 0; i <= maxDepth; i++ ) {
            count[i] = nCount[i];
        }

        return count;
    }

    public void printCount() {
        for ( int i = 0; i <= maxDepth; i++ ) {
            System.out.println( "Depth: " + i + " Nodes: " + nCount[i] );
        }
    }

    @Override
    public Object visitProgramTree( AST t ) {
        count( t );
        return null;
    }

    @Override
    public Object visitBlockTree( AST t ) {
        count( t );
        return null;
    }

    @Override
    public Object visitFunctionDeclTree( AST t ) {
        count( t );
        return null;
    }

    @Override
    public Object visitCallTree( AST t ) {
        count( t );
        return null;
    }

    @Override
    public Object visitDeclTree( AST t ) {
        count( t );
        return null;
    }

    @Override
    public Object visitIntTypeTree( AST t ) {
        count( t );
        return null;
    }

    @Override
    public Object visitBoolTypeTree( AST t ) {
        count( t );
        return null;
    }

    @Override
    public Object visitFormalsTree( AST t ) {
        count( t );
        return null;
    }

    @Override
    public Object visitActualArgsTree( AST t ) {
        count( t );
        return null;
    }

    @Override
    public Object visitIfTree( AST t ) {
        count( t );
        return null;
    }

    @Override
    public Object visitWhileTree( AST t ) {
        count( t );
        return null;
    }

    @Override
    public Object visitReturnTree( AST t ) {
        count( t );
        return null;
    }

    @Override
    public Object visitAssignTree( AST t ) {
        count( t );
        return null;
    }

    @Override
    public Object visitIntTree( AST t ) {
        count( t );
        return null;
    }

    @Override
    public Object visitIdTree( AST t ) {
        count( t );
        return null;
    }

    @Override
    public Object visitRelOpTree( AST t ) {
        count( t );
        return null;
    }

    @Override
    public Object visitAddOpTree( AST t ) {
        count( t );
        return null;
    }

    @Override
    public Object visitMultOpTree( AST t ) {
        count( t );
        return null;
    }

    //new methods here
    public Object visitFloatTree( AST t ) {
        count( t );
        return null;
    }

    @Override
    public Object visitFloatTypeTree( AST t ) {
        count( t );
        return null;
    }

    public Object visitVoidTree( AST t ) {
        count( t );
        return null;
    }

    @Override
    public Object visitVoidTypeTree( AST t ) {
        count( t );
        return null;
    }

    public Object visitRepeatTree( AST t ) {
        count( t );
        return null;
    }

}
