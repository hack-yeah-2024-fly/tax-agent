package tax.application.project.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import tax.application.project.model.CelZlozenia;
import tax.application.project.repository.CelZlozeniaRepository;
import javax.validation.Valid;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CelZlozeniaController {

    @Autowired
    CelZlozeniaRepository celZlozeniaRepository;

    @GetMapping("/celezlozenia")
    public List<CelZlozenia> getAllCeleZlozenia() {
        return celZlozeniaRepository.findAll();
    }

    @GetMapping("/celezlozenia/{id}")
    public ResponseEntity<CelZlozenia> getCelZlozeniaById(@PathVariable(value = "id") Long celZlozeniaId) {
        CelZlozenia celZlozenia = celZlozeniaRepository.findById(celZlozeniaId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono adresu o podanym id :: " + celZlozeniaId));
        return ResponseEntity.ok().body(celZlozenia);
    }

    @PostMapping("/celezlozenia")
    public CelZlozenia createCelZlozenia(@Valid @RequestBody CelZlozenia celZlozenia) {
        return celZlozeniaRepository.save(celZlozenia);
    }

    @PutMapping("/celezlozenia/{id}")
    public ResponseEntity <CelZlozenia> updateCelZlozenia(@PathVariable(value = "id") Long celZlozeniaId,
                                                          @Valid @RequestBody CelZlozenia celZlozeniaDetails) {
        CelZlozenia celZlozenia = celZlozeniaRepository.findById(celZlozeniaId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono adresu o podanym id :: " + celZlozeniaId));

        celZlozenia.setPoz(celZlozeniaDetails.getPoz());
        final CelZlozenia updatedCelZlozenia = celZlozeniaRepository.save(celZlozenia);
        return ResponseEntity.ok(updatedCelZlozenia);
    }

    @DeleteMapping("/celezlozenia/{id}")
    public Map< String, Boolean > deleteCelZlozenia(@PathVariable(value = "id") Long celZlozeniaId) {
        CelZlozenia celZlozenia = celZlozeniaRepository.findById(celZlozeniaId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono adresu o podanym id :: " + celZlozeniaId));

        celZlozeniaRepository.delete(celZlozenia);
        Map < String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
