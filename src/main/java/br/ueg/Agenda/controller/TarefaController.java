package br.ueg.Agenda.controller;

import br.ueg.Agenda.dto.CountType;
import br.ueg.Agenda.model.Tarefa;
import br.ueg.Agenda.service.TarefaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
@AllArgsConstructor
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping("/Tarefa/vData/percentcounttipo")
    private List<CountType> getPercentageGroupByTipo() {

        return tarefaService.getPercentageGroupByTipo();
    }

   @GetMapping("/Tarefa") //Já existem dois @GetMapping?
    private List<Tarefa> getTarefa() {

        return tarefaService.getTarefa();
    }

    @PostMapping("/Tarefa")
    public Tarefa addTarefa(@RequestBody Tarefa tarefa) {

        return tarefaService.save(tarefa);
    }
    public Tarefa alteraTarefa(@RequestBody Tarefa tarefa) {

        return tarefaService.alterar(tarefa);
    }

    @GetMapping("/Tarefa/{id}")
    public Tarefa getById(@PathVariable Long id) {

        return tarefaService.getTarefaById(id).orElseThrow(() -> new EntityNotFoundException("Tarefa solicitada não enconrtrada!"));
    }

    @PutMapping("/Tarefa/{id}")
    public ResponseEntity<?> addTarefa(@RequestBody Tarefa tarefaPara, @PathVariable Long id) {
        if (tarefaService.existById(id)) {
            Tarefa tarefa = tarefaService.getTarefaById(id).orElseThrow(() -> new EntityNotFoundException("Tarefa solicitada não enconrtrada!"));
            tarefa.setTitulo(tarefaPara.getTitulo());
            tarefa.setVenc(tarefaPara.getVenc());
            tarefa.setTipo(tarefaPara.getTipo());
            tarefa.setDescricao(tarefaPara.getDescricao());

            return ResponseEntity.ok().body(tarefa);
        } else {
            HashMap<String, String> message = new HashMap<>();
            message.put("message", id + "Tarefa não encontrou correpondencia!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> addTarefa(@PathVariable Long id) {
        if (tarefaService.existById(id)) {
            tarefaService.delete(id);
            HashMap<String, String> message = new HashMap<>();
            message.put("message", id + "Tarefa deletada!");
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } else {
            HashMap<String, String> message = new HashMap<>();
            message.put("message", id + "Tarefa não encontrou correpondencia!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    public ResponseEntity<?> alteraTarefa(@RequestBody Tarefa tarefaPara, @PathVariable Long id) {
        if (tarefaService.existById(id)) {
            Tarefa tarefa = tarefaService.getTarefaById(id).orElseThrow(() -> new EntityNotFoundException("Tarefa solicitada não enconrtrada!"));
            tarefa.setTitulo(tarefaPara.getTitulo());
            tarefa.setVenc(tarefaPara.getVenc());
            tarefa.setTipo(tarefaPara.getTipo());
            tarefa.setDescricao(tarefaPara.getDescricao());

            return ResponseEntity.ok().body(tarefa);
        } else {
            HashMap<String, String> message = new HashMap<>();
            message.put("message", id + "Tarefa não encontrou correpondencia!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

//    @DeleteMapping
//    public ResponseEntity<?> alteraTarefa(@PathVariable Long id) {
//        if (tarefaService.existById(id)) {
//            tarefaService.delete(id);
//            HashMap<String, String> message = new HashMap<>();
//            message.put("message", id + "Tarefa deletada!");
//            return ResponseEntity.status(HttpStatus.OK).body(message);
//        } else {
//            HashMap<String, String> message = new HashMap<>();
//            message.put("message", id + "Tarefa não encontrou correpondencia!");
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
//        }
//    }
}


