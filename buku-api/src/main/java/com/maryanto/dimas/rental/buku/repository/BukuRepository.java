package com.maryanto.dimas.rental.buku.repository;

import com.maryanto.dimas.rental.buku.model.Buku;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BukuRepository extends PagingAndSortingRepository<Buku, String> {
}
