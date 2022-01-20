package com.example.demo_fileupload.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo_fileupload.model.Documento;

public class DocumentoDTO {

	private Long id;
	private String fileName;
	private String descrizione;

	private byte[] file;

	public DocumentoDTO() {
	}

	public DocumentoDTO(Long id, String fileName, String descrizione, byte[] file) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.descrizione = descrizione;
		this.file = file;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public Documento buildDocumentoModel(DocumentoDTO documentoDTO) {
		return new Documento(documentoDTO.getId(), documentoDTO.getFileName(), documentoDTO.getDescrizione(),
				documentoDTO.getFile());
	}

	public static DocumentoDTO buildDocumentoDTOFromModel(Documento documentoModel) {
		DocumentoDTO result = new DocumentoDTO(documentoModel.getId(), documentoModel.getFileName(),
				documentoModel.getDescrizione(), documentoModel.getFile());
		return result;
	}

	public static List<DocumentoDTO> createDocumentoDTOListFromModelList(List<Documento> modelListInput) {
		return modelListInput.stream().map(documentoItem -> new DocumentoDTO(documentoItem.getId(),
				documentoItem.getFileName(), documentoItem.getDescrizione(), documentoItem.getFile()))
				.collect(Collectors.toList());
	}

}
