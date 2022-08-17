package EFLAZAROIMANOL.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EFLAZAROIMANOL.Client.OpenFeignClient;
import EFLAZAROIMANOL.DTO.Cliente;
import EFLAZAROIMANOL.Model.Pizza;
import EFLAZAROIMANOL.Model.PizzaClienteFK;
import EFLAZAROIMANOL.Model.PizzaDetalle;
import EFLAZAROIMANOL.Repository.PizzaClienteRepository;
import EFLAZAROIMANOL.Repository.PizzaRepository;


@Service
public class PizzaServiceImpl implements PizzaService {
	
	@Autowired
	private PizzaRepository repository;
	
	@Autowired
	private PizzaClienteRepository repositoryDetalle;
	
	
	@Autowired
	private OpenFeignClient feign;
	
	@Override
	public List<Pizza> listar() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Pizza ObtenerId(Integer id) {
		// TODO Auto-generated method stub
		return repository.findById(id).orElse(null);
	}

	@Override
	public void guardar(Pizza pizza) {
		// TODO Auto-generated method stub
		repository.save(pizza);

	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);

	}

	@Override
	public void actualizar(Pizza pizza) {
		// TODO Auto-generated method stub
		repository.saveAndFlush(pizza);

	}

	@Override
	public void asignarClienteaPizza() {
		List<Cliente> listado= feign.listarClientesSeleccionados();
		PizzaClienteFK fk= null;
		PizzaDetalle detalle = null;
		
		for(Cliente cliente: listado) {
			
			fk=new PizzaClienteFK();			
			fk.setIdCliente(cliente.getIdCliente());
			fk.setIdPizza(1);
			
			detalle = new PizzaDetalle();
			detalle.setFk(fk);
			
			repositoryDetalle.save(detalle);
			
		}
	}

}
