package com.korbiak.mentorship.io.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SerializerTest {
    private final String COMMON_PATH = "src/test/resources/io/%s";
    private final Serializer serializer = new Serializer();

    @Test
    void serializeParentObjectTest() {
        String currentPath = String.format(COMMON_PATH, "Parent");
        Parent parent = new Parent();
        serializer.serializeObject(parent, currentPath);
        Parent deserializeObject = serializer.deserializeObject(currentPath, Parent.class);

        Assertions.assertNotNull(deserializeObject);
        Assertions.assertEquals("parentValue", deserializeObject.getParentValue());
    }

    @Test
    void serializeChild1ObjectTest() {
        String currentPath = String.format(COMMON_PATH, "Child1");
        Parent parent = new Child1();
        serializer.serializeObject(parent, currentPath);
        Child1 deserializeObject = serializer.deserializeObject(currentPath, Child1.class);

        Assertions.assertNotNull(deserializeObject);
        Assertions.assertEquals("parentValue", deserializeObject.getParentValue());
        Assertions.assertEquals("child1Value", deserializeObject.getChild1Value());
    }

    @Test
    void serializeChild2ObjectTest() {
        String currentPath = String.format(COMMON_PATH, "Child2");
        Parent parent = new Child2(28);
        serializer.serializeObject(parent, currentPath);
        Child2 deserializeObject = serializer.deserializeObject(currentPath, Child2.class);

        Assertions.assertNotNull(deserializeObject);
        Assertions.assertEquals("parentValue", deserializeObject.getParentValue());
        Assertions.assertEquals("child2Value", deserializeObject.getChild2Value());
        Assertions.assertNull(deserializeObject.getChild2ValueInt());
    }

    @Test
    void serializeChild3ObjectTest() {
        String currentPath = String.format(COMMON_PATH, "Child3");
        Parent parent = new Child3(28, 283);
        serializer.serializeObject(parent, currentPath);
        Child3 deserializeObject = serializer.deserializeObject(currentPath, Child3.class);

        Assertions.assertNotNull(deserializeObject);
        Assertions.assertEquals("parentValue", deserializeObject.getParentValue());
        Assertions.assertEquals("child2Value", deserializeObject.getChild2Value());
        Assertions.assertEquals("child3Value", deserializeObject.getChild3Value());
        Assertions.assertNull(deserializeObject.getChild2ValueInt());
        Assertions.assertEquals(0, deserializeObject.getChild3ValueInt());
    }
}
