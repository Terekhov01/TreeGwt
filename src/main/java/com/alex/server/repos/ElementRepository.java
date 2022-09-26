package com.alex.server.repos;

import com.alex.server.entities.element.TreeElement;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ElementRepository extends MongoRepository<TreeElement, String> {
}
