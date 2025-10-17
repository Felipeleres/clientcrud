package com.felipeleres.clientcrud.repositories;

import com.felipeleres.clientcrud.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
