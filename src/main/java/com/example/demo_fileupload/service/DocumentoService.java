package com.example.demo_fileupload.service;

import java.util.List;

import com.example.demo_fileupload.model.Documento;

public interface DocumentoService {
	List<Documento> elencaTutti();

	Documento caricaSingoloElemento(Long id);

	Documento inserisciNuovo(Documento documentoInstance);

}
