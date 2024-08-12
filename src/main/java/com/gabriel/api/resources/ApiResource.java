package com.gabriel.api.resources;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiResource {

    @GetMapping("/{nome}")
    public String imprimir(@PathVariable String nome){
        return "Olá " + nome + "!";
    }
}
