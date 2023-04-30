package com.master.beans;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "controleTemplate")
public class ControleTemplateBean {

    private String template;
    private String templateCliente;

    public String getTemplate() {
		return "../templates/padraoh.xhtml";

    }

    public void setTemplate(String template) {
	this.template = template;
    }

	public String getTemplateCliente() {
		return "../templates/TemplateCliente.xhtml";
	}

	public void setTemplateCliente(String templateCliente) {
		this.templateCliente = templateCliente;
	}

}
