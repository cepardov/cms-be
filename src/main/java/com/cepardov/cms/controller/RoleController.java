package com.cepardov.cms.controller;

import com.cepardov.cms.entity.Role;
import com.cepardov.cms.entity.User;
import com.cepardov.cms.service.RoleService;
import com.cepardov.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200", "*"})
@RestController
@RequestMapping("/api/v1")
public class RoleController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/roles")
    public List<Role> getRoles() {
        return roleService.findAll();
    }

    @PutMapping("/user/add/role/{id}")
    public ResponseEntity<?> addRoleToUser(@RequestBody List<Role> roleList, @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        User actualUser = userService.findById(id);
        User userUpdated;
        if (actualUser == null) {
            response.put("message", "El usuario no se ha cargado, intente de nuevo");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        try {
            actualUser.setRoles(roleList);

            userUpdated = userService.update(actualUser);
        } catch (DataAccessException e) {
            response.put("message", "Error al guardar el usuario, intente más tarde");
            response.put("error", e.getLocalizedMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }
}
