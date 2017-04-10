package ast;

import visitor.*;

public class BoolTypeTree extends AST {

    public BoolTypeTree() {
    }

    @Override
    public Object accept( ASTVisitor v ) {
        return v.visitBoolTypeTree( this );
    }

}

