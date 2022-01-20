package com.example.demo_fileupload.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo_fileupload.model.Documento;
import com.example.demo_fileupload.repository.DocumentoRepository;

@Service
public class DocumentoServiceImpl implements DocumentoService {
	
	@Autowired
	private DocumentoRepository documentoRepository;

	@Override
	public List<Documento> elencaTutti() {
		return (List<Documento>) documentoRepository.findAll();
	}

	@Override
	public Documento caricaSingoloElemento(Long id) {
		return documentoRepository.findById(id).orElse(null);
	}

	@Override
	public Documento inserisciNuovo(Documento documentoInstance) {
		return documentoRepository.save(documentoInstance);
	}

}
