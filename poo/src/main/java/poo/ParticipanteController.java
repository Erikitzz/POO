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
@RequestMapping("/api/participante")
public class ParticipanteController {

	@Autowired
	ParticipanteRepository participanteRepository;

	@GetMapping
	public List<Participante> listarparticipantes() {
		return participanteRepository.findAll();
	}

	@PostMapping
	public Participante criarParticipante(@RequestBody Participante participante) {
		return participanteRepository.save(participante);
	}

	@DeleteMapping("/{id}")
	public void excluirParticipante(@PathVariable Long id) {
		participanteRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	public Participante atualizarParticipante(@PathVariable Long id, @RequestBody Participante participante) {

		Optional<Participante> oParticipante = participanteRepository.findById(id);
		if (oParticipante.isPresent()) {
			Participante p = oParticipante.get();
			p.setNome(participante.getNome());
			p.setEmail(participante.getEmail());
			p.setEvento(participante.getEvento());
			return participanteRepository.save(p);

		}
		return null;
	}

}
