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
import tax.application.project.model.PozycjeSzczegolowe;
import tax.application.project.repository.PozycjeSzczegoloweRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class PozycjeSzczegoloweController {

    @Autowired
    PozycjeSzczegoloweRepository pozycjeSzczegoloweRepository;

    @GetMapping("/pozycjeszczegolowe")
    public List<PozycjeSzczegolowe> getAllPozycjeSzczegolowe() {
        return pozycjeSzczegoloweRepository.findAll();
    }

    @GetMapping("/pozycjeszczegolowe/{id}")
    public ResponseEntity<PozycjeSzczegolowe> getPozycjeSzczegoloweById(@PathVariable(value = "id") Long pozycjeSzczegoloweId) {
        PozycjeSzczegolowe pozycjeSzczegolowe = pozycjeSzczegoloweRepository.findById(pozycjeSzczegoloweId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono pozycji szczegolowych o podanym id :: " + pozycjeSzczegoloweId));
        return ResponseEntity.ok().body(pozycjeSzczegolowe);
    }

    @PostMapping("/pozycjeszczegolowe")
    public PozycjeSzczegolowe createPozycjeSzczegolowe(@Valid @RequestBody PozycjeSzczegolowe pozycjeSzczegolowe) {
        return pozycjeSzczegoloweRepository.save(pozycjeSzczegolowe);
    }

    @PutMapping("/pozycjeszczegolowe/{id}")
    public ResponseEntity <PozycjeSzczegolowe> updatePozycjeSzczegolowe(@PathVariable(value = "id") Long pozycjeSzczegoloweId,
                                                                        @Valid @RequestBody PozycjeSzczegolowe pozycjeSzczegoloweDetails) {
        PozycjeSzczegolowe pozycjeSzczegolowe = pozycjeSzczegoloweRepository.findById(pozycjeSzczegoloweId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono pozycji szczegolowych o podanym id :: " + pozycjeSzczegoloweId));

        pozycjeSzczegolowe.setP_7(pozycjeSzczegoloweDetails.getP_7());
        pozycjeSzczegolowe.setP_20(pozycjeSzczegoloweDetails.getP_20());
        pozycjeSzczegolowe.setP_21(pozycjeSzczegoloweDetails.getP_21());
        pozycjeSzczegolowe.setP_22(pozycjeSzczegoloweDetails.getP_22());
        pozycjeSzczegolowe.setP_23(pozycjeSzczegoloweDetails.getP_23());
        pozycjeSzczegolowe.setP_24(pozycjeSzczegoloweDetails.getP_24());
        pozycjeSzczegolowe.setP_25(pozycjeSzczegoloweDetails.getP_25());
        pozycjeSzczegolowe.setP_46(pozycjeSzczegoloweDetails.getP_46());
        pozycjeSzczegolowe.setP_53(pozycjeSzczegoloweDetails.getP_53());
        pozycjeSzczegolowe.setP_62(pozycjeSzczegoloweDetails.getP_62());
        final PozycjeSzczegolowe updatedPozycjeSzczegolowe = pozycjeSzczegoloweRepository.save(pozycjeSzczegolowe);
        return ResponseEntity.ok(updatedPozycjeSzczegolowe);
    }

    @DeleteMapping("/pozycjeszczegolowe/{id}")
    public Map< String, Boolean > deletePozycjeSzczegolowe(@PathVariable(value = "id") Long pozycjeSzczegoloweId) {
        PozycjeSzczegolowe pozycjeSzczegolowe = pozycjeSzczegoloweRepository.findById(pozycjeSzczegoloweId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono pozycji szczegolowych o podanym id :: " + pozycjeSzczegoloweId));

        pozycjeSzczegoloweRepository.delete(pozycjeSzczegolowe);
        Map < String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
