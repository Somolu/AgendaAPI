package br.ueg.Agenda.repository;

import br.ueg.Agenda.dto.CountType;
import br.ueg.Agenda.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa,Long> {

    @Query(value = "SELECT * FROM tarefa order by venc desc",nativeQuery = true)
    public List<Tarefa> getAllTarefaVencDesc();

    @Query(value = "SELECT new br.ueg.Agenda.dto.CountType(COUNT(t), t.tipo) FROM Tarefa t GROUP BY t.tipo")
    public List<CountType> getPercentageGroupByTipo();

}
