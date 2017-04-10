package ast;

import visitor.*;

public class WhileTree extends AST {

    public WhileTree() {
    }

    @Override
    public Object accept( ASTVisitor v ) {
        return v.visitWhileTree( this );
    }
}

