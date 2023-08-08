package org.junit.jupiter.api.smells;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.*;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.List;

public class NoAssertionExtension implements BeforeAllCallback {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        Class<?> clazz = context.getRequiredTestClass();
        ClassReader reader = new ClassReader(clazz.getName());

        ClassNode classNode = new ClassNode();
        reader.accept(classNode, 0);

        final List<MethodNode> methods = classNode.methods;

        for (MethodNode method : methods) {
            List<AnnotationNode> annotationNodes = method.visibleAnnotations;

            if (annotationNodes == null || annotationNodes.isEmpty())
                continue;

            if (!methodHasTestAnnotation(method))
                continue;


            InsnList inList = method.instructions;

            boolean hasAssertion = false;

            for (int i = 0; i < inList.size(); i++) {
                AbstractInsnNode insn = inList.get(i);
                if (insn instanceof MethodInsnNode) {
                    MethodInsnNode methodInsnNode = (MethodInsnNode) insn;
                    if (methodInsnNode.owner.equals("org/junit/jupiter/api/Assertions")) {
                        hasAssertion = true;
                        break;
                    }
                }
            }

            if (!hasAssertion) {
                logger.warn(() -> "Test "+ clazz.getName()+ "." + method.name + " don't have asserts!");
            }
        }
    }

    private boolean methodHasTestAnnotation(MethodNode method) {
        return method.visibleAnnotations
                .stream().anyMatch(annotationNode -> annotationNode.desc.equals("Lorg/junit/jupiter/api/Test;"));
    }
}
