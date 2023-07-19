package com.nayarasantos.workshopmongo.resources;

import com.nayarasantos.workshopmongo.domain.User;
import com.nayarasantos.workshopmongo.dto.UserDTO;
import com.nayarasantos.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/users")
public class UserResource {

    @Autowired
    private UserService service;

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findAll() {

        List<User> list = service.findAll();
        List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @RequestMapping(value = "/{id}", method=RequestMethod.GET)
    public ResponseEntity<UserDTO> findById(@PathVariable String id){

        User user = service.findById(id);
        return ResponseEntity.ok().body(new UserDTO(user));
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody UserDTO objDTO){

        User obj = service.fromDTO(objDTO);
       obj = service.insert(obj);
       URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
       return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id){

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
