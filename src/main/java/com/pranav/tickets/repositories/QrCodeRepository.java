package com.pranav.tickets.repositories;

import com.pranav.tickets.domain.entities.QrCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QrCodeRepository extends JpaRepository<QrCode, UUID> {

}
