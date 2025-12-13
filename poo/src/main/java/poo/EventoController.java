package poo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/evento")
public class EventoController {
		
		@Autowired 
		EventoRepository eventoRepository;
		
		@GetMapping
		public List<Evento> listareventos(){
			return eventoRepository.findAll();
		}
		

		@PostMapping
		public Evento criarEvento(@RequestBody Evento evento) {
			return eventoRepository.save(evento);
		}
		
		@DeleteMapping("/{id}")
		public void excluirEvento(@PathVariable Long id) {
			eventoRepository.deleteById(id);
		}
		
		@PutMapping("/{id}")
		public Evento atualizarEvento(@PathVariable Long id, @RequestBody Evento evento) {
			
			Optional<Evento> oEvento = eventoRepository.findById(id);
			if ( oEvento.isPresent()) {
			Evento e = oEvento.get();
			e.setNome(evento.getNome());
			e.setData(evento.getData());
			return eventoRepository.save(e);
			
			}
			return null;
		}
		
	}


