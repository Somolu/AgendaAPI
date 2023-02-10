package br.ueg.Agenda.service;

import br.ueg.Agenda.dto.CountType;
import br.ueg.Agenda.model.Tarefa;
import br.ueg.Agenda.repository.TarefaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TarefaService {

    private TarefaRepository tarefaRepository;

    @Transactional
    public List<Tarefa> getTarefa(){
        return tarefaRepository.getAllTarefaVencDesc();

    }
    @Transactional
    public Tarefa save(Tarefa tarefa){
        return tarefaRepository.saveAndFlush(tarefa);
    }

    public Tarefa alterar(Tarefa tarefa){
        return tarefaRepository.saveAndFlush(tarefa);
    }
    @Transactional
    public boolean existById(Long id){
        return tarefaRepository.existsById(id);

    }
    @Transactional
    public Optional<Tarefa> getTarefaById(Long id) {
        return tarefaRepository.findById(id);
    }

    public void delete(Long id) {
        tarefaRepository.deleteById(id);
    }

    public List<CountType> getPercentageGroupByTipo(){
       return tarefaRepository.getPercentageGroupByTipo();

    }
}
