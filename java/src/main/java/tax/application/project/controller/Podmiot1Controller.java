package tax.application.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tax.application.project.model.Podmiot1;
import tax.application.project.repository.Podmiot1Repository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class Podmiot1Controller {

    @Autowired
    Podmiot1Repository podmiot1Repository;

    @GetMapping("/podmioty1")
    public List<Podmiot1> getAllPodmioty1() {
        return podmiot1Repository.findAll();
    }

    @GetMapping("/podmioty1/{id}")
    public ResponseEntity<Podmiot1> getPodmiot1ById(@PathVariable(value = "id") Long podmiot1Id) {
        Podmiot1 podmiot1 = podmiot1Repository.findById(podmiot1Id)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono podmiotu1 o podanym id :: " + podmiot1Id));
        return ResponseEntity.ok().body(podmiot1);
    }

    @PostMapping("/podmioty1")
    public Podmiot1 createPodmiot1(@Valid @RequestBody Podmiot1 podmiot1) {
        return podmiot1Repository.save(podmiot1);
    }

    @PutMapping("/podmioty1/{id}")
    public ResponseEntity <Podmiot1> updatePodmiot1(@PathVariable(value = "id") Long podmiot1Id,
                                                    @Valid @RequestBody Podmiot1 podmiot1Details) {
        Podmiot1 podmiot1 = podmiot1Repository.findById(podmiot1Id)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono podmiotu1 o podanym id :: " + podmiot1Id));

        podmiot1.setRola(podmiot1Details.getRola());
        final Podmiot1 updatedPodmiot1 = podmiot1Repository.save(podmiot1);
        return ResponseEntity.ok(updatedPodmiot1);
    }

    @DeleteMapping("/podmioty1/{id}")
    public Map< String, Boolean > deletePodmiot1(@PathVariable(value = "id") Long podmiot1Id) {
        Podmiot1 podmiot1 = podmiot1Repository.findById(podmiot1Id)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono podmiotu1 o podanym id :: " + podmiot1Id));

        podmiot1Repository.delete(podmiot1);
        Map < String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
