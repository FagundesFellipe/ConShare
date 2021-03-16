package br.conshare.model.entities;

public class Feedback extends BasePojo{
	
	private Integer nota;
	private String informacao_adicional;
	
	public Integer getNota() {
		return nota;
	}
	
	public void setNota(Integer nota) {
		this.nota = nota;
	}
	
	public String getInformacao_adicional() {
		return informacao_adicional;
	}
	
	public void setInformacao_adicional(String informacao_adicional) {
		this.informacao_adicional = informacao_adicional;
	}
	
	@Override
	public String toString() {
		return "Feedback [nota=" + nota + ", informacao_adicional=" + informacao_adicional +  "]";
	}
	
	

}
