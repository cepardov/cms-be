package com.cepardov.cms.controller;

import com.cepardov.cms.annotation.TimeExecutionMeasuring;
import com.cepardov.cms.entity.User;
import com.cepardov.cms.service.UserService;
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

@CrossOrigin(origins = {"http://localhost:4200", "*"})
@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/users")
    @TimeExecutionMeasuring
    public List<User> index(){
        return service.findAll();
    }

    @GetMapping("/users/page/{page}")
    @TimeExecutionMeasuring
    public Page<User> index(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 4);
        return service.findAll(pageable);
    }

    @GetMapping("/users/{id}")
    @TimeExecutionMeasuring
    public ResponseEntity<?> show(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        User user;
        try {
            user = service.findById(id);
        } catch (DataAccessException e) {
            response.put("message", "Error al realizar la consulta, intente más tarde");
            response.put("error", e.getLocalizedMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(user == null) {
            response.put("message", "El usuario ID"+id+" no existe en la base de datos.");
            return  new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PostMapping("/users")
    @TimeExecutionMeasuring
    public ResponseEntity<?> save(@Valid @RequestBody User user, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        User userSaved;
        if(result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            userSaved = service.save(user);
        } catch (DataAccessException e){
            response.put("message", "Error al guardar el usuario, intente más tarde");
            response.put("error", e.getLocalizedMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    @TimeExecutionMeasuring
    public ResponseEntity<?> update(@Valid @RequestBody User user, BindingResult result, @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        User actualUser = service.findById(id);
        User userUpdated;
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (actualUser == null) {
            response.put("message", "El usuario no se ha cargado, intente de nuevo");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        try {
            actualUser.setSocialId(user.getSocialId());
            actualUser.setFirstName(user.getFirstName());
            actualUser.setLastName(user.getLastName());
            actualUser.setEmail(user.getEmail());

            userUpdated = service.update(actualUser);
        } catch (DataAccessException e) {
            response.put("message", "Error al guardar el usuario, intente más tarde");
            response.put("error", e.getLocalizedMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    @TimeExecutionMeasuring
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            User user = service.findById(id);

            if(user == null) {
                response.put("message", "El usuario que intenta eliminar no existe");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            service.delete(user);
        } catch (DataAccessException e) {
            response.put("message", "Error al eliminar el usuario, intente más tarde");
            response.put("error", e.getLocalizedMessage());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "El usuario ha sido eliminado");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
