package com.pranav.tickets.services.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.pranav.tickets.domain.entities.QrCode;
import com.pranav.tickets.domain.entities.QrCodeStatusEnum;
import com.pranav.tickets.domain.entities.Ticket;
import com.pranav.tickets.exceptions.QrCodeGenerationException;
import com.pranav.tickets.exceptions.QrCodeNotFoundException;
import com.pranav.tickets.repositories.QrCodeRepository;
import com.pranav.tickets.services.QrCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class QrCodeServiceImpl implements QrCodeService {

    private final QRCodeWriter qrCodeWriter;
    private final QrCodeRepository qrCodeRepository;

    private static final int QR_WIDTH = 300;
    private static final int QR_HEIGHT = 300;

    @Override
    public QrCode generateQrCode(Ticket ticket) {
        try {
            UUID uniqueID = UUID.randomUUID();
            String qrCodeImage = generateQrCode(uniqueID);

            QrCode qrCode = new QrCode();
            qrCode.setId(uniqueID);
            qrCode.setStatus(QrCodeStatusEnum.ACTIVE);
            qrCode.setValue(qrCodeImage);
            qrCode.setTicket(ticket);

            return qrCodeRepository.saveAndFlush(qrCode);

        } catch (WriterException | IOException ex) {
            throw new QrCodeGenerationException("QR code generation failed", ex);
        }
    }

    @Override
    public byte[] getQrCodeImageForUserAndTicket(UUID userId, UUID ticketId) {

        QrCode qrCode = qrCodeRepository.findByTicketIdAndTicketPurchaserId(ticketId, userId)
                .orElseThrow(QrCodeNotFoundException::new);

        try {
            return Base64.getDecoder().decode(qrCode.getValue());
        } catch (IllegalArgumentException ex) {
            log.error("Invalid base64 QR Code for ticket Id : {}", ticketId, ex);
            throw new QrCodeGenerationException();
        }
    }

    private String generateQrCode(UUID uniqueID) throws WriterException, IOException {
        BitMatrix bitMatrix = qrCodeWriter.encode(
                uniqueID.toString(),
                BarcodeFormat.QR_CODE,
                QR_WIDTH,
                QR_HEIGHT
        );

        BufferedImage qrCodeImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(qrCodeImage, "png", baos);
            byte[] imageBytes = baos.toByteArray();

            return Base64.getEncoder().encodeToString(imageBytes);
        }
    }

}