package com.maryanto.dimas.rental.buku.controller;

import com.maryanto.dimas.rental.buku.model.Buku;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/buku")
public class BukuController {
    private final static Logger console = LoggerFactory.getLogger(BukuController.class);

    @GetMapping("/{bukuId}")
    public Buku findById(@PathVariable("bukuId") String id) {
        return new Buku(id, "Belajar Pemograman Java", "Dimas Maryanto", 2017);
    }

    @PostMapping(value = "/submit", produces = "application/json", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Buku submitBuku(@RequestBody Buku buku) {
        console.info("{}", buku.toString());
        return buku;
    }

    @GetMapping(value = "/", produces = "application/json")
    public Buku getBuku(@ModelAttribute Buku buku) {
        console.info("nilai: {}", buku.toString());
        return new Buku();
    }
}
