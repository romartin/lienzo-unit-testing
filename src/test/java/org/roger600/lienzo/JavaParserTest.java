package org.roger600.lienzo;

import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.Node;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.comments.BlockComment;
import japa.parser.ast.stmt.Statement;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;
import java.util.List;

public class JavaParserTest {

    public static void main(String[] args) throws Exception {
        // creates an input stream for the file to be parsed
        FileInputStream in = new FileInputStream("/home/romartin/development/romartin/lienzo/lienzo-unit-testing/src/main/java/org/roger600/lienzo/js/MyJSObject.java");

        CompilationUnit cu;
        try {
            // parse the file
            cu = JavaParser.parse(in);
        } finally {
            in.close();
        }

        // visit and print the methods names
        new MethodVisitor().visit(cu, null);
    }

    /**
     * Simple visitor implementation for visiting MethodDeclaration nodes. 
     */
    private static class MethodVisitor extends VoidVisitorAdapter {

        @Override
        public void visit(MethodDeclaration n, Object arg) {
            // Public Native methods. 
            if ( 273 == n.getModifiers() ) {
                List<Node> nodesList = n.getChildrenNodes();
                if ( null != nodesList ) {
                    for ( Node node : nodesList ) {
                        if ( node instanceof BlockComment ) {
                            BlockComment blockComment = (BlockComment) node;
                            System.out.println(blockComment.toString());
                        }
                    }
                }
            }
            super.visit(n, arg);
        }
    }
}
