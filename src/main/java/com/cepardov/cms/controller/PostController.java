package com.cepardov.cms.controller;

import com.cepardov.cms.annotation.TimeExecutionMeasuring;
import com.cepardov.cms.entity.Post;
import com.cepardov.cms.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/v1")
public class PostController {

    @Autowired
    private PostService service;

    @GetMapping("/post/page/{page}")
    @TimeExecutionMeasuring
    public Page<Post> index(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 4);
        return service.findAll(pageable);
    }

    @GetMapping("/post/{id}")
    @TimeExecutionMeasuring
    public ResponseEntity<?> show(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Post post;
        try {
            post = service.findById(id);
        } catch (DataAccessException e) {
            response.put("message", "No se pudo recuperar la entrada");
            response.put("error", e.getLocalizedMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(post == null) {
            response.put("message", "El post ID"+id+" no existe.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PostMapping("/post")
    @TimeExecutionMeasuring
    public ResponseEntity<?> save(@Valid @RequestBody Post post, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        Post postSaved;
        if(result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            postSaved = service.save(post);
        } catch (DataAccessException e){
            response.put("message", "No se puede guardar la noticia, intente más tarde");
            response.put("error", e.getLocalizedMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(postSaved, HttpStatus.CREATED);
    }

    @PutMapping("/post/{id}")
    @TimeExecutionMeasuring
    public ResponseEntity<?> update(@Valid @RequestBody Post post, BindingResult result, @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Post actualPost = service.findById(id);
        Post postUpdated;
        if(result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if(actualPost == null) {
            response.put("message", "La noticia no se ha cargado, intente de nuevo");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        try {
            actualPost.setTitle(post.getTitle());
            actualPost.setBody(post.getBody());
            actualPost.setDescription(post.getDescription());
            actualPost.setDefaultImage(post.getDefaultImage());

            postUpdated = service.update(actualPost);
        } catch (DataAccessException e) {
            response.put("message", "Error al guardar la noticia, intente más tarde");
            response.put("error", e.getLocalizedMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(postUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/post/{id}")
    @TimeExecutionMeasuring
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Post post = service.findById(id);

            if(post == null) {
                response.put("message", "La noticia que intenta eliminar no existe");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            service.delete(post);
        } catch (DataAccessException e) {
            response.put("message", "Error al eliminar la noticia, intente más tarde");
            response.put("error", e.getLocalizedMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "La noticia ha sido eliminada");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
