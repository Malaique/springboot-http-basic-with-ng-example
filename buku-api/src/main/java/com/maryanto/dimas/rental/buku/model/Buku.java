package com.maryanto.dimas.rental.buku.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Buku {

    private String id;
    private String nama;
    private String pengarang;
    private Integer tahunTerbit;
}
