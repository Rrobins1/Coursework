package visitor;

import ast.AST;
import ast.AddOpTree;
import ast.IdTree;
import ast.IntTree;
import ast.MultOpTree;
import ast.RelOpTree;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.*;


public class DrawOffsetVisitor extends ASTVisitor {
    private final int nodeWidth = 100;
    private final int nodeHeight = 30;
    private final int verticalSeparation = 50;
    private final int horizontalSeparation = 10;

    private int width;
    private int height;

    private HashMap<Integer, Integer> nodeOffsets;
    private int depth = 0;
    private BufferedImage bimg;
    private Graphics2D g2;
    private int numberOfNodes;

    public DrawOffsetVisitor( HashMap<Integer, Integer> nodeOffsets, int numberOfNodes, int maxDepth ) {
        this.numberOfNodes = numberOfNodes;
        this.nodeOffsets = nodeOffsets;

        width = (maximumOffset( nodeOffsets ) + 1) * (nodeWidth + horizontalSeparation);
        height = (maxDepth + 1) * (nodeHeight + verticalSeparation);

        g2 = createGraphics2D( width, height );
    }

    private int maximumOffset( HashMap<Integer, Integer> nodeOffsets ) {
        int max = 0;
        for ( int index = 1; index <= numberOfNodes; index++ ) {
            if ( max < nodeOffsets.get( index ) ) max = nodeOffsets.get( index );
        }
        return max;
    }

    public void draw( String s, AST t ) {
        int horizontalStep = nodeWidth + horizontalSeparation;
        int verticalStep = nodeHeight + verticalSeparation;

        int x = horizontalStep * nodeOffsets.get( t.getNodeNum() );
        int y = depth * verticalStep;

        g2.setColor( Color.ORANGE );
        g2.fillOval( x, y, nodeWidth, nodeHeight );
        g2.setColor( Color.BLACK );
        g2.drawString( s, x + 10, y + 2 * nodeHeight / 3 );

        int startx = x + nodeWidth / 2;
        int starty = y + nodeHeight;
        int endx;
        int endy;

        for ( int index = 1; index <= t.kidCount(); index++ ) {
            endx = (nodeOffsets.get( t.getKid( index ).getNodeNum() ) * horizontalStep) + nodeWidth / 2;
            endy = (depth + 1) * verticalStep;
            g2.drawLine( startx, starty, endx, endy );
        }

        depth++;
        visitKids( t );
        depth--;
    }

    private Graphics2D createGraphics2D( int w, int h ) {
        Graphics2D g2;
        if ( bimg == null || bimg.getWidth() != w || bimg.getHeight() != h ) {
            bimg = new BufferedImage( w, h, BufferedImage.TYPE_INT_RGB );
        }
        g2 = bimg.createGraphics();
        g2.setBackground( Color.WHITE );
        g2.setRenderingHint( RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY );
        g2.clearRect( 0, 0, w, h );
        return g2;
    }

    public BufferedImage getImage() {
        return bimg;
    }

    @Override
    public Object visitProgramTree( AST t ) {
        draw( "Program", t );
        return null;
    }

    @Override
    public Object visitBlockTree( AST t ) {
        draw( "Block", t );
        return null;
    }

    @Override
    public Object visitFunctionDeclTree( AST t ) {
        draw( "FunctionDecl", t );
        return null;
    }

    @Override
    public Object visitCallTree( AST t ) {
        draw( "Call", t );
        return null;
    }

    @Override
    public Object visitDeclTree( AST t ) {
        draw( "Decl", t );
        return null;
    }

    @Override
    public Object visitIntTypeTree( AST t ) {
        draw( "IntType", t );
        return null;
    }

    @Override
    public Object visitBoolTypeTree( AST t ) {
        draw( "BoolType", t );
        return null;
    }

    @Override
    public Object visitFormalsTree( AST t ) {
        draw( "Formals", t );
        return null;
    }

    @Override
    public Object visitActualArgsTree( AST t ) {
        draw( "ActualArgs", t );
        return null;
    }

    @Override
    public Object visitIfTree( AST t ) {
        draw( "If", t );
        return null;
    }

    @Override
    public Object visitWhileTree( AST t ) {
        draw( "While", t );
        return null;
    }

    @Override
    public Object visitReturnTree( AST t ) {
        draw( "Return", t );
        return null;
    }

    @Override
    public Object visitAssignTree( AST t ) {
        draw( "Assign", t );
        return null;
    }

    @Override
    public Object visitIntTree( AST t ) {
        draw( "Int: " + (( IntTree ) t).getSymbol().toString(), t );
        return null;
    }

    @Override
    public Object visitIdTree( AST t ) {
        draw( "Id: " + (( IdTree ) t).getSymbol().toString(), t );
        return null;
    }

    @Override
    public Object visitRelOpTree( AST t ) {
        draw( "RelOp: " + (( RelOpTree ) t).getSymbol().toString(), t );
        return null;
    }

    @Override
    public Object visitAddOpTree( AST t ) {
        draw( "AddOp: " + (( AddOpTree ) t).getSymbol().toString(), t );
        return null;
    }

    @Override
    public Object visitMultOpTree( AST t ) {
        draw( "MultOp: " + (( MultOpTree ) t).getSymbol().toString(), t );
        return null;
    }

    //new methods here


    @Override
    public Object visitFloatTypeTree( AST t ) {
        draw( "FloatType", t );
        return null;
    }

    @Override
    public Object visitVoidTypeTree( AST t ) {
        draw( "VoidType: ", t );
        return null;
    }

    public Object visitRepeatTree( AST t ) {
        draw( "Repeat", t );
        return null;
    }

}
