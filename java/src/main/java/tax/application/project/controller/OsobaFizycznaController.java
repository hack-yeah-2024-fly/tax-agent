package tax.application.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tax.application.project.model.OsobaFizyczna;
import tax.application.project.repository.OsobaFizycznaRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class OsobaFizycznaController {

    @Autowired
    private OsobaFizycznaRepository osobaFizycznaRepository;

    @GetMapping("/osobyfizyczne")
    public List<OsobaFizyczna> getAllOsobyFizyczne() {
        return osobaFizycznaRepository.findAll();
    }

    @GetMapping("/osobyfizyczne/{id}")
    public ResponseEntity<OsobaFizyczna> getOsobaFizycznaById(@PathVariable(value = "id") Long osobaFizycznaId) {
        OsobaFizyczna osobaFizyczna = osobaFizycznaRepository.findById(osobaFizycznaId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono osoby fizycznej o podanym id :: " + osobaFizycznaId));
        return ResponseEntity.ok().body(osobaFizyczna);
    }

    @PostMapping("/osobyfizyczne")
    public OsobaFizyczna createOsobaFizyczna(@Valid @RequestBody OsobaFizyczna osobaFizyczna) {
        return osobaFizycznaRepository.save(osobaFizyczna);
    }

    @PutMapping("/osobyfizyczne/{id}")
    public ResponseEntity <OsobaFizyczna> updateOsobaFizyczna(@PathVariable(value = "id") Long osobaFizycznaId,
                                                      @Valid @RequestBody OsobaFizyczna osobaFizycznaDetails) {
        OsobaFizyczna osobaFizyczna = osobaFizycznaRepository.findById(osobaFizycznaId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono osoby fizycznej o podanym id :: " + osobaFizycznaId));

        osobaFizyczna.setPesel(osobaFizycznaDetails.getPesel());
        osobaFizyczna.setImiePierwsze(osobaFizycznaDetails.getImiePierwsze());
        osobaFizyczna.setNazwisko(osobaFizycznaDetails.getNazwisko());
        osobaFizyczna.setDataUrodzenia(osobaFizycznaDetails.getDataUrodzenia());
        final OsobaFizyczna updatedOsobaFizyczna = osobaFizycznaRepository.save(osobaFizyczna);
        return ResponseEntity.ok(updatedOsobaFizyczna);
    }

    @DeleteMapping("/osobyfizyczne/{id}")
    public Map< String, Boolean > deleteOsobaFizyczna(@PathVariable(value = "id") Long osobaFizycznaId) {
        OsobaFizyczna osobaFizyczna = osobaFizycznaRepository.findById(osobaFizycznaId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono osoby fizycznej o podanym id :: " + osobaFizycznaId));

        osobaFizycznaRepository.delete(osobaFizyczna);
        Map < String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
