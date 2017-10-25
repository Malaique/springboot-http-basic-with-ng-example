package com.maryanto.dimas.rental.buku.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "buku")
public class Buku {

    @Id
    @GenericGenerator(name = "gen_buku", strategy = "uuid2")
    @GeneratedValue(generator = "gen_buku")
    private String id;
    @Column(name = "judul_buku", nullable = false, length = 50)
    private String nama;
    @Column(name = "nama_pengarang", length = 150)
    private String pengarang;
    @Column(name = "tahun_terbit", length = 4)
    private Integer tahunTerbit;
}
