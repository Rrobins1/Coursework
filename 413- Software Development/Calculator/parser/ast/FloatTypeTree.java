package ast;

import visitor.*;

public class FloatTypeTree extends AST {

    public FloatTypeTree() {
    }

    @Override
    public Object accept( ASTVisitor v ) {
        return v.visitFloatTypeTree( this );
    }
}
    
    
