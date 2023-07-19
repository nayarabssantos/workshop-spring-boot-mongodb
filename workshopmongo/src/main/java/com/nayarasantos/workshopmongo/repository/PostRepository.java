package com.nayarasantos.workshopmongo.repository;

import com.nayarasantos.workshopmongo.domain.Post;
import com.nayarasantos.workshopmongo.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
}
