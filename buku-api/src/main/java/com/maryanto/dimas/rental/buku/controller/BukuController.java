package com.maryanto.dimas.rental.buku.controller;

import com.maryanto.dimas.rental.buku.model.Buku;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/buku")
public class BukuController {

    @GetMapping("/{bukuId}")
    public Buku findById(@PathVariable("bukuId") String id) {
        return new Buku(id, "Belajar Pemograman Java", "Dimas Maryanto", 2017);
    }
}
