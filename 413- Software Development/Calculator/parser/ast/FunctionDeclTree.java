package ast;

import visitor.*;

public class FunctionDeclTree extends AST {

    public FunctionDeclTree() {
    }

    @Override
    public Object accept( ASTVisitor v ) {
        return v.visitFunctionDeclTree( this );
    }

}

