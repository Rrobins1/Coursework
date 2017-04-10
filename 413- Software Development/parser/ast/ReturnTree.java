package ast;

import visitor.*;

public class ReturnTree extends AST {

    public ReturnTree() {
    }

    @Override
    public Object accept( ASTVisitor v ) {
        return v.visitReturnTree( this );
    }

}

