package ast;

import visitor.*;

public class ActualArgsTree extends AST {

    public ActualArgsTree() {
    }

    @Override
    public Object accept( ASTVisitor v ) {
        return v.visitActualArgsTree( this );
    }

}

