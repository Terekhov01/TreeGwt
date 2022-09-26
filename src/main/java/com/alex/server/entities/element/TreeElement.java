package com.alex.server.entities.element;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
public class TreeElement {
    @MongoId
    private Long id;

    private String name;

    private ElementType elementType;
    
    private TreeElement parent;

    public void setParent(TreeElement parent) {
        this.parent = parent;
    }

    public TreeElement getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ElementType getElementType() {
        return elementType;
    }

    public void setElementType(ElementType elementType) {
        this.elementType = elementType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
