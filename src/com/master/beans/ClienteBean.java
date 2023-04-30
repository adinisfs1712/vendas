package com.master.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIPanel;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import com.master.DAO.ClienteDAO;
import com.master.entities.Cliente;
import com.master.entities.Endereco;
import com.master.repository.ClienteRepository;

@Named
@ViewScoped
public class ClienteBean extends BaseBean {
	
	@Inject
	public ClienteDAO clienteDao;

	private static final long serialVersionUID = 1L;

	private static final String PESQUISAR_STATE = "pesquisar";
	private static final String ADICIONAR_STATE = "adicionar";
	private static final String EDITAR_STATE = "editar";
	private String currentState = PESQUISAR_STATE;

	private UIPanel panelForm;

	private Cliente cliente = new Cliente();
	private Endereco endereco;

	private Long id;
	private String nome;
	private List<Cliente> filter;
	private List<Cliente> listaClientes;


	@PostConstruct
	public void init() {
		System.out.println("init");


	}

	public void selecionar(Cliente cliente) {
		this.setCliente(cliente);
		setCurrentState(EDITAR_STATE);
	}

	public void criar() {
		cliente = new Cliente();
		endereco = new Endereco();
		this.setCurrentState(ADICIONAR_STATE);
	}

	public void limpar() {
		criar();
		// endereco = null;
		listaClientes = null;
		this.setCurrentState(PESQUISAR_STATE);
	}

	public void listar() {
		limpar();
		setCurrentState(PESQUISAR_STATE);
	}

	public void pesquisarCliente() {
		ClienteRepository clienteRepository = new ClienteRepository(this.getManager());
		Long id = cliente.getId();
		String razaoSocial = cliente.getNome();
		try {
			if (id != null) {
				cliente = clienteRepository.clienteById(id);
			}
			if (razaoSocial != null) {
				cliente = clienteRepository.clienteById(id); // trocar por nome
			}
			if (cliente instanceof Cliente) {
				editar(cliente);
			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage("anotherKey",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cliente Inexistente", ""));
				limpar();
			}

		} catch (Exception e) {
			System.out.println("Pesquisa Cliente erro " + e.getCause());
		}
		setCurrentState(EDITAR_STATE);
	}

	public void clienteById() {
		ClienteRepository clienteRepository = new ClienteRepository(this.getManager());
		Long id = cliente.getId();
		try {
			if (id != null) {
				cliente = clienteRepository.clienteById(id);
			}
			if (cliente instanceof Cliente) {
				editar(cliente);
			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage("anotherKey",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cliente Inexistente", ""));
				limpar();
			}

		} catch (Exception e) {
			System.out.println("Pesquisa Cliente erro " + e.getCause());
		}
		setCurrentState(EDITAR_STATE);
	}

	public List<Cliente> listaClientesByRazaoSocial() {
		String rzSocial = cliente.getNome();
		this.listaClientes = null;
		if (listaClientes == null) {
			ClienteRepository repository = new ClienteRepository(this.getManager());
			this.listaClientes = repository.clientesByNome(rzSocial);
		}
		return listaClientes;
	}

	public List<Cliente> getListarAllClientes() {
		if (listaClientes == null) {
			ClienteRepository repository = new ClienteRepository(this.getManager());
			this.listaClientes = repository.allClientes();
		}
		return listaClientes;
	}

	public void editar(Cliente cliente) {
		this.setCliente(cliente);
		this.setEndereco(cliente.getEndereco());
		setCurrentState(EDITAR_STATE);
	}

	public void excluir(Cliente cliente) {
		ClienteRepository clienteRepository = new ClienteRepository(this.getManager());
		clienteRepository.excluir(cliente);
		this.cliente = new Cliente();
		this.listaClientes = null;
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("anotherKey", new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente Excluido", ""));
	}

	public void salvar() {
		try {
			ClienteRepository clienteRepository = new ClienteRepository(getManager());
			if (cliente.getId() == null) {
				clienteRepository.salvar(cliente);
			} else {
				clienteRepository.alterar(cliente);
			}
			limpar();
		} catch (Exception e) {
			System.out.println("erro" + e.getMessage());

		}
	}

	public void onEdit() {
	}

	public void onCancel() {
	}

	private EntityManager getManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		return (EntityManager) request.getAttribute("entityManager");
	}

	public String getCurrentState() {
		return currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	public boolean isPesquisarState() {
		String state = this.getCurrentState();
		return (state == null || PESQUISAR_STATE.equals(state));
	}

	public boolean isEditarState() {
		return EDITAR_STATE.equals(this.getCurrentState());
	}

	public boolean isAdicionarState() {
		return ADICIONAR_STATE.equals(this.getCurrentState());
	}

	/**
	 * Limpa os componentes filhos para que depois eles possam ser recriados
	 * 
	 * @param component
	 */
	protected void cleanSubmittedValues(UIComponent component) {
		component.getChildren().clear();
	}

	public UIPanel getPanelForm() {
		return panelForm;
	}

	public void setPanelForm(UIPanel panelForm) {
		this.panelForm = panelForm;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.endereco = (Endereco) endereco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Cliente> getFilter() {
		return filter;
	}

	public void setFilter(List<Cliente> filter) {
		this.filter = filter;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}




}
