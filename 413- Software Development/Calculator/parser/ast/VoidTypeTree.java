package ast;

import visitor.*;

public class VoidTypeTree extends AST {

    public VoidTypeTree() {
    }

    @Override
    public Object accept( ASTVisitor v ) {
        return v.visitVoidTypeTree( this );
    }
}
