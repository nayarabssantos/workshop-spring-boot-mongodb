package com.nayarasantos.workshopmongo.services;

import com.nayarasantos.workshopmongo.domain.Post;
import com.nayarasantos.workshopmongo.repository.PostRepository;
import com.nayarasantos.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;

    public Post findById(String id){
        Optional<Post> post = repo.findById(id);

        return post.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }
}
