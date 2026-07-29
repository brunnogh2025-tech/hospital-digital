package hospital.digital.web.dto;

import hospital.digital.web.exception.CampoErroDTO;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponseDTO(LocalDateTime timeStamp,
                               int status,
                               String typeError,
                               String message,
                               String path,
                               List<CampoErroDTO> campos,
                               String uuid) {
}

