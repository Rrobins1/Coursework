package ast;

import visitor.*;

public class BlockTree extends AST {

    public BlockTree() {
    }

    @Override
    public Object accept( ASTVisitor v ) {
        return v.visitBlockTree( this );
    }

}

