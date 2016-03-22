package org.roger600.lienzo.util;

import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.type.ClassOrInterfaceType;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;

public class OverlayTypesLocator {

    private class OverlayTypeHit {
        final String className;
        final String path;

        public OverlayTypeHit(String className, String path) {
            this.className = className;
            this.path = path;
        }

        @Override
        public boolean equals(Object obj) {
            if ( obj != null ) {
                try {
                    OverlayTypeHit hit = (OverlayTypeHit) obj;
                    return className.equals(((OverlayTypeHit) obj).className);
                } catch (ClassCastException e) {

                }
            }
            return false;
        }
        
    }
    
    private final String[] paths;
    private final List<OverlayTypeHit> overlayTypes = new LinkedList<>();

    public OverlayTypesLocator(String[] paths) {
        this.paths = paths;
    }

    public static void main(String[] args) {
        String lienzo_core = "/home/romartin/development/temp/lienzo/lienzo-core";
        String lienzo_native_tools = "/home/romartin/development/temp/lienzo/ahome-tooling-nativetools";
        OverlayTypesLocator locator = new OverlayTypesLocator(new String[] {lienzo_core, lienzo_native_tools});
        locator.run();
    }


    public void run() {
        
        for ( String path : paths ) {
            try {
                Files.walkFileTree(Paths.get(path), new FindJavaVisitor());
            } catch (IOException e) {
                log("Error walking tree for path [" + path + "]");
                e.printStackTrace();
            }
        }

        logResult();
    }
    
    private void logResult() {
        if ( overlayTypes.isEmpty() ) {
            log("No overlay types found.");
        } else {
            log("Overlay types found:");
            log("********************");
            for ( OverlayTypeHit type : overlayTypes ) {
                log(type.className + "      [" + type.path + "]");   
            }
        }
    }
    
    private void log(String message) {
        System.out.println(message);
    }

    private class FindJavaVisitor extends SimpleFileVisitor<Path> {
        
        public FindJavaVisitor() {
        }

        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if ((file != null) && (attrs != null)) {
                String path = file.toAbsolutePath().toString();
                if ( path.endsWith(".java") ) {
                    try {
                        parseJavaSource( path );
                    } catch (Exception e) {
                        System.err.println("Error parsing Java source at [" + path + "]");
                        e.printStackTrace();
                    }
                    
                }
            }
            return FileVisitResult.CONTINUE;
        }
    }
    
    public void parseJavaSource(String path) throws Exception {
        // creates an input stream for the file to be parsed
        FileInputStream in = new FileInputStream(path);

        CompilationUnit cu;
        try {
            // parse the file
            cu = JavaParser.parse(in);
            new ClassDeclarationVisitor().visit(cu, path);
        } finally {
            in.close();
        }
        
    }

    private class ClassDeclarationVisitor extends VoidVisitorAdapter {

        @Override
        public void visit(ClassOrInterfaceDeclaration n, Object arg) {
            super.visit(n, arg);
            List<ClassOrInterfaceType> extendsList = n.getExtends();
            if (null != extendsList && !extendsList.isEmpty()) {
                for (ClassOrInterfaceType type : extendsList) {
                    String superClassName = type.getName();
                    
                    if ( superClassName != null && "JavaScriptObject".equals(superClassName) ) {
                        String className = n.getName();
                        OverlayTypesLocator.this.overlayTypes.add( new OverlayTypeHit(className, (String) arg) );
                    }
                    
                }
            }
        }

    }
}
