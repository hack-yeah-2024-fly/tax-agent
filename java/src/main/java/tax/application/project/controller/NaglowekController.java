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
import tax.application.project.model.Naglowek;
import tax.application.project.repository.NaglowekRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class NaglowekController {

    @Autowired
    NaglowekRepository naglowekRepository;

    @GetMapping("/naglowki")
    public List<Naglowek> getAllNaglowki() {
        return naglowekRepository.findAll();
    }

    @GetMapping("/naglowki/{id}")
    public ResponseEntity<Naglowek> getNaglowekById(@PathVariable(value = "id") Long naglowekId) {
        Naglowek naglowek = naglowekRepository.findById(naglowekId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono naglowka o podanym id :: " + naglowekId));
        return ResponseEntity.ok().body(naglowek);
    }

    @PostMapping("/naglowki")
    public Naglowek createNaglowek(@Valid @RequestBody Naglowek naglowek) {
        return naglowekRepository.save(naglowek);
    }

    @PutMapping("/naglowki/{id}")
    public ResponseEntity <Naglowek> updateNaglowek(@PathVariable(value = "id") Long naglowekId,
                                                    @Valid @RequestBody Naglowek naglowekDetails) {
        Naglowek naglowek = naglowekRepository.findById(naglowekId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono naglowka o podanym id :: " + naglowekId));

        naglowek.setWariantFormularza(naglowekDetails.getWariantFormularza());
        naglowek.setData(naglowekDetails.getData());
        naglowek.setKodUrzedu(naglowekDetails.getKodUrzedu());

        final Naglowek updatedNaglowek = naglowekRepository.save(naglowek);
        return ResponseEntity.ok(updatedNaglowek);
    }

    @DeleteMapping("/naglowki/{id}")
    public Map< String, Boolean > deleteNaglowek(@PathVariable(value = "id") Long naglowekId) {
        Naglowek naglowek = naglowekRepository.findById(naglowekId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono naglowka o podanym id :: " + naglowekId));

        naglowekRepository.delete(naglowek);
        Map < String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
