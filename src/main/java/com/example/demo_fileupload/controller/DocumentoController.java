package com.example.demo_fileupload.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_fileupload.dto.DocumentoDTO;
import com.example.demo_fileupload.service.DocumentoService;

@RestController
@RequestMapping("/api/documento")
public class DocumentoController {

	@Autowired
	private DocumentoService documentoService;
	
	@GetMapping
	public List<DocumentoDTO> getAll() {
		// senza DTO qui hibernate dava il problema del N + 1 SELECT
		// (probabilmente dovuto alle librerie che serializzano in JSON)
		return DocumentoDTO.createDocumentoDTOListFromModelList(documentoService.elencaTutti());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DocumentoDTO createNew(@RequestBody DocumentoDTO param) {
		return DocumentoDTO
				.buildDocumentoDTOFromModel(documentoService.inserisciNuovo(param.buildDocumentoModel(param)));
	}

}
